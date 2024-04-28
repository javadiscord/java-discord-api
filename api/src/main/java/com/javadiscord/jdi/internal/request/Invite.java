package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.invite.DeleteInviteRequest;
import com.javadiscord.jdi.internal.api.invite.GetInviteRequest;
import com.javadiscord.jdi.internal.request.builders.GetInviteRequestBuilder;

public class Invite {

    public DeleteInviteRequest deleteInvite(String inviteCode) {
        return new DeleteInviteRequest(inviteCode);
    }

    public GetInviteRequest getInvite(GetInviteRequestBuilder builder) {
        return builder.build();
    }
}
