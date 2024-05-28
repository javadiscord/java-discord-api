package com.javadiscord.bot.listeners;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.javadiscord.bot.utils.chatgpt.ChatGPT;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.Inject;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.annotations.ThreadCreate;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.message.embed.EmbedAuthor;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.models.channel.Thread;

@EventListener
public class QuestionListener {
    private final List<Long> channelsThatNeedChatGpt = new ArrayList<>();

    @Inject
    private ChatGPT chatGPT;

    @ThreadCreate
    public void onQuestionCreate(Thread thread, Guild guild) {
        if (thread.newlyCreated() && thread.parentId() == 1245064991275618511L) {

            guild.channel().sendEmbed(
                thread.id(), new Embed.Builder()
                    .description(
                        """
                            # Important
                            Please make sure your question has enough details for a helper to understand the problem.

                            * If you are asking for help with code, please use a code block.
                            * If you are asking for help with an error, please include the full error message.
                            * Screenshots may also be useful. Please do not post screenshots of code, however.
                            """
                    )
                    .image(
                        "https://media.tenor.com/LoNa2zOMxoAAAAAC/its-very-important-it-matters.gif"
                    )
                    .build()
            );

            guild.channel().sendEmbed(
                thread.id(), new Embed.Builder()
                    .description(
                        """
                            Once your question has been answered, please close this thread by doing `/close`.
                            """
                    ).build()
            );

            channelsThatNeedChatGpt.add(thread.id());
        }
    }

    @MessageCreate
    public void sendChatGptAnswer(Message message, Guild guild) {
        if (channelsThatNeedChatGpt.contains(message.id())) {
            channelsThatNeedChatGpt.remove(message.id());

            StringBuilder answer = new StringBuilder();
            answer.append("## Here is an attempted answer by ChatGPT\n\n");

            chatGPT.ask(message.content())
                .ifPresentOrElse(
                    strings -> {
                        for (String string : strings) {
                            answer.append(string).append("\n");
                        }
                    },
                    () -> guild.channel().sendMessage(
                        message.channelId(),
                        "ChatGPT is currently unavailable."
                    )
                );

            Embed embed =
                new Embed.Builder()
                    .author(
                        new EmbedAuthor("", null, "https://chat.openai.com/favicon-32x32.png", null)
                    )
                    .color(Color.CYAN)
                    .description(answer.toString())
                    .build();

            guild.channel().sendEmbed(message.channelId(), embed);
        }
    }
}
