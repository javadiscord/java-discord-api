package com.javadiscord.jdi.internal.api;

import java.util.List;
import java.util.Map;

public record DiscordResponse(String body, int status, Map<String, List<String>> headers) {
}
