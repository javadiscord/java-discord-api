package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.GetInviteRequestBuilder;
import com.javadiscord.jdi.internal.api.invite.DeleteInviteRequest;
import com.javadiscord.jdi.internal.api.invite.GetInviteRequest;

public class Invite {

    public DeleteInviteRequest deleteInvite(String inviteCode) {
        return new DeleteInviteRequest(inviteCode);
    }

    public GetInviteRequest getInvite(GetInviteRequestBuilder builder) {
        return builder.build();
    }
}
