package example;

import com.javadiscord.jdi.core.Discord;

public class Main {
    public static void main(String[] args) {
        Discord discord = new Discord(System.getenv("DISCORD_TOKEN"));
        discord.start();
    }
}
