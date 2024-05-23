package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.Discord;

public class Main {
    public static void main(String[] args) {
        Discord discord = new Discord(System.getenv("BOT_TOKEN"));
        discord.start();
    }
}
