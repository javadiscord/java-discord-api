package com.javadiscord.jdi.internal.cache;

import com.javadiscord.jdi.internal.api.actions.Channel;
import com.javadiscord.jdi.internal.models.guild.Guild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {
    private final Map<String, List<Guild>> cache = new HashMap<>();

    public <T> void cache(String guildId, T object) {
        if (cache.containsKey(guildId)) {
            List<Guild> guildObjects = cache.get(guildId);
            //
        }
    }

    private <T> boolean updateObjectIfAlreadyPresent(List<T> container, T object) {
        for (int i = 0; i < container.size(); i++) {
            if (object instanceof Channel) {
                // logic
            }
        }
        return false;
    }
}
