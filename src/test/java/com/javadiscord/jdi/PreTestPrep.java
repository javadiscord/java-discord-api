package com.javadiscord.jdi;

import com.javadiscord.jdi.core.Discord;
import io.gatehill.imposter.embedded.MockEngine;
import io.gatehill.imposter.openapi.embedded.OpenApiImposterBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PreTestPrep {

    public static Discord discord;

    @BeforeAll
    public static void preIntegrationSetup() {
        MockEngine imposter = new OpenApiImposterBuilder<>()
                .withSpecificationFile(Paths.get("mocks/openapi-spec.json"))
                .startBlocking();
        System.setProperty("DISCORD_BASE_URL", imposter.getBaseUrl().toString());
        System.out.println(System.getProperty("DISCORD_BASE_URL"));
        discord = new Discord("");
        discord.start();
    }
}
