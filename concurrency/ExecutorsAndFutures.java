package concurrency;

import java.util.concurrent.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ExecutorsAndFutures.java - APIs Modernas de Threading
 *
 * FOCO: Sintaxe das APIs que o Spring usa por baixo
 * - ExecutorService (pool de threads)
 * - Future (resultado assíncrono)
 * - CompletableFuture (async moderno)
 */
public class ExecutorsAndFutures {

    public static void main(String[] args) {
        System.out.println("=== EXECUTORS AND FUTURES ===\n");

        // 1. EXECUTOR SERVICE - Pool de threads
        demonstrarExecutorService();

        // 2. FUTURE - Resultados assíncronos
        demonstrarFuture();

        // 3. COMPLETABLE FUTURE - API moderna
        demonstrarCompletableFuture();

        // 4. CASO PRÁTICO - Processamento paralelo
        processamentoParalelo();
    }

    /**
     * --- EXECUTOR SERVICE - POOL DE THREADS ---
     * O que o Tomcat/Spring usa por baixo
     */
    private static void demonstrarExecutorService() {
        System.out.println("--- EXECUTOR SERVICE ---");

        // SINTAXE: Criar pool de threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        try {
            // EXECUTE - Fire and forget (não retorna nada)
            executor.execute(() -> {
                System.out.println("Task 1 executada em: " + Thread.currentThread().getName());
                dormir(1000);
            });

            executor.execute(() -> {
                System.out.println("Task 2 executada em: " + Thread.currentThread().getName());
                dormir(1000);
            });

            // SUBMIT - Retorna Future (pode pegar resultado)
            Future<?> future = executor.submit(() -> {
                System.out.println("Task 3 executada em: " + Thread.currentThread().getName());
                dormir(1000);
                return "Task 3 concluída";
            });

            System.out.println("Tasks submetidas, main thread continua...");
            dormir(2000);

        } finally {
            // IMPORTANTE: Sempre fechar o executor
            executor.shutdown();
            try {
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
            }
        }
    }

    /**
     * --- FUTURE - RESULTADOS ASSÍNCRONOS ---
     * Como pegar resultado de operação async
     */
    private static void demonstrarFuture() {
        System.out.println("\n--- FUTURE ---");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            // SUBMIT com retorno
            Future<String> futureString = executor.submit(() -> {
                dormir(2000);
                return "Resultado após 2 segundos";
            });

            Future<Integer> futureInt = executor.submit(() -> {
                dormir(1000);
                return 42;
            });

            System.out.println("Futures criados, fazendo outras coisas...");
            dormir(500);

            // GET - Bloqueia até ter resultado
            System.out.println("Pegando resultados...");
            String resultado1 = futureString.get(); // Bloqueia
            Integer resultado2 = futureInt.get();   // Bloqueia

            System.out.println("String: " + resultado1);
            System.out.println("Integer: " + resultado2);

            // GET com timeout
            Future<String> futureTimeout = executor.submit(() -> {
                dormir(5000); // 5 segundos
                return "Nunca vai retornar";
            });

            try {
                String resultado = futureTimeout.get(1, TimeUnit.SECONDS); // Timeout de 1s
            } catch (TimeoutException e) {
                System.out.println("Timeout! Cancelando future...");
                futureTimeout.cancel(true);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    /**
     * --- COMPLETABLE FUTURE - API MODERNA ---
     * O que usar em integrações modernas
     */
    private static void demonstrarCompletableFuture() {
        System.out.println("\n--- COMPLETABLE FUTURE ---");

        // SINTAXE: Criar CompletableFuture
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            dormir(1000);
            return "Dados do serviço A";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            dormir(1500);
            return "Dados do serviço B";
        });

        // COMBINAR resultados
        CompletableFuture<String> combinado = future1.thenCombine(future2,
                (resultado1, resultado2) -> resultado1 + " + " + resultado2
        );

        // PIPELINE de processamento
        CompletableFuture<String> processado = combinado
                .thenApply(String::toUpperCase)  // Transformar
                .thenApply(s -> "[" + s + "]")   // Decorar
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> {
                    dormir(500);
                    return "Processado: " + s;
                }));

        // CALLBACK quando terminar
        processado.thenAccept(resultado ->
                System.out.println("✅ Resultado final: " + resultado)
        );

        // ESPERAR terminar
        try {
            processado.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * --- CASO PRÁTICO: PROCESSAMENTO PARALELO ---
     * Simula processamento de lista grande
     */
    private static void processamentoParalelo() {
        System.out.println("\n--- PROCESSAMENTO PARALELO ---");

        // Lista simulada
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        try {
            // PROCESSAR todos em paralelo
            List<Future<String>> futures = numeros.stream()
                    .map(numero -> executor.submit(() -> processarNumero(numero)))
                    .collect(Collectors.toList());

            // COLETAR resultados
            System.out.println("Processando " + numeros.size() + " itens em paralelo...");

            for (int i = 0; i < futures.size(); i++) {
                try {
                    String resultado = futures.get(i).get();
                    System.out.println("Item " + (i+1) + ": " + resultado);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Erro no item " + (i+1) + ": " + e.getMessage());
                }
            }

            System.out.println("Processamento paralelo concluído!");

        } finally {
            executor.shutdown();
        }

        // VERSÃO COMPLETABLE FUTURE
        System.out.println("\n--- VERSÃO COM COMPLETABLE FUTURE ---");

        List<CompletableFuture<String>> completableFutures = numeros.stream()
                .map(numero -> CompletableFuture.supplyAsync(() -> processarNumero(numero)))
                .collect(Collectors.toList());

        // ESPERAR TODOS
        CompletableFuture<Void> todosCombinados = CompletableFuture.allOf(
                completableFutures.toArray(new CompletableFuture[0])
        );

        // QUANDO TODOS TERMINAREM
        todosCombinados.thenRun(() -> {
            System.out.println("Todos os CompletableFutures terminaram!");
            completableFutures.forEach(cf -> {
                try {
                    System.out.println("Resultado CF: " + cf.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        });

        // ESPERAR
        try {
            todosCombinados.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    // =============================================
    // MÉTODOS AUXILIARES
    // =============================================

    /**
     * Simula processamento pesado de um número
     */
    private static String processarNumero(int numero) {
        String threadName = Thread.currentThread().getName();
        System.out.println("  Processando " + numero + " em " + threadName);

        // Simular processamento
        dormir(500 + (numero * 100));

        return "Número " + numero + " processado por " + threadName;
    }

    /**
     * Utilitário para dormir
     */
    private static void dormir(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
