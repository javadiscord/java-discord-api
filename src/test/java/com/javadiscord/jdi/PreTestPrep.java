package com.javadiscord.jdi;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import io.gatehill.imposter.embedded.MockEngine;
import io.gatehill.imposter.openapi.embedded.OpenApiImposterBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PreTestPrep implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    public static Discord discord;
    public static DiscordRequestDispatcher requestDispatcher;

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        MockEngine imposter = new OpenApiImposterBuilder<>()
                .withSpecificationFile(Paths.get("mocks/openapi-spec.json"))
                .startBlocking();
        System.setProperty("DISCORD_BASE_URL", imposter.getBaseUrl().toString() + "/api/v10");
        System.out.println(System.getProperty("DISCORD_BASE_URL"));
        discord = new Discord("");
        discord.startWithoutWebsocket();
        requestDispatcher = discord.getDiscordRequestDispatcher();
    }

    @Override
    public void close() {

    }
}
