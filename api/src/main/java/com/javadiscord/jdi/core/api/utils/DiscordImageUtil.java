package com.javadiscord.jdi.core.api.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class DiscordImageUtil {
    private static final List<String> IMAGE_EXTENSIONS = List.of("jpg", "jpeg", "png", "gif");

    private DiscordImageUtil() {}

    public static String base64Encode(Path path) throws IOException {
        if (isNotImage(path)) {
            throw new IOException(path + " is not an image");
        }
        return Base64.getEncoder().encodeToString(Files.readAllBytes(path));
    }

    public static String toDiscordString(Path path) throws IOException {
        return "data:image/%s;base64,%s".formatted(getExtension(path), base64Encode(path));
    }

    public static boolean isNotImage(Path path) {
        return !IMAGE_EXTENSIONS.contains(getExtension(path));
    }

    private static String getExtension(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
