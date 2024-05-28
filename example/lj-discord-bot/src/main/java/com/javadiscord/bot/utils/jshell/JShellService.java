package com.javadiscord.bot.utils.jshell;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JShellService {
    private static final Logger LOGGER = LogManager.getLogger(JShellService.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String API_URL = System.getenv("JSHELL_API_URL");

    private final Map<Long, List<String>> history = new HashMap<>();

    public JShellService() {}

    public JShellResponse sendRequest(String code) {
        record Request(String code) {}
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request =
                HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .setHeader("Content-Type", "application/json")
                    .POST(
                        HttpRequest.BodyPublishers.ofString(
                            OBJECT_MAPPER.writeValueAsString(new Request(code))
                        )
                    )
                    .build();
            HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
            return OBJECT_MAPPER.readValue(response.body(), JShellResponse.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse data received from JShell API", e);
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Failed to send request to JShell API", e);
        }
        return null;
    }

    public void updateHistory(long userId, String snippet) {
        if (history.containsKey(userId)) {
            history.get(userId).add(snippet);
        } else {
            history.put(userId, new ArrayList<>());
        }
    }

    public List<String> getHistory(long userId) {
        if (history.containsKey(userId)) {
            return history.get(userId);
        }
        return new ArrayList<>();
    }
}
