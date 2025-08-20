package com.kaue.toolbox.numbers;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public class MathAndRandom {
    public static void main(String[] args) {
        System.out.println("--- MATH ---");
        System.out.println("abs(-10) = " + Math.abs(-10));       // valor absoluto → 10
        System.out.println("max(5, 9) = " + Math.max(5, 9));     // maior → 9
        System.out.println("min(5, 9) = " + Math.min(5, 9));     // menor → 5
        System.out.println("pow(2, 3) = " + Math.pow(2, 3));     // potência → 8.0
        System.out.println("sqrt(16) = " + Math.sqrt(16));       // raiz quadrada → 4.0
        System.out.println("floor(3.9) = " + Math.floor(3.9));   // flor = chão = arredonda pra baixo → 3.0
        System.out.println("ceil(3.1) = " + Math.ceil(3.1));     // ceil = teto = arredonda pra cima → 4.0
        System.out.println("round(3.6) = " + Math.round(3.6));   // arrendondamento padrão = arredonda pro mais próximo → 4
        System.out.println("random() = " + Math.random());       // double entre 0.0 e 1.0
        System.out.println();

        // ---------------- RANDOM (java.util.Random) ----------------
        System.out.println("--- RANDOM ---");
        Random r = new Random();
        System.out.println("int aleatório (0-99) = " + r.nextInt(100));
        System.out.println("double aleatório (0-1) = " + r.nextDouble());
        System.out.println("boolean aleatório = " + r.nextBoolean());
        System.out.println();

        // ---------------- THREADLOCALRANDOM ----------------
        System.out.println("--- THREADLOCALRANDOM ---");
        int num = ThreadLocalRandom.current().nextInt(1, 7); // dado de 6 lados
        System.out.println("dado = " + num);
        System.out.println();

        // ---------------- SPLITTABLE RANDOM ----------------
        System.out.println("--- SPLITTABLE RANDOM ---");
        SplittableRandom sr = new SplittableRandom();
        System.out.println("long aleatório = " + sr.nextLong());
        System.out.println("double entre 0 e 10 = " + sr.nextDouble(0, 10));
        System.out.println();

        System.out.println("--- EXEMPLO: SENHA ALEATÓRIA ---");
        String senha = gerarSenhas(10); // senha com 10 caracteres
        System.out.println("Senha gerada: " + senha);


    }

    // METODO PARA CRIAR ARRAY DINAMICAMENETE
    private static String generateChars(){
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) sb.append(c);
        for (char c = 'a'; c <= 'z'; c++) sb.append(c);
        for (char c = '0'; c <= '9'; c++) sb.append(c);
        sb.append("@#$%");
        return sb.toString();
    }
    private static String gerarSenhas(int tamanho) {
        StringBuilder senha = new StringBuilder();
        String chars = generateChars();
        for (int i = 0; i < tamanho; i++) {
            int idx = ThreadLocalRandom.current().nextInt(chars.length());
            senha.append(chars.charAt(idx));
        }
        return senha.toString();
    }


}
