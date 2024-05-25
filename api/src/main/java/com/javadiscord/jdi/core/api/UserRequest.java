package com.javadiscord.jdi.core.api;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.api.builders.GetCurrentUserGuildsBuilder;
import com.javadiscord.jdi.core.api.builders.ModifyCurrentUserBuilder;
import com.javadiscord.jdi.core.api.builders.UpdateCurrentUserApplicationRoleConnectionBuilder;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.core.models.user.Connection;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.user.*;

public class UserRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public UserRequest(DiscordResponseParser responseParser, long guildId) {
        this.guildId = guildId;
        this.responseParser = responseParser;
    }

    public AsyncResponse<Channel> createDM(long recipientId) {
        return responseParser.callAndParse(Channel.class, new CreateDMRequest(recipientId));
    }

    public AsyncResponse<Channel> createGroupDM(
        List<String> accessTokens,
        Map<Long, String> nicks
    ) {
        return responseParser.callAndParse(
            Channel.class, new CreateGroupDMRequest(accessTokens, nicks)
        );
    }

    public AsyncResponse<Connection> getCurrentUserApplicationRoleConnection(long applicationId) {
        return responseParser.callAndParse(
            Connection.class,
            new GetCurrentUserApplicationRoleConnectionRequest(applicationId)
        );
    }

    public AsyncResponse<Connection> getCurrentUserConnections() {
        return responseParser.callAndParse(
            Connection.class, new GetCurrentUserConnectionsRequest()
        );
    }

    public AsyncResponse<Member> getCurrentUserGuildMember() {
        return responseParser.callAndParse(
            Member.class, new GetCurrentUserGuildMemberRequest(guildId)
        );
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

    public AsyncResponse<Void> leaveGuild() {
        return responseParser.callAndParse(Void.class, new LeaveGuildRequest(guildId));
    }

    public AsyncResponse<User> modifyCurrentUser(ModifyCurrentUserBuilder builder) {
        return responseParser.callAndParse(User.class, builder.build());
    }

    public AsyncResponse<Connection> updateCurrentUserApplicationRoleConnection(
        UpdateCurrentUserApplicationRoleConnectionBuilder builder
    ) {
        return responseParser.callAndParse(Connection.class, builder.build());
    }
}
