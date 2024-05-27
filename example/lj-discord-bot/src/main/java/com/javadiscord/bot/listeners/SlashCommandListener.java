package com.javadiscord.bot.listeners;

import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.InteractionCreate;
import com.javadiscord.jdi.core.models.guild.Interaction;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EventListener
public class SlashCommandListener {
    private static final Logger LOGGER = LogManager.getLogger(SlashCommandListener.class);

    @InteractionCreate
    public void slashCommandLogger(Interaction interaction) {
        Member member = interaction.member();
        User user = member.user();

        LOGGER.info(
            "{} used /{} in {}", user.displayName(), interaction.data().name(),
            interaction.channel().name()
        );
    }

}
