package com.javadiscord.jdi.internal.gateway;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebSocketRetryHandler {
    private static final Logger LOGGER = LogManager.getLogger(WebSocketRetryHandler.class);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final AtomicInteger attempts;

    public WebSocketRetryHandler() {
        this.attempts = new AtomicInteger(0);
    }

    public synchronized void retry(Runnable retryAction) {
        long delay = getDelayForNextRetry();
        LOGGER.info("Reconnecting in {}ms [attempt={}]", delay, attempts.getAndIncrement());
        scheduler.schedule(retryAction, delay, TimeUnit.MILLISECONDS);
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
