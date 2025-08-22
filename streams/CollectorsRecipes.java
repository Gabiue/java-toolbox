
package streams;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 📊 COLLECTORS RECIPES - MANUAL DE REFERÊNCIA
 *
 * Demonstra as principais "receitas" de Collectors para:
 * - Agrupamento (groupingBy)
 * - Particionamento (partitioningBy)
 * - Estatísticas (summarizing, counting, averaging)
 * - Mapas customizados (toMap)
 * - Formatação de strings (joining)
 *
 * 🎯 OBJETIVO: Dominar os Collectors mais úteis para análise de dados
 */
public class CollectorsRecipes {

    // ========== CLASSE AUXILIAR ==========

    /**
     * 🏷️ PRODUTO: Classe para demonstrar Collectors
     * Representa produtos de um e-commerce com nome, preço e categoria
     */
    static class Produto {
        private final String nome;
        private final double preco;
        private final String categoria;

        public Produto(String nome, double preco, String categoria) {
            this.nome = nome;
            this.preco = preco;
            this.categoria = categoria;
        }

        // 📖 GETTERS
        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public String getCategoria() {
            return categoria;
        }

        @Override
        public String toString() {
            return nome + " (R$ " + String.format("%.2f", preco) + ")";
        }
    }



    // ========== MAIN: DEMONSTRAÇÃO COMPLETA ==========

    public static void main(String[] args) {
        System.out.println("📊 COLLECTORS RECIPES - MANUAL DE REFERÊNCIA\n");

        // 🏪 DADOS DO E-COMMERCE
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 2500.0, "Eletrônicos"),
                new Produto("Mouse", 50.0, "Eletrônicos"),
                new Produto("Mesa", 300.0, "Móveis"),
                new Produto("Cadeira", 200.0, "Móveis"),
                new Produto("Caneta", 5.0, "Material Escolar"),
                new Produto("Caderno", 20.0, "Material Escolar"),
                new Produto("Smartphone", 1500.0, "Eletrônicos"),
                new Produto("Fone de Ouvido", 150.0, "Eletrônicos"),
                new Produto("Estante", 400.0, "Móveis"),
                new Produto("Borracha", 3.0, "Material Escolar"),
                new Produto("Impressora", 800.0, "Eletrônicos")
        );

        System.out.println("🏪 E-COMMERCE - " + produtos.size() + " produtos:");
        produtos.forEach(produto -> System.out.println("  " + produto));
        System.out.println();

        // ========== GROUPING BY - AGRUPAMENTO ==========
        System.out.println("=== GROUPING BY - AGRUPAMENTO ===");

        // 📋 AGRUPAMENTO BÁSICO: Por categoria
        Map<String, List<Produto>> porCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println("Produtos agrupados por categoria:");
        porCategoria.forEach((categoria, listaProdutos) -> {
            System.out.println("📦 " + categoria + ":");
            listaProdutos.forEach(produto -> System.out.println("  - " + produto));
        });

        // ========== ESTATÍSTICAS POR CATEGORIA ==========
        System.out.println("\n=== ESTATÍSTICAS POR CATEGORIA ===");

        // 📊 CONTAGEM: Quantos produtos por categoria
        System.out.println("--- CONTAGEM ---");
        Map<String, Long> contagem = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.counting()));

        contagem.forEach((categoria, qtd) ->
                System.out.println("📊 " + categoria + ": " + qtd + " produtos"));

        // 💰 PREÇO MÉDIO: Média de preços por categoria
        System.out.println("\n--- PREÇO MÉDIO ---");
        Map<String, Double> precoMedio = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.averagingDouble(Produto::getPreco)));

        precoMedio.forEach((categoria, media) ->
                System.out.println("💰 " + categoria + ": R$ " + String.format("%.2f", media)));

        // 💸 PREÇO TOTAL: Soma de preços por categoria
        System.out.println("\n--- PREÇO TOTAL ---");
        Map<String, Double> precoTotal = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco)));

        precoTotal.forEach((categoria, total) ->
                System.out.println("💸 " + categoria + ": R$ " + String.format("%.2f", total)));

        // 📈 TODAS AS ESTATÍSTICAS: DoubleSummaryStatistics
        System.out.println("\n--- RESUMO ESTATÍSTICO COMPLETO ---");
        Map<String, DoubleSummaryStatistics> estatisticas = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.summarizingDouble(Produto::getPreco)));

        estatisticas.forEach((categoria, stats) -> {
            System.out.println("📈 " + categoria + ":");
            System.out.println("  📊 Quantidade: " + stats.getCount());
            System.out.println("  💸 Soma: R$ " + String.format("%.2f", stats.getSum()));
            System.out.println("  💰 Média: R$ " + String.format("%.2f", stats.getAverage()));
            System.out.println("  📉 Mínimo: R$ " + String.format("%.2f", stats.getMin()));
            System.out.println("  📊 Máximo: R$ " + String.format("%.2f", stats.getMax()));
            System.out.println();
        });

        // ========== PARTICIONAMENTO ==========
        System.out.println("=== PARTICIONAMENTO - DIVIDIR EM 2 GRUPOS ===");

        // ⚖️ PARTICIONAMENTO: Produtos caros vs baratos
        Map<Boolean, List<Produto>> carosVsBaratos = produtos.stream()
                .collect(Collectors.partitioningBy(produto -> produto.getPreco() >= 300));

        System.out.println("💸 Produtos baratos (< R$ 300):");
        carosVsBaratos.get(false).forEach(p -> System.out.println("  - " + p));

        System.out.println("\n💰 Produtos caros (>= R$ 300):");
        carosVsBaratos.get(true).forEach(p -> System.out.println("  - " + p));

        // 📊 ESTATÍSTICAS DO PARTICIONAMENTO
        System.out.println("\n--- ESTATÍSTICAS POR FAIXA DE PREÇO ---");
        Map<Boolean, Long> contagemPorFaixa = produtos.stream()
                .collect(Collectors.partitioningBy(produto -> produto.getPreco() >= 300,
                        Collectors.counting()));

        System.out.println("💸 Produtos baratos: " + contagemPorFaixa.get(false));
        System.out.println("💰 Produtos caros: " + contagemPorFaixa.get(true));

        // ========== TO MAP - MAPAS CUSTOMIZADOS ==========
        System.out.println("\n=== TO MAP - MAPAS CUSTOMIZADOS ===");

        // 🗂️ MAP SIMPLES: Nome → Preço (para lookup rápido)
        System.out.println("--- MAP PARA LOOKUP (nome → preço) ---");
        Map<String, Double> nomeParaPreco = produtos.stream()
                .collect(Collectors.toMap(Produto::getNome, Produto::getPreco));

        System.out.println("🗂️ Dicionário de preços criado:");
        nomeParaPreco.entrySet().stream()
                .limit(3) // Mostra só os primeiros 3
                .forEach(entry -> System.out.println("  " + entry.getKey() + " → R$ " +
                        String.format("%.2f", entry.getValue())));

        // 🔍 CONSULTAS RÁPIDAS
        System.out.println("\n🔍 Consultas rápidas:");
        System.out.println("Quanto custa o Notebook? R$ " +
                String.format("%.2f", nomeParaPreco.get("Notebook")));
        System.out.println("Quanto custa a Caneta? R$ " +
                String.format("%.2f", nomeParaPreco.get("Caneta")));

        // 🏆 MAP AVANÇADO: Categoria → Produto mais caro
        System.out.println("\n--- PRODUTO MAIS CARO POR CATEGORIA ---");
        Map<String, Optional<Produto>> produtoMaisCaro = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.maxBy(Comparator.comparing(Produto::getPreco))));

        System.out.println("🏆 Produto mais caro de cada categoria:");
        produtoMaisCaro.forEach((categoria, produtoOpt) -> {
            if (produtoOpt.isPresent()) {
                Produto produto = produtoOpt.get();
                System.out.println("  " + categoria + " → " + produto.getNome() +
                        " (R$ " + String.format("%.2f", produto.getPreco()) + ")");
            }
        });

        // ========== JOINING - FORMATAÇÃO DE STRINGS ==========
        System.out.println("\n=== JOINING - FORMATAÇÃO DE STRINGS ===");

        // 📝 JOINING SIMPLES: Lista de nomes
        String todosOsNomes = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.joining(", "));
        System.out.println("📝 Todos os produtos: " + todosOsNomes);

        // 🎨 JOINING COM FORMATAÇÃO: Prefixo e sufixo
        String nomesFormatados = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.joining(", ", "🛒 Carrinho: [", "]"));
        System.out.println("🎨 " + nomesFormatados);

        // 💎 JOINING POR CATEGORIA: Nomes dos produtos mais caros
        String produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() >= 300)
                .map(Produto::getNome)
                .collect(Collectors.joining(" | ", "💎 Produtos Premium: ", " 💎"));
        System.out.println(produtosCaros);

        // ========== VALORES EXTREMOS ==========
        System.out.println("\n=== VALORES EXTREMOS ===");

        // 💸 PRODUTO MAIS BARATO
        Optional<Produto> maisBarato = produtos.stream()
                .collect(Collectors.minBy(Comparator.comparing(Produto::getPreco)));

        System.out.println("💸 Produto mais barato:");
        maisBarato.ifPresent(produto -> System.out.println("  " + produto));

        // 💰 PRODUTO MAIS CARO
        Optional<Produto> maisCaro = produtos.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Produto::getPreco)));

        System.out.println("💰 Produto mais caro:");
        maisCaro.ifPresent(produto -> System.out.println("  " + produto));

        // 🎉 RESUMO FINAL
        System.out.println("=== RESUMO DOS COLLECTORS ===");
        System.out.println("✅ groupingBy() - Agrupar por critério");
        System.out.println("✅ partitioningBy() - Dividir em 2 grupos");
        System.out.println("✅ counting() - Contar elementos");
        System.out.println("✅ averagingDouble() - Calcular média");
        System.out.println("✅ summingDouble() - Somar valores");
        System.out.println("✅ summarizingDouble() - Todas as estatísticas");
        System.out.println("✅ toMap() - Criar mapas customizados");
        System.out.println("✅ maxBy()/minBy() - Encontrar extremos");
        System.out.println("✅ joining() - Formatar strings");

        System.out.println("\n🚀 COLLECTORS = Análise de dados + Relatórios + Dashboards!");
    }
}