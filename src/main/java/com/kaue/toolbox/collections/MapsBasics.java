package com.kaue.toolbox.collections;

import java.util.*;

public class MapsBasics {
    public static void main(String[] args) {

        // ========== DECLARAÇÃO E CRIAÇÃO ==========
        System.out.println("=== MAPS - CHAVE-VALOR ===");

        // Forma básica
        Map<String, Integer> idades = new HashMap<>();
        System.out.println("Map vazio: " + idades);
        idades.put("Gabriel", 20);
        idades.put("Noemi",19);
        idades.put("Bruno", 21);
        System.out.println("Map vazio: " + idades);
        System.out.println(idades.get("Gabriel"));
        int idade = idades.get("Gabriel");
        System.out.println(idades.containsKey("Noemi"));

        idades.put("Bruno", 22);
        System.out.println("Map: " + idades);

        System.out.println("Inexistente: " + idades.get("PessoaQueNaoExiste"));

        // ---------------- MÉTODOS BÁSICOS ----------------
        System.out.println("\\n--- MÉTODOS BÁSICOS ---");

        // PUT - adicionar/atualizar (retorna valor anterior ou null)
        Integer anterior1 = idades.put("Ana", 25);         // null (não existia)
        Integer anterior2 = idades.put("João", 30);        // null (não existia)
        Integer anterior3 = idades.put("Ana", 26);         // 25 (valor anterior)
        System.out.println("Valor anterior da Ana: " + anterior3);
        System.out.println("Map atual: " + idades);

        // GET - obter valor pela chave
        Integer idadeAna = idades.get("Ana");               // 26
        Integer idadeInexistente = idades.get("Maria");     // null
        System.out.println("Idade da Ana: " + idadeAna);
        System.out.println("Idade inexistente: " + idadeInexistente);

        // CONTAINSKEY - verificar se chave existe
        System.out.println("Contém 'João'? " + idades.containsKey("João"));     // true
        System.out.println("Contém 'Maria'? " + idades.containsKey("Maria"));   // false

        // CONTAINSVALUE - verificar se valor existe
        System.out.println("Alguém tem 30 anos? " + idades.containsValue(30));  // true
        System.out.println("Alguém tem 50 anos? " + idades.containsValue(50));  // false

        // SIZE - quantidade de elementos
        System.out.println("Quantidade de pessoas: " + idades.size());

        // ISEMPTY - verificar se está vazio
        System.out.println("Map vazio? " + idades.isEmpty());

        // REMOVE - remover por chave (retorna valor removido)
        Integer removido = idades.remove("João");          // 30
        Integer naoRemovido = idades.remove("Pedro");      // null (não existia)
        System.out.println("Valor removido: " + removido);
        System.out.println("Após remoção: " + idades);

        // GETORDEFAULT - pegar valor ou retornar padrão se não existir
        Integer idadePadrao = idades.getOrDefault("Maria", 0);  // 0 (não existe)
        Integer idadeExistente = idades.getOrDefault("Ana", 0); // 26 (existe)
        System.out.println("Idade com padrão: " + idadePadrao);
        System.out.println("Idade existente: " + idadeExistente);

        // CLEAR - limpar tudo
        Map<String, Integer> copia = new HashMap<>(idades);
        copia.clear();
        System.out.println("Após clear: " + copia);

        // ---------------- TIPOS DE MAP ----------------
        System.out.println("\\n--- TIPOS DE MAP ---");

        // HASHMAP - sem ordem, mais rápido
        System.out.println("=== HASHMAP ===");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("C", 3);
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("D", 4);
        System.out.println("HashMap: " + hashMap);  // ordem imprevisível

        // TREEMAP - sempre ordenado por chave (alfabética/numérica)
        System.out.println("\\n=== TREEMAP ===");
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("C", 3);
        treeMap.put("A", 1);
        treeMap.put("B", 2);
        treeMap.put("D", 4);
        System.out.println("TreeMap: " + treeMap);  // {A=1, B=2, C=3, D=4} - sempre ordenado!

        // LINKEDHASHMAP - mantém ordem de inserção
        System.out.println("\\n=== LINKEDHASHMAP ===");
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("C", 3);
        linkedMap.put("A", 1);
        linkedMap.put("B", 2);
        linkedMap.put("D", 4);
        System.out.println("LinkedHashMap: " + linkedMap);  // {C=3, A=1, B=2, D=4} - ordem de inserção

        // PERFORMANCE - qual é mais rápido?
        System.out.println("\\n=== PERFORMANCE ===");
        System.out.println("HashMap:       O(1) - mais rápido para put/get/remove");
        System.out.println("LinkedHashMap: O(1) - um pouco mais lento que HashMap");
        System.out.println("TreeMap:       O(log n) - mais lento, mas sempre ordenado por chave");

        // EXEMPLO PRÁTICO: RANKING DE JOGADORES
        System.out.println("\\n=== EXEMPLO: RANKING ===");
        Map<String, Integer> hashScore = new HashMap<>();
        Map<String, Integer> treeScore = new TreeMap<>();  // por nome alfabético
        Map<String, Integer> linkedScore = new LinkedHashMap<>(); // por ordem de chegada

        // Adicionando pontuações
        String[] jogadores = {"Bruno", "Ana", "Carlos", "Diana"};
        int[] pontos = {150, 200, 175, 180};

        for (int i = 0; i < jogadores.length; i++) {
            hashScore.put(jogadores[i], pontos[i]);
            treeScore.put(jogadores[i], pontos[i]);
            linkedScore.put(jogadores[i], pontos[i]);
        }

        System.out.println("HashMap (ordem estranha): " + hashScore);
        System.out.println("TreeMap (alfabético): " + treeScore);
        System.out.println("LinkedHashMap (chegada): " + linkedScore);

        //ITERAÇÃO


        System.out.println("=== SÓ AS CHAVES ===");
        for (String nome : idades.keySet()) {
            System.out.println("Nome: " + nome);
        }

        // FORMA 2: SÓ OS VALORES
        System.out.println("\n=== SÓ OS VALORES ===");
        for (Integer idadeTest: idades.values()) {
            System.out.println("Idade: " + idadeTest);
        }
        System.out.println("\n=== CHAVE E VALOR (entrySet) ===");
        for(Map.Entry<String, Integer> entry : idades.entrySet()){
            String nomeEntry = entry.getKey();
            Integer idadeEntry = entry.getValue();
            System.out.println(nomeEntry + " tem " + idadeEntry + " anos");
        }
        System.out.println("\n=== CHAVE E VALOR (keySet + get) ===");
        for (String nome : idades.keySet()) {
            Integer idadeEntry = idades.get(nome);
            System.out.println(nome + " tem " + idadeEntry + " anos");
        }

        System.out.println("\n=== FOREACH COM LAMBDA ===");
        idades.forEach((nome, idadeEntry) -> {
            System.out.println(nome + " tem " + idadeEntry + " anos");
        });

        //---------------- OPERAÇÕES ÚTEIS ----------------
        System.out.println("\n--- OPERAÇÕES ÚTEIS ---");
        Map<String, Integer> precos = new HashMap<>();
        precos.put("Notebook", 2500);
        precos.put("Mouse", 50);
        precos.put("Teclado", 150);

        // PUTIFABSENT - adiciona apenas se chave NÃO existir
        System.out.println("=== PUTIFABSENT ===");
        Integer resultadoMonitor = precos.putIfAbsent("Monitor", 800);    // null (não existia)
        Integer resultadoMouse = precos.putIfAbsent("Mouse", 999);        // 50 (já existia, não mudou)
        System.out.println("Monitor adicionado? " + (resultadoMonitor == null));  // true
        System.out.println("Mouse alterado? " + (resultadoMouse == null));         // false
        System.out.println("Preços: " + precos);

        // REPLACE - substitui apenas se chave existir
        System.out.println("\n=== REPLACE ===");
        Integer mouseAntigo = precos.replace("Mouse", 75);               // 50 (existia)
        Integer webcamAntiga = precos.replace("Webcam", 200);            // null (não existia)
        System.out.println("Mouse alterado de: " + mouseAntigo);
        System.out.println("Webcam foi criada? " + (webcamAntiga != null));  // false

        // REPLACE (versão com valor atual) - substitui apenas se valor atual for específico
        System.out.println("\n=== REPLACE CONDICIONAL ===");
        boolean tecladoAlterado = precos.replace("Teclado", 150, 180);   // true (valor era 150)
        boolean notebookAlterado = precos.replace("Notebook", 999, 3000); // false (valor não era 999)
        System.out.println("Teclado alterado? " + tecladoAlterado);
        System.out.println("Notebook alterado? " + notebookAlterado);

        // COMPUTE - calcula novo valor baseado na chave
        System.out.println("\n=== COMPUTE ===");
        precos.compute("Mouse", (chave, valorAtual) -> valorAtual + 10);  // 75 + 10 = 85
        precos.compute("Webcam", (chave, valorAtual) -> 300);             // cria com 300
        System.out.println("Após compute: " + precos);

        // MERGE - mescla valores quando chave já existe
        System.out.println("\n=== MERGE ===");
        Map<String, Integer> vendas = new HashMap<>();
        vendas.put("Notebook", 5);
        vendas.put("Mouse", 10);

        // Adicionar mais vendas (merge soma os valores)
        vendas.merge("Mouse", 3, Integer::sum);        // 10 + 3 = 13
        vendas.merge("Teclado", 7, Integer::sum);      // primeira vez = 7
        vendas.merge("Notebook", 2, Integer::sum);     // 5 + 2 = 7

        System.out.println("Vendas totais: " + vendas);

        // ---------------- EXEMPLOS PRÁTICOS ----------------
        System.out.println("\n--- EXEMPLOS PRÁTICOS ---");

        // EXEMPLO: CONTADOR DE PALAVRAS
        System.out.println("=== CONTADOR DE PALAVRAS ===");
        String textoExp = "java e java e python";
        Map<String, Integer> contadorExp = new HashMap<>();

        String[] palavrasExp = textoExp.split(" ");
        for (String palavraExp : palavrasExp) {
            if (contadorExp.containsKey(palavraExp)) {
                contadorExp.put(palavraExp, contadorExp.get(palavraExp) + 1);
            } else {
                contadorExp.put(palavraExp, 1);
            }
        }

        System.out.println("Texto: " + textoExp);
        System.out.println("Contador: " + contadorExp);



    }



}