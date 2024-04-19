package com.javadiscord.jdi.internal.models.auditlog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuditLogEntry(
        @JsonProperty("target_id") String targetId,
        @JsonProperty("changes") List<AuditLogChange> auditLogChanges,
        @JsonProperty("user_id") long userId,
        @JsonProperty("id") long id,
        @JsonProperty("action_type") AuditLogEvent actionType,
        @JsonProperty("options") OptionalAuditEntryInfo auditEntryInfo,
        @JsonProperty("reason") String reason
        ) {}