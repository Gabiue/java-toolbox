package com.kaue.toolbox.javapillars.encapsulation;

public class ProductExample {
    // 🔒 ENCAPSULAMENTO: Todos os atributos são PRIVADOS
    // Isso impede acesso direto e força uso de getters/setters
    private String name;
    private double price;
    private int estoque;
    private String categoria;

    // 🏗️ CONSTRUTOR: Única forma de criar um produto com dados iniciais
    public ProductExample(String name, double price, int estoque, String categoria){
        this.name = name;
        this.price = price;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    // 📖 GETTERS: Métodos públicos que permitem LER os dados privados
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getEstoque() {
        return estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    // ✏️ SETTERS: Métodos públicos que permitem ALTERAR os dados privados
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // 📄 toString(): Método para exibir informações do produto de forma organizada
    @Override
    public String toString() {
        return "ProductExample{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", estoque=" + estoque +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE ENCAPSULAMENTO ===\n");

        // ✅ CRIANDO PRODUTO: Usando o construtor público
        ProductExample product = new ProductExample("Laptop", 1500.00, 10, "Eletrônicos");
        System.out.println("Produto criado: " + product);

        System.out.println("\n--- ACESSANDO DADOS COM GETTERS ---");
        // ✅ LENDO DADOS: Usando getters (métodos públicos)
        System.out.println("Nome: " + product.getName());
        System.out.println("Preço: R$ " + product.getPrice());
        System.out.println("Estoque: " + product.getEstoque() + " unidades");
        System.out.println("Categoria: " + product.getCategoria());

        System.out.println("\n--- MODIFICANDO DADOS COM SETTERS ---");
        // ✅ ALTERANDO DADOS: Usando setters (métodos públicos)
        product.setPrice(1400.00);
        product.setEstoque(8);
        System.out.println("Preço atualizado: R$ " + product.getPrice());
        System.out.println("Estoque atualizado: " + product.getEstoque() + " unidades");

        System.out.println("\n--- O QUE O ENCAPSULAMENTO IMPEDE ---");
        System.out.println("❌ As linhas abaixo NÃO COMPILAM por causa do encapsulamento:");
        System.out.println("// product.name = \"Novo Nome\";      // ERRO! Campo privado");
        System.out.println("// product.price = 999.0;           // ERRO! Campo privado");
        System.out.println("// product.estoque = 50;            // ERRO! Campo privado");
        System.out.println("// product.categoria = \"Móveis\";    // ERRO! Campo privado");

        System.out.println("\n✅ Única forma de acessar/modificar é através dos métodos públicos!");

        // 🔄 DEMONSTRANDO O CONTROLE DE ACESSO
        System.out.println("\n--- ENCAPSULAMENTO EM AÇÃO ---");
        System.out.println("1. Dados protegidos (private)");
        System.out.println("2. Acesso controlado (public getters/setters)");
        System.out.println("3. Interface bem definida");
        System.out.println("4. Segurança garantida");

        System.out.println("\nProduto final: " + product);
    }
}