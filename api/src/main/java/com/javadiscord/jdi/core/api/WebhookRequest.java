package com.javadiscord.jdi.core.api;

import java.util.List;

import com.javadiscord.jdi.core.api.builders.CreateWebhookBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookMessageBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookWithTokenBuilder;
import com.javadiscord.jdi.core.api.builders.EditWebhookMessageBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteGithubCompatibleWebhookBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteSlackCompatibleWebhooBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteWebhookBuilder;
import com.javadiscord.jdi.core.api.builders.GetWebhookMessageBuilder;
import com.javadiscord.jdi.core.api.builders.ModifyWebhookBuilder;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.webhook.Webhook;
import com.javadiscord.jdi.internal.api.webhook.GetChannelWebhooksRequest;
import com.javadiscord.jdi.internal.api.webhook.GetGuildWebhooksRequest;
import com.javadiscord.jdi.internal.api.webhook.GetWebhookRequest;
import com.javadiscord.jdi.internal.api.webhook.GetWebhookWithTokenRequest;

public final class WebhookRequest {
    private final DiscordResponseParser responseParser;

    public WebhookRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Webhook> createWebhook(CreateWebhookBuilder builder) {
        return responseParser.callAndParse(Webhook.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhookMessage(DeleteWebhookMessageBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhook(DeleteWebhookBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhookWithToken(DeleteWebhookWithTokenBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Message> editWebhookMessage(EditWebhookMessageBuilder builder) {
        return responseParser.callAndParse(Message.class, builder.build());
    }

    public AsyncResponse<Void> executeGithubCompatibleWebhook(
        ExecuteGithubCompatibleWebhookBuilder builder
    ) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> executeSlackCompatibleWebhook(
        ExecuteSlackCompatibleWebhooBuilder builder
    ) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> executeWebhook(ExecuteWebhookBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<List<Webhook>> getChannelWebhooks(long channelId) {
        return responseParser.callAndParseList(
            Webhook.class, new GetChannelWebhooksRequest(channelId)
        );
    }

    public AsyncResponse<List<Webhook>> getGuildWebhooks(long guildId) {
        return responseParser.callAndParseList(Webhook.class, new GetGuildWebhooksRequest(guildId));
    }

    public AsyncResponse<Message> getWebhookMessage(GetWebhookMessageBuilder builder) {
        return responseParser.callAndParse(Message.class, builder.build());
    }

    public AsyncResponse<Webhook> getWebhook(long webhookId) {
        return responseParser.callAndParse(Webhook.class, new GetWebhookRequest(webhookId));
    }

    public AsyncResponse<Webhook> getWebhookWithToken(long webhookId, String webhookToken) {
        return responseParser.callAndParse(
            Webhook.class, new GetWebhookWithTokenRequest(webhookId, webhookToken)
        );
    }

    public AsyncResponse<Void> modifyWebhook(ModifyWebhookBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> modifyWebhookWithToken(ModifyWebhookBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }
}
