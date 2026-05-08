package com.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {
    @Test
    public void сomparatorNum() {
        String result = com.example.NumberComparator.compare(5, 10);
        assertEquals(result, "5 меньше 10");
    }
}