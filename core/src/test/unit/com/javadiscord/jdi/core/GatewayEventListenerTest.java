package com.javadiscord.jdi.core;

import static org.mockito.Mockito.*;

import com.javadiscord.jdi.core.annotations.ChannelCreate;
import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.annotations.MessageCreate;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheType;
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
        when(mockDiscord.getEventListeners()).thenReturn(List.of(testEventListener));

        Cache cache = new Cache(CacheType.FULL);
        GatewayEventListener gatewayEventListener = new GatewayEventListener(mockDiscord, cache);
        gatewayEventListener.receive(EventType.MESSAGE_CREATE, mockMessage);

        verify(testEventListener, times(1)).dummy(any(Message.class));
    }

    @Test
    void shouldInvokeListenerOnChannelCreate() {
        TestEventListener testEventListener = mock(TestEventListener.class);

        Discord mockDiscord = mock(Discord.class);
        Channel mockMessage = mock(Channel.class);
        when(mockDiscord.getEventListeners()).thenReturn(List.of(testEventListener));

        Cache cache = new Cache(CacheType.FULL);
        GatewayEventListener gatewayEventListener = new GatewayEventListener(mockDiscord, cache);
        gatewayEventListener.receive(EventType.CHANNEL_CREATE, mockMessage);

        verify(testEventListener, times(1)).dummy(any(Channel.class));
    }

    @Test
    void shouldNotInvokeListenerOnChannelCreateWhenReceivingMessageCreate() {
        TestEventListener testEventListener = mock(TestEventListener.class);

        Discord mockDiscord = mock(Discord.class);
        Message mockMessage = mock(Message.class);
        when(mockDiscord.getEventListeners()).thenReturn(List.of(testEventListener));

        Cache cache = new Cache(CacheType.FULL);
        GatewayEventListener gatewayEventListener = new GatewayEventListener(mockDiscord, cache);
        gatewayEventListener.receive(EventType.MESSAGE_CREATE, mockMessage);

        verify(testEventListener, times(0)).dummy(any(Channel.class));
    }
}
