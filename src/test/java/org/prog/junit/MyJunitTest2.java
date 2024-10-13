package org.prog.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MyJunitTest2 extends AbstractTest {

    @AfterEach
    public void smth() {
        System.out.println("------------------------");
    }

    @Test
    public void test4() {
        System.out.println("test4!");
    }

    @Test
    public void test5() {
        throw new RuntimeException("OOPS!");
    }

    @Test
    public void tes6() {
        System.out.println("test6!");
    }

}
