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
    private Response<DiscordResponse> response;

	public DiscordRequest(Method method, String path) {
        this(method, path, null);
    }
    
    public DiscordRequest(Method method, String path, Map<String, Object> queries) {
        this(method, path, queries, BodyPublishers.noBody());
    }

    public DiscordRequest(Method method, String path, Map<String, Object> queries, Map<String, Object> payload) throws JsonProcessingException {
        this(method, path, queries, BodyPublishers.ofString(mapper.writeValueAsString(payload)));
	}

	public DiscordRequest(Method method, String path, Map<String, Object> queries, BodyPublisher body) {
        if ((method == Method.GET || method == Method.DELETE) && body != BodyPublishers.noBody())
            throw new UnsupportedOperationException("GET/DELETE method cannot have a body attached");

        this.method = method;
        this.url += path;
        this.body = body;

        if (queries != null && !queries.isEmpty())
            url += "?" + UrlUtils.encode(queries);
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
		this.response = new Response<DiscordResponse>();
        this.response.setResult(response);
	}

    public void setErrorResponse(Exception e) {
		this.response = new Response<>();
        this.response.setException(e);
    }

    public static String[] makeHeaderArray(Map<String, Object> headers) {
        return headers.keySet().stream()
            .flatMap(key -> Stream.of(key, headers.get(key).toString()))
            .toList()
            .toArray(new String[0]);
    }
}
