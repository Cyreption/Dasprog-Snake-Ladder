public final class Functions {
    public static void printLoop(String input, int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.print(input);
        }
    }

    public static int getDigits10(int input) {
        return (int) Math.log10(input) + 1;
    }
}
