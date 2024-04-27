package com.javadiscord.jdi.internal.models.identify;

public class Presence {
    private Activities activities;
    private String status;
    private boolean afk;

    public Presence() {}

    public Presence(Activities activities, String status, boolean afk) {
        this.activities = activities;
        this.status = status;
        this.afk = afk;
    }

    public Activities getActivities() {
        return activities;
    }

    public void setActivities(Activities activities) {
        this.activities = activities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAfk() {
        return afk;
    }

    public void setAfk(boolean afk) {
        this.afk = afk;
    }
}
