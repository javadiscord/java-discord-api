package com.javadiscord.jdi.internal.api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DiscordRequest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private HttpMethod method;
    private String url = "https://discord.com/api";
    private Map<String, Object> headers;
    private BodyPublisher body;
    private Response<DiscordResponse> response = new Response<DiscordResponse>();

	public DiscordRequest(HttpMethod method, String path) {
        this(method, path, null);
    }
    
    public DiscordRequest(HttpMethod method, String path, Map<String, Object> queries) {
        this(method, path, queries, BodyPublishers.noBody());
    }

    public DiscordRequest(HttpMethod method, String path, Map<String, Object> queries, Map<String, Object> payload) throws JsonProcessingException {
        this(method, path, queries, BodyPublishers.ofString(MAPPER.writeValueAsString(payload)));
	}

	public DiscordRequest(HttpMethod method, String path, Map<String, Object> queries, BodyPublisher body) {
        if ((method == HttpMethod.GET || method == HttpMethod.DELETE) && body != BodyPublishers.noBody())
            throw new UnsupportedOperationException("GET/DELETE method cannot have a body attached");

        this.method = method;
        this.url += path;
        this.body = body;

        if (queries != null && !queries.isEmpty())
            url += "?" + encode(queries);
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public HttpRequest formRequest(String botToken) {
        if (!headers.containsKey("Content-Type"))
            headers.put("Content-Type", "application/json");

        if (!headers.containsKey("Authorization"))
            headers.put("Authorization", "BOT " + botToken);

        Builder builder = HttpRequest.newBuilder(URI.create(getUrl())).headers(makeHeaderArray(getHeaders()));

        builder = switch (method) {
            case GET -> builder.GET();
            case DELETE -> builder.DELETE();
            case POST -> builder.POST(body);
            case PUT -> builder.PUT(body);
        };

        return builder.build();
    }

	public Response<DiscordResponse> getResponse() {
		return response;
	}

	public void setSuccessResponse(DiscordResponse response) {
        this.response.setResult(response);
	}

    public void setErrorResponse(Exception e) {
        this.response.setException(e);
    }

    public static String[] makeHeaderArray(Map<String, Object> headers) {
        return headers.keySet().stream()
            .flatMap(key -> Stream.of(key, headers.get(key).toString()))
            .toList()
            .toArray(new String[0]);
    }

    public static String encode(Map<String, Object> parameters) {
        return parameters.entrySet()
            .stream()
            .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8))
            .collect(Collectors.joining("&"));
    }
}
