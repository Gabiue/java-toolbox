package com.kaue.toolbox.numbers;

public class LongUtils {
    public static void main(String[] args) {

        String sNum = "123456789";
        String sHex = "7fffffffffffffff"; // Long.MAX_VALUE em hex

        System.out.println("--- PARSE ---");
        System.out.println(Long.parseLong(sNum));        // 123456789
        System.out.println(Long.parseLong(sHex, 16));    // 9223372036854775807

        System.out.println("\n--- VALUEOF ---");
        System.out.println(Long.valueOf(123456789L));
        System.out.println(Long.valueOf("123456789"));

        System.out.println("\n--- BASES ---");
        System.out.println(Long.toBinaryString(255));  // 11111111
        System.out.println(Long.toHexString(255));     // ff

        System.out.println("\n--- COMPARE / MATH ---");
        System.out.println(Long.compare(100L, 200L)); // -1
        System.out.println(Long.max(100L, 200L));     // 200
        System.out.println(Long.signum(-50L));        // -1

        System.out.println("\n--- CONSTANTES ---");
        System.out.println(Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);

        System.out.println("\n--- BITS ---");
        long x = 1024L;
        System.out.println(Long.bitCount(x));         // 1
        System.out.println(Long.highestOneBit(x));    // 1024
        System.out.println(Long.lowestOneBit(18L));   // 2

        System.out.println("\n--- UNSIGNED ---");
        long neg = -1L;
        System.out.println(Long.toUnsignedString(neg)); // 18446744073709551615

        System.out.println("\n--- RECEITAS ---");
        System.out.println(clamp(1_000_000_000_000L, 0, 500_000_000L)); // 500000000
        System.out.println(safeParse("notANumber", -99L));              // -99
    }

    // Utilitários extras (mesma ideia do IntegerUtils)
    public static long clamp(long value, long min, long max) {
        return Math.max(min, Math.min(max, value));
    }

    public static long safeParse(String s, long fallback) {
        try { return Long.parseLong(s); }
        catch (Exception e) { return fallback; }
    }
}
