public class Factorial {

    public static long calculateLong(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
