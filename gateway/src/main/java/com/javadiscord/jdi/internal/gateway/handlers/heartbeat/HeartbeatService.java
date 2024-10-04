package com.javadiscord.jdi.internal.gateway.handlers.heartbeat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.javadiscord.jdi.internal.gateway.ConnectionMediator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.client.WebSocketClient;

public class HeartbeatService {
    private static final Logger LOGGER = LogManager.getLogger(HeartbeatService.class);
    private static final ScheduledExecutorService EXECUTOR_SERVICE =
        Executors.newScheduledThreadPool(2);
    private final ConnectionMediator connectionMediator;
    private final AtomicBoolean receivedHeartbeatAck;
    private final AtomicInteger missedHeartbeatAck;

    public HeartbeatService(ConnectionMediator connectionMediator) {
        this.connectionMediator = connectionMediator;
        this.receivedHeartbeatAck = new AtomicBoolean(false);
        this.missedHeartbeatAck = new AtomicInteger(0);
    }

    public void startHeartbeat(WebSocketClient webSocket, int interval) {
        EXECUTOR_SERVICE.scheduleAtFixedRate(
            () -> sendHeartbeat(webSocket), 0, interval, TimeUnit.MILLISECONDS
        );

        EXECUTOR_SERVICE.scheduleAtFixedRate(
            () -> checkHeartbeatAckReceived(webSocket),
            interval,
            interval + 1000,
            TimeUnit.MILLISECONDS
        );
    }

    private void checkHeartbeatAckReceived(WebSocketClient webSocket) {
        if (!receivedHeartbeatAck.get()) {
            LOGGER.trace("Discord did not send a heartbeat ack, resending");
            sendHeartbeat(webSocket);
            missedHeartbeatAck.getAndIncrement();
        }
        if (missedHeartbeatAck.get() >= 3) {
            LOGGER.warn(
                "Discord missed 3 heartbeat acknowledgements, restarting due to \"zombied\""
                    + " connection"
            );
            connectionMediator.getWebSocketManagerProxy().restart(connectionMediator);
        }
    }

    public void stop() {
        EXECUTOR_SERVICE.shutdown();
    }

    public void sendHeartbeat(WebSocketClient webSocket) {
    webSocket.send(
        "{ \"op\": 1, \"d\": \"%s\" }"
            .formatted(
                connectionMediator
                    .getConnectionDetails()
                    .getSequence()
            )
        );
        receivedHeartbeatAck.set(false);
    }

    public void setSequence(int sequence) {
        connectionMediator.getConnectionDetails().setSequence(sequence);
    }

    public void receivedHeartbeatAck() {
        missedHeartbeatAck.set(0);
        receivedHeartbeatAck.set(true);
    }
}
