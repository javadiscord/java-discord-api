package com.javadiscord.jdi.internal.processor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipSecurity {

    public static ZipInputStream createSecureInputStream(InputStream stream) {
        return new SecureZipInputStream(stream);
    }

    private static class SecureZipInputStream extends ZipInputStream {

        public SecureZipInputStream(InputStream in) {
            super(in);
        }

        @Override
        public ZipEntry getNextEntry() throws IOException {
            ZipEntry entry = super.getNextEntry();
            if (entry == null) {
                return null;
            }
            String entryName = entry.getName();
            if (!entryName.trim().isEmpty()) {
                if (isAbsolutePath(entryName)) {
                    throw new SecurityException(
                        "Encountered zip file with absolute path: " + entryName
                    );
                }
                if (containsPathTraversal(entryName)) {
                    throw new SecurityException(
                        "Path contains traversal to sensitive locations: " + entryName
                    );
                }
            }
            return entry;
        }

        private boolean containsPathTraversal(String entryName) {
            if (entryName.contains("../") || entryName.contains("..\\")) {
                try {
                    if (isPathOutsideCurrentDirectory(entryName)) {
                        return true;
                    }
                } catch (IOException ignore) {
                    /* Ignore */
                }
            }
            return false;
        }

        private boolean isPathOutsideCurrentDirectory(String entryName) throws IOException {
            File currentDirectory = new File("").getCanonicalFile();
            File untrustedFile = new File(currentDirectory, entryName);
            Path untrustedPath = untrustedFile.getCanonicalFile().toPath();
            return !untrustedPath.startsWith(currentDirectory.toPath());
        }

        private boolean isAbsolutePath(String entryName) {
            return entryName.startsWith("/");
        }
    }
}
