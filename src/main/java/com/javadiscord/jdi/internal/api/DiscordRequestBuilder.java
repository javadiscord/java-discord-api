package com.javadiscord.jdi.internal.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class DiscordRequestBuilder {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private String method;
    private String url;
    private String body;
    private DiscordResponseFuture future;
    private final Map<String, Object> headers = new HashMap<>();

    public DiscordRequestBuilder putHeader(String name, String value) {
        this.headers.put(name, value);
        return this;
    }

    public DiscordRequestBuilder putHeaders(Map<String, Object> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public DiscordRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public DiscordRequestBuilder body(Object body) {
        try {
            this.body = OBJECT_MAPPER.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public DiscordRequestBuilder get() {
        this.method = "get";
        return this;
    }

    public DiscordRequestBuilder post() {
        this.method = "post";
        return this;
    }

    public DiscordRequestBuilder delete() {
        this.method = "delete";
        return this;
    }

    public DiscordRequestBuilder put() {
        this.method = "put";
        return this;
    }

    protected String getUrl() {
        return url;
    }

    protected String getMethod() {
        return method;
    }

    protected String getBody() {
        return body;
    }

    protected Map<String, Object> getHeaders() {
        return headers;
    }

    protected DiscordResponseFuture future() {
        return future;
    }

    protected void setFuture(DiscordResponseFuture future) {
        this.future = future;
    }
}
