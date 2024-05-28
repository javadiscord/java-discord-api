package com.javadiscord.jdi.core.processor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.Guild;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.message.Message;

import com.javadiscord.jdi.core.processor.validator.EventListenerValidator;
import org.junit.jupiter.api.Test;

class EventListenerValidatorTest {

    @Test
    void testValidationFailsWhenZeroArgConstructorDoesNotExist() {
        class Test {}

        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertFalse(eventListenerValidator.validate(Test.class));
    }

    public static class ClassWithConstructor {
        public ClassWithConstructor() {}
    }

    @Test
    void testValidationPassesWhenZeroArgConstructorExists() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertTrue(eventListenerValidator.validate(ClassWithConstructor.class));
    }

    @EventListener
    public static class ClassWithEventListenerAndMessageCreate {
        public ClassWithEventListenerAndMessageCreate() {}

        @MessageCreate
        public void dummy() {}
    }

    @Test
    void testValidationPassWhenMethodAnnotatedWithNoParameters() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertTrue(eventListenerValidator.validate(ClassWithEventListenerAndMessageCreate.class));
    }

    @EventListener
    public static class ClassWithMethodParameterAsModel {
        public ClassWithMethodParameterAsModel() {}

        @MessageCreate
        public void dummy(Message ignore) {}
    }

    @Test
    void testValidationPassWhenMethodAnnotatedWithOneParameter() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertTrue(eventListenerValidator.validate(ClassWithMethodParameterAsModel.class));
    }

    @EventListener
    public static class ClassWithMethodParameterDiscord {
        public ClassWithMethodParameterDiscord() {}

        @MessageCreate
        public void dummy(Message ignore, Discord ignore1) {}
    }

    @Test
    void testValidationPassWhenMethodAnnotatedWithDiscordParameter() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertTrue(eventListenerValidator.validate(ClassWithMethodParameterDiscord.class));
    }

    @EventListener
    public static class ClassWithVarietyOfSuccessMethods {
        public ClassWithVarietyOfSuccessMethods() {}

        @MessageCreate
        public void dummy(Message ignore, Discord ignore1) {}

        @MessageCreate
        public void dummy(Discord ignore) {}

        @MessageCreate
        public void dummy(Guild ignore) {}

        @MessageCreate
        public void dummy(Guild ignore, Guild ignore1) {}

        @MessageCreate
        public void dummy(Message ignore, Discord ignore2, Guild ignore3) {}
    }

    @Test
    void testValidationPassWhenMethodAnnotatedWithValidParameters() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertTrue(eventListenerValidator.validate(ClassWithVarietyOfSuccessMethods.class));
    }

    @EventListener
    public static class ClassWithInvalidParameter {
        public ClassWithInvalidParameter() {}

        @MessageCreate
        public void dummy(Channel ignore) {}
    }

    @Test
    void testValidationFailWhenMethodAnnotatedWithInvalidParameters() {
        EventListenerValidator eventListenerValidator = new EventListenerValidator();
        assertFalse(eventListenerValidator.validate(ClassWithInvalidParameter.class));
    }
}
