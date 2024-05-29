package com.javadiscord.jdi.example;

import com.javadiscord.jdi.core.Discord;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;

@Factory
public class Main {
    public static void main(String[] args) {
        Discord discord = new Discord("");
        discord.start();
    }

    @Bean
    public static String prefix() {
        return "Message: ";
    }
}
