package com.javadiscord.jdi.core.processor;

import javassist.bytecode.ClassFile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ClassFileUtil {

    private ClassFileUtil() {}

    public static List<File> getClassesInClassPath() {
        List<File> classes = new ArrayList<>();
        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        for (String entry : classpathEntries) {
            File file = new File(entry);
            classes.addAll(getClasses(file));
        }
        return classes;
    }

    public static String getClassName(File file) throws IOException {
        String className = null;
        try (FileInputStream fis = new FileInputStream(file);
                DataInputStream dis = new DataInputStream(fis)) {
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

    private static List<File> getClasses(File file) {
        List<File> classes = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    classes.addAll(getClasses(f));
                }
            }
        } else {
            if (file.getName().endsWith(".class")) {
                classes.add(file);
            }
        }
        return classes;
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
}
