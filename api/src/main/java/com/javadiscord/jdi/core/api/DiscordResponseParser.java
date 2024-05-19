package com.javadiscord.jdi.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordResponse;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;

import java.util.List;
import java.util.Map;

public class DiscordResponseParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final DiscordRequestDispatcher dispatcher;

    public DiscordResponseParser(DiscordRequestDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public <T> AsyncResponse<List<T>> callAndParseList(Class<T> clazz, DiscordRequest request) {
        AsyncResponse<List<T>> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(
                res -> {
                    if (res.status() >= 200 && res.status() < 300) {
                        try {
                            List<T> resultList = parseResponseFromList(clazz, res.body());
                            asyncResponse.setResult(resultList);
                        } catch (Exception e) {
                            asyncResponse.setException(e);
                        }
                    } else {
                        asyncResponse.setException(errorResponseException(res));
                    }
                });
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    public <T> AsyncResponse<List<T>> callAndParseMap(String key, DiscordRequest request) {
        AsyncResponse<List<T>> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(
                res -> {
                    if (res.status() >= 200 && res.status() < 300) {
                        try {
                            List<T> resultList = parseResponseFromMap(key, res.body());
                            asyncResponse.setResult(resultList);
                        } catch (Exception e) {
                            asyncResponse.setException(e);
                        }
                    } else {
                        asyncResponse.setException(errorResponseException(res));
                    }
                });
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    private <T> List<T> parseResponseFromList(Class<T> elementType, String response)
            throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(
                response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, elementType));
    }

    private <T> List<T> parseResponseFromMap(String key, String response)
            throws JsonProcessingException {
        Map<String, List<T>> res = OBJECT_MAPPER.readValue(
                response,
                OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, String.class, List.class));
        return res.get(key);
    }

    public <T> AsyncResponse<T> callAndParse(Class<T> clazz, DiscordRequest request) {
        AsyncResponse<T> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(res -> success(clazz, res, asyncResponse));
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    private <T> void success(
            Class<T> type, DiscordResponse response, AsyncResponse<T> asyncResponse) {
        if (response.status() >= 200 && response.status() < 300) {
            try {
                T result = OBJECT_MAPPER.readValue(response.body(), type);
                asyncResponse.setResult(result);
            } catch (JsonProcessingException e) {
                asyncResponse.setException(e);
            }
        } else {
            asyncResponse.setException(errorResponseException(response));
        }
    }

    private Throwable errorResponseException(DiscordResponse response) {
       return new Exception(
                "Received HTTP status code "
                        + response.status()
                        + " "
                        + response.body());
    }
}
