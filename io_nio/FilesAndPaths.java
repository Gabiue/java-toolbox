package io_nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.stream.Stream;

public class FilesAndPaths {

    public static void main(String[] args) {
        System.out.println("=== APRENDENDO PATHS ===\n");

        demonstrarCriacaoPaths();
        demonstrarOperacoesBasicas();
        demonstrarLeituraEscrita();
        demonstrarNavegacaoDiretorios();
        demonstrarMetadados();
        sistemaBackupSimples();
    }
    private static void demonstrarCriacaoPaths(){
        System.out.println("---CRIANDO PATHS:---");

        // Forma 1 - Paths.get()
        Path path1 = Paths.get("arquivo.txt");
        System.out.println("Path1: " + path1);

        // Forma 2 - Com múltiplos argumentos
        Path path2 = Paths.get("pasta","arquivo.txt" );
        System.out.println("Path2: " + path2);

        //Forma 3
        Path path3 = Paths.get("docs", "2024", "relatorio.pdf");

        System.out.println("Path1: " + path1);
        System.out.println("Path2: " + path2);
        System.out.println("Path3: " + path3);
    }

    private static void demonstrarOperacoesBasicas() {
        System.out.println("\nOPERAÇÕES BÁSICAS COM FILES:");

        Path arquivo = Paths.get("Teste.txt");

        try {
            //C - CREATE
            if (Files.notExists(arquivo)) {
                Files.createFile(arquivo);
                System.out.println("✅ Arquivo criado: " + arquivo);
            }
            //R - READ
            if (Files.exists(arquivo)) {
                System.out.println("📖 Arquivo existe!");
            }

            //D - DELETE
            Files.deleteIfExists(arquivo);
            System.out.println("🗑️ Arquivo deletado");
        } catch (IOException e) {
            System.out.println("❌ Erro: " + e.getMessage());

        }
    }
    private static void demonstrarLeituraEscrita() {
        System.out.println("\nLEXNDO E ESCREVENDO CONTEÚDO:");

        Path arquivo = Paths.get("dados.txt");

        try {
            // WRITE - Escrevendo conteúdo
            String conteudo = "Olá, Java!\nAprendendo I/O e NIO!";
            Files.write(arquivo, conteudo.getBytes());
            System.out.println("✅ Conteúdo escrito no arquivo");

            //READ
            List<String> linhas = Files.readAllLines(arquivo);
            System.out.println("📖 Conteúdo lido:");
            linhas.forEach(linha -> System.out.println("  " + linha));

            // Limpar
            Files.deleteIfExists(arquivo);

        } catch (IOException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    private static void demonstrarNavegacaoDiretorios() {
        System.out.println("\nNAVEGANDO EM DIRETÓRIOS:");

            Path pasta = Paths.get("test-dir");

            try {
                // Criar diretório se não existir
                Files.createDirectories(pasta);
                System.out.println("📁 Diretório criado: " + pasta);

                // Criar alguns arquivos de teste
                Files.write(pasta.resolve("arquivo1.txt"), "Conteúdo 1".getBytes());
                Files.write(pasta.resolve("arquivo2.txt"), "Conteúdo 2".getBytes());
                Files.write(pasta.resolve("dados.json"), "{}".getBytes());
                System.out.println("📄 Arquivos de teste criados");

                // Listar todo o conteúdo
                System.out.println("\n📋 LISTANDO TODOS OS ARQUIVOS:");
                try (Stream<Path> arquivos = Files.list(pasta)) {
                    arquivos.forEach(arquivo ->
                            System.out.println("  " + arquivo.getFileName())
                    );
                }

                // Filtrar apenas arquivos .txt
                System.out.println("\n🔍 APENAS ARQUIVOS .TXT:");
                try (Stream<Path> txtFiles = Files.list(pasta)) {
                    txtFiles
                            .filter(path -> path.toString().endsWith(".txt"))
                            .forEach(arquivo ->
                                    System.out.println("  " + arquivo.getFileName())
                            );
                }

                // Limpar - deletar todos os arquivos e a pasta
                try (Stream<Path> arquivos = Files.list(pasta)) {
                    arquivos.forEach(arquivo -> {
                        try {
                            Files.delete(arquivo);
                        } catch (IOException e) {
                            System.out.println("Erro ao deletar: " + arquivo);
                        }
                    });
                }
                Files.delete(pasta);
                System.out.println("🗑️ Diretório e arquivos deletados");

            } catch (IOException e) {
                System.out.println("❌ Erro: " + e.getMessage());
            }
        }

    private static void demonstrarMetadados() {
        System.out.println("\n️METADADOS DE ARQUIVOS:");

        Path arquivo = Paths.get("teste-meta.txt");

        try {
            // Criar arquivo com conteúdo
            Files.write(arquivo, "Testando metadados do arquivo!".getBytes());

            // Metadados básicos
            long tamanho = Files.size(arquivo);
            FileTime modificado = Files.getLastModifiedTime(arquivo);
            boolean isFile = Files.isRegularFile(arquivo);
            boolean isDir = Files.isDirectory(arquivo);

            System.out.println("📄 Arquivo: " + arquivo.getFileName());
            System.out.println("📊 Tamanho: " + tamanho + " bytes");
            System.out.println("📅 Modificado: " + modificado);
            System.out.println("📝 É arquivo: " + isFile);
            System.out.println("📁 É diretório: " + isDir);

            // Limpar
            Files.delete(arquivo);

        } catch (IOException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }
    }
    private static void sistemaBackupSimples() {
        System.out.println("\n--- CASO PRÁTICO - SISTEMA DE BACKUP ---");

        // Pasta de origem (vamos criar arquivos de teste)
        Path origem = Paths.get("documentos");
        // Pasta de backup com data
        Path backup = Paths.get("backup-" + java.time.LocalDate.now());

        try {
            // 1. PREPARAR AMBIENTE DE TESTE
            Files.createDirectories(origem);
            System.out.println("📁 Pasta origem criada: " + origem);

            // Criar alguns arquivos de teste
            Files.write(origem.resolve("relatorio.txt"), "Relatório anual de vendas...".getBytes());
            Files.write(origem.resolve("dados.json"), "{\"vendas\": 1000}".getBytes());
            Files.write(origem.resolve("config.properties"), "app.name=MeuApp".getBytes());
            System.out.println("📄 Arquivos de teste criados");

            // 2. CRIAR PASTA DE BACKUP
            Files.createDirectories(backup);
            System.out.println("💾 Pasta backup criada: " + backup);

            // 3. FAZER BACKUP COM RELATÓRIO
            System.out.println("\n🔄 INICIANDO BACKUP...");

            int arquivosCopiados = 0;
            long tamanhoTotal = 0;

            try (Stream<Path> arquivos = Files.list(origem)) {
                for (Path arquivo : arquivos.toList()) {
                    if (Files.isRegularFile(arquivo)) {
                        // Copiar arquivo
                        Path destino = backup.resolve(arquivo.getFileName());
                        Files.copy(arquivo, destino);

                        // Atualizar estatísticas
                        long tamanho = Files.size(arquivo);
                        arquivosCopiados++;
                        tamanhoTotal += tamanho;

                        System.out.printf("✅ %s (%d bytes)%n",
                                arquivo.getFileName(), tamanho);
                    }
                }
            }

            // 4. RELATÓRIO FINAL
            System.out.println("\n📊 RELATÓRIO DO BACKUP:");
            System.out.println("  📁 Pasta origem: " + origem);
            System.out.println("  💾 Pasta backup: " + backup);
            System.out.println("  📄 Arquivos copiados: " + arquivosCopiados);
            System.out.println("  📊 Tamanho total: " + tamanhoTotal + " bytes");
            System.out.println("  ✅ Backup concluído com sucesso!");

            // 5. VERIFICAR BACKUP (listar arquivos copiados)
            System.out.println("\n🔍 VERIFICANDO BACKUP:");
            try (Stream<Path> backupFiles = Files.list(backup)) {
                backupFiles.forEach(arquivo ->
                        System.out.println("  ✓ " + arquivo.getFileName())
                );
            }

            // 6. LIMPAR AMBIENTE (opcional - descomente se quiser)
            // limparAmbienteTeste(origem, backup);

        } catch (IOException e) {
            System.out.println("❌ Erro no backup: " + e.getMessage());
        }
    }

    // Método auxiliar para limpeza (opcional)
    private static void limparAmbienteTeste(Path origem, Path backup) throws IOException {
        System.out.println("\n--- LIMPANDO AMBIENTE DE TESTE ---");

        // Deletar arquivos das pastas
        try (Stream<Path> files = Files.list(origem)) {
            files.forEach(arquivo -> {
                try { Files.delete(arquivo); } catch (IOException e) {}
            });
        }

        try (Stream<Path> files = Files.list(backup)) {
            files.forEach(arquivo -> {
                try { Files.delete(arquivo); } catch (IOException e) {}
            });
        }

        // Deletar as pastas
        Files.delete(origem);
        Files.delete(backup);
        System.out.println("🧹 Ambiente limpo!");
    }

}
