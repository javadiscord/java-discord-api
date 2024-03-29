package com.javadiscord.jdi.internal.api;

import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {
    private String method;
    private String uri;
    private Map<String, String> headers;
    private String body;

    private Future<HTTPResponse> future;

    public HTTPRequest(String method, String uri) {
        this(method, uri, new HashMap<>(), "");
    }

    public HTTPRequest(String method, String uri, String body) {
        this(method, uri, new HashMap<>(), body);
    }

    public HTTPRequest(String method, String uri, Map<String, String> headers, String body) {
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public HTTPRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public HTTPRequest setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HTTPRequest setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HTTPRequest setBody(String body) {
        this.body = body;
        return this;
    }

    public Future<HTTPResponse> getFuture() {
        return future;
    }

    protected void setFuture(Future<HTTPResponse> future) {
        this.future = future;
    }
}
