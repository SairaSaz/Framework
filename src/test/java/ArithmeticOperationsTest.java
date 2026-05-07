import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

class ArithmeticOperationsTest {
    @Test
    @DisplayName("ADD: 2 + 3 = 5")
    void additionNumber() {
        Assertions.assertEquals(5, ArithmeticOperations.add(2, 3));
    }

    @Test
    @DisplayName("Subtract: 5 - 3 = 2")
    void subtractionNumber() {
        Assertions.assertEquals(2, ArithmeticOperations.subtract(5, 3));
    }

    @Test
    @DisplayName("Multiply: 4 * 3 = 12")
    void multiplicationNumber() {
        Assertions.assertEquals(12, ArithmeticOperations.multiply(4, 3));
    }

    @Test
    @DisplayName("Divide: 10 / 2 = 5")
    void divisionNumber() {
        Assertions.assertEquals(5.0, ArithmeticOperations.divide(10, 2), 0.0001);
    }
}