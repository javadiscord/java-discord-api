package com.javadiscord.api;

import java.util.List;
import java.util.Map;

public record HTTPResponse(String body, int statusCode, Map<String, List<String>> headers) {}
