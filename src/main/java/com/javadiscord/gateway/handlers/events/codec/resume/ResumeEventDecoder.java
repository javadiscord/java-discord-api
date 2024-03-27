package com.javadiscord.gateway.handlers.events.codec.resume;

import com.javadiscord.gateway.GatewayEvent;
import com.javadiscord.gateway.handlers.events.codec.EventDecoder;

public class ResumeEventDecoder implements EventDecoder<ResumeEvent> {
    @Override
    public ResumeEvent decode(GatewayEvent event) {
        return new ResumeEvent();
    }
}
