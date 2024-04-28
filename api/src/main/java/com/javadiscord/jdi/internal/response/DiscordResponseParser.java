package com.javadiscord.jdi.internal.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordResponse;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;

public class DiscordResponseParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final DiscordRequestDispatcher dispatcher;

    public DiscordResponseParser(DiscordRequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public <T> AsyncResponse<T> callAndParse(DiscordRequest request) {
        TypeReference<T> typeReference = new TypeReference<>() {};
        AsyncResponse<T> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(res -> successRef(typeReference, res, asyncResponse));
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    public <T> AsyncResponse<T> callAndParse(Class<T> clazz, DiscordRequest request) {
        AsyncResponse<T> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(res -> successT(clazz, res, asyncResponse));
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    private <T> void successT(
            Class<T> type, DiscordResponse response, AsyncResponse<T> asyncResponse) {
        if (response.status() >= 200 && response.status() < 300) {
            try {
                T result = OBJECT_MAPPER.readValue(response.body(), type);
                asyncResponse.setResult(result);
            } catch (JsonProcessingException e) {
                asyncResponse.setException(e);
            }
        } else {
            asyncResponse.setException(
                    new Exception(
                            "Received HTTP status code "
                                    + response.status()
                                    + " "
                                    + response.body()));
        }
    }

    private <T> void successRef(
            TypeReference<T> type, DiscordResponse response, AsyncResponse<T> asyncResponse) {
        if (response.status() >= 200 && response.status() < 300) {
            try {
                T result = OBJECT_MAPPER.readValue(response.body(), type);
                asyncResponse.setResult(result);
            } catch (JsonProcessingException e) {
                asyncResponse.setException(e);
            }
        } else {
            asyncResponse.setException(
                    new Exception(
                            "Received HTTP status code "
                                    + response.status()
                                    + " "
                                    + response.body()));
        }
    }
}
