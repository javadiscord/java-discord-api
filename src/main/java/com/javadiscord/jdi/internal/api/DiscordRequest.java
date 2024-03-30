package com.javadiscord.jdi.internal.api;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.utils.UrlUtils;

public class DiscordRequest {
    public enum Method {
        GET, POST, DELETE, PUT;
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    private Method method;
    private String url = "https://discord.com/api";
    private Map<String, Object> headers;
    private BodyPublisher body;
    private DiscordResponse response;

	public DiscordRequest(Method method, String path) {
        this(method, path, null);
    }
    
    public DiscordRequest(Method method, String path, Map<String, Object> queries) {
        this(method, path, queries, null);
    }
    
    public DiscordRequest(Method method, String path, Map<String, Object> queries, Map<String, Object> headers) {
        this(method, path, queries, headers, BodyPublishers.noBody());
    }

    public DiscordRequest(Method method, String path, Map<String, Object> queries, Map<String, Object> headers, Map<String, Object> payload) throws JsonProcessingException {
        this(method, path, queries, headers, BodyPublishers.ofString(mapper.writeValueAsString(payload)));
	}

	public DiscordRequest(Method method, String path, Map<String, Object> queries, Map<String, Object> headers, BodyPublisher body) {
        if ((method == Method.GET || method == Method.DELETE) && body != BodyPublishers.noBody())
            throw new UnsupportedOperationException("GET/DELETE method cannot have a body attached");

        this.method = method;
        this.url += path;
        this.headers = headers;
        this.body = body;

        if (queries != null && !queries.isEmpty())
            url += "?" + UrlUtils.encode(queries);
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getHeaders(String botToken) {
        if (!headers.containsKey("Content-Type"))
            headers.put("Content-Type", "application/json");

        if (!headers.containsKey("Authorization"))
            headers.put("Authorization", "BOT " + botToken);

        return headers;
    }

    public HttpRequest formRequest(String botToken) {
        Builder builder = HttpRequest.newBuilder(URI.create(getUrl())).headers(makeHeaderArray(getHeaders(botToken)));

        builder = switch (method) {
            case GET -> builder.GET();
            case POST -> builder.POST(body);
            case DELETE -> builder.DELETE();
            case PUT -> builder.PUT(body);
        };

        return builder.build();
    }

	public DiscordResponse getResponse() {
		return response;
	}

	public void setResponse(DiscordResponse response) {
		this.response = response;
	}

    public static String[] makeHeaderArray(Map<String, Object> headers) {
        return headers.keySet().stream()
            .flatMap(key -> Stream.of(key, headers.get(key).toString()))
            .toList()
            .toArray(new String[0]);
    }
}
