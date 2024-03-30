package com.javadiscord.jdi.internal.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RequestRunner implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BlockingQueue<DiscordRequest> queue;
    private final String botToken;
    private int numberOfRequestsSent;
    private long timeSinceLastRequest;

    public RequestRunner(String botToken) {
        this.botToken = botToken;
        this.queue = new LinkedBlockingQueue<>();
        this.numberOfRequestsSent = 0;
        this.timeSinceLastRequest = 0;
    }

    public void queue(DiscordRequest request) {
        queue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            long elapsed = currentTime - timeSinceLastRequest;

            if (elapsed < 1000 && numberOfRequestsSent >= 50) {
                try {
                    Thread.sleep(1000 - elapsed);
                } catch (InterruptedException e) { /* Ignore */ }

                numberOfRequestsSent = 0;
            }

            try {
                sendRequest(queue.take());
            } catch (InterruptedException e) { /* Ignored */ }
        }
    }

    private void sendRequest(DiscordRequest request) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest httpRequest = request.formRequest(botToken);

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            numberOfRequestsSent++;
            timeSinceLastRequest = System.currentTimeMillis();

            request.setResponse(new DiscordResponse(response.body(), response.statusCode(), response.headers().map()));
        } catch (Exception e) {
            LOGGER.error("Failed to send request to {}", request.getUrl(), e);
        }
    }
}
