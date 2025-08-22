package javapillars.abstraction;

/**
 * 🏛️ ABSTRACTION (ABSTRAÇÃO) - MANUAL DE REFERÊNCIA
 *
 * Abstração é o processo de esconder detalhes de implementação e mostrar apenas
 * a funcionalidade essencial ao usuário. É o conceito mais amplo dos 4 pilares.
 *
 * OBJETIVO: Simplificar a complexidade focando no QUE algo faz, não COMO faz.
 *
 * TIPOS DE ABSTRAÇÃO:
 * 🔒 Data Abstraction (Encapsulamento)
 * 🎭 Interface Abstraction (Interfaces)
 * 🏗️ Class Abstraction (Abstract Classes)
 * 📦 Functional Abstraction (Métodos)
 *
 * BENEFÍCIOS:
 * ✅ Reduz complexidade
 * ✅ Facilita manutenção
 * ✅ Permite foco no essencial
 * ✅ Promove reutilização
 */
public class AbstractionSyntax {

    // ========== 1. ABSTRACT CLASSES ==========

    /**
     * 🏗️ ABSTRACT CLASS: Classe que não pode ser instanciada diretamente.
     * Serve como base para outras classes, definindo estrutura comum.
     *
     * CARACTERÍSTICAS:
     * ✅ Pode ter métodos abstratos E concretos
     * ✅ Pode ter atributos (private, protected, public)
     * ✅ Pode ter construtores
     * ✅ Subclasses DEVEM implementar métodos abstratos
     * ❌ NÃO pode ser instanciada (new AbstractClass())
     * ❌ Uma classe só pode estender UMA abstract class
     */
    static abstract class AbstractVehicle {
        // 🔒 ATRIBUTOS: Abstract classes podem ter estado
        protected String marca;
        protected String modelo;
        protected int ano;

        // 🏗️ CONSTRUTOR: Abstract classes podem ter construtores
        public AbstractVehicle(String marca, String modelo, int ano) {
            this.marca = marca;
            this.modelo = modelo;
            this.ano = ano;
        }

        // 📋 MÉTODO CONCRETO: Implementação comum para todas as subclasses
        public void mostrarInfo() {
            System.out.println("🚗 Veículo: " + marca + " " + modelo + " (" + ano + ")");
        }

        // 🔄 MÉTODO ABSTRATO: DEVE ser implementado pelas subclasses
        public abstract void acelerar();
        public abstract void frear();
        public abstract double calcularConsumo();

        // 📖 GETTERS: Métodos concretos
        public String getMarca() {
            return marca;
        }

        public String getModelo() {
            return modelo;
        }

        public int getAno() {
            return ano;
        }
    }

    // ========== 2. INTERFACES ==========

    /**
     * 🎭 INTERFACE: Contrato puro que define O QUE uma classe deve fazer.
     *
     * CARACTERÍSTICAS:
     * ✅ Todos os métodos são públicos por padrão
     * ✅ Pode ter métodos abstratos, default e static
     * ✅ Pode ter constantes (public static final)
     * ✅ Uma classe pode implementar MÚLTIPLAS interfaces
     * ✅ Representa capacidades/habilidades ("can-do")
     * ❌ NÃO pode ter atributos de instância
     * ❌ NÃO pode ser instanciada
     */

    // 🎭 INTERFACE: Capacidade de voar
    interface Flyable {
        // 🔄 MÉTODO ABSTRATO: Implementação obrigatória
        void takeOff();
        void land();

        // 📊 CONSTANTE: public static final (implícito)
        double MAX_ALTITUDE = 10000.0; // Altitude máxima em metros

        // ⚡ MÉTODO DEFAULT: Implementação padrão (Java 8+)
        default void fly() {
            System.out.println("✈️ Voando na altitude máxima de " + MAX_ALTITUDE + "m");
        }

        // 📦 MÉTODO STATIC: Pertence à interface, não à instância
        static boolean canFlyAtAltitude(double altitude) {
            return altitude <= MAX_ALTITUDE;
        }
    }

    // 🎭 INTERFACE: Capacidade de nadar
    interface Swimmable {
        void swim();

        // ⚡ MÉTODO DEFAULT
        default void dive() {
            System.out.println("🌊 Mergulhando nas profundezas");
        }
    }

    // 🎭 INTERFACE: Capacidade de andar na terra
    interface Walkable {
        void walk();

        default void run() {
            System.out.println("🏃 Correndo em terra firme");
        }
    }

    // ========== 3. IMPLEMENTAÇÕES CONCRETAS ==========

    // 🚗 CLASSE CONCRETA: Herda de abstract class
    static class Car extends AbstractVehicle {
        private int numeroPortas;

        public Car(String marca, String modelo, int ano, int numeroPortas) {
            super(marca, modelo, ano); // Chama construtor da abstract class
            this.numeroPortas = numeroPortas;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Métodos abstratos
        @Override
        public void acelerar() {
            System.out.println("🚗 Carro acelerando suavemente");
        }

        @Override
        public void frear() {
            System.out.println("🚗 Carro freando com ABS");
        }

        @Override
        public double calcularConsumo() {
            return 12.5; // km/l
        }

        public int getNumeroPortas() {
            return numeroPortas;
        }
    }

    // ✈️ CLASSE CONCRETA: Herda abstract class + implementa interface
    static class Airplane extends AbstractVehicle implements Flyable {
        private int numeroPassageiros;

        public Airplane(String marca, String modelo, int ano, int numeroPassageiros) {
            super(marca, modelo, ano);
            this.numeroPassageiros = numeroPassageiros;
        }

        // 🔄 IMPLEMENTAÇÃO DA ABSTRACT CLASS
        @Override
        public void acelerar() {
            System.out.println("✈️ Avião acelerando para decolagem");
        }

        @Override
        public void frear() {
            System.out.println("✈️ Avião freando após pouso");
        }

        @Override
        public double calcularConsumo() {
            return 3.2; // litros por km
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE Flyable
        @Override
        public void takeOff() {
            System.out.println("✈️ Avião decolando - prepare-se!");
        }

        @Override
        public void land() {
            System.out.println("✈️ Avião pousando - bem-vindos!");
        }

        // ✅ HERDA fly() da interface (método default)
        // ✅ HERDA mostrarInfo() da abstract class

        public int getNumeroPassageiros() {
            return numeroPassageiros;
        }
    }

    // 🦆 CLASSE CONCRETA: Múltiplas interfaces (capacidades múltiplas)
    static class Duck implements Flyable, Swimmable, Walkable {
        private String nome;

        public Duck(String nome) {
            this.nome = nome;
        }

        // 🔄 IMPLEMENTAÇÃO DE Flyable
        @Override
        public void takeOff() {
            System.out.println("🦆 " + nome + " batendo asas para decolar");
        }

        @Override
        public void land() {
            System.out.println("🦆 " + nome + " pousando graciosamente");
        }

        // 🔄 IMPLEMENTAÇÃO DE Swimmable
        @Override
        public void swim() {
            System.out.println("🦆 " + nome + " nadando como um... pato!");
        }

        // 🔄 IMPLEMENTAÇÃO DE Walkable
        @Override
        public void walk() {
            System.out.println("🦆 " + nome + " caminhando com seus pés palmados");
        }

        // ✅ HERDA fly(), dive(), run() das interfaces (métodos default)

        public String getNome() {
            return nome;
        }
    }

    // ========== 4. FUNCTIONAL INTERFACE (PRÉVIA) ==========

    /**
     * 🎯 FUNCTIONAL INTERFACE: Interface com apenas UM método abstrato.
     * Base para expressions lambda e method references.
     */
    @FunctionalInterface
    interface VehicleAction {
        void execute(AbstractVehicle vehicle);

        // ⚡ PODE ter métodos default e static
        default void logAction() {
            System.out.println("📝 Executando ação no veículo");
        }
    }

    // ========== 5. DEMONSTRAÇÕES ==========

    public static void demonstrarAbstractClass() {
        System.out.println("=== ABSTRACT CLASSES ===\n");

        // ❌ AbstractVehicle vehicle = new AbstractVehicle(); // ERRO! Não pode instanciar

        // ✅ Pode instanciar subclasses concretas
        Car carro = new Car("Toyota", "Corolla", 2023, 4);
        Airplane aviao = new Airplane("Boeing", "737", 2022, 180);

        System.out.println("--- Carro ---");
        carro.mostrarInfo();      // Método herdado da abstract class
        carro.acelerar();         // Implementação específica
        System.out.println("Consumo: " + carro.calcularConsumo() + " km/l");

        System.out.println("\n--- Avião ---");
        aviao.mostrarInfo();      // Método herdado da abstract class
        aviao.acelerar();         // Implementação específica
        System.out.println("Consumo: " + aviao.calcularConsumo() + " l/km");
    }

    public static void demonstrarInterfaces() {
        System.out.println("\n=== INTERFACES ===\n");

        Airplane aviao = new Airplane("Airbus", "A380", 2024, 850);
        Duck pato = new Duck("Donald");

        System.out.println("--- Avião (implementa Flyable) ---");
        aviao.takeOff();         // Método da interface
        aviao.fly();             // Método default da interface
        aviao.land();            // Método da interface

        System.out.println("\n--- Pato (múltiplas interfaces) ---");
        pato.takeOff();          // De Flyable
        pato.fly();              // Default de Flyable
        pato.swim();             // De Swimmable
        pato.dive();             // Default de Swimmable
        pato.walk();             // De Walkable
        pato.run();              // Default de Walkable

        System.out.println("\n--- Método static da interface ---");
        System.out.println("Pode voar a 5000m? " + Flyable.canFlyAtAltitude(5000));
        System.out.println("Pode voar a 15000m? " + Flyable.canFlyAtAltitude(15000));
    }

    public static void demonstrarPolimorfismo() {
        System.out.println("\n=== POLIMORFISMO COM ABSTRAÇÃO ===\n");

        // 🔄 POLIMORFISMO com abstract class
        AbstractVehicle[] veiculos = {
                new Car("Honda", "Civic", 2023, 4),
                new Airplane("Embraer", "E190", 2024, 100)
        };

        System.out.println("--- Polimorfismo com Abstract Class ---");
        for (AbstractVehicle veiculo : veiculos) {
            veiculo.mostrarInfo();    // Método comum
            veiculo.acelerar();       // Implementação específica
            System.out.println("Consumo: " + veiculo.calcularConsumo());
            System.out.println();
        }

        // 🔄 POLIMORFISMO com interfaces
        Flyable[] voadores = {
                new Airplane("Boeing", "747", 2023, 400),
                new Duck("Patolino")
        };

        System.out.println("--- Polimorfismo com Interface ---");
        for (Flyable voador : voadores) {
            voador.takeOff();        // Comportamento específico
            voador.fly();            // Método default
            voador.land();           // Comportamento específico
            System.out.println();
        }
    }

    public static void demonstrarFunctionalInterface() {
        System.out.println("=== FUNCTIONAL INTERFACE (PRÉVIA) ===\n");

        Car carro = new Car("Ford", "Focus", 2023, 4);

        // 🎯 Lambda expression (prévia para módulo posterior)
        VehicleAction acelerar = (vehicle) -> {
            System.out.println("🚀 Executando ação personalizada:");
            vehicle.acelerar();
        };

        acelerar.logAction();     // Método default
        acelerar.execute(carro);  // Método abstrato via lambda
    }

    // ========== 6. COMPARAÇÕES E REGRAS ==========

    /**
     * 📋 ABSTRACT CLASS vs INTERFACE:
     *
     * ABSTRACT CLASS:
     * ✅ Pode ter métodos concretos e abstratos
     * ✅ Pode ter atributos de instância
     * ✅ Pode ter construtores
     * ✅ Representa relação "is-a"
     * ❌ Herança simples (extends apenas uma)
     *
     * INTERFACE:
     * ✅ Métodos abstratos, default e static
     * ✅ Múltipla implementação
     * ✅ Representa capacidade "can-do"
     * ❌ Não pode ter atributos de instância
     * ❌ Não pode ter construtores
     *
     * QUANDO USAR:
     * 🏗️ Abstract Class: Quando há código comum para compartilhar
     * 🎭 Interface: Quando há diferentes formas de fazer algo
     * 🔄 Ambos: Para máxima flexibilidade
     *
     * EXEMPLOS PRÁTICOS:
     * - Animal (abstract) vs Flyable (interface)
     * - Shape (abstract) vs Drawable (interface)
     * - Vehicle (abstract) vs Startable (interface)
     */

    // ========== MAIN: DEMONSTRAÇÃO COMPLETA ==========

    public static void main(String[] args) {
        System.out.println("🏛️ ABSTRACTION (ABSTRAÇÃO) - MANUAL DE REFERÊNCIA\n");

        demonstrarAbstractClass();
        demonstrarInterfaces();
        demonstrarPolimorfismo();
        demonstrarFunctionalInterface();

        System.out.println("\n=== CONCEITOS FUNDAMENTAIS ===");
        System.out.println("🏛️ Abstração = Esconder complexidade, mostrar essencial");
        System.out.println("🏗️ Abstract Class = Base comum + alguns métodos obrigatórios");
        System.out.println("🎭 Interface = Contrato puro + capacidades múltiplas");
        System.out.println("🔄 Polimorfismo funciona com ambos");
        System.out.println("⚡ Default methods = Evolução de interfaces sem quebrar código");

        System.out.println("\n=== HIERARQUIA DE ABSTRAÇÃO ===");
        System.out.println("Concreto ← Abstract Class ← Interface ← Concept");
        System.out.println("Car ← AbstractVehicle ← Flyable ← \"Capacidade de voar\"");

        System.out.println("\n=== SINTAXE CHAVE ===");
        System.out.println("abstract class Base { abstract void method(); }");
        System.out.println("interface Capability { void method(); }");
        System.out.println("class Concrete extends Base implements Capability { ... }");
        System.out.println("@FunctionalInterface interface Action { void execute(); }");

        System.out.println("\n🎯 ABSTRAÇÃO = Simplicidade + Flexibilidade + Reutilização!");
    }
}