package com.javadiscord.jdi.internal.gateway.handlers.events.codec.resume;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;

public class ResumeEventDecoder implements EventDecoder<ResumeEvent> {
    @Override
    public ResumeEvent decode(GatewayEvent gatewayEvent) {
        return new ResumeEvent();
    }
}
