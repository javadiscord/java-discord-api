package com.javadiscord.jdi.internal.gateway.handlers.heartbeat;

import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.GatewayOperationHandler;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloOperationHandler implements GatewayOperationHandler {
    private static final Logger LOGGER = LogManager.getLogger(HelloOperationHandler.class);
    private static final ObjectMapper OBJECT_MAPPER =
        JsonMapper.builder().addModule(new JavaTimeModule()).build();
    private final HeartbeatService heartbeatService;

    public HelloOperationHandler(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record HeartbeatData(@JsonProperty("heartbeat_interval") int interval) {}

    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator) {
        JsonNode jsonData = event.data();
        try {
            HeartbeatData heartbeatData =
                OBJECT_MAPPER.readValue(jsonData.toString(), HeartbeatData.class);
            heartbeatService.startHeartbeat(
                connectionMediator.getWebSocketManagerProxy().getWebSocket(),
                heartbeatData.interval
            );
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse Heartbeat data", e);
        }
    }
}
