package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.user.*;
import com.javadiscord.jdi.internal.request.builders.GetCurrentUserGuildsBuilder;
import com.javadiscord.jdi.internal.request.builders.ModifyCurrentUserBuilder;
import com.javadiscord.jdi.internal.request.builders.UpdateCurrentUserApplicationRoleConnectionBuilder;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;
import java.util.Map;

// TODO:
public class UserRequest {
    private final DiscordResponseParser responseParser;

    public UserRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public CreateDMRequest createDM(long recipientId) {
        return new CreateDMRequest(recipientId);
    }

    public CreateGroupDMRequest createGroupDM(List<String> accessTokens, Map<Long, String> nicks) {
        return new CreateGroupDMRequest(accessTokens, nicks);
    }

    public GetCurrentUserApplicationRoleConnectionRequest getCurrentUserApplicationRoleConnection(
            long applicationId) {
        return new GetCurrentUserApplicationRoleConnectionRequest(applicationId);
    }

    public GetCurrentUserConnectionsRequest getCurrentUserConnections() {
        return new GetCurrentUserConnectionsRequest();
    }

    public GetCurrentUserGuildMemberRequest getCurrentUserGuildMember(long guildId) {
        return new GetCurrentUserGuildMemberRequest(guildId);
    }

    public GetCurrentUserGuildsRequest getCurrentUserGuilds(GetCurrentUserGuildsBuilder builder) {
        return builder.build();
    }

    public GetCurrentUserRequest getCurrentUser() {
        return new GetCurrentUserRequest();
    }

    public GetUserRequest getUser(long userId) {
        return new GetUserRequest(userId);
    }

    public LeaveGuildRequest leaveGuild(long guildId) {
        return new LeaveGuildRequest(guildId);
    }

    public ModifyCurrentUserRequest modifyCurrentUser(ModifyCurrentUserBuilder builder) {
        return builder.build();
    }

    public UpdateCurrentUserApplicationRoleConnectionRequest
            updateCurrentUserApplicationRoleConnection(
                    UpdateCurrentUserApplicationRoleConnectionBuilder builder) {
        return builder.build();
    }
}
