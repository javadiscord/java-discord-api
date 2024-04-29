package com.javadiscord.jdi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.processor.ClassFileUtil;
import com.javadiscord.jdi.core.processor.EventListenerValidator;
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
    private final List<Object> eventListeners = new ArrayList<>();
    private final EventListenerValidator eventListenerValidator = new EventListenerValidator();

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
    }

    public void start() {
        try {
            loadListeners();
            if (eventListeners.isEmpty()) {
                LOGGER.warn("No event listeners have been registered");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to load listeners", e);
            throw new RuntimeException(e);
        }

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
        connectionMediator.addObserver(new GatewayEventListener(this, cache));
        webSocketManagerProxy.start(connectionMediator);

        EXECUTOR.execute(discordRequestDispatcher);
    }

    public void startWithoutGatewayEvents() {
        EXECUTOR.execute(discordRequestDispatcher);
    }

    private void loadListeners() throws Exception {
        List<File> classes = ClassFileUtil.getClassesInClassPath();
        for (File classFile : classes) {
            Class<?> clazz = Class.forName(ClassFileUtil.getClassName(classFile));
            if (clazz.isAnnotationPresent(EventListener.class)) {
                if (validateListener(clazz)) {
                    registerListener(clazz);
                } else {
                    LOGGER.error("{} failed validation", clazz.getName());
                }
            }
        }
    }

    private void registerListener(Class<?> clazz) {
        try {
            LOGGER.info("Registered listener {}", clazz.getName());
            eventListeners.add(getZeroArgConstructor(clazz).newInstance());
        } catch (Exception e) {
            LOGGER.error("Failed to create {} instance", clazz.getName(), e);
        }
    }

    boolean validateListener(Class<?> clazz) {
        return eventListenerValidator.validate(clazz);
    }

    public DiscordResponseFuture sendRequest(DiscordRequest request) {
        return discordRequestDispatcher.queue(request);
    }

    static Constructor<?> getZeroArgConstructor(Class<?> clazz) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        throw new RuntimeException("No zero arg constructor found for " + clazz.getName());
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

    public List<Object> getEventListeners() {
        return eventListeners;
    }
}
