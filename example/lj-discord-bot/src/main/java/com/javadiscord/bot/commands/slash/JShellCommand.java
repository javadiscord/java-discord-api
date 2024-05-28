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
                String reply = "Failed to execute the provided code, was it bad?";
                Embed embed =
                    new Embed.Builder()
                        .author(new EmbedAuthor(user.asMention(), user.avatar(), null, null))
                        .description(reply)
                        .color(Color.ORANGE)
                        .build();
                event.reply(embed);
                return;
            }

            if (response.error() != null && !response.error().isEmpty()) {
                String reply =
                    """
                        An error occurred while executing command:

                        ```java
                        %s
                        ```

                        %s
                        """
                        .formatted(msg.valueAsString(), response.error());
                Embed embed =
                    new Embed.Builder()
                        .author(new EmbedAuthor(user.asMention(), user.avatar(), null, null))
                        .description(reply)
                        .color(Color.RED)
                        .build();
                event.reply(embed);
                return;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("## Snippets\n");
            for (JShellSnippet snippet : response.events()) {
                sb.append("`");
                sb.append(snippet.statement());
                sb.append("`\n\n");
                sb.append("**Status**: ");
                sb.append(snippet.status());
                sb.append("\n");

                if (snippet.value() != null && !snippet.value().isEmpty()) {
                    sb.append("**Output**\n");
                    sb.append("```java\n");
                    sb.append(snippet.value());
                    sb.append("```\n");
                }
            }

            if (!response.outputStream().isEmpty()) {
                sb.append("## Console Output\n");
                sb.append("```java\n");
                sb.append(response.outputStream());
                sb.append("```\n");
            }

            if (response.errorStream() != null && !response.errorStream().isEmpty()) {
                sb.append("## Error Output\n");
                sb.append("```java\n");
                sb.append(response.errorStream());
                sb.append("```\n");
            }

            Embed.Builder embed =
                new Embed.Builder()
                    .author(new EmbedAuthor(user.asMention(), null, null, null));

            if (sb.length() > 4000) {
                embed.description(sb.substring(0, 4000));
            } else {
                embed.description(sb.toString());
            }

            embed.color(Color.GREEN);
            embed.footer("Time taken: " + (System.currentTimeMillis() - start) + "ms");
            event.reply(embed.build()).onError(System.err::println);
        });
    }

}
