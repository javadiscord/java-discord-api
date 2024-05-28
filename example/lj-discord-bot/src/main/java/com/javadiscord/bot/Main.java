package com.javadiscord.bot;

import com.javadiscord.bot.commands.slash.jshell.JShellService;
import com.javadiscord.bot.utils.chatgpt.ChatGPT;
import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.annotations.Component;

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
}
