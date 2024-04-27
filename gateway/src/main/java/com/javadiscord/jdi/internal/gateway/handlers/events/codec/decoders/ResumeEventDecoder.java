package com.javadiscord.jdi.internal.gateway.handlers.events.codec.decoders;

import com.javadiscord.jdi.internal.gateway.GatewayEvent;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.EventDecoder;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.resume.ResumeEvent;

public class ResumeEventDecoder implements EventDecoder<ResumeEvent> {
    @Override
    public ResumeEvent decode(GatewayEvent gatewayEvent) {
        return new ResumeEvent();
    }
}
