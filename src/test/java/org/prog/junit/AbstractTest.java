package org.prog.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AbstractTest {

    @BeforeAll
    public static void setUp() {
        System.out.println("This runs before all!");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("==============================");
    }
}
