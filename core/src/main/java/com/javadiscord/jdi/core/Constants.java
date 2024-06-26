package com.javadiscord.jdi.core;

public class Constants {
    public static final String COMMAND_OPTION_CHOICE_ANNOTATION =
        "com.javadiscord.jdi.core.annotations.CommandOptionChoice";

    public static final String SLASH_COMMAND_ANNOTATION =
        "com.javadiscord.jdi.core.annotations.SlashCommand";

    public static final String LISTENER_LOADER_CLASS =
        "com.javadiscord.jdi.internal.processor.loader.ListenerLoader";
    public static final String COMPONENT_LOADER_CLASS =
        "com.javadiscord.jdi.internal.processor.loader.ComponentLoader";
    public static final String SLASH_COMMAND_LOADER_CLASS =
        "com.javadiscord.jdi.internal.processor.loader.SlashCommandLoader";

    public static final String LAUNCH_HEADER = """

             _ ____ ___
            | |  _ \\_ _|  https://github.com/javadiscord/java-discord-api
         _  | | | | | |   Open-Source Discord Framework
        | |_| | |_| | |   GPL-3.0 license
         \\___/|____/___|  Version 1.0
        """;

    private Constants() {
        throw new UnsupportedOperationException("Utility class");
    }

}
