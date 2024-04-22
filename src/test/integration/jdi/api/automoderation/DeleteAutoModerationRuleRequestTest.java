package jdi.api.automoderation;

import jdi.api.RestTestHelper;
import com.javadiscord.jdi.internal.api.impl.automoderation.DeleteAutoModerationRuleRequest;

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
