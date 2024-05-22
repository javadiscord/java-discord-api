package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.user.ModifyCurrentUserRequest;

public class ModifyCurrentUserBuilder {
    private final String username;
    private final String type;
    private Optional<String> avatarImage;

    public ModifyCurrentUserBuilder(String username, String type) {
        this.username = username;
        this.type = type;
        this.avatarImage = Optional.empty();
    }

    public ModifyCurrentUserBuilder avatarImage(String avatarImage) {
        this.avatarImage = Optional.of(avatarImage);
        return this;
    }

    public ModifyCurrentUserRequest build() {
        return new ModifyCurrentUserRequest(username, avatarImage, type);
    }
}
