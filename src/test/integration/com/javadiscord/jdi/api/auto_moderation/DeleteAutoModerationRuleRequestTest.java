package com.javadiscord.jdi.api.auto_moderation;

import helpers.RestTestHelper;
import com.javadiscord.jdi.internal.api.auto_moderation.DeleteAutoModerationRuleRequest;

import org.junit.jupiter.api.Test;

class DeleteAutoModerationRuleRequestTest extends RestTestHelper {

    @Test
    void test() throws InterruptedException {
        sendRequestAndAssert(
            new DeleteAutoModerationRuleRequest(
                0L,
                0L
            ), 204
        );

    }
}
