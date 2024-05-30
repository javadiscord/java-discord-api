package com.javadiscord.bot.commands.slash;

import java.awt.*;

import com.javadiscord.bot.utils.jshell.JShellResponse;
import com.javadiscord.bot.utils.jshell.JShellService;
import com.javadiscord.bot.utils.jshell.JShellSnippet;
import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.Inject;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.message.embed.EmbedAuthor;
import com.javadiscord.jdi.core.models.user.User;

public class JShellCommand {

    @Inject
    private JShellService jShellService;

    @SlashCommand(
        name = "jshell", description = "Run Java code using JShell", options = {
            @CommandOption(
                name = "code", description = "The code you would like to execute", type = CommandOptionType.STRING
            )
        }
    )
    public void handle(SlashCommandEvent event) {
        event.deferReply();
        Thread.ofVirtual().start(() -> handleJShell(event));
    }

    private void handleJShell(SlashCommandEvent event) {
        User user = event.user();
        long start = System.currentTimeMillis();

        event.option("code").ifPresent(msg -> {
            JShellResponse response = jShellService.sendRequest(msg.valueAsString());
            if (response == null) {
                handleNullResponse(event, user);
                return;
            }

            if (response.error() != null && !response.error().isEmpty()) {
                handleErrorResponse(event, user, msg.valueAsString(), response);
                return;
            }

            handleSuccessResponse(event, user, response, start);
        });
    }

    private void handleNullResponse(SlashCommandEvent event, User user) {
        String reply = "Failed to execute the provided code, was it bad?";
        Embed embed =
            new Embed.Builder()
                .author(new EmbedAuthor(user.asMention(), user.avatar(), null, null))
                .description(reply)
                .color(Color.ORANGE)
                .build();
        event.reply(embed);
    }

    private void handleErrorResponse(
        SlashCommandEvent event,
        User user,
        String code,
        JShellResponse response
    ) {
        String reply =
            String.format(
                """
                    An error occurred while executing command:

                    ```java
                    %s
                    ```

                    %s
                    """, code, response.error()
            );
        Embed embed =
            new Embed.Builder()
                .author(new EmbedAuthor(user.asMention(), user.avatar(), null, null))
                .description(reply)
                .color(Color.RED)
                .build();
        event.reply(embed);
    }

    private void handleSuccessResponse(
        SlashCommandEvent event,
        User user,
        JShellResponse response,
        long start
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("## Snippets\n");
        for (JShellSnippet snippet : response.events()) {
            sb.append("`").append(snippet.statement()).append("`\n\n");
            sb.append("**Status**: ").append(snippet.status()).append("\n");

            if (snippet.value() != null && !snippet.value().isEmpty()) {
                sb.append("**Output**\n");
                sb.append("```java\n").append(snippet.value()).append("```\n");
            }
        }

        appendOutputStreams(sb, response);

        Embed.Builder embed =
            new Embed.Builder()
                .author(new EmbedAuthor(user.asMention(), null, null, null))
                .description(sb.length() > 4000 ? sb.substring(0, 4000) : sb.toString())
                .color(Color.GREEN)
                .footer("Time taken: " + (System.currentTimeMillis() - start) + "ms");

        event.reply(embed.build());
    }

    private void appendOutputStreams(StringBuilder sb, JShellResponse response) {
        if (!response.outputStream().isEmpty()) {
            sb.append("## Console Output\n");
            sb.append("```java\n").append(response.outputStream()).append("```\n");
        }

        if (response.errorStream() != null && !response.errorStream().isEmpty()) {
            sb.append("## Error Output\n");
            sb.append("```java\n").append(response.errorStream()).append("```\n");
        }
    }

}
