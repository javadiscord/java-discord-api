package com.javadiscord.gateway.handlers.heartbeat;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.MessageHandler;

public class HeartbeatHandler implements MessageHandler {
    private final HeartbeatService heartbeatService;

    public HeartbeatHandler(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord) {
        heartbeatService.sendHeartbeat(
                connectionMediator.getWebSocketManagerProxy().getWebSocket());
    }
}
