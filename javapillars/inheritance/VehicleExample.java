
package javapillars.inheritance;

public class VehicleExample {

    // 🚗 CLASSE PAI (SUPERCLASSE): Define características comuns de todos os veículos
    static class Vehicle {
        // 🔒 ENCAPSULAMENTO: Atributos privados protegidos
        private String marca;
        private String modelo;
        private int ano;
        private double velocidade;

        // 🏗️ CONSTRUTOR: Define como criar um veículo
        public Vehicle(String marca, String modelo, int ano, double velocidade) {
            this.marca = marca;
            this.modelo = modelo;
            this.ano = ano;
            this.velocidade = velocidade;
        }

        // 📖 GETTERS E SETTERS: Acesso controlado aos atributos privados
        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public int getAno() {
            return ano;
        }

        public void setAno(int ano) {
            this.ano = ano;
        }

        public double getVelocidade() {
            return velocidade;
        }

        public void setVelocidade(double velocidade) {
            this.velocidade = velocidade;
        }

        // 🔄 MÉTODOS SOBRESCRITÍVEIS: Cada veículo pode ter comportamento específico
        public void acelerar() {
            System.out.println("Acelerando o veículo " + marca + " " + modelo);
        }

        public void frear() {
            System.out.println("Freando o veículo " + marca + " " + modelo);
        }

        public void ligar() {
            System.out.println("Ligando o veículo " + marca + " " + modelo);
        }

        public void desligar() {
            System.out.println("Desligando o veículo " + marca + " " + modelo);
        }

        // 📄 toString(): Informações básicas do veículo
        @Override
        public String toString() {
            return marca + " " + modelo + " (" + ano + ") - Velocidade máxima: " + velocidade + " km/h";
        }
    }

    // 🏍️ CLASSE FILHA: Motorcycle herda de Vehicle (relação "is-a")
    static class Motorcycle extends Vehicle {
        // ⭐ ATRIBUTO ESPECÍFICO: Só motocicleta tem tipo de guidão
        private String tipoDeGuidao;

        // 🏗️ CONSTRUTOR: Chama construtor do pai com super()
        public Motorcycle(String marca, String modelo, int ano, double velocidade, String tipoDeGuidao) {
            super(marca, modelo, ano, velocidade); // ✅ HERANÇA: Reutiliza construtor do Vehicle
            this.tipoDeGuidao = tipoDeGuidao;
        }

        // 📖 GETTER/SETTER para atributo específico
        public String getTipoDeGuidao() {
            return tipoDeGuidao;
        }

        public void setTipoDeGuidao(String tipoDeGuidao) {
            this.tipoDeGuidao = tipoDeGuidao;
        }

        // 🔄 SOBRESCRITAS: Comportamentos específicos da motocicleta
        @Override
        public void acelerar() {
            System.out.println("🏍️ Acelerando a motocicleta " + getMarca() + " " + getModelo() + " com guidão " + tipoDeGuidao);
        }

        @Override
        public void frear() {
            System.out.println("🏍️ Freando a motocicleta " + getMarca() + " " + getModelo() + " (cuidado com a derrapagem!)");
        }

        @Override
        public void ligar() {
            System.out.println("🏍️ Ligando a motocicleta " + getMarca() + " " + getModelo() + " - *ronco do motor*");
        }

        @Override
        public void desligar() {
            System.out.println("🏍️ Desligando a motocicleta " + getMarca() + " " + getModelo());
        }

        // ⭐ MÉTODO ESPECÍFICO: Só motocicleta pode empinar
        public void empinar() {
            System.out.println("🏍️ Empinando a motocicleta " + getMarca() + " " + getModelo() + " - Manobra radical!");
        }

        // 📄 toString(): Sobrescreve para incluir informações específicas
        @Override
        public String toString() {
            return super.toString() + " | Tipo de guidão: " + tipoDeGuidao;
        }
    }

    // 🚙 CLASSE FILHA: Car herda de Vehicle (relação "is-a")
    static class Car extends Vehicle {
        // ⭐ ATRIBUTO ESPECÍFICO: Só carro tem número de portas
        private int numeroDePortas;

        // 🏗️ CONSTRUTOR: Chama construtor do pai com super()
        public Car(String marca, String modelo, int ano, double velocidade, int numeroDePortas) {
            super(marca, modelo, ano, velocidade); // ✅ HERANÇA: Reutiliza construtor do Vehicle
            this.numeroDePortas = numeroDePortas;
        }

        // 📖 GETTER/SETTER para atributo específico
        public int getNumeroDePortas() {
            return numeroDePortas;
        }

        public void setNumeroDePortas(int numeroDePortas) {
            this.numeroDePortas = numeroDePortas;
        }

        // 🔄 SOBRESCRITAS: Comportamentos específicos do carro
        @Override
        public void acelerar() {
            System.out.println("🚙 Acelerando o carro " + getMarca() + " " + getModelo() + " (" + numeroDePortas + " portas)");
        }

        @Override
        public void frear() {
            System.out.println("🚙 Freando o carro " + getMarca() + " " + getModelo() + " com sistema ABS");
        }

        @Override
        public void ligar() {
            System.out.println("🚙 Ligando o carro " + getMarca() + " " + getModelo() + " - *motor silencioso*");
        }

        @Override
        public void desligar() {
            System.out.println("🚙 Desligando o carro " + getMarca() + " " + getModelo());
        }

        // ⭐ MÉTODO ESPECÍFICO: Só carro pode abrir porta
        public void abrirPorta() {
            System.out.println("🚙 Abrindo uma das " + numeroDePortas + " portas do " + getMarca() + " " + getModelo());
        }

        // 📄 toString(): Sobrescreve para incluir informações específicas
        @Override
        public String toString() {
            return super.toString() + " | Número de portas: " + numeroDePortas;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE HERANÇA COM VEÍCULOS ===\n");

        // 🚗 CRIANDO VEÍCULO GENÉRICO
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2020, 180.0);
        System.out.println("--- VEÍCULO GENÉRICO ---");
        System.out.println("Informações: " + vehicle);
        vehicle.ligar();      // ✅ Método original da classe Vehicle
        vehicle.acelerar();   // ✅ Método original da classe Vehicle
        vehicle.frear();      // ✅ Método original da classe Vehicle
        vehicle.desligar();   // ✅ Método original da classe Vehicle

        System.out.println();

        // 🏍️ CRIANDO MOTOCICLETA (herda de Vehicle)
        Motorcycle motorcycle = new Motorcycle("Honda", "CB500", 2021, 200.0, "Esportivo");
        System.out.println("--- MOTOCICLETA (herda de Vehicle) ---");
        System.out.println("Informações: " + motorcycle); // 📄 toString() sobrescrito
        motorcycle.ligar();     // 🔄 SOBRESCRITO: versão específica da Motorcycle
        motorcycle.acelerar();  // 🔄 SOBRESCRITO: versão específica da Motorcycle
        motorcycle.empinar();   // ⭐ ESPECÍFICO: só Motorcycle tem este método
        motorcycle.frear();     // 🔄 SOBRESCRITO: versão específica da Motorcycle
        motorcycle.desligar();  // 🔄 SOBRESCRITO: versão específica da Motorcycle

        // ✅ Motorcycle também herda getters/setters:
        System.out.println("Marca da moto: " + motorcycle.getMarca()); // 📋 HERDADO

        System.out.println();

        // 🚙 CRIANDO CARRO (herda de Vehicle)
        Car car = new Car("Ford", "Focus", 2019, 220.0, 4);
        System.out.println("--- CARRO (herda de Vehicle) ---");
        System.out.println("Informações: " + car); // 📄 toString() sobrescrito
        car.ligar();        // 🔄 SOBRESCRITO: versão específica do Car
        car.abrirPorta();   // ⭐ ESPECÍFICO: só Car tem este método
        car.acelerar();     // 🔄 SOBRESCRITO: versão específica do Car
        car.frear();        // 🔄 SOBRESCRITO: versão específica do Car
        car.desligar();     // 🔄 SOBRESCRITO: versão específica do Car

        System.out.println("\n=== O QUE A HERANÇA PROPORCIONOU ===");
        System.out.println("✅ REUTILIZAÇÃO: Motorcycle e Car reutilizam código do Vehicle");
        System.out.println("✅ ESPECIALIZAÇÃO: Cada um tem comportamentos específicos");
        System.out.println("✅ EXTENSÃO: Cada um adiciona métodos específicos (empinar, abrirPorta)");
        System.out.println("✅ ORGANIZAÇÃO: Hierarquia clara Vehicle → Motorcycle/Car");

        System.out.println("\n=== TENTATIVAS QUE NÃO FUNCIONARIAM ===");
        System.out.println("❌ vehicle.empinar(); // ERRO! Vehicle não tem método empinar()");
        System.out.println("❌ vehicle.abrirPorta(); // ERRO! Vehicle não tem método abrirPorta()");
        System.out.println("❌ motorcycle.abrirPorta(); // ERRO! Motorcycle não tem porta");
        System.out.println("❌ car.empinar(); // ERRO! Car não empina");

        System.out.println("\n=== HIERARQUIA DE HERANÇA ===");
        System.out.println("Object");
        System.out.println("  └── Vehicle");
        System.out.println("      ├── Motorcycle (+ tipoDeGuidao, empinar())");
        System.out.println("      └── Car (+ numeroDePortas, abrirPorta())");

        System.out.println("\n=== POLIMORFISMO (PRÉVIA) ===");
        // 🔄 POLIMORFISMO: Mesmo tipo de referência, comportamentos diferentes
        Vehicle veiculo1 = new Motorcycle("Yamaha", "R1", 2022, 300.0, "Racing");
        Vehicle veiculo2 = new Car("BMW", "X5", 2023, 250.0, 5);

        System.out.println("Ambos são Vehicle, mas comportamentos diferentes:");
        veiculo1.acelerar(); // Vai chamar acelerar() da Motorcycle
        veiculo2.acelerar(); // Vai chamar acelerar() do Car
        // ⚠️ Mas veiculo1.empinar() não funciona (referência é Vehicle, não Motorcycle)
    }
}