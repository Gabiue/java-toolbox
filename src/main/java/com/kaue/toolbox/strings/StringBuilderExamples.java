package com.kaue.toolbox.strings;

public class StringBuilderExamples {
    public static void main(String[] args) {

        // Variáveis base
        String base = "Java";
        String extra = " é massa";
        String inser = " muito";

        // ---------------- APPEND ----------------
        StringBuilder sb = new StringBuilder(base);
        sb.append(extra);
        System.out.println(sb); // Java é massa
        System.out.println();

        // ---------------- INSERT ----------------
        sb = new StringBuilder(base + extra); // "Java é massa"
        sb.insert(4, inser);                  // insere a partir do índice
        System.out.println(sb); // Java muito é massa
        System.out.println();

        // ---------------- DELETE ----------------
        sb = new StringBuilder("abcdef");
        sb.delete(2, 4); // remove [2..4)
        System.out.println(sb); // abef
        System.out.println();

        // ---------------- DELETECHARAT ----------------
        sb = new StringBuilder("abcd");
        sb.deleteCharAt(1);
        System.out.println(sb); // acd
        System.out.println();

        // ---------------- REPLACE (StringBuilder) ----------------
        sb = new StringBuilder("Hello, World");
        sb.replace(7, 12, "Java"); // troca o trecho
        System.out.println(sb); // Hello, Java
        System.out.println();

        // ---------------- SETCHARAT ----------------
        sb = new StringBuilder("java");
        sb.setCharAt(0, 'J');
        System.out.println(sb); // Java
        System.out.println();

        // ---------------- REVERSE ----------------
        sb = new StringBuilder("abcdef");
        sb.reverse();
        System.out.println(sb); // fedcba
        System.out.println();

        // ---------------- LENGTH / SETLENGTH ----------------
        sb = new StringBuilder("abc");
        System.out.println("length = " + sb.length());
        sb.setLength(5); // preenche com '\u0000'
        System.out.println("length após setLength(5) = " + sb.length());
        System.out.println();

        // ---------------- CAPACITY / ENSURECAPACITY / TRIMTOSIZE ----------------
        sb = new StringBuilder(); // capacidade inicial padrão
        System.out.println("capacity = " + sb.capacity());
        sb.ensureCapacity(100);
        System.out.println("capacity após ensureCapacity(100) = " + sb.capacity());
        sb.trimToSize();
        System.out.println("capacity após trimToSize() = " + sb.capacity());
        System.out.println();

        // ---------------- INDEXOF / LASTINDEXOF ----------------
        sb = new StringBuilder("Java é incrível e Java é poderoso");
        System.out.println("indexOf('Java') = " + sb.indexOf("Java"));
        System.out.println("lastIndexOf('Java') = " + sb.lastIndexOf("Java"));
        System.out.println();

        // ---------------- SUBSTRING (retorna String) ----------------
        sb = new StringBuilder("Hello Java");
        String sub = sb.substring(6); // de 6 até o fim
        System.out.println(sub); // Java
        System.out.println();

        // ---------------- TOSTRING ----------------
        sb = new StringBuilder(base).append(extra);
        String result = sb.toString();
        System.out.println(result); // Java é massa

        // ---------------- EXEMPLO REAL: MINI CRUD COM STRINGBUILDER ----------------
        System.out.println("--- MINI CRUD ---");

        // CREATE (criar um "registro" de texto)
        StringBuilder usuario = new StringBuilder("Nome: Kaue, Idade: 19");
        System.out.println("CREATE → " + usuario);

        // READ (ler: acessar informações)
        System.out.println("READ → charAt(6) = " + usuario.charAt(6)); // pega a letra do nome
        System.out.println("READ → substring(6, 10) = " + usuario.substring(6, 10)); // pega "Kaue"

        // UPDATE (atualizar informações)
        usuario.replace(6, 10, "Gabriel"); // troca Kaue por Gabriel
        usuario.insert(usuario.length(), ", Cidade: São Paulo"); // adiciona cidade
        System.out.println("UPDATE → " + usuario);

// DELETE (remover informações)
        int posIdade = usuario.indexOf("Idade");
        usuario.delete(posIdade, posIdade + "Idade: 19".length()); // remove idade
        System.out.println("DELETE → " + usuario);


    }

}