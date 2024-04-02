package com.javadiscord.jdi.internal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.HashMap;
import java.util.Map;

public class DiscordRequestBuilder {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private HttpMethod method;
    private String path;
    private Map<String, String> parameters;
    private BodyPublisher body;
    private DiscordResponseFuture future = new DiscordResponseFuture();
    private final Map<String, Object> headers = new HashMap<>();

    public DiscordRequestBuilder putHeader(String name, Object value) {
        this.headers.put(name, value);
        return this;
    }

    public DiscordRequestBuilder putHeaders(Map<String, Object> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public DiscordRequestBuilder path(String path) {
        this.path = path;
        return this;
    }

    public DiscordRequestBuilder parameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public DiscordRequestBuilder body(Map<String, Object> payload) {
        try {
            this.body = BodyPublishers.ofString(OBJECT_MAPPER.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public DiscordRequestBuilder get() {
        this.method = HttpMethod.GET;
        return this;
    }

    public DiscordRequestBuilder post() {
        this.method = HttpMethod.POST;
        return this;
    }

    public DiscordRequestBuilder delete() {
        this.method = HttpMethod.DELETE;
        return this;
    }

    public DiscordRequestBuilder put() {
        this.method = HttpMethod.PUT;
        return this;
    }

    protected String getPath() {
        return path;
    }

    protected Map<String, String> getParameters() {
        return parameters;
    }

    HttpMethod getMethod() {
        return method;
    }

    protected HttpRequest.BodyPublisher getBody() {
        return body;
    }

    protected Map<String, Object> getHeaders() {
        return headers;
    }

    protected DiscordResponseFuture future() {
        return future;
    }

    protected void setSuccessResult(DiscordResponse result) {
        future.setResult(result);
    }

    protected void setFailureError(Throwable ex) {
        future.setException(ex);
    }
}
