package com.kaue.toolbox.collections;

import java.util.*;

public class ListsBasics {
    public static void main(String[] args) {
        System.out.println("=== DECLARAÇÃO E CRIAÇÃO ===");

        List<String> lista1 = new ArrayList<>();
        System.out.println("Lista vazia: " + lista1);
        //ADICIONAR UTILIZANDO O METODO ADD
        lista1.add("Banana");
        lista1.add("Maça");
        lista1.add("Mexerica");

        List<String> lista2 = new ArrayList<>(Arrays.asList("Java", "Python", "C++"));
        List<String> lista3 = List.of("A", "B", "C"); // Java 9+
        List<String> lista4 = new ArrayList<>(List.of("X", "Y", "Z"));


        lista4.add("A");

        System.out.println("Lista 1: " + lista1);
        System.out.println("Lista 2: " + lista2);
        System.out.println("Lista 3: " + lista3);
        System.out.println("Lista 4: " + lista4);


        // ========== MÉTODOS FUNDAMENTAIS ==========
        System.out.println("--- MÉTODOS BÁSICOS ---");
        List<String> frutas = new ArrayList<>(Arrays.asList("Maçã", "Banana", "Laranja"));
        System.out.println("Lista inicial: " + frutas);

        // ADD - adicionar elementos
        frutas.add("Uva");                    // adiciona no final
        frutas.add(1, "Manga");               // adiciona em posição específica
        System.out.println("Após add: " + frutas);

        // GET - acessar por índice
        System.out.println("Primeiro elemento: " + frutas.get(0));
        System.out.println("Último elemento: " + frutas.get(frutas.size() - 1));

        // SIZE - tamanho da lista
        System.out.println("Tamanho: " + frutas.size());

        // CONTAINS - verificar se contém elemento
        System.out.println("Contém 'Banana'? " + frutas.contains("Banana"));
        System.out.println("Contém 'Abacaxi'? " + frutas.contains("Abacaxi"));

        // INDEXOF / LASTINDEXOF - encontrar posição
        System.out.println("Posição da 'Banana': " + frutas.indexOf("Banana"));
        System.out.println("Posição de item inexistente: " + frutas.indexOf("Abacaxi")); // -1

        // ISEMPTY - verificar se está vazia
        System.out.println("Lista vazia? " + frutas.isEmpty());

        // TOARRAY - converter para array
        String[] array = frutas.toArray(new String[0]);
        System.out.println("Como array: " + Arrays.toString(array));

        //SUBLIST - pegar sublista (cuidado: é uma view!)
        List<String> sub = frutas.subList(1, 3);  // índices 1 e 2 (3 é exclusivo)
        System.out.println("Sublista [1,3): " + sub);

        //MODIFICAÇÃO
        System.out.println("\n--- MODIFICAÇÃO ---");
        List<String> cores = new ArrayList<>(Arrays.asList("Azul", "Verde", "Azul", "Vermelho"));
        System.out.println("Lista inicial: " + cores);

        // REMOVE - remover elementos
        cores.remove("Azul");           // remove primeira ocorrência por valor
        System.out.println("Remove 'Azul': " + cores);
        cores.remove(0);                // remove por índice
        System.out.println("Remove índice 0: " + cores);

        // SET - substituir elemento em posição específica
        cores.set(0, "Amarelo");        // substitui elemento no índice 0
        System.out.println("Set índice 0 = 'Amarelo': " + cores);

        // ADDALL - adicionar outra coleção
        cores.addAll(Arrays.asList("Preto", "Branco"));
        System.out.println("AddAll: " + cores);
        cores.addAll(1, Arrays.asList("Rosa", "Roxo")); // adiciona em posição específica
        System.out.println("AddAll no índice 1: " + cores);

        // REMOVEALL - remove todos os elementos de outra coleção
        cores.removeAll(Arrays.asList("Rosa", "Roxo"));
        System.out.println("RemoveAll: " + cores);

        // RETAINALL - mantém apenas elementos que estão em outra coleção
        List<String> manter = Arrays.asList("Amarelo", "Preto");
        cores.retainAll(manter);
        System.out.println("RetainAll: " + cores);

        //  - limpar toda a lista
        cores.clear();
        System.out.println("Clear: " + cores);
        System.out.println("Vazia após clear? " + cores.isEmpty());

        //ARRAYLIST VS LINKEDLIST
        System.out.println("\n--- ARRAYLIST VS LINKEDLIST ---");

        // CRIAÇÃO
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        // Adicionando elementos iniciais
        for (int i = 0; i < 5; i++) {
            arrayList.add("Item" + i);
            linkedList.add("Item" + i);
        }
        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);

        // ACESSO POR ÍNDICE - ArrayList é O(1), LinkedList é O(n)
        System.out.println("\n=== ACESSO POR ÍNDICE ===");
        System.out.println("ArrayList.get(2): " + arrayList.get(2));    // Rápido: acesso direto
        System.out.println("LinkedList.get(2): " + linkedList.get(2));  // Lento: percorre desde o início

        // INSERÇÃO NO MEIO - ArrayList é O(n), LinkedList é O(1) se tiver a posição
        System.out.println("\n=== INSERÇÃO NO MEIO ===");
        arrayList.add(2, "NovoItem");     // Precisa mover elementos para a direita
        linkedList.add(2, "NovoItem");    // Só muda os ponteiros (se souber a posição)
        System.out.println("Após inserção no índice 2:");
        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);

        // INSERÇÃO NO FINAL - Ambos são O(1) na maioria dos casos
        System.out.println("\n=== INSERÇÃO NO FINAL ===");
        arrayList.add("Final");
        linkedList.add("Final");

        // REMOÇÃO - ArrayList O(n) no meio, LinkedList O(1) se tiver a posição
        System.out.println("\n=== REMOÇÃO ===");
        arrayList.remove(0);     // Remove primeiro, move todos para esquerda
        linkedList.remove(0);    // Só ajusta ponteiros
        System.out.println("Após remoção do índice 0:");
        System.out.println("ArrayList: " + arrayList);
        System.out.println("LinkedList: " + linkedList);

        // RESUMO DAS DIFERENÇAS
        System.out.println("\n=== QUANDO USAR CADA UMA ===");
        System.out.println("ArrayList: Muito acesso por índice, poucas inserções/remoções no meio");
        System.out.println("LinkedList: Muitas inserções/remoções, pouco acesso por índice");

        // ---------------- EXEMPLOS PRÁTICOS ----------------
        System.out.println("\n--- EXEMPLOS PRÁTICOS ---");

        // EXEMPLO 1: CARRINHO DE COMPRAS
        System.out.println("=== CARRINHO DE COMPRAS ===");
        List<String> carrinho = new ArrayList<>();
        carrinho.add("Notebook");
        carrinho.add("Mouse");
        carrinho.add("Teclado");
        System.out.println("Carrinho: "+carrinho);

        // FOREACH - controle total da formatação
        System.out.println("Itens do carrinho:");
        for (String item : carrinho) {
            System.out.println("- " + item);
        }

        // Cliente mudou de ideia - remove item
        carrinho.remove("Mouse");
        System.out.println("Após remoção: " + carrinho);

        // Adiciona mais itens
        carrinho.addAll(Arrays.asList("Monitor", "Webcam"));
        System.out.println("Carrinho final: " + carrinho);
        System.out.println("Total de itens: " + carrinho.size());

        // EXEMPLO 2: LISTA DE TAREFAS (TODO LIST)
        System.out.println("\n=== TODO LIST ===");
        List<String> tarefas = new ArrayList<>(Arrays.asList(
                "Estudar Java", "Fazer exercícios", "Revisar código", "Fazer compras"
        ));
        System.out.println("Tarefas pendentes: " + tarefas);

        // Marcar tarefa como concluída (remover)
        String tarefaConcluida = "Estudar Java";
        if (tarefas.remove(tarefaConcluida)) {
            System.out.println("✅ Tarefa concluída: " + tarefaConcluida);
        }
        System.out.println("Tarefas restantes: " + tarefas);

        // Adicionar tarefa urgente no início
        tarefas.add(0, "URGENTE: Reunião às 14h");
        System.out.println("Com tarefa urgente: " + tarefas);

        // EXEMPLO 3: HISTÓRICO DE NAVEGAÇÃO
        System.out.println("\n=== HISTÓRICO NAVEGAÇÃO ===");
        List<String> historico = new ArrayList<>();
        historico.add("google.com");
        historico.add("github.com");
        historico.add("stackoverflow.com");
        historico.add("google.com"); // visitou novamente

        System.out.println("Histórico completo: " + historico);
        System.out.println("Última página: " + historico.get(historico.size() - 1));
        System.out.println("Quantas vezes visitou Google: " +
                Collections.frequency(historico, "google.com"));

        // Limpar histórico antigo (manter só os 2 últimos)
        while (historico.size() > 2) {
            historico.remove(0);
        }
        System.out.println("Histórico recente: " + historico);

    }
}
