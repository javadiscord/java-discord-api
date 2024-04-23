package com.javadiscord.jdi.core;

import java.io.File;

import com.javadiscord.jdi.core.annotations.EventListener;
import com.javadiscord.jdi.core.processor.ClassFileUtil;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotReload implements Runnable, FileAlterationListener {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Discord discord;
    private volatile boolean running;
    private final FileAlterationMonitor monitor;

    public HotReload(Discord discord) {
        this.discord = discord;
        this.running = true;
        this.monitor = new FileAlterationMonitor(1000);
    }

    @Override
    public void run() {
        FileAlterationObserver observer = new FileAlterationObserver("./");
        observer.addListener(this);
        monitor.addObserver(observer);
        try {
            monitor.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void reload(Class<?> clazz) {
        if (clazz.isAnnotationPresent(EventListener.class)) {
            boolean valid = discord.validateListener(clazz);
            if (!valid) {
                LOGGER.error("{} failed validation", clazz.getName());
                return;
            }
            discord.updateOrRegisterListener(clazz);
        }
    }

    public void stop() {
        running = false;
        try {
            monitor.stop();
        } catch (Exception ignore) {
            /* Ignore */
        }
    }

    @Override
    public void onFileChange(File file) {
        if (file.getName().endsWith(".class")) {
            try {
                Class<?> clazz = Class.forName(ClassFileUtil.getClassName(file));
                reload(clazz);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    @Override
    public void onFileCreate(File file) {
        if (file.getName().endsWith(".class")) {
            try {
                Class<?> clazz = Class.forName(ClassFileUtil.getClassName(file));
                reload(clazz);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onDirectoryChange(File file) {

    }

    @Override
    public void onDirectoryCreate(File file) {

    }

    @Override
    public void onDirectoryDelete(File file) {

    }

    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {

    }

    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {

    }
}
