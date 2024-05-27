package com.javadiscord.jdi.core.interaction;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.GatewayEventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.api.AsyncResponse;
import com.javadiscord.jdi.core.api.builders.command.CallbackMessage;
import com.javadiscord.jdi.core.api.builders.command.CallbackMessageBuilder;
import com.javadiscord.jdi.core.api.builders.command.CallbackResponseType;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.Interaction;
import com.javadiscord.jdi.core.models.guild.InteractionData;
import com.javadiscord.jdi.core.models.guild.InteractionType;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SlashCommandEvent {
    private static final Logger LOGGER = LogManager.getLogger(SlashCommandEvent.class);
    private final Interaction interaction;
    private final Discord discord;
    private boolean deferred;

    public SlashCommandEvent(Interaction interaction, Discord discord) {
        this.interaction = interaction;
        this.discord = discord;
        this.deferred = false;
    }

    public void deferReply() {
        CallbackMessageBuilder builder =
            new CallbackMessageBuilder(
                CallbackResponseType.DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE,
                interaction.id(),
                interaction.token()
            );

        DiscordResponseFuture future = discord.sendRequest(builder.build());

        future.onError(err -> LOGGER.error("Failed to defer response", err));
        deferred = true;
    }

    public void deferReplyWithoutSpinner() {
        CallbackMessageBuilder builder =
            new CallbackMessageBuilder(
                CallbackResponseType.DEFERRED_UPDATE_MESSAGE,
                interaction.id(),
                interaction.token()
            );

        DiscordResponseFuture future = discord.sendRequest(builder.build());

        future.onError(err -> LOGGER.error("Failed to defer response", err));
        deferred = true;
    }

    public AsyncResponse<String> reply(CallbackMessageBuilder builder) {
        return sendReply(builder);
    }

    public AsyncResponse<String> reply(Embed... embed) {
        return sendReply(
            new CallbackMessageBuilder(
                CallbackResponseType.CHANNEL_MESSAGE_WITH_SOURCE,
                interaction.id(),
                interaction.token()
            )
                .message(new CallbackMessage().setEmbeds(List.of(embed)))
        );
    }

    public AsyncResponse<String> reply(String message) {
        return sendReply(
            new CallbackMessageBuilder(
                CallbackResponseType.CHANNEL_MESSAGE_WITH_SOURCE,
                interaction.id(),
                interaction.token()
            )
                .message(new CallbackMessage().setContent(message))
        );
    }

    private AsyncResponse<String> sendReply(CallbackMessageBuilder builder) {
        AsyncResponse<String> asyncResponse = new AsyncResponse<>();
        DiscordResponseFuture future;

        if (deferred) {
            builder.applicationId(discord.getApplicationId());
            future = discord.sendRequest(builder.buildEdit());
        } else {
            future = discord.sendRequest(builder.build());
        }

        future.onSuccess(res -> {
            if (res.status() >= 200 && res.status() < 300) {
                asyncResponse.setResult(res.body());
            } else {
                asyncResponse.setException(
                    new Exception("Received " + res.status() + "\n" + res.body())
                );
            }
        });
        future.onError(asyncResponse::setException);

        return asyncResponse;
    }

    public Channel channel() {
        return interaction.channel();
    }

    public Guild guild() {
        return GatewayEventListener.getGuild(discord, interaction.guild());
    }

    public User user() {
        return interaction.user();
    }

    public InteractionType interactionType() {
        return interaction.type();
    }

    public InteractionData interactionData() {
        return interaction.data();
    }

    public Optional<ApplicationCommandOption> option(String name) {
        InteractionData interactionData = interaction.data();
        ApplicationCommandOption[] options = interactionData.options();
        for (ApplicationCommandOption option : options) {
            if (option.name().equals(name)) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }

    public ApplicationCommandOption[] options() {
        return interaction.data().options();
    }
}
