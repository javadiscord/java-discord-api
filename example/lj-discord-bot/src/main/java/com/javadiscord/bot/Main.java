package com.javadiscord.bot;

import com.javadiscord.bot.utils.chatgpt.ChatGPT;
import com.javadiscord.bot.utils.docker.DockerCommandRunner;
import com.javadiscord.bot.utils.docker.DockerSessions;
import com.javadiscord.bot.utils.jshell.JShellService;
import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.annotations.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

public class Main {
    public static void main(String[] args) {
        Discord discord = new Discord(System.getenv("BOT_TOKEN"));
        discord.start();
    }

    @Component
    public static ChatGPT chatGpt() {
        return new ChatGPT();
    }

    @Component
    public static JShellService jShellService() {
        return new JShellService();
    }

    private static final DockerClient DOCKER_CLIENT =
        DockerClientBuilder.getInstance("tcp://localhost:2375").build();

    @Component
    public static DockerClient dockerClient() {
        return DOCKER_CLIENT;
    }

    @Component
    public static DockerCommandRunner dockerCommandRunner() {
        return new DockerCommandRunner(DOCKER_CLIENT);
    }

    @Component
    public static DockerSessions dockerSessions() {
        return new DockerSessions(DOCKER_CLIENT);
    }
}
