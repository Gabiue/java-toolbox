package pattern;

/**
 * 🏗️ DESIGN PATTERNS BASICS - PADRÕES DE PROJETO
 *
 * Design Patterns = "Jeitos testados de resolver problemas comuns"
 *
 * SINGLETON: Garantir que só existe UMA instância de uma classe
 */
public class DesignPatternsBasics {

    public static void main(String[] args) {
        System.out.println("=== SINGLETON PATTERN ===\n");

        // ❌ Isso NÃO funciona - construtor é privado
        // Logger logger = new Logger(); // ERRO!

        // ✅ Jeito correto - pedir para a própria classe
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Vamos ver se são o MESMO objeto
        System.out.println("logger1 == logger2? " + (logger1 == logger2));
        System.out.println("HashCode logger1: " + logger1.hashCode());
        System.out.println("HashCode logger2: " + logger2.hashCode());

        // Usando o logger
        logger1.log("Sistema iniciado");
        logger2.log("Usuário logou");

        // Mesmo sendo "dois" loggers, as mensagens vão pro mesmo lugar!
        System.out.println("\nTodos os logs:");
        logger1.mostrarLogs();

        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== FACTORY PATTERN ===\n");

        demonstrarFactoryPattern();
    }

    // ========== SINGLETON BÁSICO ==========

    /**
     * 📝 LOGGER SINGLETON
     *
     * PROBLEMA: Queremos um logger único em toda aplicação
     * SOLUÇÃO: Só permitir UMA instância dessa classe
     */
    static class Logger {
        // 🔒 A instância única fica guardada aqui
        private static Logger instancia = null;

        // 📋 Para guardar as mensagens de log
        private StringBuilder logs;

        // 🚫 CONSTRUTOR PRIVADO - ninguém pode fazer "new Logger()"
        private Logger() {
            this.logs = new StringBuilder();
            System.out.println("🔨 Logger criado! (só acontece uma vez)");
        }

        // 🚪 PORTEIRO - único jeito de pegar o Logger
        public static Logger getInstance() {
            if (instancia == null) {
                instancia = new Logger();
            }
            return instancia;
        }

        // 📄 Método para adicionar log
        public void log(String mensagem) {
            String logEntry = "[LOG] " + mensagem;
            logs.append(logEntry).append("\n");
            System.out.println(logEntry);
        }

        // 👀 Método para ver todos os logs
        public void mostrarLogs() {
            System.out.println("=== HISTÓRICO DE LOGS ===");
            System.out.println(logs.toString());
        }
    }

    // ========== FACTORY PATTERN DEMO ==========

    private static void demonstrarFactoryPattern() {
        System.out.println("--- PROBLEMA SEM FACTORY ---");

        // Simulando dados que vêm do banco/usuário
        String[] tiposDoSistema = {"pdf", "excel", "txt"};

        // ❌ Jeito ruim - if/else espalhado por todo código
        for (String tipo : tiposDoSistema) {
            System.out.println("\nCriando relatório: " + tipo);

            // Imagina ter isso em vários lugares do código...
            if (tipo.equals("pdf")) {
                RelatoriopDF relatorio = new RelatoriopDF();
                relatorio.gerar();
            } else if (tipo.equals("excel")) {
                RelatorioExcel relatorio = new RelatorioExcel();
                relatorio.gerar();
            } else if (tipo.equals("txt")) {
                RelatorioTXT relatorio = new RelatorioTXT();
                relatorio.gerar();
            }
        }

        System.out.println("\n--- SOLUÇÃO COM FACTORY ---");

        // ✅ Jeito profissional - Factory decide qual criar
        for (String tipo : tiposDoSistema) {
            System.out.println("\nCriando relatório: " + tipo);

            // Uma linha só! Factory decide qual instanciar
            Relatorio relatorio = RelatorioFactory.criarRelatorio(tipo);
            relatorio.gerar();
        }

        // Testando tipo inválido
        System.out.println("\n--- TESTANDO TIPO INVÁLIDO ---");
        try {
            Relatorio relatorio = RelatorioFactory.criarRelatorio("powerpoint");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro capturado: " + e.getMessage());
        }
    }

    // ========== FACTORY PATTERN IMPLEMENTATION ==========

    /**
     * 📋 INTERFACE COMUM - Todos os relatórios implementam
     */
    interface Relatorio {
        void gerar();
        String getTipo();
    }

    /**
     * 📄 RELATÓRIO PDF - Implementação específica
     */
    static class RelatoriopDF implements Relatorio {
        @Override
        public void gerar() {
            System.out.println("📄 Gerando relatório PDF...");
            System.out.println("   - Configurando layout");
            System.out.println("   - Adicionando gráficos");
            System.out.println("   - Exportando arquivo.pdf");
        }

        @Override
        public String getTipo() {
            return "PDF";
        }
    }

    /**
     * 📊 RELATÓRIO EXCEL - Implementação específica
     */
    static class RelatorioExcel implements Relatorio {
        @Override
        public void gerar() {
            System.out.println("📊 Gerando relatório Excel...");
            System.out.println("   - Criando planilha");
            System.out.println("   - Adicionando dados");
            System.out.println("   - Salvando arquivo.xlsx");
        }

        @Override
        public String getTipo() {
            return "Excel";
        }
    }

    /**
     * 📝 RELATÓRIO TXT - Implementação específica
     */
    static class RelatorioTXT implements Relatorio {
        @Override
        public void gerar() {
            System.out.println("📝 Gerando relatório TXT...");
            System.out.println("   - Formatando texto");
            System.out.println("   - Salvando arquivo.txt");
        }

        @Override
        public String getTipo() {
            return "TXT";
        }
    }

    /**
     * 🏭 FACTORY - Decide qual relatório criar
     *
     * VANTAGENS:
     * ✅ Centralizou a lógica de criação
     * ✅ Fácil adicionar novos tipos
     * ✅ Eliminou if/else espalhados
     */
    static class RelatorioFactory {
        public static Relatorio criarRelatorio(String tipo) {
            switch (tipo.toLowerCase()) {
                case "pdf":
                    return new RelatoriopDF();
                case "excel":
                case "xls":
                case "xlsx":
                    return new RelatorioExcel();
                case "txt":
                case "text":
                    return new RelatorioTXT();
                default:
                    throw new IllegalArgumentException("Tipo de relatório não suportado: " + tipo);
            }
        }
    }
}