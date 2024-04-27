package com.javadiscord.jdi.internal.models.auto_moderation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutoModerationTriggerMetadata(
        @JsonProperty("keyword_filter") List<String> keywordFilter,
        @JsonProperty("regex_patterns") List<String> regexPatterns,
        @JsonProperty("presets") List<Integer> presets, // AutoModerationKeywordPresetType
        @JsonProperty("allow_list") List<String> allowList,
        @JsonProperty("mention_total_limit") int totalMentionLimit,
        @JsonProperty("mention_raid_protection_enabled") boolean mentionRaidProtectionEnabled) {}
