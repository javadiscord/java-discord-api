package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.GetCurrentUserGuildsBuilder;
import com.javadiscord.jdi.core.request.builders.ModifyCurrentUserBuilder;
import com.javadiscord.jdi.core.request.builders.UpdateCurrentUserApplicationRoleConnectionBuilder;
import com.javadiscord.jdi.internal.api.user.*;

import java.util.List;
import java.util.Map;

public class User {

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
