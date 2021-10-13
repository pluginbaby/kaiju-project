package com.kaiju.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DnaUtilsTest {

    @Test
    public void calculateSumTest() {
        String sample = "12345abc";
        int total = DnaUtils.calculateSum(sample);
        Assertions.assertEquals(12, total);
    }

    @Test
    public void calculateSumTestFail() {
        String sample = "12abc";
        int total = DnaUtils.calculateSum(sample);
        Assertions.assertEquals(-1, total);
    }

    @Test
    public void calculateSumTestSuccess2() {
        String sample = "54321abc";
        int total = DnaUtils.calculateSum(sample);
        Assertions.assertEquals(12, total);
    }
}
