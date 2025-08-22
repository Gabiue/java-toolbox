package regex;

import java.util.regex.*;

public class RegexBasics {
    public static void main(String[] args) {
        String texto = "Meu email é kaue@gmail.com e o dele é teste@outlook.com";

        // ---------------- MATCHES ----------------
        System.out.println("--- MATCHES ---");
        System.out.println("Só dígitos? " + "12345".matches("\\d+"));  // true
        System.out.println("Só dígitos? " + "123a".matches("\\d+"));   // false
        System.out.println();

        // ---------------- PATTERN + MATCHER ----------------
        System.out.println("--- PATTERN & MATCHER ---");
        Pattern p = Pattern.compile("\\w+@\\w+\\.\\w+"); // padrão de email simples
        Matcher m = p.matcher(texto);

        while (m.find()) {
            System.out.println("Encontrado: " + m.group());
        }
        System.out.println();

        // ---------------- SPLIT COM REGEX ----------------
        System.out.println("--- SPLIT ---");
        String[] palavras = texto.split("\\s+"); // quebra por espaços
        for (String palavra : palavras) {
            System.out.println(palavra);
        }
        System.out.println();

        // ---------------- REPLACE COM REGEX ----------------
        System.out.println("--- REPLACE ---");
        String censurado = texto.replaceAll("\\w+@\\w+\\.\\w+", "[EMAIL]");
        System.out.println(censurado);
    }
}
