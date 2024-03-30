package com.javadiscord.jdi.internal.api.actions;

import com.javadiscord.jdi.internal.api.Future;
import com.javadiscord.jdi.internal.api.HTTPRequest;
import com.javadiscord.jdi.internal.api.HTTPResponse;
import com.javadiscord.jdi.internal.api.RequestRunner;

public class Guild {
    private final RequestRunner requestRunner;

    public Guild(RequestRunner requestRunner) {
        this.requestRunner = requestRunner;
    }

    public Future<HTTPResponse> createChannel(String guildId, String name, String type) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/guilds/%s/channels".formatted(guildId),
                        "{ \"name\": \"%s\", \"type\": \"%s\" }".formatted(name, type)));
    }

    public Future<HTTPResponse> deleteChannel(String channelId) {
        return requestRunner.queue(new HTTPRequest("DELETE", "/channels/%s".formatted(channelId)));
    }

    public Future<HTTPResponse> editChannel(String channelId, String name) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/channels/%s".formatted(channelId),
                        "{ \"name\": \"%s\" }".formatted(name)));
    }

    public Future<HTTPResponse> createInvite(
            String channelId, int maxAge, int maxUses, boolean temporary) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/channels/%s/invites".formatted(channelId),
                        "{ \"max_age\": \"%s\", \"max_uses\": \"%s\", \"temporary\": %b }"
                                .formatted(maxAge, maxUses, temporary)));
    }

    // TODO: banMember
    // TODO: unbanMember
    // TODO: getBanList
    // TODO: kickMember
    // TODO: getBannedMembers

    // TODO: getCurrentUser
    // TODO: modifyCurrentUserNickname
    // TODO: getGuildMembers

    // TODO: acceptInvite
    // TODO: getUserConnections
    // TODO: getBotApplication (requires OAuth)
    // TODO: getGuildIntegrations (requires OAuth)

    // TODO: sendMessageWithEmbed
    // TODO: registerSlashCommand
    // TODO: sendMessageWithButtons

    // TODO: Crosspost Message
    // TODO: Trigger Typing Indicator
    // TODO: Follow Announcement Channel
    // TODO: Group DM Add Recipient
    // TODO: Group DM Remove Recipient
    // TODO: Start Thread from Message
    // TODO: Start Thread without Message
    // TODO: Start Thread in Forum or Media Channel
    // TODO: Join Thread
    // TODO: Add Thread Member
    // TODO: Leave Thread
    // TODO: Remove Thread Member
    // TODO: Get Thread Member
    // TODO: List Thread Members
    // TODO: List Public Archived Threads
    // TODO: List Private Archived Threads
    // TODO: List Joined Private Archived Threads
    // TODO:

    // TODO: getRole
    // TODO: createRole
    // TODO: deleteRole
    // TODO: updateRole
    // TODO: getRolesInGuild
    // TODO: getMemberRoles
    // TODO: assignRoleToMember
    // TODO: removeRoleFromMember

    // TODO: createWebhook
    // TODO: deleteWebhook
    // TODO: updateWebhook
    // TODO: getWebhooks
    // TODO: sendWebhookMessage
    // TODO: getWebhookMessages

    // TODO: getAuditLog
    // TODO: getGuildEmojis

    // TODO: getUsersInGuild
    // TODO: getUserInGuild
    // TODO: getGuild
    // TODO: getChannelsInGuild

    // TODO: createCategory
    // TODO: deleteCategory
}
