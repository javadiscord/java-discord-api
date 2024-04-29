package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.*;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.core.models.voice.VoiceRegion;
import com.javadiscord.jdi.core.models.voice.VoiceState;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class GuildRequest {

    private final DiscordResponseParser responseParser;

    public GuildRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Void> addGuildMemberRole(long guildId, long userId, long roleId) {
        return responseParser.callAndParse(Void.class, null);
    }

    public AsyncResponse<List<Member>> beginGuildPrune() {
        return responseParser.callAndParseList(Member.class, null);
    }

    public AsyncResponse<Void> bulkGuildBan() {
        return responseParser.callAndParse(Void.class, null);
    }

    public AsyncResponse<Void> createGuildBan() {
        return responseParser.callAndParse(Void.class, null);
    }

    public AsyncResponse<Channel> createGuildChannel() {
        return responseParser.callAndParse(Channel.class, null);
    }

    public AsyncResponse<Guild> createGuild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<Role> createGuildRole() {
        return responseParser.callAndParse(Role.class, null);
    }

    public AsyncResponse<Void> deleteGuildIntegration() {
        return responseParser.callAndParse(Void.class, null);
    }

    public AsyncResponse<Guild> deleteGuild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<Role> deleteGuildRole() {
        return responseParser.callAndParse(Role.class, null);
    }

    public AsyncResponse<GuildBan> guildBan() {
        return responseParser.callAndParse(GuildBan.class, null);
    }

    public AsyncResponse<List<GuildBan>> guildBans() {
        return responseParser.callAndParseList(GuildBan.class, null);
    }

    public AsyncResponse<List<Channel>> guildChannels() {
        return responseParser.callAndParseList(Channel.class, null);
    }

    public AsyncResponse<Integration> guildIntegrations() {
        return responseParser.callAndParse(Integration.class, null);
    }

    public AsyncResponse<Invite> guildInvites() {
        return responseParser.callAndParse(Invite.class, null);
    }

    public AsyncResponse<Member> guildMember() {
        return responseParser.callAndParse(Member.class, null);
    }

    public AsyncResponse<Onboarding> guildOnboarding() {
        return responseParser.callAndParse(Onboarding.class, null);
    }

    public AsyncResponse<Guild> guildPreview() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<PruneCount> guildPruneCount() {
        return responseParser.callAndParse(PruneCount.class, null);
    }

    public AsyncResponse<Guild> guild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<List<Role>> guildRoles() {
        return responseParser.callAndParseList(Role.class, null);
    }

    public AsyncResponse<Invite> guildVanityURL() {
        return responseParser.callAndParse(Invite.class, null);
    }

    public AsyncResponse<VoiceRegion> guildVoiceRegions() {
        return responseParser.callAndParse(VoiceRegion.class, null);
    }

    public AsyncResponse<WelcomeScreen> guildWelcomeScreen() {
        return responseParser.callAndParse(WelcomeScreen.class, null);
    }

    public AsyncResponse<WidgetObject> guildWidgetImage() {
        return responseParser.callAndParse(WidgetObject.class, null);
    }

    public AsyncResponse<byte[]> guildWidget() {
        return responseParser.callAndParse(byte[].class, null);
    }

    public AsyncResponse<WidgetSettingsObject> guildWidgetSettings() {
        return responseParser.callAndParse(WidgetSettingsObject.class, null);
    }

    public AsyncResponse<List<Channel>> listActiveGuildThreads() {
        return responseParser.callAndParseList(Channel.class, null);
    }

    public AsyncResponse<List<Member>> listGuildMembers() {
        return responseParser.callAndParseList(Member.class, null);
    }

    public AsyncResponse<Member> modifyCurrentMember() {
        return responseParser.callAndParse(Member.class, null);
    }

    public AsyncResponse<User> modifyCurrentUserNick() {
        return responseParser.callAndParse(User.class, null);
    }

    public AsyncResponse<VoiceState> modifyCurrentUserVoiceState() {
        return responseParser.callAndParse(VoiceState.class, null);
    }

    public AsyncResponse<Channel> modifyGuildChannelPositions() {
        return responseParser.callAndParse(Channel.class, null);
    }

    public AsyncResponse<MFALevel> modifyGuildMFALevel() {
        return responseParser.callAndParse(MFALevel.class, null);
    }

    public AsyncResponse<Member> modifyGuildMember() {
        return responseParser.callAndParse(Member.class, null);
    }

    public AsyncResponse<Onboarding> modifyGuildOnboarding() {
        return responseParser.callAndParse(Onboarding.class, null);
    }

    public AsyncResponse<Guild> modifyGuild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<List<Role>> modifyGuildRolePositions() {
        return responseParser.callAndParseList(Role.class, null);
    }

    public AsyncResponse<Role> modifyGuildRole() {
        return responseParser.callAndParse(Role.class, null);
    }

    public AsyncResponse<WelcomeScreen> modifyGuildWelcomeScreen() {
        return responseParser.callAndParse(WelcomeScreen.class, null);
    }

    public AsyncResponse<WidgetObject> modifyGuildWidget() {
        return responseParser.callAndParse(WidgetObject.class, null);
    }

    public AsyncResponse<VoiceState> modifyUserVoiceState() {
        return responseParser.callAndParse(VoiceState.class, null);
    }

    public AsyncResponse<Guild> removeGuildBan() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<Member> removeGuildMemberRole() {
        return responseParser.callAndParse(Member.class, null);
    }

    public AsyncResponse<List<Member>> searchGuildMembers() {
        return responseParser.callAndParseList(Member.class, null);
    }
}
