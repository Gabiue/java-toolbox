package com.kaue.toolbox.javapillars.polymorphism;

/**
 * 🔄 POLYMORPHISM (POLIMORFISMO) - MANUAL DE REFERÊNCIA
 *
 * Polimorfismo permite que objetos de diferentes classes sejam tratados
 * como objetos de uma classe comum (superclasse), mas mantendo seus
 * comportamentos específicos.
 *
 * SIGNIFICADO: "Poli" = muitas, "morphism" = formas
 * UM MESMO MÉTODO pode ter MUITAS IMPLEMENTAÇÕES diferentes
 *
 * BENEFÍCIOS:
 * ✅ Flexibilidade no código
 * ✅ Facilita extensibilidade
 * ✅ Reduz acoplamento
 * ✅ Permite código genérico
 */
public class PolymorphismSyntax {

    // ========== 1. CLASSE PAI PARA DEMONSTRAÇÃO ==========

    static abstract class Forma {
        protected String nome;

        public Forma(String nome) {
            this.nome = nome;
        }

        // 🔄 MÉTODO POLIMÓRFICO: Cada subclasse implementa diferente
        public abstract double calcularArea();

        // 📋 MÉTODO COMUM: Todas herdam igual
        public void mostrarInfo() {
            System.out.println("Forma: " + nome + " | Área: " + calcularArea());
        }

        // 🔄 MÉTODO SOBRESCRITÍVEL: Pode ser personalizado
        public void desenhar() {
            System.out.println("Desenhando uma forma genérica: " + nome);
        }
    }

    // ========== 2. SUBCLASSES COM COMPORTAMENTOS ESPECÍFICOS ==========

    static class Circulo extends Forma {
        private double raio;

        public Circulo(double raio) {
            super("Círculo");
            this.raio = raio;
        }

        // 🔄 IMPLEMENTAÇÃO ESPECÍFICA: Como círculo calcula área
        @Override
        public double calcularArea() {
            return Math.PI * raio * raio;
        }

        // 🔄 COMPORTAMENTO ESPECÍFICO: Como círculo desenha
        @Override
        public void desenhar() {
            System.out.println("🔵 Desenhando círculo com raio " + raio);
        }

        // ⭐ MÉTODO ESPECÍFICO: Só círculo tem
        public void mostrarRaio() {
            System.out.println("Raio do círculo: " + raio);
        }
    }

    static class Retangulo extends Forma {
        private double largura, altura;

        public Retangulo(double largura, double altura) {
            super("Retângulo");
            this.largura = largura;
            this.altura = altura;
        }

        // 🔄 IMPLEMENTAÇÃO ESPECÍFICA: Como retângulo calcula área
        @Override
        public double calcularArea() {
            return largura * altura;
        }

        // 🔄 COMPORTAMENTO ESPECÍFICO: Como retângulo desenha
        @Override
        public void desenhar() {
            System.out.println("🔲 Desenhando retângulo " + largura + "x" + altura);
        }

        // ⭐ MÉTODO ESPECÍFICO: Só retângulo tem
        public void mostrarDimensoes() {
            System.out.println("Dimensões: " + largura + " x " + altura);
        }
    }

    static class Triangulo extends Forma {
        private double base, altura;

        public Triangulo(double base, double altura) {
            super("Triângulo");
            this.base = base;
            this.altura = altura;
        }

        // 🔄 IMPLEMENTAÇÃO ESPECÍFICA: Como triângulo calcula área
        @Override
        public double calcularArea() {
            return (base * altura) / 2;
        }

        // 🔄 COMPORTAMENTO ESPECÍFICO: Como triângulo desenha
        @Override
        public void desenhar() {
            System.out.println("🔺 Desenhando triângulo base:" + base + " altura:" + altura);
        }

        // ⭐ MÉTODO ESPECÍFICO: Só triângulo tem
        public void mostrarLados() {
            System.out.println("Base: " + base + ", Altura: " + altura);
        }
    }

    // ========== 3. DEMONSTRAÇÃO DE POLIMORFISMO ==========

    public static void demonstrarPolimorfismoBasico() {
        System.out.println("=== POLIMORFISMO BÁSICO ===\n");

        // 🔑 CHAVE DO POLIMORFISMO: Referência da superclasse → objeto da subclasse
        Forma forma1 = new Circulo(5);        // ✅ Círculo É UMA Forma
        Forma forma2 = new Retangulo(4, 6);   // ✅ Retângulo É UMA Forma
        Forma forma3 = new Triangulo(3, 8);   // ✅ Triângulo É UMA Forma

        // 🔄 POLIMORFISMO EM AÇÃO: Mesmo método, comportamentos diferentes
        System.out.println("--- CHAMADAS POLIMÓRFICAS ---");
        forma1.mostrarInfo();  // Chama versão do Círculo
        forma2.mostrarInfo();  // Chama versão do Retângulo
        forma3.mostrarInfo();  // Chama versão do Triângulo

        System.out.println("\n--- DESENHOS POLIMÓRFICOS ---");
        forma1.desenhar();     // Desenho específico do Círculo
        forma2.desenhar();     // Desenho específico do Retângulo
        forma3.desenhar();     // Desenho específico do Triângulo
    }

    public static void demonstrarArrayPolimorfico() {
        System.out.println("\n=== ARRAY POLIMÓRFICO ===\n");

        // 🔄 PODER DO POLIMORFISMO: Array de diferentes tipos tratados igual
        Forma[] formas = {
                new Circulo(3),
                new Retangulo(5, 2),
                new Triangulo(4, 6),
                new Circulo(7),
                new Retangulo(3, 3)
        };

        System.out.println("Processando " + formas.length + " formas diferentes:");

        // 🔄 LOOP POLIMÓRFICO: Cada forma se comporta do seu jeito
        for (Forma forma : formas) {
            forma.desenhar();      // Método polimórfico: cada um desenha diferente
            forma.mostrarInfo();   // Cada um calcula área do seu jeito
            System.out.println("---");
        }

        // 📊 EXEMPLO: Somar áreas de formas diferentes
        double areaTotal = 0;
        for (Forma forma : formas) {
            areaTotal += forma.calcularArea(); // Polimorfismo: cada um calcula diferente
        }
        System.out.println("Área total de todas as formas: " + String.format("%.2f", areaTotal));
    }

    // ========== 4. INSTANCEOF E CASTING ==========

    public static void demonstrarInstanceofCasting() {
        System.out.println("\n=== INSTANCEOF E CASTING ===\n");

        Forma[] formas = {
                new Circulo(4),
                new Retangulo(3, 5),
                new Triangulo(2, 7)
        };

        for (Forma forma : formas) {
            // 🔍 INSTANCEOF: Verifica o tipo real do objeto
            System.out.println("Processando: " + forma.nome);

            if (forma instanceof Circulo) {
                // ⬇️ CASTING: Converte referência para acessar métodos específicos
                Circulo circulo = (Circulo) forma;
                circulo.mostrarRaio();  // Método específico do Círculo

            } else if (forma instanceof Retangulo) {
                Retangulo retangulo = (Retangulo) forma;
                retangulo.mostrarDimensoes();  // Método específico do Retângulo

            } else if (forma instanceof Triangulo) {
                Triangulo triangulo = (Triangulo) forma;
                triangulo.mostrarLados();  // Método específico do Triângulo
            }
            System.out.println();
        }
    }

    // ========== 5. POLIMORFISMO COM MÉTODOS ==========

    // 🔄 MÉTODO POLIMÓRFICO: Aceita qualquer Forma
    public static void processarForma(Forma forma) {
        System.out.println("📋 Processando forma recebida:");
        forma.desenhar();       // Comportamento específico da forma
        forma.mostrarInfo();    // Informações específicas da forma

        // Tratamento específico se necessário
        if (forma instanceof Circulo) {
            System.out.println("🔵 É um círculo!");
        } else if (forma instanceof Retangulo) {
            System.out.println("🔲 É um retângulo!");
        } else if (forma instanceof Triangulo) {
            System.out.println("🔺 É um triângulo!");
        }
    }

    // 🔄 MÉTODO QUE DEMONSTRA FLEXIBILIDADE
    public static double calcularAreaTotal(Forma... formas) {
        double total = 0;
        System.out.println("Calculando área total de " + formas.length + " formas:");

        for (Forma forma : formas) {
            double area = forma.calcularArea(); // Polimorfismo!
            System.out.println("- " + forma.nome + ": " + String.format("%.2f", area));
            total += area;
        }

        return total;
    }

    // ========== 6. REGRAS E CONCEITOS IMPORTANTES ==========

    /**
     * 📋 REGRAS DO POLIMORFISMO:
     *
     * ✅ Referência da superclasse pode apontar para objeto da subclasse
     * ✅ Método chamado é determinado em TEMPO DE EXECUÇÃO (late binding)
     * ✅ Sobrescrita (@Override) é essencial para polimorfismo
     * ✅ instanceof verifica o tipo real do objeto
     * ✅ Casting permite acessar métodos específicos da subclasse
     *
     * ❌ Referência da subclasse NÃO pode apontar para objeto da superclasse
     * ❌ Métodos específicos da subclasse não são acessíveis via referência da superclasse
     *
     * 🔑 TIPOS DE POLIMORFISMO:
     * - Polimorfismo de método (method overriding)
     * - Polimorfismo de tipo (superclass reference)
     * - Polimorfismo paramétrico (generics - veremos depois)
     *
     * 💡 VANTAGENS:
     * - Código mais flexível e extensível
     * - Facilita adição de novos tipos
     * - Reduz duplicação de código
     * - Permite programação genérica
     */

    // ========== MAIN: DEMONSTRAÇÃO COMPLETA ==========

    public static void main(String[] args) {
        System.out.println("🔄 POLYMORPHISM (POLIMORFISMO) - MANUAL DE REFERÊNCIA\n");

        // Demonstrações passo a passo
        demonstrarPolimorfismoBasico();
        demonstrarArrayPolimorfico();
        demonstrarInstanceofCasting();

        System.out.println("=== POLIMORFISMO COM MÉTODOS ===\n");

        // Testando método polimórfico
        processarForma(new Circulo(6));
        System.out.println();
        processarForma(new Retangulo(4, 8));
        System.out.println();

        // Calculando área total de formas diferentes
        System.out.println("=== CÁLCULO POLIMÓRFICO ===");
        double areaTotal = calcularAreaTotal(
                new Circulo(3),
                new Retangulo(4, 5),
                new Triangulo(6, 4),
                new Circulo(2)
        );
        System.out.println("🔢 Área total: " + String.format("%.2f", areaTotal));

        System.out.println("\n=== CONCEITOS FUNDAMENTAIS ===");
        System.out.println("🔑 Uma referência, múltiplos comportamentos");
        System.out.println("🔄 Mesmo método, implementações diferentes");
        System.out.println("⏰ Decisão do método em tempo de execução");
        System.out.println("🎯 instanceof + casting para acesso específico");
        System.out.println("📦 Arrays/Collections podem misturar tipos diferentes");
        System.out.println("🚀 Base para programação flexível e extensível");

        System.out.println("\n=== SINTAXE CHAVE ===");
        System.out.println("Superclasse ref = new Subclasse();  // ✅ Polimorfismo");
        System.out.println("ref.metodo();                        // Chama versão da subclasse");
        System.out.println("if (ref instanceof Subclasse) { ... } // Verificar tipo");
        System.out.println("Subclasse obj = (Subclasse) ref;     // Casting para acesso específico");
    }
}