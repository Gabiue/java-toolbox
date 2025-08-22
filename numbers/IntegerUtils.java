
package numbers;

public class IntegerUtils {
    public static void main(String[] args) {

        String sNum = "42";
        String sHex = "ff";
        int a = 10;

        System.out.println("--- PARSE ---");
        System.out.println(Integer.parseInt(sNum));         // 42
        System.out.println(Integer.parseInt(sHex, 16));// 255

        System.out.println("\n--- VALUEOF ---");
        System.out.println(Integer.valueOf(42));
        System.out.println(Integer.valueOf("42"));

        System.out.println("\n--- BASES ---");
        System.out.println(Integer.toBinaryString(255)); // 11111111
        System.out.println(Integer.toHexString(255));    // ff

        System.out.println("\n--- COMPARE / MATH ---");
        System.out.println(Integer.compare(10, 20));  // -1
        System.out.println(Integer.max(10, 20));      // 20
        System.out.println(Integer.signum(-5));       // -1

        System.out.println("\n--- CONSTANTES ---");
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println("\n--- BITS ---");
        int x = 177; //
        System.out.println(Integer.bitCount(x));         // 4
        System.out.println(Integer.highestOneBit(x));    // 128
        System.out.println(Integer.lowestOneBit(x));     // 1

        System.out.println("\n--- UNSIGNED ---");
        int neg = -1;
        System.out.println(Integer.toUnsignedString(neg)); // 4294967295

        System.out.println("\n--- AUTOBOXING ---");
        Integer boxed = a;
        int prim = boxed;
        System.out.println(boxed + " / " + prim);

        System.out.println("\n--- RECEITAS ---");
        System.out.println(clamp(150, 0, 100));             // 100
        System.out.println(safeParse("abc", -1));           // -1
    }

    public static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    public static int safeParse(String s, int fallback) {
        try { return Integer.parseInt(s); }
        catch (Exception e) { return fallback; }
    }
}
