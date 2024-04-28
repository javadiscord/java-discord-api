package com.javadiscord.jdi.core;

import static org.mockito.Mockito.*;

import com.javadiscord.jdi.core.annotations.ChannelCreate;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.internal.gateway.handlers.events.EventType;

import org.junit.jupiter.api.Test;

import java.util.List;

class GatewayEventListenerTest {

    @EventListener
    public static class TestEventListener {
        @MessageCreate
        public void dummy(Message ignore) {}

        @ChannelCreate
        public void dummy(Channel ignore) {}
    }

    @Test
    void shouldInvokeListenerOnMessageCreate() {
        TestEventListener testEventListener = mock(TestEventListener.class);

        Discord mockDiscord = mock(Discord.class);
        Message mockMessage = mock(Message.class);
        when(mockDiscord.getAnnotatedEventListeners()).thenReturn(List.of(testEventListener));

        GatewayEventListenerAnnotations gatewayEventListener =
                new GatewayEventListenerAnnotations(mockDiscord);
        gatewayEventListener.receive(EventType.MESSAGE_CREATE, mockMessage);

        verify(testEventListener, times(1)).dummy(any(Message.class));
    }

    @Test
    void shouldInvokeListenerOnChannelCreate() {
        TestEventListener testEventListener = mock(TestEventListener.class);

        Discord mockDiscord = mock(Discord.class);
        Channel mockMessage = mock(Channel.class);
        when(mockDiscord.getAnnotatedEventListeners()).thenReturn(List.of(testEventListener));

        GatewayEventListenerAnnotations gatewayEventListener =
                new GatewayEventListenerAnnotations(mockDiscord);
        gatewayEventListener.receive(EventType.CHANNEL_CREATE, mockMessage);

        verify(testEventListener, times(1)).dummy(any(Channel.class));
    }

    @Test
    void shouldNotInvokeListenerOnChannelCreateWhenReceivingMessageCreate() {
        TestEventListener testEventListener = mock(TestEventListener.class);

        Discord mockDiscord = mock(Discord.class);
        Message mockMessage = mock(Message.class);
        when(mockDiscord.getAnnotatedEventListeners()).thenReturn(List.of(testEventListener));

        GatewayEventListenerAnnotations gatewayEventListener =
                new GatewayEventListenerAnnotations(mockDiscord);
        gatewayEventListener.receive(EventType.MESSAGE_CREATE, mockMessage);

        verify(testEventListener, times(0)).dummy(any(Channel.class));
    }
}
