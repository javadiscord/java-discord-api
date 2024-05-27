package com.javadiscord.bot.commands.slash;

import java.awt.*;

import com.javadiscord.bot.utils.chatgpt.ChatGPT;
import com.javadiscord.jdi.core.CommandOptionType;
import com.javadiscord.jdi.core.annotations.CommandOption;
import com.javadiscord.jdi.core.annotations.SlashCommand;
import com.javadiscord.jdi.core.interaction.SlashCommandEvent;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.message.embed.EmbedAuthor;

public class ChatGPTCommand {
    private final ChatGPT chatGPT = new ChatGPT();

    @SlashCommand(
        name = "chatgpt", description = "Ask ChatGPT a question", options = {
            @CommandOption(
                name = "message", description = "What would you like to ask?", type = CommandOptionType.STRING
            )
        }
    )
    public void handle(SlashCommandEvent event) {
        event.deferReply();
        Thread.ofVirtual().start(() -> handleCommand(event));
    }

    private void handleCommand(SlashCommandEvent event) {
        event.option("message").ifPresent(msg -> {
            StringBuilder answer = new StringBuilder();
            answer.append(event.user().asMention());
            answer.append(" asked:\n");
            answer.append(msg.valueAsString());
            answer.append("\n");
            answer.append("───────────────\n");

            chatGPT.ask(msg.valueAsString())
                .ifPresentOrElse(
                    strings -> {
                        for (String string : strings) {
                            answer.append(string).append("\n");
                        }
                    },
                    () -> sendChatGptUnavailableMessage(event)
                );

            Embed embed =
                new Embed.Builder().color(Color.CYAN)
                    .description(answer.toString())
                    .author(
                        new EmbedAuthor("", "https://chat.openai.com/favicon-32x32.png", null, null)
                    ).build();
            event.reply(embed);
        });
    }

    private void sendChatGptUnavailableMessage(SlashCommandEvent event) {
        event.reply("ChatGPT is currently unavailable.");
    }
}
