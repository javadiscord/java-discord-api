package com.javadiscord.bot.utils.chatgpt;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatGPTResponseParser {
    private static final Logger LOGGER = LogManager.getLogger(ChatGPTResponseParser.class);
    private static final int RESPONSE_LENGTH_LIMIT = 2000;

    private ChatGPTResponseParser() {}

    public static String[] parse(String response) {
        String[] partedResponse = new String[] {response};
        if (response.length() > RESPONSE_LENGTH_LIMIT) {
            LOGGER.debug("Response to parse:\n{}", response);
            partedResponse = partitionAiResponse(response);
        }
        return partedResponse;
    }

    private static String[] partitionAiResponse(String response) {
        List<String> responseChunks = new ArrayList<>();
        String[] splitResponseOnMarks = response.split("```");
        for (int i = 0; i < splitResponseOnMarks.length; i++) {
            String split = splitResponseOnMarks[i];
            List<String> chunks = new ArrayList<>();
            chunks.add(split);

            while (!chunks.stream().allMatch(s -> s.length() < RESPONSE_LENGTH_LIMIT)) {
                for (int j = 0; j < chunks.size(); j++) {
                    String chunk = chunks.get(j);
                    if (chunk.length() > RESPONSE_LENGTH_LIMIT) {
                        int midpointNewline = chunk.lastIndexOf("\n", chunk.length() / 2);
                        chunks.set(j, chunk.substring(0, midpointNewline));
                        chunks.add(j + 1, chunk.substring(midpointNewline));
                    }
                }
            }

            if (i % 2 != 0) {
                String lang = split.substring(0, split.indexOf(System.lineSeparator()));
                chunks =
                    chunks.stream()
                        .map(s -> ("```" + lang).concat(s).concat("```"))
                        .map(s -> s.replaceFirst("```" + lang + lang, "```" + lang))
                        .toList();
            }

            responseChunks.addAll(filterEmptyStrings(chunks));
        }

        return responseChunks.toArray(new String[0]);
    }

    private static List<String> filterEmptyStrings(List<String> chunks) {
        return chunks.stream().filter(string -> !string.isEmpty()).toList();
    }
}
