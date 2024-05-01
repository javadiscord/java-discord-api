package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.GetInviteRequestBuilder;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.internal.api.invite.DeleteInviteRequest;

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
