package org.example;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        int result = ArithmeticOperations.add(5, 3);
        assertEquals(result, 8);
    }

    @Test
    public void testSubtract() {
        int result = ArithmeticOperations.subtract(10, 4);
        assertEquals(result, 6);
    }

    @Test
    public void testMultiply() {
        int result = ArithmeticOperations.multiply(6, 7);
        assertEquals(result, 42);
    }

    @Test
    public void testDivide() {
        double result = ArithmeticOperations.divide(10, 2);
        assertEquals(result, 5.0, 0.0001);
    }
}

