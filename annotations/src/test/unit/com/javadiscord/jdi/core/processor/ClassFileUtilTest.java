package com.javadiscord.jdi.core.processor;

import static org.junit.jupiter.api.Assertions.*;

import com.javadiscord.jdi.internal.processor.ClassFileUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class ClassFileUtilTest {

    @Test
    void testGetClassesInClassPathReturnsItems() {
        assertFalse(ClassFileUtil.getClassesInClassPath().isEmpty());
    }

    @Test
    void testGetClassNameReturnsClassName() {
        File file =
                new File(
                        "build/classes/java/test/com/javadiscord/jdi/core/processor/ClassFileUtilTest.class");
        try {
            assertEquals(
                    "com.javadiscord.jdi.core.processor.ClassFileUtilTest",
                    ClassFileUtil.getClassName(file));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
