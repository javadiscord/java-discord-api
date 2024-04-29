package com.javadiscord.jdi.core;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.request.*;
import com.javadiscord.jdi.internal.request.builders.CreateMessageBuilder;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class Guild {
    private final com.javadiscord.jdi.core.models.guild.Guild metadata;
    private final Cache cache;
    private final Discord discord;
    private final ApplicationRequest applicationRequest;
    private final ApplicationRoleConnectionMetaRequest applicationRoleConnectionMetaRequest;
    private final AuditLogsRequest auditLogsRequest;
    private final AutoModerationRequest autoModerationRequest;
    private final ChannelRequest channelRequest;
    private final EmojiRequest emojiRequest;
    private final GuildRequest guildRequest;
    private final GuildScheduledEventRequest guildScheduledEventRequest;
    private final InviteRequest inviteRequest;
    private final PollRequest pollRequest;
    private final StageRequest stageRequest;
    private final StickerRequest stickerRequest;
    private final UserRequest userRequest;
    private final VoiceRequest voiceRequest;

    public Guild(com.javadiscord.jdi.core.models.guild.Guild guild, Cache cache, Discord discord) {
        this.metadata = guild;
        this.cache = cache;
        this.discord = discord;

        DiscordResponseParser discordResponseParser =
                new DiscordResponseParser(discord.getDiscordRequestDispatcher());

        this.applicationRequest = new ApplicationRequest(discordResponseParser);
        this.applicationRoleConnectionMetaRequest =
                new ApplicationRoleConnectionMetaRequest(discordResponseParser);
        this.auditLogsRequest = new AuditLogsRequest(discordResponseParser);
        this.autoModerationRequest = new AutoModerationRequest(discordResponseParser);
        this.channelRequest = new ChannelRequest(discordResponseParser);
        this.emojiRequest = new EmojiRequest(discordResponseParser);
        this.guildRequest = new GuildRequest(discordResponseParser);
        this.guildScheduledEventRequest = new GuildScheduledEventRequest(discordResponseParser);
        this.inviteRequest = new InviteRequest(discordResponseParser);
        this.pollRequest = new PollRequest(discordResponseParser);
        this.stageRequest = new StageRequest(discordResponseParser);
        this.stickerRequest = new StickerRequest(discordResponseParser);
        this.userRequest = new UserRequest(discordResponseParser);
        this.voiceRequest = new VoiceRequest(discordResponseParser);
    }

    public ChannelRequest channel() {
        return channelRequest;
    }

    public AuditLogsRequest auditLogs() {
        return auditLogsRequest;
    }

    public void sendMessage(CreateMessageBuilder builder) {
        channelRequest.createMessage(builder);
    }

    public com.javadiscord.jdi.core.models.guild.Guild getMetadata() {
        return metadata;
    }

    public Cache getCache() {
        return cache;
    }

    public ApplicationRequest application() {
        return applicationRequest;
    }

    public ApplicationRoleConnectionMetaRequest applicationRoleConnectionMeta() {
        return applicationRoleConnectionMetaRequest;
    }

    public AutoModerationRequest autoModeration() {
        return autoModerationRequest;
    }

    public EmojiRequest emoji() {
        return emojiRequest;
    }

    public GuildRequest guild() {
        return guildRequest;
    }

    public GuildScheduledEventRequest scheduledEvent() {
        return guildScheduledEventRequest;
    }

    public InviteRequest invite() {
        return inviteRequest;
    }

    public PollRequest poll() {
        return pollRequest;
    }

    public StageRequest stage() {
        return stageRequest;
    }

    public StickerRequest sticker() {
        return stickerRequest;
    }

    public UserRequest user() {
        return userRequest;
    }

    public VoiceRequest voice() {
        return voiceRequest;
    }
}
