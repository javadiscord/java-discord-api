package com.javadiscord.bot.listeners;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.javadiscord.bot.utils.Tenor;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.api.builders.CreateMessageBuilder;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.databind.JsonNode;

@EventListener
public class RolePlayMessageListener {

    @MessageCreate
    public void handleRolePlay(Message message, Guild guild) {
        if (containsRolePlayAction(message.content())) {
            String content = message.content();
            String[] split = content.split("-");
            String action = split[1].trim();

            List<User> mentions = message.mentions();

            if (!mentions.isEmpty()) {
                String from = message.author().asMention();
                StringBuilder names = new StringBuilder();
                mentions.forEach(
                    m -> {
                        names.append(m.asMention());
                        names.append(" ");
                    }
                );

                if (names.toString().trim().equals("**")) {
                    return;
                }

                String searchTerm = action.replaceAll(" ", "%20") + "ing%20anime";
                JsonNode json = Tenor.search(searchTerm, 50);

                if (json != null && json.has("results")) {
                    JsonNode results = json.get("results");
                    JsonNode result =
                        results.get(ThreadLocalRandom.current().nextInt(results.size()));
                    JsonNode media = result.get("media").get(0);
                    JsonNode gif = media.get("gif");
                    String url = gif.get("url").asText();

                    Embed embed =
                        new Embed.Builder()
                            .description("**" + from + "** " + action + " **" + names + "**")
                            .image(url)
                            .color(Color.RED)
                            .build();

                    guild.channel().createMessage(
                        new CreateMessageBuilder(message.channelId())
                            .embeds(embed)
                    );
                }
            }

        }
    }

    private static boolean containsRolePlayAction(String message) {
        return message.matches(".*-.*-.*") && message.startsWith("-");
    }
}
