package com.javadiscord.jdi.core.processor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javassist.bytecode.ClassFile;

public class ClassFileUtil {
    private static final List<File> classesInPath = new ArrayList<>();
    private static boolean loadedParentJar = false;

    private ClassFileUtil() {}

    public static List<File> getClassesInClassPath() {
        if (!classesInPath.isEmpty()) {
            return classesInPath;
        }
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);

        for (String entry : classpathEntries) {
            File file = new File(entry);
            try {
                classesInPath.addAll(getClasses(file));
            } catch (IOException ignore) {
                /* Ignore */
            }
        }
        return classesInPath;
    }

    public static String getClassName(File file) throws IOException {
        String className = null;
        try (
            FileInputStream fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis)
        ) {
            if (isJarFile(file)) {
                try (ZipInputStream zip = new ZipInputStream(fis)) {
                    ZipEntry entry;
                    while ((entry = zip.getNextEntry()) != null) {
                        if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                            className = extractClassName(zip);
                            break;
                        }
                    }
                }
            } else {
                className = extractClassName(dis);
            }
        }
        return className;
    }

    private static List<File> getClasses(File file) throws IOException {
        List<File> classFiles = new ArrayList<>();
        if (file.isDirectory()) {
            classFiles.addAll(getClassesFromDirectory(file));
        } else if (isJarFile(file) && !loadedParentJar) {
            loadedParentJar = true;
            classFiles.addAll(getClassesFromJar(file));
        } else if (file.getName().endsWith(".class")) {
            classFiles.add(file);
        }
        return classFiles;
    }

    private static List<File> getClassesFromDirectory(File directory) throws IOException {
        List<File> classFiles = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                classFiles.addAll(getClasses(file));
            }
        }
        return classFiles;
    }

    private static List<File> getClassesFromJar(File jarFile) throws IOException {
        List<File> classFiles = new ArrayList<>();
        try (
            FileInputStream fis = new FileInputStream(jarFile);
            ZipInputStream zip = new ZipInputStream(fis)
        ) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    File tempFile = extractClassFileFromJar(zip, entry.getName());
                    classFiles.add(tempFile);
                }
            }
        }
        return classFiles;
    }

    private static boolean isJarFile(File file) {
        return file.getName().toLowerCase().endsWith(".jar");
    }

    private static String extractClassName(DataInputStream dis) throws IOException {
        return new ClassFile(new DataInputStream(dis)).getName();
    }

    private static String extractClassName(ZipInputStream zip) throws IOException {
        return new ClassFile(new DataInputStream(zip)).getName();
    }

    private static File extractClassFileFromJar(
        ZipInputStream zip,
        String entryName
    ) throws IOException {
        File tempFile = File.createTempFile(entryName.replace('/', '_'), ".class");
        tempFile.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zip.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
        return tempFile;
    }
}
