package utilities;
import java.util.Objects;
import java.util.Collection;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Preconditions.java - Validações Robustas e Fail-Fast
 *
 * CONCEITOS IMPORTANTES:
 * - Objects.requireNonNull() - Validação padrão do Java
 * - Fail-fast principle - Falhar cedo é melhor que falhar tarde
 * - Preconditions customizadas - Validações de negócio específicas
 * - Supplier<String> - Mensagens lazy (só criadas se necessário)
 */
public class Preconditions {

    public static void main(String[] args) {
        System.out.println("=== PRECONDITIONS E VALIDAÇÕES ===\n");

        // 1. VALIDAÇÕES BÁSICAS COM OBJECTS
        demonstrarObjectsBasico();

        // 2. PRECONDITIONS CUSTOMIZADAS
        demonstrarPreconditionsCustomizadas();

        // 3. VALIDAÇÕES AVANÇADAS
        demonstrarValidacoesAvancadas();

        // 4. CASO PRÁTICO: SISTEMA DE CADASTRO COM VALIDAÇÕES
        sistemaCadastroComValidacoes();
    }

    /**
     * --- VALIDAÇÕES BÁSICAS COM OBJECTS ---
     * A classe Objects do Java oferece métodos úteis para validações
     */
    private static void demonstrarObjectsBasico() {
        System.out.println("--- VALIDAÇÕES BÁSICAS COM OBJECTS ---");

        // 1. requireNonNull() - Lança NullPointerException se null
        System.out.println("1. REQUIRE NON NULL:");
        try {
            String nome = "João";
            String nomeValidado = requireNonNull(nome, "Nome não pode ser null");
            System.out.println("   Nome válido: " + nomeValidado);

            // Tentativa com null
            String nomeNull = null;
            requireNonNull(nomeNull, "Nome não pode ser null");

        } catch (NullPointerException e) {
            System.out.println("   Erro capturado: " + e.getMessage());
        }

        // 2. requireNonNullElse() - Retorna valor padrão se null
        System.out.println("\n2. REQUIRE NON NULL ELSE:");
        String nomeUsuario = null;
        String nomeComPadrao = Objects.requireNonNullElse(nomeUsuario, "Usuário Anônimo");
        System.out.println("   Nome com padrão: " + nomeComPadrao);

        // 3. requireNonNullElseGet() - Supplier lazy (só executa se necessário)
        System.out.println("\n3. REQUIRE NON NULL ELSE GET (LAZY):");
        String nomeCompleto = Objects.requireNonNullElseGet(nomeUsuario,
                () -> {
                    System.out.println("   Gerando nome padrão...");
                    return "Usuário_" + System.currentTimeMillis();
                }
        );
        System.out.println("   Nome lazy: " + nomeCompleto);

        // 4. Objects.equals() - Comparação null-safe
        System.out.println("\n4. EQUALS NULL-SAFE:");
        String a = null;
        String b = null;
        String c = "teste";
        System.out.println("   null equals null: " + Objects.equals(a, b));
        System.out.println("   null equals 'teste': " + Objects.equals(a, c));
        System.out.println("   'teste' equals 'teste': " + Objects.equals(c, "teste"));
    }

    /**
     * --- PRECONDITIONS CUSTOMIZADAS ---
     * Criando validações específicas para regras de negócio
     */
    private static void demonstrarPreconditionsCustomizadas() {
        System.out.println("\n--- PRECONDITIONS CUSTOMIZADAS ---");

        try {
            // Validações customizadas
            System.out.println("1. VALIDANDO IDADE:");
            int idade = 25;
            requireValidAge(idade);
            System.out.println("   Idade válida: " + idade);

            System.out.println("\n2. VALIDANDO EMAIL:");
            String email = "usuario@exemplo.com";
            requireValidEmail(email);
            System.out.println("   Email válido: " + email);

            System.out.println("\n3. VALIDANDO COLEÇÃO:");
            java.util.List<String> lista = java.util.Arrays.asList("item1", "item2");
            requireNonEmpty(lista, "Lista não pode estar vazia");
            System.out.println("   Lista válida com " + lista.size() + " itens");

            // Testando validação que falha
            System.out.println("\n4. TESTANDO VALIDAÇÃO QUE FALHA:");
            requireValidAge(-5); // Vai gerar erro

        } catch (IllegalArgumentException e) {
            System.out.println("   Erro de validação: " + e.getMessage());
        }
    }

    /**
     * --- VALIDAÇÕES AVANÇADAS ---
     * Combinando múltiplas validações e usando Suppliers
     */
    private static void demonstrarValidacoesAvancadas() {
        System.out.println("\n--- VALIDAÇÕES AVANÇADAS ---");

        try {
            // 1. Validação com múltiplas condições
            System.out.println("1. VALIDAÇÃO DE SENHA FORTE:");
            String senha = "MinhaSenh@123";
            requireStrongPassword(senha);
            System.out.println("   Senha forte validada");

            // 2. Validação com Supplier (mensagem lazy)
            System.out.println("\n2. VALIDAÇÃO COM SUPPLIER LAZY:");
            double preco = 99.99;
            requirePositive(preco, () ->
                    "Preço deve ser positivo. Valor recebido: " + preco + " (calculado em " +
                            java.time.LocalDateTime.now() + ")"
            );
            System.out.println("   Preço válido: R$ " + preco);

            // 3. Validação em cadeia (fluent validation)
            System.out.println("\n3. VALIDAÇÃO EM CADEIA:");
            String produto = "Notebook Dell";
            String produtoValidado = requireNonNull(produto, "Produto não pode ser null")
                    .trim(); // Método encadeado após validação
            requireNonEmpty(produtoValidado, "Produto não pode estar vazio");
            requireMinLength(produtoValidado, 3, "Produto deve ter pelo menos 3 caracteres");
            System.out.println("   Produto validado: " + produtoValidado);

        } catch (IllegalArgumentException e) {
            System.out.println("   Erro de validação avançada: " + e.getMessage());
        }
    }

    /**
     * --- CASO PRÁTICO: SISTEMA DE CADASTRO ---
     * Aplicando todas as validações em um cenário real
     */
    private static void sistemaCadastroComValidacoes() {
        System.out.println("\n--- CASO PRÁTICO: SISTEMA DE CADASTRO ---");

        try {
            // Dados do usuário
            String nome = "Ana Silva";
            String email = "ana@empresa.com";
            int idade = 28;
            String senha = "MinhaSuperSenh@2024";
            Double salario = 5500.00;

            // Cadastrar usuário com validações completas
            Usuario usuario = cadastrarUsuario(nome, email, idade, senha, salario);
            System.out.println("✅ USUÁRIO CADASTRADO COM SUCESSO:");
            System.out.println("   " + usuario);

            // Testar cadastro inválido
            System.out.println("\n--- TESTANDO CADASTRO INVÁLIDO ---");
            cadastrarUsuario(null, "email-inválido", -5, "123", null);

        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("❌ ERRO NO CADASTRO: " + e.getMessage());
        }
    }

    // ============================================
    // MÉTODOS DE VALIDAÇÃO CUSTOMIZADOS
    // ============================================

    /**
     * Valida se a idade está em um range válido
     */
    public static void requireValidAge(int idade) {
        if (idade < 0 || idade > 120) {
            throw new IllegalArgumentException("Idade deve estar entre 0 e 120 anos. Recebido: " + idade);
        }
    }

    /**
     * Valida formato básico de email
     */
    public static void requireValidEmail(String email) {
        requireNonNull(email, "Email não pode ser null");
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email deve ter formato válido. Recebido: " + email);
        }
    }

    /**
     * Valida se coleção não está vazia
     */
    public static <T> void requireNonEmpty(Collection<T> collection, String message) {
        requireNonNull(collection, message);
        if (collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Valida se string não está vazia
     */
    public static void requireNonEmpty(String str, String message) {
        requireNonNull(str, message);
        if (str.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Valida senha forte
     */
    public static void requireStrongPassword(String password) {
        requireNonNull(password, "Senha não pode ser null");
        if (password.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos uma letra maiúscula");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um número");
        }
        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos um caractere especial");
        }
    }

    /**
     * Valida se número é positivo usando Supplier
     */
    public static void requirePositive(double number, Supplier<String> messageSupplier) {
        if (number <= 0) {
            throw new IllegalArgumentException(messageSupplier.get());
        }
    }

    /**
     * Valida comprimento mínimo de string
     */
    public static void requireMinLength(String str, int minLength, String message) {
        requireNonNull(str, message);
        if (str.length() < minLength) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Método de cadastro com todas as validações
     */
    private static Usuario cadastrarUsuario(String nome, String email, int idade, String senha, Double salario) {
        // Validações obrigatórias
        requireNonNull(nome, "Nome é obrigatório");
        requireNonEmpty(nome, "Nome não pode estar vazio");
        requireMinLength(nome.trim(), 2, "Nome deve ter pelo menos 2 caracteres");

        requireValidEmail(email);
        requireValidAge(idade);
        requireStrongPassword(senha);

        requireNonNull(salario, "Salário é obrigatório");
        requirePositive(salario, () -> "Salário deve ser positivo: R$ " + salario);

        // Se chegou até aqui, todas as validações passaram
        return new Usuario(nome.trim(), email.toLowerCase(), idade, senha, salario);
    }

    // Classe auxiliar para o exemplo
    private static class Usuario {
        private final String nome;
        private final String email;
        private final int idade;
        private final String senha;
        private final double salario;

        public Usuario(String nome, String email, int idade, String senha, double salario) {
            this.nome = nome;
            this.email = email;
            this.idade = idade;
            this.senha = senha;
            this.salario = salario;
        }

        @Override
        public String toString() {
            return String.format("Usuario{nome='%s', email='%s', idade=%d, salario=R$ %.2f}",
                    nome, email, idade, salario);
        }
    }
}