package com.javadiscord.jdi.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.RequestRunner;
import com.javadiscord.jdi.internal.gateway.*;
import com.javadiscord.jdi.internal.gateway.identify.IdentifyRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Discord {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String WEBSITE = "https://javadiscord.com/";
    private final String botToken;
    private final IdentifyRequest identifyRequest;
    private final RequestRunner requestRunner;
    private final Gateway gateway;
    private final GatewaySetting gatewaySetting;

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
        this.botToken = botToken;
        this.requestRunner = new RequestRunner(botToken);
        this.gateway = getGatewayURL(botToken);
        this.gatewaySetting =
                new GatewaySetting().setEncoding(GatewayEncoding.JSON).setApiVersion(10);
        this.identifyRequest = identifyRequest;
    }

    public void start() {
        WebSocketManager webSocketManager =
                new WebSocketManager(
                        new GatewaySetting().setApiVersion(10).setEncoding(GatewayEncoding.JSON),
                        identifyRequest,
                        this);

        WebSocketManagerProxy webSocketManagerProxy = new WebSocketManagerProxy(webSocketManager);
        ConnectionDetails connectionDetails =
                new ConnectionDetails(gateway.url(), botToken, gatewaySetting);
        ConnectionMediator connectionMediator =
                new ConnectionMediator(connectionDetails, webSocketManagerProxy);
        webSocketManagerProxy.start(connectionMediator);

        EXECUTOR.execute(requestRunner);
    }

    private static Gateway getGatewayURL(String authentication) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create("https://discord.com/api/gateway/bot"))
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
}
