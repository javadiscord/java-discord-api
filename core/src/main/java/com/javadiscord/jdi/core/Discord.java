package com.javadiscord.jdi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheType;
import com.javadiscord.jdi.internal.gateway.*;
import com.javadiscord.jdi.internal.gateway.identify.IdentifyRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Discord {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Executor EXECUTOR = Executors.newCachedThreadPool();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String WEBSITE = "https://javadiscord.com/";

    private static final String BASE_URL =
            System.getProperty("DISCORD_BASE_URL") != null
                    ? System.getProperty("DISCORD_BASE_URL")
                    : "https://discord.com/api";

    private final String botToken;
    private final IdentifyRequest identifyRequest;
    private final DiscordRequestDispatcher discordRequestDispatcher;
    private final Gateway gateway;
    private final GatewaySetting gatewaySetting;
    private final Cache cache;
    private final List<Object> annotatedEventListeners = new ArrayList<>();
    private final List<EventListener> eventListeners = new ArrayList<>();

    private Object listenerLoader;

    public Discord(String botToken) {
        this(
                botToken,
                IdentifyRequest.builder()
                        .token(botToken)
                        .os(WEBSITE)
                        .browser(WEBSITE)
                        .device(WEBSITE)
                        .compress(false)
                        .largeThreshold(250)
                        .shard(new int[] {0, 1})
                        .activityName("")
                        .activityType(0)
                        .presenceStatus("online")
                        .afk(false)
                        .intents(GatewayIntent.allIntents())
                        .build());
    }

    public Discord(String botToken, List<GatewayIntent> intents) {
        this(
                botToken,
                IdentifyRequest.builder()
                        .token(botToken)
                        .os(WEBSITE)
                        .browser(WEBSITE)
                        .device(WEBSITE)
                        .compress(false)
                        .largeThreshold(250)
                        .shard(new int[] {0, 1})
                        .activityName("")
                        .activityType(0)
                        .presenceStatus("online")
                        .afk(false)
                        .intents(GatewayIntent.valueOf(intents))
                        .build());
    }

    public Discord(String botToken, IdentifyRequest identifyRequest) {
        this(botToken, identifyRequest, new Cache(CacheType.FULL));
    }

    public Discord(String botToken, IdentifyRequest identifyRequest, Cache cache) {
        this.botToken = botToken;
        this.discordRequestDispatcher = new DiscordRequestDispatcher(botToken);
        this.gateway = getGatewayURL(botToken);
        this.gatewaySetting =
                new GatewaySetting().setEncoding(GatewayEncoding.JSON).setApiVersion(10);
        this.identifyRequest = identifyRequest;
        this.cache = cache;
        if (annotationLibPresent()) {
            loadAnnotations();
        }
    }

    private boolean annotationLibPresent() {
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        for (String entry : classpathEntries) {
            if (entry.endsWith("annotations-1.0.0.jar")) {
                return true;
            }
        }
        return false;
    }

    private void loadAnnotations() {
        try {
            Class<?> clazz = Class.forName("com.javadiscord.jdi.core.processor.ListenerLoader");
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == 1) {
                    Parameter parameters = constructor.getParameters()[0];
                    if (parameters.getType().equals(List.class)) {
                        listenerLoader = constructor.newInstance(annotatedEventListeners);
                        return;
                    }
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException ignore) {
            /* Ignore */
        }
    }

    public void start() {
        WebSocketManager webSocketManager =
                new WebSocketManager(
                        new GatewaySetting().setApiVersion(10).setEncoding(GatewayEncoding.JSON),
                        identifyRequest,
                        cache);

        WebSocketManagerProxy webSocketManagerProxy = new WebSocketManagerProxy(webSocketManager);
        ConnectionDetails connectionDetails =
                new ConnectionDetails(gateway.url(), botToken, gatewaySetting);
        ConnectionMediator connectionMediator =
                new ConnectionMediator(connectionDetails, webSocketManagerProxy);
        connectionMediator.addObserver(new GatewayEventListenerAnnotations(this));
        connectionMediator.addObserver(new GatewayEventListener(this));
        webSocketManagerProxy.start(connectionMediator);

        EXECUTOR.execute(discordRequestDispatcher);
    }

    public void startWithoutGatewayEvents() {
        EXECUTOR.execute(discordRequestDispatcher);
    }

    public void registerListener(EventListener eventListener) {
        eventListeners.add(eventListener);
        LOGGER.info("Registered listener {}", eventListener.getClass().getSimpleName());
    }

    public DiscordResponseFuture sendRequest(DiscordRequest request) {
        return discordRequestDispatcher.queue(request);
    }

    private static Gateway getGatewayURL(String authentication) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(BASE_URL + "/gateway/bot"))
                            .header("Authorization", "Bot " + authentication)
                            .GET()
                            .build();
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return OBJECT_MAPPER.readValue(response.body(), Gateway.class);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch the gateway URL from discord");
            throw new RuntimeException(e);
        }
    }

    public DiscordRequestDispatcher getDiscordRequestDispatcher() {
        return discordRequestDispatcher;
    }

    public Cache getCache() {
        return cache;
    }

    public List<Object> getAnnotatedEventListeners() {
        return annotatedEventListeners;
    }

    public List<EventListener> getEventListeners() {
        return eventListeners;
    }
}