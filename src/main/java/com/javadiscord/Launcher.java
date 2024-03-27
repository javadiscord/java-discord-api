package com.javadiscord;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.api.RequestRunner;
import com.javadiscord.gateway.*;
import com.javadiscord.gateway.identify.IdentifyRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Launcher {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String BOT_TOKEN = "";

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        RequestRunner requestRunner = new RequestRunner(BOT_TOKEN);
        Discord discord = new Discord(requestRunner);
        Gateway gateway = getGatewayURL(BOT_TOKEN);
        GatewaySetting gatewaySetting =
                new GatewaySetting().setEncoding(GatewayEncoding.JSON).setApiVersion(10);

        IdentifyRequest request =
                IdentifyRequest.builder()
                        .token(BOT_TOKEN)
                        .os("javadiscord.com")
                        .browser("javadiscord.com")
                        .device("javadiscord.com")
                        .compress(false)
                        .largeThreshold(250)
                        .shard(new int[] {0, 1})
                        .activityName("")
                        .activityType(0)
                        .presenceStatus("online")
                        .afk(false)
                        .intents(GatewayIntent.allIntents())
                        .build();

        WebSocketManager webSocketManager =
                new WebSocketManager(
                        new GatewaySetting().setApiVersion(10).setEncoding(GatewayEncoding.JSON),
                        request,
                        discord);

        WebSocketManagerProxy webSocketManagerProxy = new WebSocketManagerProxy(webSocketManager);
        ConnectionDetails connectionDetails =
                new ConnectionDetails(gateway.url(), BOT_TOKEN, gatewaySetting);
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
