
package numbers;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
    public static void main(String[] args) {
        //Motivo de evitar usar float e double sempre
        //BigDecimal garante alta precisão
        System.out.println(0.1 + 0.2); //Irá retornar: 0.30000000000000004

        System.out.println();

        //Com BigDecimal terá precisão maior
        //Lembre de importar a biblioteca java.math.BigDecimal
        System.out.println(new BigDecimal("0.1").add(new BigDecimal("0.2"))); // 0.3 ✅

        //Seguimos a exemplos e etc

        // ---------------- DECLARAÇÃO ----------------
        BigDecimal bd1 = new BigDecimal("10.25");   // recomendado: usar String
        BigDecimal bd2 = BigDecimal.valueOf(3);     // alternativa com valueOf
        System.out.println("bd1 = " + bd1); // 10.25
        System.out.println("bd2 = " + bd2); // 3

        // ---------------- CONSTRUTORES ----------------
        BigDecimal fromInt    = new BigDecimal(42);         // int → BigDecimal
        BigDecimal fromLong   = BigDecimal.valueOf(123456L);// long → BigDecimal
        BigDecimal fromDouble = BigDecimal.valueOf(0.1);    // melhor que new BigDecimal(0.1)
        System.out.println("fromInt = " + fromInt);
        System.out.println("fromLong = " + fromLong);
        System.out.println("fromDouble = " + fromDouble);

        // ---------------- OPERAÇÕES BÁSICAS ----------------
        System.out.println("--- OPERAÇÕES ---");
        System.out.println("soma = " + bd1.add(bd2));       // 10.25 + 3 = 13.25
        System.out.println("subtração = " + bd1.subtract(bd2)); // 7.25
        System.out.println("multiplicação = " + bd1.multiply(bd2)); // 30.75
        System.out.println("divisão = " + bd1.divide(bd2, 2, RoundingMode.HALF_UP)); // 3.42

        // ---------------- COMPARAÇÃO ----------------
        System.out.println("--- COMPARAÇÃO ---");
        System.out.println("bd1 > bd2 ? " + (bd1.compareTo(bd2) > 0));  // true
        System.out.println("bd1 == 10.25 ? " + (bd1.compareTo(new BigDecimal("10.25")) == 0)); // true

        // ---------------- ESCALA / ARREDONDAMENTO ----------------
        System.out.println("--- ARREDONDAMENTO ---");
        BigDecimal bd3 = new BigDecimal("10.12345");
        System.out.println("HALF_UP = " + bd3.setScale(2, RoundingMode.HALF_UP));   // 10.12
        System.out.println("CEILING = " + bd3.setScale(2, RoundingMode.CEILING));   // 10.13
        System.out.println("FLOOR = " + bd3.setScale(2, RoundingMode.FLOOR));       // 10.12

        // ---------------- CONSTANTES ----------------
        System.out.println("--- CONSTANTES ---");
        System.out.println("ZERO = " + BigDecimal.ZERO);
        System.out.println("ONE = " + BigDecimal.ONE);
        System.out.println("TEN = " + BigDecimal.TEN);

        // ---------------- MÉTODOS ESPECIAIS ----------------
        System.out.println("--- MÉTODOS ESPECIAIS ---");
        BigDecimal base = new BigDecimal("2");
        BigDecimal exp = base.pow(3); // potência: 2³ = 8
        System.out.println("pow(3) = " + exp);

        BigDecimal bd4 = new BigDecimal("123.4500");
        System.out.println("scale (casas decimais) = " + bd4.scale());   // 4
        System.out.println("stripTrailingZeros = " + bd4.stripTrailingZeros()); // 123.45

        // ---------------- EXEMPLO REAL ----------------
        System.out.println("--- JUROS COMPOSTOS ---");
        BigDecimal capital = new BigDecimal("1000.00");
        BigDecimal taxa = new BigDecimal("0.05"); // 5% ao mês
        int meses = 12;

        BigDecimal montante = capital.multiply(
                BigDecimal.ONE.add(taxa).pow(meses) // (1 + taxa)^meses
        ).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Montante após 12 meses = " + montante); // 1795.86
    }
}

