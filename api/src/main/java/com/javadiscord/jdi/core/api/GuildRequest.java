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

    public AsyncResponse<List<GuildMember>> beginGuildPrune(
            int days, boolean computePruneCount, List<Long> includeRoles) {
        return responseParser.callAndParseList(
                GuildMember.class,
                new BeginGuildPruneRequest(guildId, days, computePruneCount, includeRoles));
    }

    public AsyncResponse<Void> bulkGuildBan(BulkGuildBanBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<Void> createGuildBan(GuildBanBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<Channel> createGuildChannel(CreateGuildChannelBuilder builder) {
        return responseParser.callAndParse(Channel.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<Guild> createGuild(CreateGuildBuilder builder) {
        return responseParser.callAndParse(Guild.class, builder.build());
    }

    public AsyncResponse<Role> createGuildRole(CreateGuildRoleBuilder builder) {
        return responseParser.callAndParse(Role.class, builder.guildId(guildId).build());
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

    public AsyncResponse<GuildMember> guildMember(long userId) {
        return responseParser.callAndParse(
                GuildMember.class, new GetGuildMemberRequest(guildId, userId));
    }

    public AsyncResponse<Onboarding> guildOnboarding() {
        return responseParser.callAndParse(
                Onboarding.class, new GetGuildOnboardingRequest(guildId));
    }

    public AsyncResponse<Guild> guildPreview() {
        return responseParser.callAndParse(Guild.class, new GetGuildPreviewRequest(guildId));
    }

    public AsyncResponse<PruneCount> guildPruneCount(GetGuildPruneCountBuilder builder) {
        return responseParser.callAndParse(PruneCount.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<Guild> guild(GetGuildBuilder builder) {
        return responseParser.callAndParse(Guild.class, builder.guildId(guildId).build());
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

    public AsyncResponse<WidgetObject> guildWidgetImage(GetGuildWidgetImageBuilder builder) {
        return responseParser.callAndParse(WidgetObject.class, builder.guildId(guildId).build());
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

    public AsyncResponse<List<GuildMember>> listGuildMembers(ListGuildMembersBuilder builder) {
        return responseParser.callAndParseList(GuildMember.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<GuildMember> modifyCurrentMember(ModifyCurrentMemberBuilder builder) {
        return responseParser.callAndParse(GuildMember.class, builder.guildId(guildId).build());
    }

    @Deprecated
    public AsyncResponse<User> modifyCurrentUserNick(ModifyCurrentUserNickBuilder builder) {
        return responseParser.callAndParse(User.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<VoiceState> modifyCurrentUserVoiceState(
            ModifyCurrentUserVoiceStateBuilder builder) {
        return responseParser.callAndParse(VoiceState.class, builder.guildId(guildId).build());
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

    public AsyncResponse<GuildMember> modifyGuildMember(ModifyGuildMemberBuilder builder) {
        return responseParser.callAndParse(GuildMember.class, builder.guildId(guildId).build());
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

    public AsyncResponse<Guild> modifyGuild(ModifyGuildBuilder builder) {
        return responseParser.callAndParse(Guild.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<List<Role>> modifyGuildRolePositions(
            List<ModifyGuildRolePositionsRequest.ModifyGuildRolePositionData> roles) {
        return responseParser.callAndParseList(
                Role.class, new ModifyGuildRolePositionsRequest(guildId, roles));
    }

    public AsyncResponse<Role> modifyGuildRole(ModifyGuildRoleBuilder builder) {
        return responseParser.callAndParse(Role.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<WelcomeScreen> modifyGuildWelcomeScreen(
            ModifyGuildWelcomeScreenBuilder builder) {
        return responseParser.callAndParse(WelcomeScreen.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<WidgetObject> modifyGuildWidget(ModifyGuildWidgetBuilder builder) {
        return responseParser.callAndParse(WidgetObject.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<VoiceState> modifyUserVoiceState(ModifyUserVoiceStateBuilder builder) {
        return responseParser.callAndParse(VoiceState.class, builder.guildId(guildId).build());
    }

    public AsyncResponse<Guild> removeGuildBan(long userId) {
        return responseParser.callAndParse(Guild.class, new RemoveGuildBanRequest(guildId, userId));
    }

    public AsyncResponse<GuildMember> removeGuildMemberRole(long userId, long roleId) {
        return responseParser.callAndParse(
                GuildMember.class, new RemoveGuildMemberRoleRequest(guildId, userId, roleId));
    }

    public AsyncResponse<List<GuildMember>> searchGuildMembers(SearchGuildMembersBuilder builder) {
        return responseParser.callAndParseList(GuildMember.class, builder.guildId(guildId).build());
    }
}
