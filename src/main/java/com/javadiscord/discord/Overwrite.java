package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Overwrite {
    private long id;
    private int type;
    private String allow;
    private String deny;
}
