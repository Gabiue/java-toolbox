    package net_http;

    import javax.swing.event.HyperlinkEvent;
    import java.io.IOException;
    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;

    public class HttpClientBasics {
        public static void main(String[] args) {

            System.out.println("=== PRIMEIRA REQUISIÇÃO HTTP ===");

            // 1. Criar o cliente HTTP
            HttpClient client = HttpClient.newHttpClient();
            System.out.println("Cliente HTTP criado!");

            //2. Criar a requisição
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/99999-999/json/"))                .GET()
                    .build();

            System.out.println("Requisição criada para: " + request.uri());

            // 3. ENVIAR a requisição (aqui começa a mágica!)
            try{
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("✅ Requisição enviada!");
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Resposta: " + response.body());
            }catch (IOException | InterruptedException e){
                System.out.println("❌ Erro: " + e.getMessage());
            }


            System.out.println("\n=== TESTANDO API MAIS PROFISSIONAL ===");

    // Vamos buscar um post existente
            HttpRequest requestPost = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(requestPost,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Content-Type: " +
                        response.headers().firstValue("Content-Type").orElse("N/A"));
                System.out.println("Response:");
                System.out.println(response.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }


            System.out.println("\n=== TESTANDO ERRO 404 (Post inexistente) ===");

            HttpRequest request404 = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/99999"))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(request404,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Content-Type: " +
                        response.headers().firstValue("Content-Type").orElse("N/A"));
                System.out.println("Response Body: '" + response.body() + "'");
                System.out.println("Tamanho da resposta: " + response.body().length());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }

            System.out.println("\n=== TRATAMENTO PROFISSIONAL DE STATUS ===");

    // Testando vários cenários
            String[] endpoints = {
                    "posts/1",      // Existe (200)
                    "posts/99999",  // Não existe (404)
                    "posts/abc"     // Formato inválido (?)
            };

            for (String endpoint : endpoints) {
                System.out.println("\n--- Testando: " + endpoint + " ---");

                HttpRequest requestPro = HttpRequest.newBuilder()
                        .uri(URI.create("https://jsonplaceholder.typicode.com/" + endpoint))
                        .GET()
                        .build();

                try {
                    HttpResponse<String> response = client.send(requestPro,
                            HttpResponse.BodyHandlers.ofString());

                    int status = response.statusCode();
                    System.out.println("Status: " + status);

                    if (status >= 200 && status < 300) {
                        System.out.println("✅ Sucesso!");
                    } else if (status >= 400 && status < 500) {
                        System.out.println("🚫 Erro do cliente: " + status);
                    } else if (status >= 500) {
                        System.out.println("💥 Erro do servidor: " + status);
                    }

                } catch (IOException | InterruptedException e) {
                    System.out.println("❌ Erro de conexão: " + e.getMessage());
                }
            }

            System.out.println("\n=== CRIANDO UM POST (POST request) ===");

            String jsonPost = """
        {
            "title": "Meu primeiro post via Java",
            "body": "Estou aprendendo HTTP Client!",
            "userId": 1
        }
        """;

            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPost))
                    .build();

            try {
                HttpResponse<String> response = client.send(postRequest,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status: " + response.statusCode());
                System.out.println("Response:");
                System.out.println(response.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }


            System.out.println("\n=== POST SEM CONTENT-TYPE (teste de erro) ===");

            HttpRequest postSemHeader = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                    // SEM o header Content-Type!
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPost))
                    .build();

            try {
                HttpResponse<String> response = client.send(postSemHeader,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status: " + response.statusCode());
                System.out.println("Response: " + response.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }

            System.out.println("\n=== TESTANDO API MAIS RIGOROSA (httpbin.org) ===");

            HttpRequest testRigoroso = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/post"))
                    // SEM Content-Type de propósito
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPost))
                    .build();

            try {
                HttpResponse<String> response = client.send(testRigoroso,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status httpbin: " + response.statusCode());
                System.out.println("Resposta httpbin:");
                System.out.println(response.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }

            System.out.println("\n=== UPDATE - PUT Request ===");

            String jsonUpdate = """
        {
            "id": 1,
            "title": "Post ATUALIZADO via Java",
            "body": "Aprendi a fazer PUT request!",
            "userId": 1
        }
        """;

            HttpRequest putRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(jsonUpdate))
                    .build();

            try {
                HttpResponse<String> response = client.send(putRequest,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("PUT Status: " + response.statusCode());
                System.out.println("Post atualizado:");
                System.out.println(response.body());

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro no PUT: " + e.getMessage());
            }
            System.out.println("\n=== DELETE - Removendo Post ===");

            HttpRequest deleteRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .DELETE()  // Método DELETE - sem body!
                    .build();

            try {
                HttpResponse<String> response = client.send(deleteRequest,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("DELETE Status: " + response.statusCode());
                System.out.println("Response body tamanho: " + response.body().length());
                System.out.println("Response body: '" + response.body() + "'");

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro no DELETE: " + e.getMessage());
            }
            System.out.println("\n=== VERIFICAÇÃO: Post deletado existe ainda? ===");

            HttpRequest verificaDelete = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                    .GET()
                    .build();

            try {
                HttpResponse<String> response = client.send(verificaDelete,
                        HttpResponse.BodyHandlers.ofString());

                System.out.println("Status ao buscar post deletado: " + response.statusCode());

                if (response.statusCode() == 404) {
                    System.out.println("✅ DELETE confirmado - Post não existe mais!");
                } else if (response.statusCode() == 200) {
                    System.out.println("🤔 Estranho... Post ainda existe:");
                    System.out.println(response.body());
                }

            } catch (IOException | InterruptedException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }
    }
