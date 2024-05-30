package com.javadiscord.bot.utils.docker;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private final Instant startTime;
    private final String sessionId;
    private final String containerId;

    private final List<String> commandHistory = new ArrayList<>();

    public Session(String sessionId, String containerId) {
        this.startTime = Instant.now();
        this.sessionId = sessionId;
        this.containerId = containerId;
    }

    public void updateHistory(String command) {
        commandHistory.add(command);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getContainerId() {
        return containerId;
    }

    public Instant getStartTime() {
        return startTime;
    }
}
