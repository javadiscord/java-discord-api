package com.javadiscord.jdi.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.javadiscord.jdi.core.api.builders.command.CommandBuilder;
import com.javadiscord.jdi.core.api.builders.command.CommandOption;
import com.javadiscord.jdi.core.api.builders.command.CommandOptionChoice;
import com.javadiscord.jdi.core.api.builders.command.CommandOptionType;
import com.javadiscord.jdi.core.interaction.InteractionEventHandler;
import com.javadiscord.jdi.core.models.ready.ReadyEvent;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestDispatcher;
import com.javadiscord.jdi.internal.api.DiscordResponseFuture;
import com.javadiscord.jdi.internal.api.application_commands.CreateCommandRequest;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheType;
import com.javadiscord.jdi.internal.gateway.*;
import com.javadiscord.jdi.internal.gateway.identify.IdentifyRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Discord {
    private static final Logger LOGGER = LogManager.getLogger(Discord.class);
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    private static final ObjectMapper OBJECT_MAPPER =
        JsonMapper.builder().addModule(new JavaTimeModule()).build();
    private static final String WEBSITE = "https://javadiscord.com/";

    private static final String BASE_URL =
        System.getProperty("DISCORD_BASE_URL") != null
            ? System.getProperty("DISCORD_BASE_URL")
            : "https://discord.com/api";

    private final String botToken;
    private final IdentifyRequest identifyRequest;
    private final DiscordRequestDispatcher discordRequestDispatcher;
    private final Gateway gateway;
    private final GatewaySetting gatewaySetting;
    private final Cache cache;
    private final List<Object> annotatedEventListeners = new ArrayList<>();
    private final Map<String, Object> loadedSlashCommands = new HashMap<>();
    private final List<EventListener> eventListeners = new ArrayList<>();
    private final List<CommandBuilder> createInteractionRequests = new ArrayList<>();

    private WebSocketManager webSocketManager;
    private long applicationId;
    private boolean started = false;

    public Discord(String botToken) {
        this(
            botToken,
            IdentifyRequest.builder()
                .token(botToken)
                .os(WEBSITE)
                .browser(WEBSITE)
                .device(WEBSITE)
                .compress(false)
                .largeThreshold(250)
                .shard(new int[] {0, 1})
                .activityName("")
                .activityType(0)
                .presenceStatus("online")
                .afk(false)
                .intents(GatewayIntent.allIntents())
                .build()
        );
    }

    public Discord(String botToken, List<GatewayIntent> intents) {
        this(
            botToken,
            IdentifyRequest.builder()
                .token(botToken)
                .os(WEBSITE)
                .browser(WEBSITE)
                .device(WEBSITE)
                .compress(false)
                .largeThreshold(250)
                .shard(new int[] {0, 1})
                .activityName("")
                .activityType(0)
                .presenceStatus("online")
                .afk(false)
                .intents(GatewayIntent.valueOf(intents))
                .build()
        );
    }

    public Discord(String botToken, IdentifyRequest identifyRequest) {
        this(botToken, identifyRequest, new Cache(CacheType.FULL));
    }

    public Discord(String botToken, IdentifyRequest identifyRequest, Cache cache) {
        System.err.println("""
                 _ ____ ___
                | |  _ \\_ _|  https://github.com/javadiscord/java-discord-api
             _  | | | | | |   Open-Source Discord Framework
            | |_| | |_| | |   GPL-3.0 license
             \\___/|____/___|  Version 1.0
            """);

        this.botToken = botToken;
        this.discordRequestDispatcher = new DiscordRequestDispatcher(botToken);
        this.gateway = getGatewayURL(botToken);
        this.gatewaySetting =
            new GatewaySetting().setEncoding(GatewayEncoding.JSON).setApiVersion(10);
        this.identifyRequest = identifyRequest;
        this.cache = cache;
        if (annotationLibPresent()) {
            LOGGER.info("Annotation lib is present");
            loadComponents();
            loadAnnotations();
            loadSlashCommands();
            registerLoadedAnnotationsWithDiscord();
        }
    }

    private void registerLoadedAnnotationsWithDiscord() {
        LOGGER.info("Registering slash commands with Discord");
        loadedSlashCommands.forEach((commandName, slashCommandClassInstance) -> {
            try {
                Class<?> slashCommandClassInstanceClass = slashCommandClassInstance.getClass();
                Method method =
                    (Method) slashCommandClassInstanceClass.getMethod("method")
                        .invoke(slashCommandClassInstance);

                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if (
                        annotation.annotationType().getName()
                            .equals("com.javadiscord.jdi.core.annotations.SlashCommand")
                    ) {
                        CommandBuilder builder = buildCommand(annotation);
                        createInteractionRequests.add(builder);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Error registering slash command with Discord", e);
            }
        });
    }

    private CommandBuilder buildCommand(Annotation annotation) throws ReflectiveOperationException {
        Method nameMethod = annotation.annotationType().getMethod("name");
        String name = (String) nameMethod.invoke(annotation);

        Method descriptionMethod = annotation.annotationType().getMethod("description");
        String description = (String) descriptionMethod.invoke(annotation);

        Method optionsMethod = annotation.annotationType().getMethod("options");
        Object[] options = (Object[]) optionsMethod.invoke(annotation);

        CommandBuilder builder = new CommandBuilder(name, description);
        for (Object option : options) {
            addCommandOption(builder, option);
        }

        return builder;
    }

    private void addCommandOption(
        CommandBuilder builder,
        Object option
    ) throws ReflectiveOperationException {
        Method optionNameMethod = option.getClass().getMethod("name");
        String optionName = (String) optionNameMethod.invoke(option);

        Method optionDescriptionMethod = option.getClass().getMethod("description");
        String optionDescription = (String) optionDescriptionMethod.invoke(option);

        Method optionTypeMethod = option.getClass().getMethod("type");
        Enum<?> optionType = (Enum<?>) optionTypeMethod.invoke(option);
        String optionTypeValue = optionType.name();

        Method optionRequiredMethod = option.getClass().getMethod("required");
        boolean optionRequired = (boolean) optionRequiredMethod.invoke(option);

        List<CommandOptionChoice> choices = new ArrayList<>();
        Object[] choicesArray = (Object[]) option.getClass().getMethod("choices").invoke(option);
        for (Object choice : choicesArray) {
            addCommandOptionChoice(choices, choice);
        }

        builder.addOption(
            new CommandOption(
                optionName,
                optionDescription,
                CommandOptionType.fromName(optionTypeValue),
                optionRequired
            ).addChoice(choices)
        );
    }

    private void addCommandOptionChoice(
        List<CommandOptionChoice> choices,
        Object choice
    ) throws ReflectiveOperationException {
        Annotation annotation1 = (Annotation) choice;
        if (
            annotation1.annotationType().getName()
                .equals("com.javadiscord.jdi.core.annotations.CommandOptionChoice")
        ) {
            Method nameMethod1 = annotation1.annotationType().getMethod("name");
            Method valueMethod1 = annotation1.annotationType().getMethod("value");
            String name1 = (String) nameMethod1.invoke(annotation1);
            String value1 = (String) valueMethod1.invoke(annotation1);
            choices.add(new CommandOptionChoice(value1, name1));
        }
    }

    private boolean annotationLibPresent() {
        try {
            Class.forName("com.javadiscord.jdi.core.processor.loader.ListenerLoader");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void loadComponents() {
        LOGGER.info("Loading Components");
        try {
            Class<?> clazz =
                Class.forName("com.javadiscord.jdi.core.processor.loader.ComponentLoader");
            for (Constructor<?> constructor : clazz.getConstructors()) {
                constructor.newInstance();
            }
        } catch (Exception | Error e) {
            LOGGER.warn("Component loading failed", e);
        }
    }

    private void loadAnnotations() {
        LOGGER.info("Loading EventListeners");
        try {
            Class<?> clazz =
                Class.forName("com.javadiscord.jdi.core.processor.loader.ListenerLoader");
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == 1) {
                    Parameter parameters = constructor.getParameters()[0];
                    if (parameters.getType().equals(List.class)) {
                        constructor.newInstance(annotatedEventListeners);
                        return;
                    }
                }
            }
        } catch (Exception | Error e) {
            LOGGER.warn("Event listener loading failed", e);
        }
    }

    private void loadSlashCommands() {
        LOGGER.info("Loading SlashCommands");
        try {
            Class<?> clazz =
                Class.forName("com.javadiscord.jdi.core.processor.loader.SlashCommandLoader");
            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == 1) {
                    Parameter parameters = constructor.getParameters()[0];
                    if (parameters.getType().equals(Map.class)) {
                        eventListeners.add(
                            new InteractionEventHandler(
                                constructor.newInstance(loadedSlashCommands),
                                this
                            )
                        );
                        return;
                    }
                }
            }
        } catch (Exception | Error e) {
            LOGGER.error("Failed to load SlashCommands", e);
        }
    }

    public void start() {
        started = true;

        webSocketManager = new WebSocketManager(gatewaySetting, identifyRequest, cache);
        WebSocketManagerProxy webSocketManagerProxy = new WebSocketManagerProxy(webSocketManager);
        ConnectionDetails connectionDetails =
            new ConnectionDetails(gateway.url(), botToken, gatewaySetting);
        ConnectionMediator connectionMediator =
            new ConnectionMediator(connectionDetails, webSocketManagerProxy);
        connectionMediator.addObserver(new GatewayEventListenerAnnotations(this));
        connectionMediator.addObserver(new GatewayEventListener(this));
        webSocketManagerProxy.start(connectionMediator);
    }

    public void stop() {
        started = false;

        if (this.webSocketManager != null) {
            this.webSocketManager.stop();
        }

        LOGGER.info("Shutdown initiated");

        discordRequestDispatcher.stop();
        EXECUTOR.shutdown();

        try {
            if (!EXECUTOR.awaitTermination(30, TimeUnit.SECONDS)) {
                EXECUTOR.shutdownNow();
                if (!EXECUTOR.awaitTermination(30, TimeUnit.SECONDS)) {
                    LOGGER.warn(
                        "Executor failed to shutdown within the specified time limit, some tasks may still be running"
                    );
                }
            }
        } catch (InterruptedException e) {
            LOGGER.error("Termination was interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

    public void startWithoutGatewayEvents() {
        EXECUTOR.execute(discordRequestDispatcher);
    }

    public void registerListener(EventListener eventListener) {
        eventListeners.add(eventListener);
        LOGGER.info("Registered listener {}", eventListener.getClass().getName());
    }

    public DiscordResponseFuture sendRequest(DiscordRequest request) {
        return discordRequestDispatcher.queue(request);
    }

    private static Gateway getGatewayURL(String authentication) {
        try (HttpClient httpClient = HttpClient.newBuilder().build()) {
            HttpRequest request =
                HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/gateway/bot"))
                    .header("Authorization", "Bot " + authentication)
                    .GET()
                    .build();
            HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 401) {
                throw new RuntimeException("Invalid bot token provided");
            }
            if (response.statusCode() != 200) {
                throw new RuntimeException("Unexpected error occurred: " + response.body());
            }
            return OBJECT_MAPPER.readValue(response.body(), Gateway.class);
        } catch (Exception e) {
            LOGGER.error("Failed to fetch the gateway URL from discord", e);
            throw new RuntimeException(e);
        }
    }

    public void registerSlashCommand(String name, String description, CommandOption... options) {
        CommandBuilder builder = new CommandBuilder(name, description);
        for (CommandOption option : options) {
            builder.addOption(option);
        }
        builder.applicationId(applicationId);
        createInteractionRequests.add(builder);
    }

    public void registerSlashCommand(CommandBuilder builder) {
        builder.applicationId(applicationId);
        createInteractionRequests.add(builder);
    }

    public void deleteSlashCommand(long id) {
        // Implement command deletion logic
    }

    public DiscordRequestDispatcher getDiscordRequestDispatcher() {
        return discordRequestDispatcher;
    }

    public Cache getCache() {
        return cache;
    }

    public List<Object> getAnnotatedEventListeners() {
        return annotatedEventListeners;
    }

    public boolean isStarted() {
        return started;
    }

    public List<EventListener> getEventListeners() {
        return eventListeners;
    }

    public long getApplicationId() {
        return applicationId;
    }

    void handleReadyEvent(ReadyEvent event) {
        applicationId = event.application().id();
        EXECUTOR.execute(discordRequestDispatcher);

        for (CommandBuilder builder : createInteractionRequests) {
            builder.applicationId(applicationId);
            CreateCommandRequest request = builder.build();
            DiscordResponseFuture future = sendRequest(request);
            handleCommandRegistrationResponse(request, future);
        }

        createInteractionRequests.clear();
    }

    private void handleCommandRegistrationResponse(
        CreateCommandRequest request,
        DiscordResponseFuture future
    ) {
        future.onSuccess(res -> {
            if (res.status() >= 200 && res.status() < 300) {
                LOGGER.info("Registered slash command {} with discord", request.name());
            } else {
                LOGGER.error(
                    "Failed to register slash command {} with discord\n{}", request.name(),
                    res.body()
                );
            }
        });
        future.onError(
            err -> LOGGER
                .error("Failed to register slash command {} with discord", request.name(), err)
        );
    }
}
