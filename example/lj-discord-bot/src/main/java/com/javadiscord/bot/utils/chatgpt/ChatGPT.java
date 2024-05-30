package com.javadiscord.bot.utils.chatgpt;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatGPT {
    private static final Logger LOGGER = LogManager.getLogger(ChatGPT.class);
    private static final String API_KEY = System.getenv("CHATGPT_API_KEY");
    private static final Duration TIMEOUT = Duration.ofMinutes(3);
    private static final String AI_MODEL = "gpt-3.5-turbo";
    private static final int MAX_TOKENS = 2000;
    private static final double FREQUENCY_PENALTY = 0.5;
    private static final double TEMPERATURE = 0.8;
    private static final int MAX_NUMBER_OF_RESPONSES = 1;

    private final OpenAiService openAiService;

    public ChatGPT() {
        openAiService = new OpenAiService(API_KEY, TIMEOUT);

        ChatMessage setupMessage =
            new ChatMessage(
                ChatMessageRole.SYSTEM.value(),
                """
                    Please answer questions in 2000 characters or less. Remember to count spaces in the
                    character limit. The context is Java Programming:\s"""
            );

        ChatCompletionRequest systemSetupRequest =
            ChatCompletionRequest.builder()
                .model(AI_MODEL)
                .messages(List.of(setupMessage))
                .frequencyPenalty(FREQUENCY_PENALTY)
                .temperature(TEMPERATURE)
                .maxTokens(50)
                .n(MAX_NUMBER_OF_RESPONSES)
                .build();

        openAiService.createChatCompletion(systemSetupRequest);
    }

    public Optional<String[]> ask(String question) {
        try {
            ChatMessage chatMessage =
                new ChatMessage(ChatMessageRole.USER.value(), Objects.requireNonNull(question));

            ChatCompletionRequest chatCompletionRequest =
                ChatCompletionRequest.builder()
                    .model(AI_MODEL)
                    .messages(List.of(chatMessage))
                    .frequencyPenalty(FREQUENCY_PENALTY)
                    .temperature(TEMPERATURE)
                    .maxTokens(MAX_TOKENS)
                    .n(MAX_NUMBER_OF_RESPONSES)
                    .build();

            String response =
                openAiService
                    .createChatCompletion(chatCompletionRequest)
                    .getChoices()
                    .getFirst()
                    .getMessage()
                    .getContent();

            return Optional.of(ChatGPTResponseParser.parse(response));
        } catch (OpenAiHttpException openAiHttpException) {
            LOGGER.warn(
                String.format(
                    "There was an error using the OpenAI API: %s Code: %s Type: %s Status"
                        + " Code: %s",
                    openAiHttpException.getMessage(),
                    openAiHttpException.code,
                    openAiHttpException.type,
                    openAiHttpException.statusCode
                )
            );
        } catch (RuntimeException e) {
            LOGGER.warn(
                "There was an error using the OpenAI API: {}", e.getMessage()
            );
        }
        return Optional.empty();
    }
}
