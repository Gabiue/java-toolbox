package com.kaue.toolbox.collections;

import java.util.*;

public class SetsBasics {
    public static void main(String[] args) {
        // ========== DECLARAÇÃO E CRIAÇÃO ==========
        System.out.println("=== SETS - SEM DUPLICATAS ===");

        Set<String> frutas = new HashSet<>();
        System.out.println("Set vazio: " + frutas);

        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Maçã");     // duplicata!
        frutas.add("Laranja");
        frutas.add("Banana");   // duplicata!

        System.out.println("Após adicionar duplicatas: " + frutas);
        System.out.println("Tamanho: " + frutas.size());

        //Teste de número hash
        System.out.println("\n=== TESTANDO ORDEM ===");
        Set<Integer> numeros = new HashSet<>();
        for (int i = 1; i <= 999; i++) {
            //numeros.add(i*100);
            numeros.add(i);
        }
        System.out.println("Números 1-5: " + numeros);

        //AGORA COM NÚMEROS MAIORES
        System.out.println("\n=== TESTE COM NÚMEROS MAIORES ===");
        Set<Integer> numerosMaiores = new HashSet<>();
        numerosMaiores.add(1000);
        numerosMaiores.add(500);
        numerosMaiores.add(1500);
        numerosMaiores.add(100);
        numerosMaiores.add(2000);

        System.out.println("Números maiores: " + numerosMaiores);

        //HASHCODES
        System.out.println("\n=== HASH CODES ===");
        System.out.println("1000: " + Integer.valueOf(1000).hashCode());
        System.out.println("2000: " + Integer.valueOf(2000).hashCode());
        System.out.println("Maçã: " + "Maçã".hashCode());
        System.out.println("Banana: " + "Banana".hashCode());

        // ---------------- MÉTODOS BÁSICOS ----------------
        System.out.println("\n--- MÉTODOS BÁSICOS ---");
        Set<String> linguagens = new HashSet<>(Arrays.asList("Java", "Python", "C++"));
        System.out.println("Set inicial: " + linguagens);

        //ADD BOOLEAN
        boolean adicionou1 = linguagens.add("JavaScript");  // novo elemento
        boolean adicionou2 = linguagens.add("Java");        // já existe
        System.out.println("Adicionou JavaScript? " + adicionou1);  // true
        System.out.println("Adicionou Java novamente? " + adicionou2);  // false
        System.out.println("Após add: " + linguagens);

        //SIZE - tamanho do conjunto
        System.out.println("Tamanho: " + linguagens.size());

        // CONTAINS - verificar se contém elemento
        System.out.println("Contém 'Python'? " + linguagens.contains("Python"));
        System.out.println("Contém 'Ruby'? " + linguagens.contains("Ruby"));

        // REMOVE - remover elemento (retorna boolean)
        boolean removeu1 = linguagens.remove("C++");     // existe
        boolean removeu2 = linguagens.remove("Ruby");    // não existe
        System.out.println("Removeu C++? " + removeu1);       // true
        System.out.println("Removeu Ruby? " + removeu2);      // false
        System.out.println("Após remove: " + linguagens);

        // ISEMPTY - verificar se está vazio
        System.out.println("Está vazio? " + linguagens.isEmpty());

        // CLEAR - limpar tudo
        Set<String> copia = new HashSet<>(linguagens);
        copia.clear();
        System.out.println("Após clear: " + copia);
        System.out.println("Cópia vazia? " + copia.isEmpty());

        //FOREACH
        System.out.println("Iterando:");
        for (String lang : linguagens) {
            System.out.println("- " + lang);
        }
        // ---------------- TIPOS DE SET ----------------
        System.out.println("\n--- TIPOS DE SET ---");

        // HASHSET - sem ordem, mais rápido
        System.out.println("=== HASHSET ===");
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(Arrays.asList("C", "A", "B", "D"));
        System.out.println("HashSet: " + hashSet);  // ordem imprevisível

        // TREESET - sempre ordenado (alfabética/numérica)
        System.out.println("\n=== TREESET ===");
        Set<String> treeSet = new TreeSet<>();
        treeSet.addAll(Arrays.asList("C", "A", "B", "D"));
        System.out.println("TreeSet: " + treeSet);  // [A, B, C, D] - sempre ordenado!

        // LINKEDHASHSET - mantém ordem de inserção
        System.out.println("\n=== LINKEDHASHSET ===");
        Set<String> linkedSet = new LinkedHashSet<>();
        linkedSet.addAll(Arrays.asList("C", "A", "B", "D"));
        System.out.println("LinkedHashSet: " + linkedSet);  // [C, A, B, D] - ordem de inserção

        // PERFORMANCE - qual é mais rápido?
        System.out.println("\n=== PERFORMANCE ===");
        System.out.println("HashSet:       O(1) - mais rápido para add/contains/remove");
        System.out.println("LinkedHashSet: O(1) - um pouco mais lento que HashSet");
        System.out.println("TreeSet:       O(log n) - mais lento, mas sempre ordenado");

        // EXEMPLO COM NÚMEROS
        System.out.println("\n=== EXEMPLO COM NÚMEROS ===");
        Set<Integer> hashNum = new HashSet<>(Arrays.asList(5, 1, 9, 3));
        Set<Integer> treeNum = new TreeSet<>(Arrays.asList(5, 1, 9, 3));
        Set<Integer> linkedNum = new LinkedHashSet<>(Arrays.asList(5, 1, 9, 3));

        System.out.println("HashSet números:       " + hashNum);    // ordem estranha
        System.out.println("TreeSet números:       " + treeNum);    // [1, 3, 5, 9]
        System.out.println("LinkedHashSet números: " + linkedNum);  // [5, 1, 9, 3]

        // ---------------- OPERAÇÕES DE CONJUNTO ----------------
        System.out.println("\n--- OPERAÇÕES DE CONJUNTO ---");

        // Conjuntos base para exemplos
        Set<String> desenvolvedores = new HashSet<>(Arrays.asList("Ana", "João", "Maria", "Pedro"));
        Set<String> designers = new HashSet<>(Arrays.asList("Maria", "Pedro", "Carlos", "Lucia"));

        System.out.println("Desenvolvedores: " + desenvolvedores);
        System.out.println("Designers: " + designers);

        // UNIÃO - todos os elementos (sem duplicatas)
        System.out.println("\n=== UNIÃO ===");
        Set<String> uniao = new HashSet<>(desenvolvedores);
        uniao.addAll(designers);
        System.out.println("União (todos): " + uniao);

        // INTERSEÇÃO - elementos em comum
        System.out.println("\n=== INTERSEÇÃO ===");
        Set<String> intersecao = new HashSet<>(desenvolvedores);
        intersecao.retainAll(designers);
        System.out.println("Interseção (em comum): " + intersecao);

        // DIFERENÇA - elementos que estão em A mas não em B
        System.out.println("\n=== DIFERENÇA ===");
        Set<String> diferenca = new HashSet<>(desenvolvedores);
        diferenca.removeAll(designers);
        System.out.println("Só desenvolvedores: " + diferenca);

        // DIFERENÇA SIMÉTRICA - elementos que estão em A ou B, mas não em ambos
        System.out.println("\n=== DIFERENÇA SIMÉTRICA ===");
        Set<String> difSimetrica = new HashSet<>(uniao);
        difSimetrica.removeAll(intersecao);
        System.out.println("Ou dev OU designer (não ambos): " + difSimetrica);

        // SUBCONJUNTO - verificar se um conjunto está contido no outro
        System.out.println("\n=== SUBCONJUNTO ===");
        Set<String> seniors = new HashSet<>(Arrays.asList("Ana", "João"));
        boolean ehSubconjunto = desenvolvedores.containsAll(seniors);
        System.out.println("Seniors é subconjunto de Desenvolvedores? " + ehSubconjunto);

        // CONJUNTOS DISJUNTOS - não têm elementos em comum
        System.out.println("\n=== DISJUNTOS ===");
        Set<String> backend = new HashSet<>(Arrays.asList("Java", "Python"));
        Set<String> frontend = new HashSet<>(Arrays.asList("HTML", "CSS"));
        boolean saoDisjuntos = Collections.disjoint(backend, frontend);
        System.out.println("Backend e Frontend são disjuntos? " + saoDisjuntos);

        // ---------------- EXEMPLOS PRÁTICOS ----------------
        System.out.println("\n--- EXEMPLOS PRÁTICOS ---");

        // EXEMPLO 1: SISTEMA DE PERMISSÕES
        System.out.println("=== SISTEMA DE PERMISSÕES ===");
        Set<String> permissoesUsuario = new HashSet<>(Arrays.asList("ler", "escrever", "comentar"));
        Set<String> permissoesNecessarias = new HashSet<>(Arrays.asList("escrever", "deletar"));

        System.out.println("Permissões do usuário: " + permissoesUsuario);
        System.out.println("Permissões necessárias: " + permissoesNecessarias);

        // INTERSEÇÃO: o que ele pode fazer do que precisa?
        Set<String> podeRealizar = new HashSet<>(permissoesUsuario);
        podeRealizar.retainAll(permissoesNecessarias);
        System.out.println("✅ Pode realizar: " + podeRealizar);

        // DIFERENÇA: o que falta para ele?
        Set<String> faltaPermissao = new HashSet<>(permissoesNecessarias);
        faltaPermissao.removeAll(permissoesUsuario);
        System.out.println("❌ Precisa liberar: " + faltaPermissao);

        // EXEMPLO 2: E-COMMERCE - PRODUTOS
        System.out.println("\n=== E-COMMERCE ===");
        Set<String> produtosFavoritos = new HashSet<>(Arrays.asList("Notebook", "Mouse", "Teclado"));
        Set<String> produtosCarrinho = new HashSet<>(Arrays.asList("Mouse", "Monitor", "Webcam"));

        System.out.println("Produtos favoritos: " + produtosFavoritos);
        System.out.println("Produtos no carrinho: " + produtosCarrinho);

        // INTERSEÇÃO: itens já no carrinho que são favoritos
        Set<String> favoritosNoCarrinho = new HashSet<>(produtosFavoritos);
        favoritosNoCarrinho.retainAll(produtosCarrinho);
        System.out.println("✅ Favoritos já no carrinho: " + favoritosNoCarrinho);

        // DIFERENÇA: favoritos que faltam adicionar
        Set<String> favoritosFaltando = new HashSet<>(produtosFavoritos);
        favoritosFaltando.removeAll(produtosCarrinho);
        System.out.println("💡 Sugestões para adicionar: " + favoritosFaltando);

        // EXEMPLO 3: SISTEMA DE NOTIFICAÇÕES
        System.out.println("\n=== NOTIFICAÇÕES ===");
        Set<String> usuariosOntem = new HashSet<>(Arrays.asList("user1", "user2", "user3"));
        Set<String> usuariosHoje = new HashSet<>(Arrays.asList("user2", "user4", "user5"));

        System.out.println("Usuários ontem: " + usuariosOntem);
        System.out.println("Usuários hoje: " + usuariosHoje);

        // DIFERENÇA: novos usuários (enviar boas-vindas)
        Set<String> novosUsuarios = new HashSet<>(usuariosHoje);
        novosUsuarios.removeAll(usuariosOntem);
        System.out.println("🎉 Enviar boas-vindas: " + novosUsuarios);

        // DIFERENÇA: usuários inativos (enviar "sentimos sua falta")
        Set<String> usuariosInativos = new HashSet<>(usuariosOntem);
        usuariosInativos.removeAll(usuariosHoje);
        System.out.println("😢 Reativar conta: " + usuariosInativos);

        // INTERSEÇÃO: usuários ativos nos dois dias
        Set<String> usuariosAtivos = new HashSet<>(usuariosOntem);
        usuariosAtivos.retainAll(usuariosHoje);
        System.out.println("🔥 Usuários engajados: " + usuariosAtivos);

        // EXEMPLO 4: TAGS DE POSTS
        System.out.println("\n=== SISTEMA DE TAGS ===");
        Set<String> tagsPost1 = new HashSet<>(Arrays.asList("java", "programacao", "tutorial"));
        Set<String> tagsPost2 = new HashSet<>(Arrays.asList("programacao", "python", "iniciante"));

        // INTERSEÇÃO: tags em comum (posts relacionados)
        Set<String> tagsComuns = new HashSet<>(tagsPost1);
        tagsComuns.retainAll(tagsPost2);
        System.out.println("🔗 Posts relacionados por: " + tagsComuns);

        // UNIÃO: todas as tags (nuvem de tags)
        Set<String> todasTags = new HashSet<>(tagsPost1);
        todasTags.addAll(tagsPost2);
        System.out.println("☁️ Nuvem de tags: " + todasTags);

        // EXEMPLO 5: CONTROLE DE ACESSO A RECURSOS
        System.out.println("\n=== CONTROLE DE ACESSO ===");
        Set<String> recursosDisponiveis = new HashSet<>(Arrays.asList("database", "api", "logs", "admin"));
        Set<String> recursosSolicitados = new HashSet<>(Arrays.asList("database", "api", "backup"));

        // INTERSEÇÃO: recursos que pode acessar
        Set<String> podeAcessar = new HashSet<>(recursosDisponiveis);
        podeAcessar.retainAll(recursosSolicitados);
        System.out.println("✅ Acesso liberado: " + podeAcessar);

        // DIFERENÇA: recursos negados
        Set<String> acessoNegado = new HashSet<>(recursosSolicitados);
        acessoNegado.removeAll(recursosDisponiveis);
        System.out.println("🚫 Acesso negado: " + acessoNegado);


    }
}
