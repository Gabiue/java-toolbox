package net_http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpGitHubAPI {
    public static void main(String[] args) {

        // ========== SETUP DO CLIENTE ==========
        System.out.println("=== GITHUB API - CONSUMINDO DADOS REAIS ===");

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // ---------------- BUSCAR USUÁRIO ----------------
        System.out.println("\n--- BUSCANDO USUÁRIO DO GITHUB ---");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/octocat"))
                    .header("User-Agent", "JavaApp/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());

            if (response.statusCode() == 200) {
                System.out.println("✅ Usuário encontrado!");
                System.out.println("Dados do usuário:");
                System.out.println(response.body());
            } else {
                System.out.println("❌ Erro: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }

        // ---------------- BUSCAR REPOSITÓRIOS DE UM USUÁRIO ----------------
        System.out.println("\n--- REPOSITÓRIOS DO USUÁRIO ---");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/octocat/repos"))
                    .header("User-Agent", "JavaApp/1.0")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Repositórios:");
            System.out.println(response.body());

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar repositórios: " + e.getMessage());
        }

        // ---------------- TRATANDO ERRO 404 ----------------
        System.out.println("\n--- TESTANDO USUÁRIO INEXISTENTE ---");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/users/usuarioquenoexiste999"))
                    .header("User-Agent", "JavaApp/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());

            if (response.statusCode() == 404) {
                System.out.println("❌ Usuário não encontrado");
                System.out.println("Mensagem de erro:");
                System.out.println(response.body());
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }

        // ---------------- RATE LIMITING INFO ----------------
        System.out.println("\n--- VERIFICANDO RATE LIMIT ---");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/rate_limit"))
                    .header("User-Agent", "JavaApp/1.0")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());
            System.out.println("Informações de rate limit:");
            System.out.println(response.body());

            // Headers importantes do rate limit
            response.headers().firstValue("X-RateLimit-Limit").ifPresent(
                    limit -> System.out.println("Limite: " + limit + " requests/hora"));

            response.headers().firstValue("X-RateLimit-Remaining").ifPresent(
                    remaining -> System.out.println("Restante: " + remaining + " requests"));

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao verificar rate limit: " + e.getMessage());
        }

        // ---------------- BUSCAR INFORMAÇÕES DE UM REPOSITÓRIO ----------------
        System.out.println("\n--- INFORMAÇÕES DE REPOSITÓRIO ESPECÍFICO ---");

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.github.com/repos/octocat/Hello-World"))
                    .header("User-Agent", "JavaApp/1.0")
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Status: " + response.statusCode());

            if (response.statusCode() == 200) {
                System.out.println("✅ Repositório encontrado!");
                System.out.println("Informações do repositório:");
                System.out.println(response.body());
            } else if (response.statusCode() == 404) {
                System.out.println("❌ Repositório não encontrado");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar repositório: " + e.getMessage());
        }

        // ---------------- EXEMPLO DE MÚLTIPLAS REQUISIÇÕES ----------------
        System.out.println("\n--- EXEMPLO: COMPARANDO DOIS USUÁRIOS ---");

        String[] usuarios = {"octocat", "defunkt"};  // Fundadores do GitHub

        for (String usuario : usuarios) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.github.com/users/" + usuario))
                        .header("User-Agent", "JavaApp/1.0")
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("\n=== Usuário: " + usuario + " ===");
                System.out.println("Status: " + response.statusCode());

                if (response.statusCode() == 200) {
                    // Em uma aplicação real, você faria parse do JSON aqui
                    // Para simplificar, só mostramos que funcionou
                    System.out.println("✅ Dados recebidos para " + usuario);
                    System.out.println("Tamanho da resposta: " + response.body().length() + " caracteres");
                } else {
                    System.out.println("❌ Erro para usuário " + usuario);
                }

                // Pausa pequena para não sobrecarregar a API
                Thread.sleep(500);

            } catch (IOException | InterruptedException e) {
                System.err.println("Erro ao buscar " + usuario + ": " + e.getMessage());
            }
        }

        System.out.println("\n✅ Consumo da GitHub API finalizado!");
        System.out.println("💡 Próximos passos: Parse JSON, autenticação com token, async requests");
    }
}