package com.javadiscord.gateway;

public class GatewayCloseEventCode {
    public static final int UNKNOWN_ERROR = 4000;
    public static final int UNKNOWN_OPCODE = 4001;
    public static final int DECODE_ERROR = 4002;
    public static final int NOT_AUTHENTICATED = 4003;
    public static final int AUTHENTICATION_FAILED = 4004;
    public static final int ALREADY_AUTHENTICATED = 4005;
    public static final int INVALID_SEQUENCE = 4007;
    public static final int RATE_LIMITED = 4008;
    public static final int SESSION_TIMED_OUT = 4009;
    public static final int INVALID_SHARD = 4010;
    public static final int SHARDING_REQUIRED = 4011;
    public static final int INVALID_API_VERSION = 4012;
    public static final int INVALID_INTENTS = 4013;
    public static final int DISALLOWED_INTENTS = 4014;
}
