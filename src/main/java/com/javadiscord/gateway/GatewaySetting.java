package com.javadiscord.gateway;

public class GatewaySetting {
    private GatewayEncoding encoding;
    private int apiVersion;

    public GatewaySetting setEncoding(GatewayEncoding encoding) {
        this.encoding = encoding;
        return this;
    }

    public GatewayEncoding getEncoding() {
        return encoding;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public GatewaySetting setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }
}
