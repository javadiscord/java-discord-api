package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private long id;
    private String username;
    private String discriminator;

    @JsonProperty("global_name")
    private String displayName;

    private String avatar;

    private boolean bot;

    private boolean system;

    @JsonProperty("mfa_enabled")
    private boolean mfaEnabled;

    private String banner;

    @JsonProperty("accent_color")
    private int accentColor;

    private String locale;

    private boolean verified;
    private String email;
    private int flags;

    @JsonProperty("premium_type") // see: PremiumType
    private int premiumType;

    @JsonProperty("public_flags")
    private int publicFlags;
}
