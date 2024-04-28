package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.invite.DeleteInviteRequest;
import com.javadiscord.jdi.internal.request.builders.GetInviteRequestBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class InviteRequest {
    private final DiscordResponseParser responseParser;

    public InviteRequest(DiscordRequestDispatcher discordRequestDispatcher) {
        this.responseParser = new DiscordResponseParser(discordRequestDispatcher);
    }

    public AsyncResponse<Invite> deleteInvite(String inviteCode) {
        return responseParser.callAndParse(new DeleteInviteRequest(inviteCode));
    }

    public AsyncResponse<Invite> getInvite(GetInviteRequestBuilder builder) {
        return responseParser.callAndParse(builder.build());
    }
}
