package com.kaue.toolbox.javapillars.inheritance;

/**
 * 🧬 INHERITANCE (HERANÇA) - MANUAL DE REFERÊNCIA
 *
 * Herança permite que uma classe herde características (atributos e métodos)
 * de outra classe, estabelecendo uma relação "is-a" (é um/uma).
 *
 * BENEFÍCIOS:
 * ✅ Reutilização de código
 * ✅ Organização hierárquica
 * ✅ Facilita manutenção
 * ✅ Permite polimorfismo
 */
public class InheritanceSyntax {

    // ========== 1. CLASSE PAI (SUPERCLASSE/BASE) ==========

    static class ClassePai {
        // 🔓 protected: Visível para subclasses
        protected String atributoProtegido = "Acessível pelas filhas";

        // 🔒 private: NÃO visível para subclasses
        private String atributoPrivado = "Só a classe pai vê";

        // 🌍 public: Visível para todos
        public String atributoPublico = "Todos podem ver";

        // Construtor da classe pai
        public ClassePai(String valor) {
            this.atributoPublico = valor;
            System.out.println("🏗️ Construtor da ClassePai executado");
        }

        // Método que pode ser herdado
        public void metodoHerdado() {
            System.out.println("📋 Método da classe pai");
        }

        // Método que pode ser sobrescrito
        public void metodoSobrescritivel() {
            System.out.println("🔄 Método original da classe pai");
        }

        // Método final NÃO pode ser sobrescrito
        public final void metodoFinal() {
            System.out.println("🔒 Método final - não pode ser alterado");
        }
    }

    // ========== 2. CLASSE FILHA (SUBCLASSE/DERIVADA) ==========

    static class ClasseFilha extends ClassePai {
        // 🆕 Atributo específico da classe filha
        private String atributoFilha = "Só da filha";

        // Construtor da classe filha
        public ClasseFilha(String valorPai, String valorFilha) {
            // 🔑 super() DEVE ser a primeira linha (chama construtor do pai)
            super(valorPai);
            this.atributoFilha = valorFilha;
            System.out.println("🏗️ Construtor da ClasseFilha executado");
        }

        // ========== TIPOS DE MÉTODOS NA HERANÇA ==========

        // 📋 Método HERDADO: Usa exatamente como está no pai
        // metodoHerdado() está disponível automaticamente

        // 🔄 Método SOBRESCRITO: Nova implementação
        @Override  // 📌 Anotação recomendada para clareza
        public void metodoSobrescritivel() {
            System.out.println("🆕 Nova implementação na classe filha");

            // 🔗 super.metodo(): Chama a versão do pai se necessário
            super.metodoSobrescritivel();
        }

        // 🆕 Método NOVO: Específico da classe filha
        public void metodoEspecificoFilha() {
            System.out.println("⭐ Método que só a filha tem");

            // ✅ Pode acessar atributos protected do pai
            System.out.println("Acessando pai: " + atributoProtegido);

            // ❌ NÃO pode acessar atributos private do pai
            // System.out.println(atributoPrivado); // ERRO!
        }

        // 🔒 TENTATIVA de sobrescrever método final resultaria em ERRO
        // public void metodoFinal() { } // ❌ ERRO DE COMPILAÇÃO!
    }

    // ========== 3. HERANÇA MULTINÍVEL ==========

    static class ClasseNeta extends ClasseFilha {
        public ClasseNeta(String valorPai, String valorFilha, String valorNeta) {
            super(valorPai, valorFilha);
            System.out.println("🏗️ Construtor da ClasseNeta executado");
        }

        // A neta herda TUDO que a filha herdou do pai
        // + os métodos específicos da filha

        @Override
        public void metodoSobrescritivel() {
            System.out.println("🔄 Implementação da neta");
            // Pode chamar versão da filha OU do pai
            super.metodoSobrescritivel(); // Chama versão da filha
        }
    }

    // ========== 4. SINTAXES IMPORTANTES ==========

    public static void demonstrarSintaxes() {
        System.out.println("=== SINTAXES DE HERANÇA ===\n");

        // ✅ Criando objeto da classe filha
        ClasseFilha filha = new ClasseFilha("Valor do Pai", "Valor da Filha");

        System.out.println("\n--- MÉTODOS HERDADOS ---");
        filha.metodoHerdado();           // Método do pai
        filha.metodoSobrescritivel();    // Versão sobrescrita
        filha.metodoFinal();             // Método final do pai
        filha.metodoEspecificoFilha();   // Método próprio

        System.out.println("\n--- HERANÇA MULTINÍVEL ---");
        ClasseNeta neta = new ClasseNeta("Pai", "Filha", "Neta");
        neta.metodoHerdado();            // Do bisavô (ClassePai)
        neta.metodoEspecificoFilha();    // Do pai (ClasseFilha)
        neta.metodoSobrescritivel();     // Própria implementação

        System.out.println("\n--- VERIFICAÇÃO DE TIPO ---");
        // instanceof: Verifica se objeto é instância de uma classe
        System.out.println("neta instanceof ClasseNeta: " + (neta instanceof ClasseNeta));
        System.out.println("neta instanceof ClasseFilha: " + (neta instanceof ClasseFilha));
        System.out.println("neta instanceof ClassePai: " + (neta instanceof ClassePai));
        System.out.println("neta instanceof Object: " + (neta instanceof Object));
    }

    // ========== 5. REGRAS E LIMITAÇÕES ==========

    /**
     * 📋 REGRAS DA HERANÇA EM JAVA:
     *
     * ✅ UMA classe pode estender APENAS UMA classe (herança simples)
     * ✅ Uma classe pode implementar MÚLTIPLAS interfaces
     * ✅ Todas as classes herdam de Object (implicitamente)
     * ✅ Construtores NÃO são herdados
     * ✅ super() deve ser primeira linha do construtor (se usado)
     *
     * ❌ Java NÃO tem herança múltipla de classes
     * ❌ Métodos private NÃO são herdados
     * ❌ Métodos final NÃO podem ser sobrescritos
     * ❌ Classes final NÃO podem ser estendidas
     *
     * 🔑 PALAVRAS-CHAVE:
     * - extends: Criar herança
     * - super(): Chamar construtor do pai
     * - super.metodo(): Chamar método do pai
     * - @Override: Marcar sobrescrita
     * - final: Impedir herança/sobrescrita
     * - protected: Visível para subclasses
     * - instanceof: Verificar tipo
     */

    // ========== 6. MODIFICADORES DE ACESSO NA HERANÇA ==========

    /**
     * 👁️ VISIBILIDADE NA HERANÇA:
     *
     * 🌍 public    → Herdado e visível para todos
     * 🔓 protected → Herdado e visível para subclasses (mesmo que em pacotes diferentes)
     * 📦 package   → Herdado APENAS se subclasse estiver no mesmo pacote
     * 🔒 private   → NÃO é herdado (invisível para subclasses)
     *
     * EXEMPLO:
     * class Pai {
     *     public String publico = "Todos veem";          // ✅ Herdado
     *     protected String protegido = "Filhas veem";    // ✅ Herdado
     *     String pacote = "Mesmo pacote vê";             // ✅ Herdado (se mesmo pacote)
     *     private String privado = "Só eu vejo";         // ❌ NÃO herdado
     * }
     */

    // ========== MAIN: DEMONSTRAÇÃO ==========

    public static void main(String[] args) {
        System.out.println("🧬 INHERITANCE (HERANÇA) - MANUAL DE REFERÊNCIA\n");

        demonstrarSintaxes();

        System.out.println("\n=== HIERARQUIA DE HERANÇA ===");
        System.out.println("Object");
        System.out.println("  └── ClassePai");
        System.out.println("      └── ClasseFilha");
        System.out.println("          └── ClasseNeta");

        System.out.println("\n=== CONCEITOS FUNDAMENTAIS ===");
        System.out.println("🔑 Herança estabelece relação 'is-a'");
        System.out.println("🔄 Permite reutilização de código");
        System.out.println("📋 Subclasses herdam características do pai");
        System.out.println("🆕 Subclasses podem adicionar novos comportamentos");
        System.out.println("🔄 Subclasses podem sobrescrever comportamentos");
        System.out.println("🏗️ Construtores são chamados em cadeia (pai → filha)");
    }
}