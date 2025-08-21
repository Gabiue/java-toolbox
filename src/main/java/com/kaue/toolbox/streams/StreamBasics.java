package com.kaue.toolbox.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasics {
    public static void main(String[] args) {
        System.out.println("=== STREAMS - FUNDAMENTOS ===");
        // Dados base para exemplos
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carlos", "Diana", "Eduardo");

        System.out.println("Lista original: " + numeros);
        System.out.println("Nomes originais: " + nomes);

        //CRIAÇÃO DE STREAMS
        System.out.println("\n--- CRIAÇÃO DE STREAMS ---");
        Stream<Integer> streamNumeros = numeros.stream();
        Stream<String> streamNomes = nomes.stream();
        Stream<String> streamCriado = Stream.of("Java", "Python", "C++");

        System.out.println("\nStream criado com Stream.of(): ");
        streamCriado.forEach(lang -> System.out.println("- " + lang));

        System.out.println("\nStream criado com Stream.of(): ");
        streamNumeros.forEach(lang -> System.out.println("- " + lang));

        System.out.println("\nStream criado com Stream.of(): ");
        streamNomes.forEach(lang -> System.out.println("- " + lang));

        // ---------------- FILTER (FILTRAR) ----------------
        System.out.println("\n--- FILTER - FILTRAGEM ---");

        // NÚMEROS PARES
        List<Integer> pares = numeros.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Números pares: " + pares);

        // NÚMEROS MAIORES QUE 5
        List<Integer> maiores = numeros.stream().filter(n -> n > 5).collect(Collectors.toList());
        System.out.println("Maiores que 5: " + maiores);

        // NOMES COM MAIS DE 4 LETRAS
        List<String> nomesGrandes = nomes.stream().filter(nome -> nome.length() > 4).collect(Collectors.toList());
        System.out.println("Nomes com mais de 4 letras: " + nomesGrandes);

        // MÚLTIPLOS FILTROS (AND lógico)
        List<Integer> paresEGrandes = numeros.stream().filter(n -> n % 2 == 0)      // primeiro filtro: pares
                .filter(n -> n > 5)           // segundo filtro: maiores que 5
                .collect(Collectors.toList());
        System.out.println("Pares E maiores que 5: " + paresEGrandes);

        //NUMEROS MENORES QUE 5 E IMPARES
        List<Integer> impares = numeros.stream().filter(n -> n % 2 != 0).filter(n -> n < 5).collect(Collectors.toList());
        System.out.println("Números Impares menores que 5: " + impares);

        //FILTER OR

        List<Integer> paresOuEight = numeros.stream().filter(n -> n % 2 == 0 || n > 8).collect(Collectors.toList());
        System.out.println("Números pares ou maiores que 8: " + paresOuEight);

        // ---------------- MAP (TRANSFORMAR) ----------------
        System.out.println("\n--- MAP - TRANSFORMAÇÕES ---");

        // DOBRAR NÚMEROS
        List<Integer> dobrados = numeros.stream().map(n -> n * 2)  //
                .collect(Collectors.toList());
        System.out.println("Números dobrados: " + dobrados);

        // NOMES EM MAIÚSCULA
        List<String> maiusculas = nomes.stream().map(n -> n.toUpperCase())  //
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúscula: " + maiusculas);

        // RAIZ QUADRADA DOS NÚMEROS
        List<Double> raizes = numeros.stream().map(n -> Math.sqrt(n))  //
                .collect(Collectors.toList());
        System.out.println("Raízes quadradas: " + raizes);

        // TAMANHO DOS NOMES
        List<Integer> tamanhos = nomes.stream().map(nome -> nome.length()).collect(Collectors.toList());
        System.out.println("Tamanhos dos nomes: " + tamanhos);

        // COMBINANDO FILTER + MAP
        List<String> nomesGrandesEmMaiuscula = nomes.stream().filter(nome -> nome.length() > 4).map(nome -> nome.toUpperCase()).collect(Collectors.toList());
        System.out.println("Nomes grandes em maiúscula: " + nomesGrandesEmMaiuscula);

        //---------------- COLLECT (COLETAR) ----------------
        System.out.println("\n--- COLLECT - DIFERENTES COLETORES ---");

        // PARA LIST
        List<Integer> comoLista = numeros.stream().filter(n -> n > 5).collect(Collectors.toList());
        System.out.println("Como List: " + comoLista);

        // PARA SET (remove duplicatas)
        List<Integer> comDuplicatas = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5);
        Set<Integer> comoSet = comDuplicatas.stream().collect(Collectors.toSet());
        System.out.println("Como Set (sem duplicatas): " + comoSet);

        // JOINING - JUNTAR STRINGS COM SEPARADOR
        String nomesJuntos = nomes.stream().collect(Collectors.joining(", "));
        System.out.println("Nomes com vírgula: " + nomesJuntos);

        // JOINING COM PREFIXO E SUFIXO
        String nomesFormatados = nomes.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println("Nomes formatados: " + nomesFormatados);

        // COUNT - CONTAR (não é collect!)
        long quantidade = numeros.stream().filter(n -> n % 2 == 0).count();
        System.out.println("Quantidade de pares: " + quantidade);

        // SOMA DOS NÚMEROS (mapToInt + sum)
        int soma = numeros.stream().mapToInt(n -> n)    // converte para IntStream
                .sum();              // soma tudo
        System.out.println("Soma de todos: " + soma);

        // MAIOR E MENOR VALOR
        Optional<Integer> maior = numeros.stream().max(Integer::compareTo);
        Optional<Integer> menor = numeros.stream().min(Integer::compareTo);
        System.out.println("Maior: " + maior.orElse(0));
        System.out.println("Menor: " + menor.orElse(0));

        // ---------------- EXEMPLOS PRÁTICOS ----------------
        System.out.println("\n--- EXEMPLOS PRÁTICOS ---");

        // EXEMPLO 1: E-COMMERCE - PRODUTOS
        System.out.println("=== E-COMMERCE ===");
        List<Produto> produtos = Arrays.asList(new Produto("Notebook", 2500.0, "Eletrônicos"), new Produto("Mouse", 50.0, "Eletrônicos"), new Produto("Teclado", 150.0, "Eletrônicos"), new Produto("Mesa", 300.0, "Móveis"), new Produto("Cadeira", 200.0, "Móveis"));

        // Produtos caros (acima de R$ 200)
        List<String> produtosCaros = produtos.stream().filter(p -> p.preco > 200).map(p -> p.nome).collect(Collectors.toList());
        System.out.println("Produtos caros: " + produtosCaros);

        // Valor total dos eletrônicos
        double totalEletronicos = produtos.stream().filter(p -> p.categoria.equals("Eletrônicos")).mapToDouble(p -> p.preco).sum();
        System.out.println("Total eletrônicos: R$ " + totalEletronicos);

        // Produto mais caro
        Optional<Produto> maisCaro = produtos.stream().max((p1, p2) -> Double.compare(p1.preco, p2.preco));
        System.out.println("Mais caro: " + maisCaro.get().nome + " - R$ " + maisCaro.get().preco);

        // EXEMPLO 2: ANÁLISE DE IDADES
        System.out.println("\n=== ANÁLISE DE PESSOAS ===");
        List<Integer> idades = Arrays.asList(17, 25, 30, 16, 40, 22, 35, 15, 28);

        // Quantos adultos (>= 18)?
        long adultos = idades.stream().filter(idade -> idade >= 18).count();
        System.out.println("Quantidade de adultos: " + adultos);

        // Média das idades dos adultos
        OptionalDouble mediaAdultos = idades.stream().filter(idade -> idade >= 18).mapToInt(idade -> idade).average();
        System.out.println("Média idade adultos: " + mediaAdultos.orElse(0.0));

        // EXEMPLO 3: PROCESSAMENTO DE TEXTO
        System.out.println("\n=== PROCESSAMENTO DE TEXTO ===");
        List<String> frases = Arrays.asList("Java é incrível", "Stream facilita muito", "Programação é arte", "Código limpo é essencial");

        // Todas as palavras únicas (sem duplicatas)
        Set<String> palavrasUnicas = frases.stream().flatMap(frase -> Arrays.stream(frase.split(" "))).map(palavra -> palavra.toLowerCase()).collect(Collectors.toSet());
        System.out.println("Palavras únicas: " + palavrasUnicas);

        // Frases que têm mais de 15 caracteres
        List<String> frasesLongas = frases.stream().filter(frase -> frase.length() > 15).collect(Collectors.toList());
        System.out.println("Frases longas: " + frasesLongas);

        // ---------------- OUTRAS OPERAÇÕES ÚTEIS ----------------
        System.out.println("\n--- OUTRAS OPERAÇÕES ---");

        // DISTINCT - remover duplicatas
        List<Integer> comDuplicatas2 = Arrays.asList(1, 2, 2, 3, 3, 4, 1, 5);
        List<Integer> semDuplicatas = comDuplicatas2.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Sem duplicatas: " + semDuplicatas);

        // SORTED - ordenar
        List<Integer> desordenados = Arrays.asList(5, 1, 9, 3, 7);
        List<Integer> ordenados = desordenados.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Ordenados: " + ordenados);

        // LIMIT - pegar só os primeiros N
        List<Integer> primeiros3 = numeros.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("Primeiros 3: " + primeiros3);

        // SKIP - pular os primeiros N
        List<Integer> semPrimeiros3 = numeros.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("Sem primeiros 3: " + semPrimeiros3);

        // ANYMATCH - existe algum que atende condição?
        boolean temPar = numeros.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("Tem algum par? " + temPar);

        // ALLMATCH - todos atendem a condição?
        boolean todosPositivos = numeros.stream()
                .allMatch(n -> n > 0);
        System.out.println("Todos positivos? " + todosPositivos);

        // FINDFIRST - pegar o primeiro que atende
        Optional<Integer> primeiroPar = numeros.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();
        System.out.println("Primeiro par: " + primeiroPar.orElse(-1));

    }


    static class Produto {
        String nome;
        double preco;
        String categoria;

        Produto(String nome, double preco, String categoria) {
            this.nome = nome;
            this.preco = preco;
            this.categoria = categoria;
        }
    }
}




