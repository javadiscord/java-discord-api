package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.internal.api.invite.DeleteInviteRequest;
import com.javadiscord.jdi.internal.request.builders.GetInviteRequestBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class InviteRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public InviteRequest(DiscordResponseParser discordRequestDispatcher, long guildId) {
        this.responseParser = discordRequestDispatcher;
        this.guildId = guildId;
    }

    public AsyncResponse<Invite> deleteInvite(String inviteCode) {
        return responseParser.callAndParse(Invite.class, new DeleteInviteRequest(inviteCode));
    }

    public AsyncResponse<Invite> getInvite(GetInviteRequestBuilder builder) {
        return responseParser.callAndParse(Invite.class, builder.build());
    }
}
