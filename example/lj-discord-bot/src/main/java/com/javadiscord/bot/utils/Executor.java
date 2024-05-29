package com.javadiscord.bot.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executor {
    private static final ScheduledExecutorService EXECUTOR_SERVICE =
        Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    private Executor() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void execute(Runnable runnable) {
        EXECUTOR_SERVICE.submit(runnable);
    }

    public static void run(Runnable runnable, int period, TimeUnit timeUnit) {
        EXECUTOR_SERVICE.scheduleAtFixedRate(runnable, 0, period, timeUnit);
    }

    public static void run(Runnable runnable, int delay, int period, TimeUnit timeUnit) {
        EXECUTOR_SERVICE.scheduleAtFixedRate(runnable, delay, period, timeUnit);
    }
}
