package com.javadiscord.jdi.internal.gateway.identify;

import com.javadiscord.jdi.internal.gateway.GatewayOpcode;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentifyRequest {
    private final int op = GatewayOpcode.IDENTIFY;
    private final Data d;

    public static IdentifyRequestBuilder builder() {
        return new IdentifyRequestBuilder();
    }

    private IdentifyRequest(Data data) {
        this.d = data;
    }

    public int getOp() {
        return op;
    }

    public Data getD() {
        return d;
    }

    public static class IdentifyRequestBuilder {
        private String token;
        private String os;
        private String browser;
        private String device;
        private boolean compress;
        private int large_threshold;
        private int[] shard;
        private String activityName;
        private int activityType;
        private String presenceStatus;
        private boolean afk;
        private int intents;

        public IdentifyRequestBuilder token(String token) {
            this.token = token;
            return this;
        }

        public IdentifyRequestBuilder os(String os) {
            this.os = os;
            return this;
        }

        public IdentifyRequestBuilder browser(String browser) {
            this.browser = browser;
            return this;
        }

        public IdentifyRequestBuilder device(String device) {
            this.device = device;
            return this;
        }

        public IdentifyRequestBuilder compress(boolean compress) {
            this.compress = compress;
            return this;
        }

        public IdentifyRequestBuilder largeThreshold(int large_threshold) {
            this.large_threshold = large_threshold;
            return this;
        }

        public IdentifyRequestBuilder shard(int[] shard) {
            this.shard = shard;
            return this;
        }

        public IdentifyRequestBuilder activityName(String name) {
            this.activityName = name;
            return this;
        }

        public IdentifyRequestBuilder activityType(int type) {
            this.activityType = type;
            return this;
        }

        public IdentifyRequestBuilder presenceStatus(String status) {
            this.presenceStatus = status;
            return this;
        }

        public IdentifyRequestBuilder afk(boolean afk) {
            this.afk = afk;
            return this;
        }

        public IdentifyRequestBuilder intents(int intents) {
            this.intents = intents;
            return this;
        }

        public IdentifyRequest build() {
            Data data =
                new Data(
                    token,
                    new Properties(os, browser, device),
                    compress,
                    large_threshold,
                    shard,
                    new Presence(
                        new Activities(activityName, activityType),
                        presenceStatus,
                        afk
                    ),
                    intents
                );
            return new IdentifyRequest(data);
        }
    }
}
