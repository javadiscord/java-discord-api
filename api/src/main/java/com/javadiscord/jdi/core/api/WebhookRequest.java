package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.CreateWebhookRequestBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookMessageRequestBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookRequestBuilder;
import com.javadiscord.jdi.core.api.builders.DeleteWebhookWithTokenRequestBuilder;
import com.javadiscord.jdi.core.api.builders.EditWebhookMessageRequestBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteGithubCompatibleWebhookRequestBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteSlackCompatibleWebhookRequestBuilder;
import com.javadiscord.jdi.core.api.builders.ExecuteWebhookRequestBuilder;
import com.javadiscord.jdi.core.api.builders.GetWebhookMessageRequestBuilder;
import com.javadiscord.jdi.core.api.builders.ModifyWebhookRequestBuilder;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.webhook.Webhook;
import com.javadiscord.jdi.internal.api.webhook.GetChannelWebhooksRequest;
import com.javadiscord.jdi.internal.api.webhook.GetGuildWebhooksRequest;
import com.javadiscord.jdi.internal.api.webhook.GetWebhookRequest;
import com.javadiscord.jdi.internal.api.webhook.GetWebhookWithTokenRequest;

import java.util.List;

public final class WebhookRequest {
    private final DiscordResponseParser responseParser;

    public WebhookRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Webhook> createWebhook(CreateWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Webhook.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhookMessage(DeleteWebhookMessageRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhook(DeleteWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> deleteWebhookWithToken(
            DeleteWebhookWithTokenRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Message> editWebhookMessage(EditWebhookMessageRequestBuilder builder) {
        return responseParser.callAndParse(Message.class, builder.build());
    }

    public AsyncResponse<Void> executeGithubCompatibleWebhook(
            ExecuteGithubCompatibleWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> executeSlackCompatibleWebhook(
            ExecuteSlackCompatibleWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> executeWebhook(ExecuteWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<List<Webhook>> getChannelWebhooks(long channelId) {
        return responseParser.callAndParseList(
                Webhook.class, new GetChannelWebhooksRequest(channelId));
    }

    public AsyncResponse<List<Webhook>> getGuildWebhooks(long guildId) {
        return responseParser.callAndParseList(Webhook.class, new GetGuildWebhooksRequest(guildId));
    }

    public AsyncResponse<Message> getWebhookMessage(GetWebhookMessageRequestBuilder builder) {
        return responseParser.callAndParse(Message.class, builder.build());
    }

    public AsyncResponse<Webhook> getWebhook(long webhookId) {
        return responseParser.callAndParse(Webhook.class, new GetWebhookRequest(webhookId));
    }

    public AsyncResponse<Webhook> getWebhookWithToken(long webhookId, String webhookToken) {
        return responseParser.callAndParse(
                Webhook.class, new GetWebhookWithTokenRequest(webhookId, webhookToken));
    }

    public AsyncResponse<Void> modifyWebhook(ModifyWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Void> modifyWebhookWithToken(ModifyWebhookRequestBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }
}
