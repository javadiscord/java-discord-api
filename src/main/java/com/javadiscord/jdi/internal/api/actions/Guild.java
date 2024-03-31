package com.javadiscord.jdi.internal.api.actions;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponse;
import com.javadiscord.jdi.internal.api.RequestRunner;
import com.javadiscord.jdi.internal.api.Response;
import com.javadiscord.jdi.internal.api.HttpMethod;

public class Guild {
    private final RequestRunner requestRunner;

    public Guild(RequestRunner requestRunner) {
        this.requestRunner = requestRunner;
    }

    public Response<DiscordResponse> createChannel(String guildId, String name, String type) throws JsonProcessingException {
        DiscordRequest createChannelRequest = new DiscordRequest(
            HttpMethod.POST,
            "/guilds/%s/channels".formatted(guildId),
            null,
            Map.of(
                "name", name,
                "type", type
            )
        );

        return requestRunner.queue(createChannelRequest);
    }

    public Response<DiscordResponse> deleteChannel(String channelId) {
        DiscordRequest deleteChannelRequest = new DiscordRequest(
            HttpMethod.DELETE,
            "/channels/%s".formatted(channelId)
        );

        return requestRunner.queue(deleteChannelRequest);
    }

    public Response<DiscordResponse> editChannel(String channelId, String name) throws JsonProcessingException {
        DiscordRequest editChannelRequest = new DiscordRequest(
            HttpMethod.POST,
            "/channels/%s".formatted(channelId),
            null,
            Map.of("name", name)
        );

        return requestRunner.queue(editChannelRequest);
    }

    public Response<DiscordResponse> createInvite(String channelId, int maxAge, int maxUses, boolean temporary) throws JsonProcessingException {
        DiscordRequest createInviteRequest = new DiscordRequest(
            HttpMethod.POST,
            "/channels/%s/invites".formatted(channelId),
            null,
            Map.of(
                "max_age", maxAge,
                "max_uses", maxUses,
                "temporary", temporary
            )
        );

        return requestRunner.queue(createInviteRequest);
    }

    //TODO: banMember
    //TODO: unbanMember
    //TODO: getBanList
    //TODO: kickMember
    //TODO: getBannedMembers

    //TODO: getCurrentUser
    //TODO: modifyCurrentUserNickname
    //TODO: getGuildMembers

    //TODO: acceptInvite
    //TODO: getUserConnections
    //TODO: getBotApplication (requires OAuth)
    //TODO: getGuildIntegrations (requires OAuth)

    //TODO: sendMessageWithEmbed
    //TODO: registerSlashCommand
    //TODO: sendMessageWithButtons


    //TODO: Crosspost Message
    //TODO: Trigger Typing Indicator
    //TODO: Follow Announcement Channel
    //TODO: Group DM Add Recipient
    //TODO: Group DM Remove Recipient
    //TODO: Start Thread from Message
    //TODO: Start Thread without Message
    //TODO: Start Thread in Forum or Media Channel
    //TODO: Join Thread
    //TODO: Add Thread Member
    //TODO: Leave Thread
    //TODO: Remove Thread Member
    //TODO: Get Thread Member
    //TODO: List Thread Members
    //TODO: List Public Archived Threads
    //TODO: List Private Archived Threads
    //TODO: List Joined Private Archived Threads
    //TODO:


    //TODO: getRole
    //TODO: createRole
    //TODO: deleteRole
    //TODO: updateRole
    //TODO: getRolesInGuild
    //TODO: getMemberRoles
    //TODO: assignRoleToMember
    //TODO: removeRoleFromMember

    //TODO: createWebhook
    //TODO: deleteWebhook
    //TODO: updateWebhook
    //TODO: getWebhooks
    //TODO: sendWebhookMessage
    //TODO: getWebhookMessages

    //TODO: getAuditLog
    //TODO: getGuildEmojis

    //TODO: getUsersInGuild
    //TODO: getUserInGuild
    //TODO: getGuild
    //TODO: getChannelsInGuild

    //TODO: createCategory
    //TODO: deleteCategory
}
