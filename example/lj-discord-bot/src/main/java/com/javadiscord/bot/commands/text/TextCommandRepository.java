package com.javadiscord.bot.commands.text;

import java.util.HashMap;
import java.util.Map;

import com.javadiscord.bot.commands.text.impl.*;

public class TextCommandRepository {
    private static final Map<String, TextCommand> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("clear", new ClearChannelCommand());
        COMMANDS.put("mute", new MuteCommand());
        COMMANDS.put("unmute", new UnmuteCommand());
        COMMANDS.put("say", new SayCommand());
        COMMANDS.put("embed", new SayEmbedCommand());
    }

    public static TextCommand get(String key) {
        return COMMANDS.get(key);
    }
}
