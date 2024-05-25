package com.javadiscord.jdi.core.models.guild;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OnboardingPrompt(
    @JsonProperty("id") long id,
    @JsonProperty("type") OnboardingPromptType type,
    @JsonProperty("options") List<OnboardingPromptOption> options,
    @JsonProperty("title") String title,
    @JsonProperty("single_select") boolean singleSelect,
    @JsonProperty("required") boolean required,
    @JsonProperty("in_onboarding") boolean inOnboarding
) {}
