package com.javadiscord.gateway;

public class ConnectionDetails {
    private final GatewaySetting gatewaySetting;
    private final String authorization;
    private String gatewayURL;
    private String sessionId;
    private int sequence;

    public ConnectionDetails(
            String gatewayURL, String authorization, GatewaySetting gatewaySetting) {
        this.gatewayURL = gatewayURL;
        this.authorization = authorization;
        this.gatewaySetting = gatewaySetting;
        this.sequence = 0;
    }

    public String getGatewayURL() {
        return gatewayURL;
    }

    public void setGatewayURL(String gatewayURL) {
        this.gatewayURL = gatewayURL;
    }

    public String getAuthorization() {
        return authorization;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public GatewaySetting getGatewaySetting() {
        return gatewaySetting;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
