package example;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.processor.DynamicClassLoader;

public class Main {

    public static final DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(
        Discord.class.getClassLoader().getParent()
    );

    public static void main(String[] args) {
        Discord discord = new Discord(System.getenv("DISCORD_TOKEN"));
        discord.start();
    }

}
