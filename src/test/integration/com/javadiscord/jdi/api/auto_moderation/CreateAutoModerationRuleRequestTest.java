package com.javadiscord.jdi.api.auto_moderation;

import java.util.List;
import java.util.Optional;

import helpers.RestTestHelper;
import com.javadiscord.jdi.internal.api.auto_moderation.CreateAutoModerationRuleRequest;

import org.junit.jupiter.api.Test;

class CreateAutoModerationRuleRequestTest extends RestTestHelper {

    @Test
    void test() throws InterruptedException {
        sendRequestAndAssert(
            new CreateAutoModerationRuleRequest(
                0L,
                "",
                0,
                0,
                Optional.empty(),
                List.of(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()
            ), 200
        );
    }
}
