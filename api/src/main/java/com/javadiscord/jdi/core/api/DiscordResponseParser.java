package com.javadiscord.jdi.core.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordResponse;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.cache.Cache;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class DiscordResponseParser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final DiscordRequestDispatcher dispatcher;
    private final Cache cache;

    public DiscordResponseParser(DiscordRequestDispatcher dispatcher, Cache cache) {
        this.dispatcher = dispatcher;
        this.cache = cache;
    }

    public <T> AsyncResponse<List<T>> callAndParseList(Class<T> clazz, DiscordRequest request) {
        AsyncResponse<List<T>> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(
                response -> {
                    if (isSuccessfulResponse(response)) {
                        try {
                            List<T> resultList = parseResponseFromList(clazz, response.body());
                            asyncResponse.setResult(resultList);
                            for (T result : resultList) {
                                cacheResult(result);
                            }
                        } catch (NoSuchFieldException e) { // ignore
                        } catch (Exception e) {
                            asyncResponse.setException(e);
                        }
                    } else {
                        asyncResponse.setException(errorResponseException(response));
                    }
                });
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    private <T> List<T> parseResponseFromList(Class<T> elementType, String response)
            throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(
                response,
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, elementType));
    }

    public <T> AsyncResponse<List<T>> callAndParseMap(String key, DiscordRequest request) {
        AsyncResponse<List<T>> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future = dispatcher.queue(request);
        future.onSuccess(
                response -> {
                    if (isSuccessfulResponse(response)) {
                        try {
                            List<T> resultList = parseResponseFromMap(key, response.body());
                            asyncResponse.setResult(resultList);
                            for(T result : resultList) {
                                cacheResult(result);
                            }
                        } catch (NoSuchFieldException e) { // ignore
                        } catch (Exception e){
                            asyncResponse.setException(e);
                        }
                    } else {
                        asyncResponse.setException(errorResponseException(response));
                    }
                });
        future.onError(asyncResponse::setException);
        return asyncResponse;
    }

    private <T> List<T> parseResponseFromMap(String key, String response)
            throws JsonProcessingException {
        Map<String, List<T>> res =
                OBJECT_MAPPER.readValue(
                        response,
                        OBJECT_MAPPER
                                .getTypeFactory()
                                .constructMapType(Map.class, String.class, List.class));
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
        if (isSuccessfulResponse(response)) {
            try {
                T result = OBJECT_MAPPER.readValue(response.body(), type);
                asyncResponse.setResult(result);
                cacheResult(result);
            } catch(NoSuchFieldException e) { // ignore
            } catch (JsonProcessingException | IllegalAccessException e) {
                asyncResponse.setException(e);
            }
        } else {
            asyncResponse.setException(errorResponseException(response));
        }
    }

    private <T> void cacheResult(T result) throws IllegalAccessException, NoSuchFieldException {
        Field guildIdField = result.getClass().getDeclaredField("guildId");
        Field idField = result.getClass().getDeclaredField("id");

        long guildId = getLongFromField(guildIdField);
        long id = getLongFromField(idField);

        cache.getCacheForGuild(guildId).add(id, result);
    }

    private long getLongFromField(Field field) throws IllegalAccessException {
        field.setAccessible(true);
        if (field.getType() == String.class) {
            return Long.parseLong((String) field.get(field.getName()));
        }
        return (long) field.get(field.getName());
    }

    private boolean isSuccessfulResponse(DiscordResponse response) {
        return response.status() >= 200 && response.status() < 300;
    }

    private Throwable errorResponseException(DiscordResponse response) {
        return new Exception(
                "Received HTTP status code " + response.status() + " " + response.body());
    }
}
