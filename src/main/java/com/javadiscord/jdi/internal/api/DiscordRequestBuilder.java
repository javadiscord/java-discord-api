package com.javadiscord.jdi.internal.api;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mizosoft.methanol.MultipartBodyPublisher;

public class DiscordRequestBuilder {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final DiscordResponseFuture future = new DiscordResponseFuture();
    private final Map<String, Object> headers = new HashMap<>();
    private final Map<String, Object> queryParameters = new HashMap<>();
    private HttpMethod method;
    private String path;
    private HttpRequest.BodyPublisher body;

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

    public DiscordRequestBuilder queryParam(String name, Object value) {
        queryParameters.put(name, value);
        return this;
    }

    public DiscordRequestBuilder queryParams(Map<String, Object> queryParameters) {
        this.queryParameters.putAll(queryParameters);
        return this;
    }

    public DiscordRequestBuilder body(Map<String, Object> payload) {
        try {
            body = HttpRequest.BodyPublishers.ofString(OBJECT_MAPPER.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public DiscordRequestBuilder body(HttpRequest.BodyPublisher body) {
        this.body = body;
        return this;
    }

    public DiscordRequestBuilder multipartBody(MultipartBodyPublisher body) {
        this.body = body;
        this.headers.put("Content-Type", "multipart/form-data");
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

    public DiscordRequestBuilder patch() {
        this.method = HttpMethod.PATCH;
        return this;
    }

    protected String getQueryParameters() {
        StringBuilder encodedParams = new StringBuilder();
        for (Map.Entry<String, Object> entry : queryParameters.entrySet()) {
            if (encodedParams.isEmpty()) {
                encodedParams.append("?");
            } else {
                encodedParams.append("&");
            }
            encodedParams.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            encodedParams.append("=");
            encodedParams.append(
                URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8)
            );
        }
        return encodedParams.toString();
    }

    protected String getPath() {
        return path;
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
