package com.javadiscord.bot.utils.docker;

import java.time.Instant;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class ContainerCleanupTask implements Runnable {
    private static final long CONTAINER_DURATION = TimeUnit.MINUTES.toSeconds(5);

    private final DockerSessions dockerSessions;

    public ContainerCleanupTask(DockerSessions dockerSessions) {
        this.dockerSessions = dockerSessions;
    }

    @Override
    public void run() {
        Instant now = Instant.now();
        Iterator<Session> it = dockerSessions.getSessions().iterator();
        while (it.hasNext()) {
            Session session = it.next();
            Instant sessionStart = session.getStartTime();
            boolean sessionExpired = sessionStart.plusSeconds(CONTAINER_DURATION).isAfter(now);
            if (sessionExpired) {
                dockerSessions.stopContainer(session);
                it.remove();
            }
        }
    }
}
