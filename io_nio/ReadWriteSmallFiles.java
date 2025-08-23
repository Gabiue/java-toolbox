package io_nio;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * ReadWriteSmallFiles.java - Leitura e Escrita de Arquivos Pequenos
 *
 * CONCEITOS IMPORTANTES:
 * - Files.readAllLines() / Files.readString() - Leitura completa
 * - Files.write() com diferentes opções - Escrita completa
 * - Charset e encoding - UTF-8, ASCII, etc.
 * - StandardOpenOption - Controle de como escrever
 * - Try-with-resources para AutoCloseable
 */
public class ReadWriteSmallFiles {

    public static void main(String[] args) {
        System.out.println("=== READ/WRITE SMALL FILES ===\n");

        // 1. LEITURA BÁSICA DE ARQUIVOS
        demonstrarLeituraBasica();

        // 2. ESCRITA COM DIFERENTES OPÇÕES
        demonstrarEscritaOpcoes();

        // 3. TRABALHANDO COM ENCODINGS
        demonstrarEncodings();

        // 4. CASO PRÁTICO: SISTEMA DE LOG SIMPLES
        sistemaLogSimples();
    }

    /**
     * --- LEITURA BÁSICA DE ARQUIVOS ---
     * Diferentes formas de ler arquivos pequenos
     */
    private static void demonstrarLeituraBasica() {
        System.out.println("--- LEITURA BÁSICA ---");

        Path arquivo = Paths.get("teste-leitura.txt");

        try {
            // Criar arquivo de teste
            String conteudo = "Primeira linha\nSegunda linha\nTerceira linha com acentos: ção, ã, ê";
            Files.write(arquivo, conteudo.getBytes(StandardCharsets.UTF_8));

            // MÉTODO 1: readAllLines() - Retorna List<String>
            System.out.println("1. LENDO COMO LISTA DE LINHAS:");
            List<String> linhas = Files.readAllLines(arquivo);
            for (int i = 0; i < linhas.size(); i++) {
                System.out.println("  Linha " + (i+1) + ": " + linhas.get(i));
            }

            System.out.println("\n2. LENDO COMO STRING ÚNICA:");
            // MÉTODO 2: readString() - Retorna String completa (Java 11+)
            String textoCompleto = Files.readString(arquivo);
            System.out.println("  Conteúdo completo:");
            System.out.println("  " + textoCompleto.replace("\n", "\\n"));

            System.out.println("\n3. LENDO COMO BYTES:");
            // MÉTODO 3: readAllBytes() - Retorna byte[]
            byte[] bytes = Files.readAllBytes(arquivo);
            System.out.println("  Tamanho em bytes: " + bytes.length);
            System.out.println("  Primeiros 10 bytes: " + Arrays.toString(Arrays.copyOf(bytes, 10)));

            // Limpar
            Files.delete(arquivo);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * --- ESCRITA COM DIFERENTES OPÇÕES ---
     * Como controlar o comportamento da escrita
     */
    private static void demonstrarEscritaOpcoes() {
        System.out.println("\n--- ESCRITA COM OPÇÕES ---");

        Path arquivo = Paths.get("teste-escrita.txt");

        try {
            // OPÇÃO 1: Escrita normal (substitui conteúdo)
            Files.write(arquivo, "Conteúdo inicial\n".getBytes());
            System.out.println("1. Arquivo criado com conteúdo inicial");

            // OPÇÃO 2: Append - adiciona ao final
            String novoConteudo = "Linha adicionada\n";
            Files.write(arquivo, novoConteudo.getBytes(), StandardOpenOption.APPEND);
            System.out.println("2. Conteúdo adicionado com APPEND");

            // OPÇÃO 3: Create + Write (falha se já existir)
            Path arquivo2 = Paths.get("teste-create.txt");
            Files.write(arquivo2, "Só cria se não existir".getBytes(),
                    StandardOpenOption.CREATE_NEW);
            System.out.println("3. Arquivo criado com CREATE_NEW");

            // Verificar resultados
            System.out.println("\n--- RESULTADO DA ESCRITA ---");
            System.out.println("Arquivo principal:");
            Files.readAllLines(arquivo).forEach(linha -> System.out.println("  " + linha));

            System.out.println("Arquivo CREATE_NEW:");
            System.out.println("  " + Files.readString(arquivo2));

            // Limpar
            Files.delete(arquivo);
            Files.delete(arquivo2);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * --- TRABALHANDO COM ENCODINGS ---
     * UTF-8, ASCII, e outros charsets
     */
    private static void demonstrarEncodings() {
        System.out.println("\n--- ENCODINGS E CHARSETS ---");

        Path arquivo = Paths.get("teste-encoding.txt");

        try {
            // Texto com caracteres especiais
            String textoComAcentos = "Olá! Programação em Java: ção, ã, é, ô";

            // ESCRITA COM UTF-8 (padrão recomendado)
            Files.write(arquivo, textoComAcentos.getBytes(StandardCharsets.UTF_8));
            System.out.println("1. Escrito em UTF-8");

            // LEITURA COM UTF-8
            String leituraUTF8 = Files.readString(arquivo, StandardCharsets.UTF_8);
            System.out.println("2. Lido em UTF-8: " + leituraUTF8);

            // LEITURA COM CHARSET DIFERENTE (pode dar problema)
            try {
                String leituraASCII = Files.readString(arquivo, StandardCharsets.US_ASCII);
                System.out.println("3. Lido em ASCII: " + leituraASCII);
            } catch (Exception e) {
                System.out.println("3. Erro ao ler em ASCII: caracteres especiais não suportados");
            }

            // Mostrar informações sobre charsets
            System.out.println("\n--- CHARSETS DISPONÍVEIS ---");
            System.out.println("Charset padrão do sistema: " + Charset.defaultCharset());
            System.out.println("UTF-8: " + StandardCharsets.UTF_8);
            System.out.println("ASCII: " + StandardCharsets.US_ASCII);
            System.out.println("ISO-8859-1: " + StandardCharsets.ISO_8859_1);

            // Limpar
            Files.delete(arquivo);

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * --- CASO PRÁTICO: SISTEMA DE LOG SIMPLES ---
     * Sistema que grava logs com timestamps
     */
    private static void sistemaLogSimples() {
        System.out.println("\n--- CASO PRÁTICO: SISTEMA DE LOG ---");

        Path logFile = Paths.get("application.log");

        try {
            // Método para escrever log
            escreverLog(logFile, "INFO", "Sistema iniciado");
            escreverLog(logFile, "DEBUG", "Carregando configurações");
            escreverLog(logFile, "WARN", "Configuração padrão utilizada");
            escreverLog(logFile, "INFO", "Sistema pronto para uso");
            escreverLog(logFile, "ERROR", "Falha na conexão com banco de dados");

            // Ler e exibir logs
            System.out.println("\n--- CONTEÚDO DO LOG ---");
            List<String> logs = Files.readAllLines(logFile);
            logs.forEach(linha -> System.out.println(linha));

            // Estatísticas do log
            System.out.println("\n--- ESTATÍSTICAS ---");
            long totalLinhas = logs.size();
            long errors = logs.stream().filter(linha -> linha.contains("[ERROR]")).count();
            long warnings = logs.stream().filter(linha -> linha.contains("[WARN]")).count();
            long infos = logs.stream().filter(linha -> linha.contains("[INFO]")).count();

            System.out.println("Total de logs: " + totalLinhas);
            System.out.println("Errors: " + errors);
            System.out.println("Warnings: " + warnings);
            System.out.println("Infos: " + infos);

            System.out.println("Tamanho do arquivo: " + Files.size(logFile) + " bytes");

            // Limpar (descomente se quiser)
            // Files.delete(logFile);

        } catch (IOException e) {
            System.out.println("Erro no sistema de log: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para escrever log com timestamp
     */
    private static void escreverLog(Path logFile, String nivel, String mensagem) throws IOException {
        String timestamp = java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );

        String logEntry = String.format("%s [%s] %s%n", timestamp, nivel, mensagem);

        // Usar APPEND para adicionar ao final (CREATE se não existir)
        Files.write(logFile, logEntry.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}