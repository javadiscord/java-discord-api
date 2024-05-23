package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.user.ModifyCurrentUserRequest;

public class ModifyCurrentUserBuilder {
    private final String username;
    private Optional<String> avatarImage;

    public ModifyCurrentUserBuilder(String username) {
        this.username = username;
        this.avatarImage = Optional.empty();
    }

    public ModifyCurrentUserBuilder avatarImage(String avatarImage) {
        this.avatarImage = Optional.of(avatarImage);
        return this;
    }

    public ModifyCurrentUserRequest build() {
        return new ModifyCurrentUserRequest(username, avatarImage);
    }
}
