import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

class TriangleAreaTest {

    @Test
    @DisplayName("Area triangle 3,4,5 = 6")
    void areaTriangleNum() {
        Assertions.assertEquals(6.0, TriangleArea.calculate(3, 4, 5), 0.0001);
    }
}