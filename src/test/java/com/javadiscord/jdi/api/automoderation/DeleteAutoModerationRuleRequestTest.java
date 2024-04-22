package com.javadiscord.jdi.api.automoderation;

import com.javadiscord.jdi.PreTestPrep;
import com.javadiscord.jdi.api.RestAPITest;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.impl.automoderation.DeleteAutoModerationRuleRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

class DeleteAutoModerationRuleRequestTest extends RestAPITest {

    @Test
    void test() throws InterruptedException {
        testRequest(new DeleteAutoModerationRuleRequest(
                0L,
                0L
        ), 200);

    }
}