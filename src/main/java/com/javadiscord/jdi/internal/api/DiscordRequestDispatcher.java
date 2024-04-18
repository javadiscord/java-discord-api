package com.javadiscord.jdi.internal.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiscordRequestDispatcher implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BASE_URL = "https://discord.com/api";
    private final BlockingQueue<DiscordRequestBuilder> queue;
    private final String botToken;
    private int numberOfRequestsSent;
    private long timeSinceLastRequest;

    public DiscordRequestDispatcher(String botToken) {
        this.botToken = botToken;
        this.queue = new LinkedBlockingQueue<>();
        this.numberOfRequestsSent = 0;
        this.timeSinceLastRequest = 0;
    }

    public DiscordResponseFuture queue(DiscordRequest discordRequest) {
        DiscordRequestBuilder discordRequestBuilder = discordRequest.create();
        queue.add(discordRequestBuilder);
        return discordRequestBuilder.future();
    }

    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - timeSinceLastRequest;

            if (elapsed < 1000 && numberOfRequestsSent >= 50) {
                try {
                    Thread.sleep(1000 - elapsed);
                } catch (InterruptedException e) {
                    /* Ignore */
                }
                numberOfRequestsSent = 0;
            }

            try {
                sendRequest(queue.take());
            } catch (InterruptedException e) {
                /* Ignore */
            }
        }
    }

    private void sendRequest(DiscordRequestBuilder discordRequestBuilder) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(
                    URI.create(
                        "%s%s%s"
                            .formatted(
                                BASE_URL,
                                discordRequestBuilder.getPath(),
                                discordRequestBuilder
                                    .getQueryParameters()
                            )
                    )
                )
                .header("Authorization", "Bot " + botToken);

            if (!discordRequestBuilder.getHeaders().containsKey("Content-Type")) {
                requestBuilder.headers("Content-Type", "application/json");
            }

            if (!discordRequestBuilder.getHeaders().isEmpty()) {
                requestBuilder.headers(headerMapToStringArr(discordRequestBuilder.getHeaders()));
            }

            switch (discordRequestBuilder.getMethod()) {
                case HttpMethod.GET:
                    requestBuilder.GET();
                    break;
                case HttpMethod.POST:
                    requestBuilder.POST(discordRequestBuilder.getBody());
                    break;
                case HttpMethod.DELETE:
                    requestBuilder.DELETE();
                    break;
                case HttpMethod.PUT:
                    requestBuilder.PUT(discordRequestBuilder.getBody());
                    break;
                case HttpMethod.PATCH:
                    requestBuilder.method(HttpMethod.PATCH.name(), discordRequestBuilder.getBody());
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Unsupported HTTP method: " + discordRequestBuilder.getMethod()
                    );
            }

            HttpRequest httpRequest = requestBuilder.build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            numberOfRequestsSent++;
            timeSinceLastRequest = System.currentTimeMillis();

            discordRequestBuilder.setSuccessResult(
                new DiscordResponse(
                    response.body(), response.statusCode(), response.headers().map()
                )
            );

        } catch (Exception e) {
            LOGGER.error(
                "Failed to send request to {}{}", BASE_URL, discordRequestBuilder.getPath(), e
            );
            discordRequestBuilder.setFailureError(e);
        }
    }

    private static String[] headerMapToStringArr(Map<String, Object> headers) {
        return headers.keySet().stream()
            .flatMap(key -> Stream.of(key, headers.get(key).toString()))
            .toList()
            .toArray(new String[0]);
    }
}
