package com.javadiscord.example;

import com.javadiscord.jdi.core.Discord;

public class Main {
    private static final String BOT_TOKEN = "";

    public static void main(String[] args) {
        Discord discord = new Discord(BOT_TOKEN);
        discord.start();
    }
}
