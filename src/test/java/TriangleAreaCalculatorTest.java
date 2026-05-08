package com.example;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleAreaCalculatorTest {

    @Test
    public void testArea() {
        double result = com.example.TriangleAreaCalculator.calculate(3, 4, 5);
        Assert.assertEquals(result, 6.0, 0.0001);
    }
}
