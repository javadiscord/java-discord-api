package com.javadiscord.jdi.core.processor;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ClassFileUtilTest {

    @Test
    void testGetClassesInClassPathReturnsItems() {
        assertFalse(ClassFileUtil.getClassesInClassPath().isEmpty());
    }

    @Test
    void testGetClassNameReturnsClassName() {
        File file = new File("build/classes/java/test/com/javadiscord/jdi/core/processor/ClassFileUtilTest.class");
        try {
            assertEquals("com.javadiscord.jdi.core.processor.ClassFileUtilTest", ClassFileUtil.getClassName(file));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
