package com.javadiscord.jdi.internal.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestRunner implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String BASE_URL = "https://discord.com/api";
    private final BlockingQueue<HTTPRequest> queue;
    private final String botToken;
    private int numberOfRequestsSent;
    private long timeSinceLastRequest;

    public RequestRunner(String botToken) {
        this.botToken = botToken;
        this.queue = new LinkedBlockingQueue<>();
        this.numberOfRequestsSent = 0;
        this.timeSinceLastRequest = 0;
    }

    public Future<HTTPResponse> queue(HTTPRequest request) {
        Future<HTTPResponse> future = new Future<>();
        request.setFuture(future);
        queue.add(request);
        return future;
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
            } catch (Exception e) {
                /* Ignore */
            }
        }
    }

    private void sendRequest(HTTPRequest request) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest.Builder requestBuilder =
                    HttpRequest.newBuilder()
                            .uri(URI.create("%s%s".formatted(BASE_URL, request.getUri())))
                            .header("Authorization", "Bot " + botToken)
                            .headers("Content-Type", "application/json");

            request.getHeaders().forEach(requestBuilder::setHeader);

            switch (request.getMethod().toUpperCase()) {
                case "GET":
                    requestBuilder.GET();
                    break;
                case "POST":
                    requestBuilder.POST(HttpRequest.BodyPublishers.ofString(request.getBody()));
                    break;
                case "DELETE":
                    requestBuilder.DELETE();
                    break;
                case "PUT":
                    requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(request.getBody()));
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unsupported HTTP method: " + request.getBody());
            }

            HttpRequest httpRequest = requestBuilder.build();

            HttpResponse<String> response =
                    httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            numberOfRequestsSent++;
            timeSinceLastRequest = System.currentTimeMillis();

            request.getFuture()
                    .setResult(
                            new HTTPResponse(
                                    response.body(),
                                    response.statusCode(),
                                    response.headers().map()));

        } catch (Exception e) {
            LOGGER.error("Failed to send request to {}{}", BASE_URL, request.getUri(), e);
            request.getFuture().setException(e);
        }
    }
}
