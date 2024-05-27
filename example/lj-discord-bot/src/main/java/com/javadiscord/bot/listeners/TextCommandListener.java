package com.javadiscord.bot.listeners;

import java.awt.*;

import com.javadiscord.bot.commands.text.TextCommand;
import com.javadiscord.bot.commands.text.TextCommandRepository;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.message.embed.EmbedAuthor;

@EventListener
public class TextCommandListener {

    @MessageCreate
    public void handleTextCommand(Message message, Guild guild) {
        if (!message.author().bot()) {
            String msg = message.content();
            if (msg.startsWith("!")) {
                String cmd = msg.split(" ")[0].replace("!", "").trim();
                String input = msg.replace(String.format("!%s", cmd), "").trim();
                TextCommand command = TextCommandRepository.get(cmd);
                if (command != null) {
                    command.handle(guild, message, input);
                    guild.channel().deleteMessage(message.channelId(), message.id());
                } else {
                    System.err.println("Command not found.");
                }
            }
        }
    }

    public static Embed create(String title, String caption, String imageURL) {
        Embed.Builder eb = new Embed.Builder();
        if (!imageURL.isEmpty()) {
            eb.image(imageURL);
        }
        eb.color(Color.RED);
        eb.author(new EmbedAuthor(title, null, null, null));
        eb.description(caption);
        return eb.build();
    }
}
