package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.*;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.*;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.core.models.voice.VoiceRegion;
import com.javadiscord.jdi.core.models.voice.VoiceState;
import com.javadiscord.jdi.internal.api.guild.*;

import java.util.List;

public class GuildRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public GuildRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Void> addGuildMemberRole(long userId, long roleId) {
        return responseParser.callAndParse(
                Void.class, new AddGuildMemberRoleRequest(guildId, userId, roleId));
    }

    public AsyncResponse<List<Member>> beginGuildPrune(
            int days, boolean computePruneCount, List<Long> includeRoles) {
        return responseParser.callAndParseList(
                Member.class,
                new BeginGuildPruneRequest(guildId, days, computePruneCount, includeRoles));
    }

    public AsyncResponse<Void> bulkGuildBan(BulkGuildBanBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<Void> createGuildBan(GuildBanBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<Channel> createGuildChannel(CreateGuildChannelBuilder builder) {
        return responseParser.callAndParse(Channel.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<Guild> createGuild(CreateGuildBuilder builder) {
        return responseParser.callAndParse(Guild.class, builder.build());
    }

    public AsyncResponse<Role> createGuildRole(CreateGuildRoleBuilder builder) {
        return responseParser.callAndParse(Role.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<Void> deleteGuildIntegration(long integrationId) {
        return responseParser.callAndParse(
                Void.class, new DeleteGuildIntegrationRequest(guildId, integrationId));
    }

    public AsyncResponse<Guild> deleteGuild() {
        return responseParser.callAndParse(Guild.class, new DeleteGuildRequest(guildId));
    }

    public AsyncResponse<Role> deleteGuildRole(long roleId) {
        return responseParser.callAndParse(Role.class, new DeleteGuildRoleRequest(guildId, roleId));
    }

    public AsyncResponse<GuildBan> guildBan(long userId) {
        return responseParser.callAndParse(GuildBan.class, new GetGuildBanRequest(guildId, userId));
    }

    public AsyncResponse<List<GuildBan>> guildBans(GetGuildBansBuilder builder) {
        return responseParser.callAndParseList(GuildBan.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<List<Channel>> guildChannels() {
        return responseParser.callAndParseList(Channel.class, new GetGuildChannelsRequest(guildId));
    }

    public AsyncResponse<Integration> guildIntegrations() {
        return responseParser.callAndParse(
                Integration.class, new GetGuildIntegrationsRequest(guildId));
    }

    public AsyncResponse<Invite> guildInvites() {
        return responseParser.callAndParse(Invite.class, new GetGuildInvitesRequest(guildId));
    }

    public AsyncResponse<Member> guildMember(long userId) {
        return responseParser.callAndParse(
                Member.class, new GetGuildMemberRequest(guildId, userId));
    }

    public AsyncResponse<Onboarding> guildOnboarding() {
        return responseParser.callAndParse(
                Onboarding.class, new GetGuildOnboardingRequest(guildId));
    }

    public AsyncResponse<Guild> guildPreview() {
        return responseParser.callAndParse(Guild.class, new GetGuildPreviewRequest(guildId));
    }

    // TODO:
    public AsyncResponse<PruneCount> guildPruneCount() {
        return responseParser.callAndParse(PruneCount.class, null);
    }

    // TODO:
    public AsyncResponse<Guild> guild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<List<Role>> guildRoles() {
        return responseParser.callAndParseList(Role.class, new GetGuildRolesRequest(guildId));
    }

    public AsyncResponse<Invite> guildVanityURL() {
        return responseParser.callAndParse(Invite.class, new GetGuildVanityURLRequest(guildId));
    }

    public AsyncResponse<VoiceRegion> guildVoiceRegions() {
        return responseParser.callAndParse(
                VoiceRegion.class, new GetGuildVoiceRegionsRequest(guildId));
    }

    public AsyncResponse<WelcomeScreen> guildWelcomeScreen() {
        return responseParser.callAndParse(
                WelcomeScreen.class, new GetGuildWelcomeScreenRequest(guildId));
    }

    // TODO:
    public AsyncResponse<WidgetObject> guildWidgetImage() {
        return responseParser.callAndParse(WidgetObject.class, null);
    }

    public AsyncResponse<byte[]> guildWidget() {
        return responseParser.callAndParse(byte[].class, new GetGuildWidgetRequest(guildId));
    }

    public AsyncResponse<WidgetSettingsObject> guildWidgetSettings() {
        return responseParser.callAndParse(
                WidgetSettingsObject.class, new GetGuildWidgetSettingsRequest(guildId));
    }

    public AsyncResponse<List<Channel>> listActiveGuildThreads() {
        return responseParser.callAndParseList(
                Channel.class, new ListActiveGuildThreadsRequest(guildId));
    }

    // TODO:
    public AsyncResponse<List<Member>> listGuildMembers() {
        return responseParser.callAndParseList(Member.class, null);
    }

    // TODO:
    public AsyncResponse<Member> modifyCurrentMember() {
        return responseParser.callAndParse(Member.class, null);
    }

    // TODO:
    public AsyncResponse<User> modifyCurrentUserNick() {
        return responseParser.callAndParse(User.class, null);
    }

    // TODO:
    public AsyncResponse<VoiceState> modifyCurrentUserVoiceState() {
        return responseParser.callAndParse(VoiceState.class, null);
    }

    public AsyncResponse<Channel> modifyGuildChannelPositions(
            List<ModifyGuildChannelPositionsRequest.ModifyChannelPositionData> channels) {
        return responseParser.callAndParse(
                Channel.class, new ModifyGuildChannelPositionsRequest(guildId, channels));
    }

    public AsyncResponse<MFALevel> modifyGuildMFALevel(MFALevel level) {
        return responseParser.callAndParse(
                MFALevel.class, new ModifyGuildMFALevelRequest(guildId, level));
    }

    // TODO:
    public AsyncResponse<Member> modifyGuildMember() {
        return responseParser.callAndParse(Member.class, null);
    }

    public AsyncResponse<Onboarding> modifyGuildOnboarding(
            List<OnboardingPrompt> prompts,
            List<Long> defaultChannelIds,
            boolean enabled,
            OnboardingMode mode) {
        return responseParser.callAndParse(
                Onboarding.class,
                new ModifyGuildOnboardingRequest(
                        guildId, prompts, defaultChannelIds, enabled, mode));
    }

    // TODO:
    public AsyncResponse<Guild> modifyGuild() {
        return responseParser.callAndParse(Guild.class, null);
    }

    public AsyncResponse<List<Role>> modifyGuildRolePositions(
            List<ModifyGuildRolePositionsRequest.ModifyGuildRolePositionData> roles) {
        return responseParser.callAndParseList(
                Role.class, new ModifyGuildRolePositionsRequest(guildId, roles));
    }

    // TODO:
    public AsyncResponse<Role> modifyGuildRole() {
        return responseParser.callAndParse(Role.class, null);
    }

    // TODO:
    public AsyncResponse<WelcomeScreen> modifyGuildWelcomeScreen() {
        return responseParser.callAndParse(WelcomeScreen.class, null);
    }

    // TODO:
    public AsyncResponse<WidgetObject> modifyGuildWidget() {
        return responseParser.callAndParse(WidgetObject.class, null);
    }

    // TODO:
    public AsyncResponse<VoiceState> modifyUserVoiceState() {
        return responseParser.callAndParse(VoiceState.class, null);
    }

    public AsyncResponse<Guild> removeGuildBan(long userId) {
        return responseParser.callAndParse(Guild.class, new RemoveGuildBanRequest(guildId, userId));
    }

    public AsyncResponse<Member> removeGuildMemberRole(long userId, long roleId) {
        return responseParser.callAndParse(
                Member.class, new RemoveGuildMemberRoleRequest(guildId, userId, roleId));
    }

    // TODO:
    public AsyncResponse<List<Member>> searchGuildMembers() {
        return responseParser.callAndParseList(Member.class, null);
    }
}
