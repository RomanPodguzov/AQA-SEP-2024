package org.prog.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

public class MyJunitTest extends AbstractTest {

    @ParameterizedTest
    @MethodSource("argumentsStream")
    public void test3(String s1, int i1) {
        System.out.println(s1 + i1);
        Assertions.assertTrue(i1 > 30);
    }

    public static Stream<Arguments> argumentsStream() {
        Random random = new Random();
        return Stream.of(
                Arguments.of("test run 1 : ", random.nextInt(100)),
                Arguments.of("test run 2 : ", random.nextInt(100)),
                Arguments.of("test run 3 : ", random.nextInt(100)),
                Arguments.of("test run 4 : ", random.nextInt(100))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "c"})
    public void test2(String s) {
        System.out.println(s);
    }

    @ParameterizedTest
    @EnumSource(TestStuff.class)
    public void test(TestStuff enumVal) {
        if (enumVal.equals(TestStuff.TEST_1)) {
            System.out.println("do 1st");
        }
        if (enumVal.equals(TestStuff.TEST_2)) {
            System.out.println("do 2nd");
        }
        if (enumVal.equals(TestStuff.TEST_3)) {
            System.out.println("do 3rd");
        }
    }

    public enum TestStuff {
        TEST_1, TEST_2, TEST_3;

    }

}
