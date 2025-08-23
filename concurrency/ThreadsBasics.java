package concurrency;

/**
 * ThreadsBasics.java - Sintaxe de Threads em Java
 *
 * FOCO: Sintaxe, não conceitos (você já sabe os conceitos!)
 */
public class ThreadsBasics {

    // Variável compartilhada para demonstrar sincronização
    private static int contador = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        System.out.println("=== THREADS BASICS - SINTAXE ===\n");

        // 1. CRIANDO THREADS - 3 formas
        demonstrarCriacaoThreads();

        // 2. SINCRONIZAÇÃO - synchronized
        demonstrarSincronizacao();

        // 3. JOIN - Esperar thread terminar
        demonstrarJoin();

        // 4. CASO PRÁTICO - Download simulado
        simuladorDownload();
    }

    /**
     * --- CRIANDO THREADS - 3 FORMAS ---
     */
    private static void demonstrarCriacaoThreads() {
        System.out.println("--- CRIANDO THREADS ---");

        // FORMA 1: Lambda (mais comum)
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 (lambda): " + Thread.currentThread().getName());
            dormir(1000);
            System.out.println("Thread 1 terminada");
        });

        // FORMA 2: Runnable
        Thread t2 = new Thread(new MinhaTask("Thread 2"));

        // FORMA 3: Extending Thread (menos usada)
        Thread t3 = new MinhaThread("Thread 3");

        // Iniciar threads
        t1.start();
        t2.start();
        t3.start();

        dormir(2000); // Esperar um pouco antes do próximo exemplo
    }

    /**
     * --- SINCRONIZAÇÃO COM SYNCHRONIZED ---
     */
    private static void demonstrarSincronizacao() {
        System.out.println("\n--- SINCRONIZAÇÃO ---");

        // Criar múltiplas threads que incrementam contador
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            final int id = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    incrementarContador();
                }
                System.out.println("Thread " + id + " terminou");
            });
        }

        // Iniciar todas
        for (Thread t : threads) {
            t.start();
        }

        // Esperar todas terminarem
        for (Thread t : threads) {
            try {
                t.join(); // SINTAXE: join() espera thread terminar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Contador final: " + contador + " (esperado: 5000)");
    }

    /**
     * --- DEMONSTRAR JOIN ---
     */
    private static void demonstrarJoin() {
        System.out.println("\n--- DEMONSTRAR JOIN ---");

        Thread tarefa = new Thread(() -> {
            System.out.println("Processando tarefa...");
            dormir(2000);
            System.out.println("Tarefa concluída!");
        });

        tarefa.start();

        try {
            System.out.println("Aguardando tarefa terminar...");
            tarefa.join(); // Espera a thread terminar
            System.out.println("Tarefa terminada, continuando main");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * --- CASO PRÁTICO: SIMULADOR DE DOWNLOAD ---
     */
    private static void simuladorDownload() {
        System.out.println("\n--- SIMULADOR DE DOWNLOAD ---");

        String[] arquivos = {"video.mp4", "musica.mp3", "documento.pdf", "imagem.jpg"};
        Thread[] downloads = new Thread[arquivos.length];

        // Criar thread para cada download
        for (int i = 0; i < arquivos.length; i++) {
            final String arquivo = arquivos[i];
            final int tamanho = (i + 1) * 500; // Tamanhos diferentes

            downloads[i] = new Thread(() -> {
                downloadArquivo(arquivo, tamanho);
            });
        }

        // Iniciar todos os downloads
        System.out.println("Iniciando downloads...");
        for (Thread download : downloads) {
            download.start();
        }

        // Esperar todos terminarem
        for (Thread download : downloads) {
            try {
                download.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Todos os downloads concluídos!");
    }

    // =============================================
    // MÉTODOS AUXILIARES
    // =============================================

    /**
     * Incrementa contador de forma thread-safe
     */
    private static void incrementarContador() {
        synchronized (lock) { // SINTAXE: synchronized(objeto)
            contador++;
        }
    }

    /**
     * Simula download de arquivo
     */
    private static void downloadArquivo(String nome, int tamanhoMB) {
        System.out.println("Baixando " + nome + " (" + tamanhoMB + "MB)");

        // Simular progresso
        for (int progresso = 10; progresso <= 100; progresso += 10) {
            dormir(100); // Simular tempo de download
            System.out.println(nome + ": " + progresso + "%");
        }

        System.out.println("✅ " + nome + " baixado com sucesso!");
    }

    /**
     * Utilitário para dormir sem try-catch verboso
     */
    private static void dormir(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =============================================
    // CLASSES AUXILIARES
    // =============================================

    /**
     * FORMA 2: Implementando Runnable
     */
    static class MinhaTask implements Runnable {
        private final String nome;

        public MinhaTask(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            System.out.println(nome + " (Runnable): " + Thread.currentThread().getName());
            dormir(1000);
            System.out.println(nome + " terminada");
        }
    }

    /**
     * FORMA 3: Extending Thread
     */
    static class MinhaThread extends Thread {
        private final String nome;

        public MinhaThread(String nome) {
            this.nome = nome;
        }

        @Override
        public void run() {
            System.out.println(nome + " (extends Thread): " + Thread.currentThread().getName());
            dormir(1000);
            System.out.println(nome + " terminada");
        }
    }
}
