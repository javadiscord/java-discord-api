package com.javadiscord.bot.commands.slash;

import java.awt.*;
import java.io.OutputStream;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.javadiscord.bot.utils.docker.*;
import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.message.embed.EmbedAuthor;
import com.javadiscord.jdi.core.models.user.User;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinuxCommand {
    private static final Logger LOGGER = LogManager.getLogger(LinuxCommand.class);
    private static final ScheduledExecutorService EXECUTOR_SERVICE =
        Executors.newSingleThreadScheduledExecutor();

    private final DockerClient dockerClient;
    public final DockerSessions dockerSessions;
    private final DockerCommandRunner commandRunner;

    public LinuxCommand(
        DockerClient dockerClient,
        DockerSessions dockerSessions,
        DockerCommandRunner commandRunner
    ) {
        this.dockerClient = dockerClient;
        this.dockerSessions = dockerSessions;
        this.commandRunner = commandRunner;

        Runtime.getRuntime()
            .addShutdownHook(
                new Thread(
                    () -> dockerSessions
                        .getSessions()
                        .forEach(dockerSessions::stopContainer)
                )
            );

        EXECUTOR_SERVICE.scheduleAtFixedRate(
            new ContainerCleanupTask(dockerSessions), 0, 5, TimeUnit.MINUTES
        );
    }

    @SlashCommand(
        name = "linux", description = "Run commands in your very own Linux session", options = {
            @CommandOption(
                name = "code", description = "The command you would like to run", type = CommandOptionType.STRING
            )
        }
    )
    public void handle(SlashCommandEvent event) {
        event.deferReply();

        Optional<ApplicationCommandOption> codeOption = event.option("code");
        User user = event.user();

        codeOption.ifPresent(option -> {
            LOGGER.info("Running command {}", option.valueAsString());

            Thread.ofVirtual().start(() -> handleLinuxCommand(event, option.valueAsString(), user));
        });
    }

    private void handleLinuxCommand(SlashCommandEvent event, String command, User member) {
        String memberId = String.valueOf(member.id());
        Session session = getSessionForUser(memberId);
        try (OutputStream output = commandRunner.sendCommand(session, command)) {
            String reply =
                """
                    Ran command:
                    ```
                    $ %s
                    ```

                    ```java
                    %s
                    ```

                    Session expires in %s
                    """
                    .formatted(command, output, getSessionExpiry(session));

            Embed embed =
                new Embed.Builder()
                    .author(new EmbedAuthor(member.asMention(), null, null, null))
                    .description(shortenOutput(reply))
                    .color(Color.RED)
                    .build();

            event.reply(embed);
        } catch (Exception e) {
            LOGGER.error(e);
            event.reply("An error occurred: " + e.getMessage());
        }
    }

    private String getSessionExpiry(Session session) {
        Instant expiry = session.getStartTime().plusSeconds(TimeUnit.MINUTES.toSeconds(5));
        long epochSeconds = expiry.getEpochSecond();
        return "<t:" + epochSeconds + ":R>";
    }

    private String shortenOutput(String input) {
        String concatMessage = "\n**Rest of the output as been removed as it was too long**\n";
        if (input.length() > 4096) {
            input = input.substring(0, 4096 - concatMessage.length()) + concatMessage;
        }
        StringBuilder sb = new StringBuilder();
        String[] parts = input.split("\n");
        if (parts.length > 50) {
            for (int i = 50; i > 0; i--) {
                sb.append(parts[i]).append("\n");
            }
            sb.append(concatMessage);
        } else {
            sb.append(input);
        }
        return sb.toString();
    }

    private Session getSessionForUser(String name) {
        if (!dockerSessions.hasSession(name)) {

            LOGGER.info("Creating new session for {}", name);

            DockerContainerCreator containerCreator = new DockerContainerCreator(dockerClient);

            CreateContainerResponse createContainerResponse =
                containerCreator.createContainerStarted(
                    "session-" + ThreadLocalRandom.current().nextInt(),
                    "ubuntu:latest",
                    mb(256),
                    mb(256),
                    512,
                    100000,
                    cpuQuota(100000, 0.5)
                );

            return dockerSessions.createSession(name, createContainerResponse.getId());
        }

        LOGGER.info("Found existing session for {}", name);

        return dockerSessions.getSessionForUser(name);
    }

    public static long mb(long megabytes) {
        return megabytes * 1024 * 1024;
    }

    public static long cpuQuota(int cpuPeriod, double percentage) {
        return (long) (cpuPeriod * (percentage / 10.0));
    }
}
