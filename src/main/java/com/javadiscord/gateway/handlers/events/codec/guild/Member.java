package com.javadiscord.gateway.handlers.events.codec.guild;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record Member(
        User user,
        String[] roles,
        @JsonProperty("joined_at")
                @JsonFormat(
                        shape = JsonFormat.Shape.STRING,
                        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")
                Date joinDate) {}
