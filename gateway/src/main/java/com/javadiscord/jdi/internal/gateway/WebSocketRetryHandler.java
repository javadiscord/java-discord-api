package com.javadiscord.jdi.internal.gateway;

import io.vertx.core.Vertx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class WebSocketRetryHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Vertx vertx;
    private AtomicInteger attempts;

    public WebSocketRetryHandler(Vertx vertx) {
        this.vertx = vertx;
        this.attempts = new AtomicInteger(0);
    }

    public synchronized void retry(Runnable retryAction) {
        long delay = getDelayForNextRetry();
        LOGGER.info("Reconnecting in {}ms [attempt={}]", delay, attempts.getAndIncrement());
        vertx.setTimer(delay, timerId -> retryAction.run());
    }

    private long getDelayForNextRetry() {
        return (long) (2000 * Math.pow(2, attempts.get()));
    }

    public synchronized void clear() {
        attempts.set(0);
    }

    public boolean hasRetried() {
        return attempts.get() > 0;
    }
}
