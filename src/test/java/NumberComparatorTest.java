import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

class NumberComparatorTest {

    @Test
    @DisplayName("Comparator 20 > 10")
    void comparator() {
        Assertions.assertEquals("20 больше 10", NumberComparator.compare(20, 10));
    }
}