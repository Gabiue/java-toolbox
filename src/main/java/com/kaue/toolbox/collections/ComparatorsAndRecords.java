package com.kaue.toolbox.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorsAndRecords {
    public static void main(String[] args) {

        Pessoa p = new Pessoa("Ana", 25);
        String nome = p.nome();
        int idade = p.idade();
        System.out.println(p);
        //ARRAYASLIST
        System.out.println("---COLLECTIONS---");
        List<Pessoa> pessoas = Arrays.asList(new Pessoa("Carlos", 25),
                new Pessoa("Ana", 30),
                new Pessoa("Bruno", 20),
                new Pessoa("Bruno", 25),
                new Pessoa("Diana", 25),    // mesma idade que Carlos
                new Pessoa("Eduardo", 20)
        );


        Collections.sort(pessoas);

        for (Pessoa print : pessoas) {
            System.out.println("-" + print);
        }
        Collections.sort(pessoas, (p1, p2) -> p1.nome().compareTo(p2.nome()));

        //Ordenar por idade crescente
        Collections.sort(pessoas, (p1, p2) -> Integer.compare(p1.idade, p2.idade));
        System.out.println("---Por idade Crescente---");
        for (Pessoa print : pessoas) {
            System.out.println("-" + print);
        }
        //Ordenar por idade decrescente
        Collections.sort(pessoas, (p1, p2) -> Integer.compare(p2.idade, p1.idade));
        System.out.println("---Por idade Decrescente---");
        for (Pessoa print : pessoas) {
            System.out.println("-" + print);
        }

        System.out.println("\n--- VERSÕES MODERNAS ---");

        // Por nome (mais legível)
        pessoas.sort(Comparator.comparing(Pessoa::nome));
        System.out.println("Por nome (moderno):");
        for (Pessoa print : pessoas) {
            System.out.println("- " + print);
        }

        // Por idade (mais legível)
        pessoas.sort(Comparator.comparing(Pessoa::idade));
        System.out.println("Por idade (moderno):");
        for (Pessoa print : pessoas) {
            System.out.println("- " + print);
        }

        // Por idade decrescente (mais legível)
        pessoas.sort(Comparator.comparing(Pessoa::idade).reversed());
        System.out.println("Por idade decrescente (moderno):");
        for (Pessoa print : pessoas) {
            System.out.println("- " + print);
        }

        System.out.println("\n--- ORDENAÇÃO MÚLTIPLA ---");

        // Ordenar por idade, depois por nome
        pessoas.sort(Comparator.comparing(Pessoa::idade).thenComparing(Pessoa::nome));
        System.out.println("\nPor idade, depois nome:");
        for(Pessoa print : pessoas) {
            System.out.println("- " + print);
        }

        pessoas.sort(Comparator.comparing(Pessoa::nome).thenComparing(Pessoa::idade));
        System.out.println("\nPor nome, depois idade:\n");
        for(Pessoa print : pessoas) {
            System.out.println("- " + print);
        }
        pessoas.sort(Comparator.comparing(Pessoa::nome).thenComparing(Comparator.comparing(Pessoa::idade).reversed()));
        System.out.println("\nPor nome, depois idade decrescente:\n");
        for(Pessoa print : pessoas) {
            System.out.println("- " + print);
        }
        System.out.println("\n--- COMPARAÇÕES NULL-SAFE ---");

        // Se algum nome puder ser null
        pessoas.sort(Comparator.comparing(Pessoa::nome,
                Comparator.nullsLast(String::compareTo)));

        System.out.println("\n--- IGNORAR MAIÚSCULA/MINÚSCULA ---");
        pessoas.sort(Comparator.comparing(Pessoa::nome,
                String.CASE_INSENSITIVE_ORDER));

        System.out.println("\n--- EXEMPLO: SISTEMA RH ---");

        // Ranking de funcionários: primeiro por idade (experiência),
        // depois por nome (desempate)

        pessoas.sort(Comparator.comparing(Pessoa::idade).reversed().thenComparing(Pessoa::nome));
        System.out.println("Ranking de senioridade:");
        for(int i = 0; i<pessoas.size();i++){
            System.out.println((i+1)+"º lugar: "+pessoas.get(i));
        }

    }


    public record Pessoa(String nome, int idade) implements Comparable<Pessoa> {
        @Override
        public int compareTo(Pessoa outra) {
            return this.nome.compareTo(outra.nome);
        }
    }
}


