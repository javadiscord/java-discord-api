package com.javadiscord.jdi.core.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.javadiscord.jdi.core.CommandOptionType;

@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface CommandOption {
    String name();

    String description();

    CommandOptionType type();

    boolean required() default true;
}
