package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.core.models.user.Connection;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.user.*;
import com.javadiscord.jdi.internal.request.builders.GetCurrentUserGuildsBuilder;
import com.javadiscord.jdi.internal.request.builders.ModifyCurrentUserBuilder;
import com.javadiscord.jdi.internal.request.builders.UpdateCurrentUserApplicationRoleConnectionBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;
import java.util.Map;

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

    public AsyncResponse<Connection> getCurrentUserApplicationRoleConnection(long applicationId) {
        return responseParser.callAndParse(
                Connection.class,
                new GetCurrentUserApplicationRoleConnectionRequest(applicationId));
    }

    public AsyncResponse<Connection> getCurrentUserConnections() {
        return responseParser.callAndParse(
                Connection.class, new GetCurrentUserConnectionsRequest());
    }

    public AsyncResponse<Member> getCurrentUserGuildMember(long guildId) {
        return responseParser.callAndParse(
                Member.class, new GetCurrentUserGuildMemberRequest(guildId));
    }

    public AsyncResponse<List<Guild>> getCurrentUserGuilds(GetCurrentUserGuildsBuilder builder) {
        return responseParser.callAndParseList(Guild.class, builder.build());
    }

    public AsyncResponse<User> getCurrentUser() {
        return responseParser.callAndParse(User.class, new GetCurrentUserRequest());
    }

    public AsyncResponse<User> getUser(long userId) {
        return responseParser.callAndParse(User.class, new GetUserRequest(userId));
    }

    public AsyncResponse<Void> leaveGuild(long guildId) {
        return responseParser.callAndParse(Void.class, new LeaveGuildRequest(guildId));
    }

    public AsyncResponse<User> modifyCurrentUser(ModifyCurrentUserBuilder builder) {
        return responseParser.callAndParse(User.class, builder.build());
    }

    public AsyncResponse<Connection> updateCurrentUserApplicationRoleConnection(
            UpdateCurrentUserApplicationRoleConnectionBuilder builder) {
        return responseParser.callAndParse(Connection.class, builder.build());
    }
}
