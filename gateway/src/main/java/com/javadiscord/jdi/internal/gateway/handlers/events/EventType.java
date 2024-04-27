package com.javadiscord.jdi.internal.gateway.handlers.events;

import com.javadiscord.jdi.internal.gateway.GatewayIntent;

public enum EventType {
    READY,
    RESUMED,
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
    APPLICATION_COMMAND_PERMISSIONS_UPDATE,
    AUTO_MODERATION_RULE_CREATE,
    AUTO_MODERATION_RULE_UPDATE,
    AUTO_MODERATION_RULE_DELETE,
    AUTO_MODERATION_ACTION_EXECUTION,
    ENTITLEMENT_CREATE,
    ENTITLEMENT_UPDATE,
    ENTITLEMENT_DELETE,
    GUILD_EMOJIS_UPDATE,
    GUILD_STICKERS_UPDATE,
    GUILD_INTEGRATIONS_UPDATE,
    GUILD_MEMBERS_CHUNK,
    GUILD_SCHEDULED_EVENT_CREATE,
    GUILD_SCHEDULED_EVENT_UPDATE,
    GUILD_SCHEDULED_EVENT_DELETE,
    GUILD_SCHEDULED_EVENT_USER_ADD,
    GUILD_SCHEDULED_EVENT_USER_REMOVE,
    INTERACTION_CREATE,
    INVITE_CREATE,
    INVITE_DELETE,
    MESSAGE_CREATE,
    MESSAGE_UPDATE,
    MESSAGE_DELETE,
    MESSAGE_DELETE_BULK,
    MESSAGE_REACTION_ADD,
    MESSAGE_REACTION_REMOVE,
    MESSAGE_REACTION_REMOVE_ALL,
    MESSAGE_REACTION_REMOVE_EMOJI,
    TYPING_START,
    USER_UPDATE,
    VOICE_STATE_UPDATE,
    VOICE_SERVER_UPDATE,
    WEBHOOKS_UPDATE;
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
