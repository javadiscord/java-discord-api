package helpers;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.javadiscord.jdi.core.Discord;
import com.javadiscord.jdi.core.EventListener;
import com.javadiscord.jdi.core.Guild;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class LiveDiscordHelper {
    private static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    private static final TestListener TEST_LISTENER = new TestListener();
    private static final AtomicReference<Guild> GUILD_ATOMIC_REFERENCE = new AtomicReference<>();
    private static final CountDownLatch STARTUP_LATCH = new CountDownLatch(1);
    private static Guild guild;

    private static class TestListener implements EventListener {

        @Override
        public void onGuildCreate(Guild guild) {
            GUILD_ATOMIC_REFERENCE.set(guild);
            STARTUP_LATCH.countDown();
        }
    }

    public LiveDiscordHelper() throws InterruptedException {
        Discord discord = new Discord(BOT_TOKEN);
        discord.registerListener(TEST_LISTENER);
        discord.start();
        assertTrue(STARTUP_LATCH.await(30, TimeUnit.SECONDS));
        if (GUILD_ATOMIC_REFERENCE.get() == null) {
            throw new NullPointerException("Received a null guild");
        }
        guild = GUILD_ATOMIC_REFERENCE.get();
    }

    public Guild getGuild() {
        return guild;
    }
}
