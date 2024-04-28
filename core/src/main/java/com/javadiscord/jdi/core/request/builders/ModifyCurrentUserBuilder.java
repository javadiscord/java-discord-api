package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.user.ModifyCurrentUserRequest;

import java.util.Optional;

public class ModifyCurrentUserBuilder {
    private final String username;
    private final String type;
    private Optional<String> avatarImage;

    public ModifyCurrentUserBuilder(String username, String type) {
        this.username = username;
        this.type = type;
        avatarImage = Optional.empty();
    }

    public ModifyCurrentUserBuilder avatarImage(String avatarImage) {
        this.avatarImage = Optional.of(avatarImage);
        return this;
    }

    public ModifyCurrentUserRequest build() {
        return new ModifyCurrentUserRequest(username, avatarImage, type);
    }
}
