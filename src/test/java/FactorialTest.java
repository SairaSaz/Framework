import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

class FactorialTest {

    @Test
    @DisplayName("Factorial 5 = 120")
    void factorialFive() {
        Assertions.assertEquals(120, Factorial.calculateLong(5));
    }
}
