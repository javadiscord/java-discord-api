package com.javadiscord.bot.utils.docker;

import java.util.ArrayList;
import java.util.List;

import com.github.dockerjava.api.DockerClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DockerSessions {
    private static final Logger LOGGER = LogManager.getLogger();
    private final DockerClient dockerClient;
    private final List<Session> sessions = new ArrayList<>();

    public DockerSessions(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public Session createSession(String userId, String containerId) {
        Session session = new Session(userId, containerId);
        sessions.add(session);
        LOGGER.info("Created new session for {}", userId);
        return session;
    }

    public void removeSession(Session session) {
        sessions.remove(session);
        dockerClient.stopContainerCmd(session.getContainerId()).exec();
    }

    public void stopContainer(Session session) {
        dockerClient.stopContainerCmd(session.getContainerId()).exec();
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public boolean hasSession(String userId) {
        for (Session session : sessions) {
            if (session.getSessionId().equalsIgnoreCase(userId)) {
                return true;
            }
        }
        return false;
    }

    public Session getSessionForUser(String userId) {
        for (Session session : sessions) {
            if (session.getSessionId().equals(userId)) {
                return session;
            }
        }
        throw new RuntimeException("No session found for user");
    }
}
