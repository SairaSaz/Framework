package org.example;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialCalculatorTest {

    @Test
    public void testFactorial() {
        assertEquals(com.example.FactorialCalculator.calculateFactorial(5), 120);
    }

}