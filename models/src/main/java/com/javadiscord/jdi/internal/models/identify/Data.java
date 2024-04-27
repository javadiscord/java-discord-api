package com.javadiscord.jdi.internal.models.identify;

public class Data {
    private String token;
    private Properties properties;
    private boolean compress;
    private int large_threshold;
    private int[] shard;
    private Presence presence;
    private int intents;

    public Data() {}

    public Data(
        String token,
        Properties properties,
        boolean compress,
        int large_threshold,
        int[] shard,
        Presence presence,
        int intents
    ) {
        this.token = token;
        this.properties = properties;
        this.compress = compress;
        this.large_threshold = large_threshold;
        this.shard = shard;
        this.presence = presence;
        this.intents = intents;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public boolean isCompress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public int getLarge_threshold() {
        return large_threshold;
    }

    public void setLarge_threshold(int large_threshold) {
        this.large_threshold = large_threshold;
    }

    public int[] getShard() {
        return shard;
    }

    public void setShard(int[] shard) {
        this.shard = shard;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public int getIntents() {
        return intents;
    }

    public void setIntents(int intents) {
        this.intents = intents;
    }
}
