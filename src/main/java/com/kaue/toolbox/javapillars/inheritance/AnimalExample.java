package com.kaue.toolbox.javapillars.inheritance;

public class AnimalExample {

    // 🏗️ CLASSE PAI (SUPERCLASSE): Define características comuns de todos os animais
    static class Animal {
        // 🔒 ENCAPSULAMENTO: Atributos privados protegidos
        private String nome;
        private int idade;
        private String especie;

        // 🏗️ CONSTRUTOR: Define como criar um animal
        public Animal(String nome, int idade, String especie) {
            this.nome = nome;
            this.idade = idade;
            this.especie = especie;
        }

        // 📖 GETTERS E SETTERS: Acesso controlado aos atributos privados
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            this.idade = idade;
        }

        public String getEspecie() {
            return especie;
        }

        public void setEspecie(String especie) {
            this.especie = especie;
        }

        // 🔄 MÉTODO SOBRESCRITÍVEL: Cada animal pode fazer som diferente
        public void som() {
            System.out.println("O animal faz um som genérico.");
        }

        // 📋 MÉTODOS HERDÁVEIS: Comportamentos comuns que subclasses vão herdar
        public void dormir() {
            System.out.println("O animal está dormindo.");
        }

        public void comer() {
            System.out.println("O animal está comendo.");
        }
    }

    // 🧬 CLASSE FILHA: Gato herda de Animal (relação "is-a")
    static class Gato extends Animal {

        // 🏗️ CONSTRUTOR: Chama construtor do pai com super()
        public Gato(String nome, int idade, String especie) {
            super(nome, idade, especie); // ✅ HERANÇA: Reutiliza construtor do Animal
        }

        // 🔄 SOBRESCRITA: Gato tem seu próprio som (diferente do Animal genérico)
        @Override  // 📌 Anotação que confirma sobrescrita do método pai
        public void som() {
            System.out.println("O gato mia: Miau Miau!");
        }

        // ⭐ MÉTODO ESPECÍFICO: Só Gato tem este comportamento
        public void miar() {
            System.out.println("O gato está miando.");
        }

        // 📋 MÉTODOS HERDADOS (não precisam ser reescritos):
        // - comer() ← vem do Animal
        // - dormir() ← vem do Animal
        // - getNome(), setNome(), etc. ← vem do Animal
    }

    // 🧬 CLASSE FILHA: Cachorro herda de Animal (relação "is-a")
    static class Cachorro extends Animal {

        // 🏗️ CONSTRUTOR: Chama construtor do pai com super()
        public Cachorro(String nome, int idade, String especie) {
            super(nome, idade, especie); // ✅ HERANÇA: Reutiliza construtor do Animal
        }

        // 🔄 SOBRESCRITA: Cachorro tem seu próprio som (diferente do Animal genérico)
        @Override  // 📌 Anotação que confirma sobrescrita do método pai
        public void som() {
            System.out.println("O cachorro late: Au Au!");
        }

        // ⭐ MÉTODO ESPECÍFICO: Só Cachorro tem este comportamento
        public void latir() {
            System.out.println("O cachorro está latindo.");
        }

        // 📋 MÉTODOS HERDADOS (não precisam ser reescritos):
        // - comer() ← vem do Animal
        // - dormir() ← vem do Animal
        // - getNome(), setNome(), etc. ← vem do Animal
    }

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE HERANÇA ===\n");

        // 🏗️ CRIANDO ANIMAL GENÉRICO
        Animal meuAnimal = new Animal("Animal Genérico", 5, "Desconhecida");
        System.out.println("--- ANIMAL GENÉRICO ---");
        meuAnimal.som();      // ✅ Método original da classe Animal
        meuAnimal.comer();    // ✅ Método da classe Animal
        meuAnimal.dormir();   // ✅ Método da classe Animal

        System.out.println();

        // 🐱 CRIANDO GATO (herda de Animal)
        Gato meuGato = new Gato("Whiskers", 3, "Siamês");
        System.out.println("--- GATO (herda de Animal) ---");
        meuGato.som();        // 🔄 SOBRESCRITO: versão específica do Gato
        meuGato.miar();       // ⭐ ESPECÍFICO: só Gato tem este método
        meuGato.comer();      // 📋 HERDADO: usa método do Animal
        meuGato.dormir();     // 📋 HERDADO: usa método do Animal

        // ✅ Gato também herda getters/setters:
        System.out.println("Nome do gato: " + meuGato.getNome()); // 📋 HERDADO

        System.out.println();

        // 🐕 CRIANDO CACHORRO (herda de Animal)
        Cachorro meuCachorro = new Cachorro("Rex", 4, "Labrador");
        System.out.println("--- CACHORRO (herda de Animal) ---");
        meuCachorro.som();    // 🔄 SOBRESCRITO: versão específica do Cachorro
        meuCachorro.latir();  // ⭐ ESPECÍFICO: só Cachorro tem este método
        meuCachorro.comer();  // 📋 HERDADO: usa método do Animal
        meuCachorro.dormir(); // 📋 HERDADO: usa método do Animal

        System.out.println("\n=== O QUE A HERANÇA PROPORCIONOU ===");
        System.out.println("✅ REUTILIZAÇÃO: Gato e Cachorro reutilizam código do Animal");
        System.out.println("✅ ESPECIALIZAÇÃO: Cada um tem seu próprio som()");
        System.out.println("✅ EXTENSÃO: Cada um adiciona métodos específicos");
        System.out.println("✅ ORGANIZAÇÃO: Hierarquia clara Animal → Gato/Cachorro");

        System.out.println("\n=== TENTATIVAS QUE NÃO FUNCIONARIAM ===");
        System.out.println("❌ meuAnimal.miar(); // ERRO! Animal não tem método miar()");
        System.out.println("❌ meuAnimal.latir(); // ERRO! Animal não tem método latir()");
        System.out.println("❌ meuGato.latir(); // ERRO! Gato não tem método latir()");
        System.out.println("❌ meuCachorro.miar(); // ERRO! Cachorro não tem método miar()");

        System.out.println("\n=== POLIMORFISMO (PRÉVIA) ===");
        // 🔄 POLIMORFISMO: Mesmo tipo de referência, comportamentos diferentes
        Animal animal1 = new Gato("Fluffy", 2, "Persa");
        Animal animal2 = new Cachorro("Buddy", 3, "Golden");

        animal1.som(); // Vai chamar som() do Gato: "Miau Miau!"
        animal2.som(); // Vai chamar som() do Cachorro: "Au Au!"
        // ⚠️ Mas animal1.miar() não funciona (referência é Animal, não Gato)
    }
}