package com.javadiscord.gateway.handlers.events;

import com.javadiscord.gateway.GatewayIntent;

public enum EventType {
    READY(GatewayIntent.GUILDS),
    RESUMED(GatewayIntent.GUILDS),
    GUILD_CREATE(GatewayIntent.GUILDS),
    GUILD_UPDATE(GatewayIntent.GUILDS),
    GUILD_DELETE(GatewayIntent.GUILDS),
    GUILD_ROLE_CREATE(GatewayIntent.GUILDS),
    GUILD_ROLE_UPDATE(GatewayIntent.GUILDS),
    GUILD_ROLE_DELETE(GatewayIntent.GUILDS),
    CHANNEL_CREATE(GatewayIntent.GUILDS),
    CHANNEL_UPDATE(GatewayIntent.GUILDS),
    CHANNEL_DELETE(GatewayIntent.GUILDS),
    CHANNEL_PINS_UPDATE(GatewayIntent.GUILDS),
    THREAD_CREATE(GatewayIntent.GUILDS),
    THREAD_UPDATE(GatewayIntent.GUILDS),
    THREAD_DELETE(GatewayIntent.GUILDS),
    THREAD_LIST_SYNC(GatewayIntent.GUILDS),
    THREAD_MEMBER_UPDATE(GatewayIntent.GUILDS),
    THREAD_MEMBERS_UPDATE(GatewayIntent.GUILDS, GatewayIntent.GUILD_MEMBERS),
    STAGE_INSTANCE_CREATE(GatewayIntent.GUILDS),
    STAGE_INSTANCE_UPDATE(GatewayIntent.GUILDS),
    STAGE_INSTANCE_DELETE(GatewayIntent.GUILDS),
    GUILD_MEMBER_ADD(GatewayIntent.GUILD_MEMBERS),
    GUILD_MEMBER_UPDATE(GatewayIntent.GUILD_MEMBERS),
    GUILD_MEMBER_REMOVE(GatewayIntent.GUILD_MEMBERS),
    GUILD_AUDIT_LOG_ENTRY_CREATE(GatewayIntent.GUILD_MODERATION),
    GUILD_BAN_ADD(GatewayIntent.GUILD_MODERATION),
    GUILD_BAN_REMOVE(GatewayIntent.GUILD_MODERATION),
    ;

    private final GatewayIntent[] requiredIntent;

    EventType(GatewayIntent... requiredIntent) {
        this.requiredIntent = requiredIntent;
    }

    public GatewayIntent[] getRequiredIntent() {
        return requiredIntent;
    }

    public static boolean nameExists(String name) {
        for (EventType eventType : EventType.values()) {
            if (eventType.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
