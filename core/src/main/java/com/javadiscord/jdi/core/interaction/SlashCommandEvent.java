package com.javadiscord.jdi.core.interaction;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.GatewayEventListener;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.Interaction;
import com.javadiscord.jdi.core.models.guild.InteractionData;
import com.javadiscord.jdi.core.models.guild.InteractionType;
import com.javadiscord.jdi.core.models.user.User;

public record SlashCommandEvent(Interaction interaction, Discord discord) {

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

    public Object option(String name) {
        InteractionData interactionData = interaction.data();
        ApplicationCommandOption[] options = interactionData.options();
        for (ApplicationCommandOption option : options) {
            if (option.name().equals(name)) {
                return option.value();
            }
        }
        return null;
    }

    public ApplicationCommandOption[] options() {
        return interaction.data().options();
    }
}
