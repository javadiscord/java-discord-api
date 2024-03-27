package com.javadiscord.gateway;

import io.vertx.core.Vertx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebSocketRetryHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Vertx vertx;
    private int attempts;

    public WebSocketRetryHandler(Vertx vertx) {
        this.vertx = vertx;
        this.attempts = 0;
    }

    public synchronized void retry(Runnable retryAction) {
        attempts++;
        long delay = getDelayForNextRetry();
        LOGGER.info("Reconnecting in {}ms [attempt={}]", delay, attempts);
        vertx.setTimer(delay, timerId -> retryAction.run());
    }

    private long getDelayForNextRetry() {
        return (long) (2000 * Math.pow(2, attempts));
    }

    public synchronized void clear() {
        attempts = 0;
    }

    public boolean hasRetried() {
        return attempts > 0;
    }
}
