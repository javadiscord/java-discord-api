package com.javadiscord.jdi.internal.gateway.identify;

public class Properties {
    private String os;
    private String browser;
    private String device;

    public Properties() {}

    public Properties(String os, String browser, String device) {
        this.os = os;
        this.browser = browser;
        this.device = device;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
}
