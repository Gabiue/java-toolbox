
package streams;

import java.util.*;

public class OptionalPatterns {

    // Classe auxiliar simples
    static class Usuario {
        String nome;
        String email;

        public Usuario(String nome, String email) {
            this.nome = nome;
            this.email = email;
        }

        public String getNome() { return nome; }
        public String getEmail() { return email; }

        @Override
        public String toString() {
            return nome + " (" + email + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println("=== OPTIONAL PATTERNS - EVITAR NULL ===\n");

        // Simulando busca que pode não encontrar
        List<Usuario> usuarios = Arrays.asList(
                new Usuario("Ana", "ana@email.com"),
                new Usuario("Bruno", "bruno@email.com")
        );

        // BUSCAR USUÁRIO (pode retornar null = perigo!)
        System.out.println("--- PROBLEMA: BUSCA PODE SER NULL ---");
        Usuario usuarioNull = buscarUsuario(usuarios, "Carlos"); // não existe
        // System.out.println(usuarioNull.getNome()); // 💥 NullPointerException!

        // BUSCAR COM OPTIONAL (seguro!)
        System.out.println("--- SOLUÇÃO: OPTIONAL ---");
        Optional<Usuario> usuarioOpt = buscarUsuarioSeguro(usuarios, "Ana");

        // ========== MÉTODOS ESSENCIAIS DO OPTIONAL ==========

// 1. orElse() - valor padrão se não encontrar
        System.out.println("--- orElse (valor padrão) ---");
        Usuario usuarioEncontrado = buscarUsuarioSeguro(usuarios, "Ana")
                .orElse(new Usuario("Padrão", "padrao@email.com"));
        System.out.println("Encontrado: " + usuarioEncontrado);

        Usuario usuarioNaoEncontrado = buscarUsuarioSeguro(usuarios, "Carlos")
                .orElse(new Usuario("Não encontrado", "nada@email.com"));
        System.out.println("Não encontrado: " + usuarioNaoEncontrado);

// 2. orElseThrow() - lança exceção se não encontrar
        System.out.println("\n--- orElseThrow (exceção) ---");
        try {
            Usuario usuario = buscarUsuarioSeguro(usuarios, "Bruno").orElseThrow();
            System.out.println("Achou Bruno: " + usuario);

            // Esta linha vai dar erro:
            // Usuario erro = buscarUsuarioSeguro(usuarios, "Pedro").orElseThrow();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

// 3. isPresent() / ifPresent() - verificar se tem valor
        System.out.println("\n--- isPresent / ifPresent ---");
        Optional<Usuario> opt1 = buscarUsuarioSeguro(usuarios, "Ana");
        if (opt1.isPresent()) {
            System.out.println("Ana existe: " + opt1.get());
        }

// Forma mais elegante:
        buscarUsuarioSeguro(usuarios, "Bruno")
                .ifPresent(usuario -> System.out.println("Bruno existe: " + usuario));

// 4. map() - transformar o valor se existir
        System.out.println("\n--- map (transformar) ---");
        String nomeEmMaiuscula = buscarUsuarioSeguro(usuarios, "Ana")
                .map(Usuario::getNome)
                .map(String::toUpperCase)
                .orElse("SEM NOME");
        System.out.println("Nome em maiúscula: " + nomeEmMaiuscula);

// 5. filter() - filtrar com condição
        System.out.println("\n--- filter (filtrar) ---");
        Optional<Usuario> usuarioComEmail = buscarUsuarioSeguro(usuarios, "Ana")
                .filter(u -> u.getEmail().contains("@"));
        System.out.println("Tem email válido? " + usuarioComEmail.isPresent());

    }

    // Método PERIGOSO (pode retornar null)
    public static Usuario buscarUsuario(List<Usuario> usuarios, String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                return u;
            }
        }
        return null; // 💥 PERIGO!
    }

    // Método SEGURO (retorna Optional)
    public static Optional<Usuario> buscarUsuarioSeguro(List<Usuario> usuarios, String nome) {
        for (Usuario u : usuarios) {
            if (u.getNome().equals(nome)) {
                return Optional.of(u);      // Achou = Optional com valor
            }
        }
        return Optional.empty();            // Não achou = Optional vazio


    }
}