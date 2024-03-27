package com.javadiscord.gateway.handlers.heartbeat;

import com.javadiscord.Discord;
import com.javadiscord.gateway.ConnectionMediator;
import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.GatewayOperationHandler;

public class HeartbeatAckOperationHandler implements GatewayOperationHandler {
    private final HeartbeatService heartbeatService;

    public HeartbeatAckOperationHandler(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @Override
    public void handle(GatewayEvent event, ConnectionMediator connectionMediator, Discord discord) {
        heartbeatService.setSequence(event.sequenceNumber());
        heartbeatService.receivedHeartbeatAck();
    }
}
