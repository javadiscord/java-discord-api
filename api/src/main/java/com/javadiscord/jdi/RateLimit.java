package com.javadiscord.jdi;

public class RateLimit {
    private String bucket;
    private int limit;
    private int remaining;
    private long reset;
    private int resetAfter;
    private boolean globalRateLimit;

    public RateLimit() {}

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public long getReset() {
        return reset;
    }

    public void setReset(long reset) {
        this.reset = reset;
    }

    public int getResetAfter() {
        return resetAfter;
    }

    public void setResetAfter(int resetAfter) {
        this.resetAfter = resetAfter;
    }

    public boolean isGlobalRateLimit() {
        return globalRateLimit;
    }

    public void setGlobalRateLimit(boolean globalRateLimit) {
        this.globalRateLimit = globalRateLimit;
    }
}
