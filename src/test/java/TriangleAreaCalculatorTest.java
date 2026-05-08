package com.example;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaCalculatorTest {

    @Test
    public void testArea() {
        double result = com.example.TriangleAreaCalculator.calculate(3, 4, 5);
        assertEquals(result, 6.0, 0.0001);
    }
}
