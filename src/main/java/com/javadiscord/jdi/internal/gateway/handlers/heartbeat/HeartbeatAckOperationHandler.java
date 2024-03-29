package com.javadiscord.jdi.internal.gateway.handlers.heartbeat;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.gateway.ConnectionMediator;
import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.GatewayOperationHandler;

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
