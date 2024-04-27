package com.javadiscord.jdi.internal.channel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javadiscord.jdi.internal.models.channel.ThreadMember;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

class ThreadMemberTest {
    private static final ObjectMapper OBJECT_MAPPER =
            JsonMapper.builder().addModule(new JavaTimeModule()).build();

    @Test
    void testDecodingThreadMember() {
        String input =
                """
                {
                  "id": 1,
                  "user_id": 10,
                  "join_timestamp": "2024-04-25T21:37:44Z",
                  "flags": 0
                }
                """;
        try {
            ThreadMember threadMember = OBJECT_MAPPER.readValue(input, ThreadMember.class);
            assertEquals(1, threadMember.threadId());
            assertEquals(10, threadMember.userId());
            assertEquals(OffsetDateTime.parse("2024-04-25T21:37:44Z"), threadMember.joinTime());
            assertEquals(0, threadMember.flags());
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }
}
