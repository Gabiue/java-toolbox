
package javapillars.polymorphism;

import java.util.ArrayList;
import java.util.List;

public class ShapeExample {

    // 🔄 CLASSE ABSTRATA PAI: Define o "contrato" que todas as formas devem seguir
    static abstract class Shape {
        // 🔒 ENCAPSULAMENTO: Atributos protegidos para subclasses acessarem
        protected String nome;
        protected String color;

        // 🏗️ CONSTRUTOR: Características comuns de todas as formas
        public Shape(String nome, String color) {
            this.nome = nome;
            this.color = color;
        }

        // 📖 GETTERS: Acesso aos atributos comuns
        public String getName() {
            return nome;
        }

        public String getColor() {
            return color;
        }

        // 🔄 MÉTODOS ABSTRATOS: DEVEM ser implementados por cada subclasse
        public abstract double calculateArea();
        public abstract double calculatePerimeter();
        public abstract void draw();

        // 📋 MÉTODO COMUM: Todas as formas herdam (pode ser sobrescrito)
        public void displayInfo() {
            System.out.println("📋 Shape: " + nome + ", Color: " + color +
                    " | Área: " + String.format("%.2f", calculateArea()) +
                    " | Perímetro: " + String.format("%.2f", calculatePerimeter()));
        }

        // 📄 toString(): Representação textual da forma
        @Override
        public String toString() {
            return "Shape{" +
                    "nome='" + nome + '\'' +
                    ", color='" + color + '\'' +
                    ", area=" + String.format("%.2f", calculateArea()) +
                    '}';
        }
    }

    // 🔵 SUBCLASSE: Circle implementa Shape
    static class Circle extends Shape {
        private double radius;

        // 🏗️ CONSTRUTOR: Chama super() + atributos específicos
        public Circle(String nome, String color, double radius) {
            super(nome, color); // ✅ HERANÇA: Reutiliza construtor do Shape
            this.radius = radius;
        }

        // 📖 GETTER: Atributo específico do círculo
        public double getRadius() {
            return radius;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como círculo calcula área
        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como círculo calcula perímetro (circunferência)
        @Override
        public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como círculo se desenha
        @Override
        public void draw() {
            System.out.println("🔵 Drawing a circle (" + color + ") with radius: " + radius);
        }
    }

    // 🔲 SUBCLASSE: Rectangle implementa Shape
    static class Rectangle extends Shape {
        private double width;
        private double height;

        // 🏗️ CONSTRUTOR: Chama super() + atributos específicos
        public Rectangle(String nome, String color, double width, double height) {
            super(nome, color); // ✅ HERANÇA: Reutiliza construtor do Shape
            this.width = width;
            this.height = height;
        }

        // 📖 GETTERS: Atributos específicos do retângulo
        public double getWidth() {
            return width;
        }

        public double getHeight() {
            return height;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como retângulo calcula área
        @Override
        public double calculateArea() {
            return width * height;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como retângulo calcula perímetro
        @Override
        public double calculatePerimeter() {
            return 2 * (width + height);
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como retângulo se desenha
        @Override
        public void draw() {
            System.out.println("🔲 Drawing a rectangle (" + color + ") with width: " + width + " and height: " + height);
        }
    }

    // 🔺 SUBCLASSE: Triangle implementa Shape
    static class Triangle extends Shape {
        private double base;
        private double height;

        // 🏗️ CONSTRUTOR: Chama super() + atributos específicos
        public Triangle(String nome, String color, double base, double height) {
            super(nome, color); // ✅ HERANÇA: Reutiliza construtor do Shape
            this.base = base;
            this.height = height;
        }

        // 📖 GETTERS: Atributos específicos do triângulo
        public double getBase() {
            return base;
        }

        public double getHeight() {
            return height;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como triângulo calcula área
        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como triângulo calcula perímetro
        // ⚠️ SIMPLIFICAÇÃO: Assumindo triângulo equilátero para demonstração
        @Override
        public double calculatePerimeter() {
            return 3 * base; // Para triângulo equilátero
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como triângulo se desenha
        @Override
        public void draw() {
            System.out.println("🔺 Drawing a triangle (" + color + ") with base: " + base + " and height: " + height);
        }
    }

    // ========== MÉTODOS POLIMÓRFICOS ==========

    // 🔄 MÉTODO POLIMÓRFICO: Aceita QUALQUER Shape
    public static void processShape(Shape shape) {
        System.out.println("🔄 Processando forma polimorficamente:");
        shape.draw();           // Comportamento específico de cada forma
        shape.displayInfo();    // Informações específicas de cada forma
        System.out.println("---");
    }

    // 🔄 MÉTODO POLIMÓRFICO: Calcula área total de várias formas
    public static double calculateTotalArea(Shape... shapes) {
        double total = 0;
        System.out.println("📊 Calculando área total de " + shapes.length + " formas:");

        for (Shape shape : shapes) {
            double area = shape.calculateArea(); // 🔄 POLIMORFISMO: cada um calcula diferente
            System.out.println("- " + shape.getName() + " " + shape.getColor() + ": " + String.format("%.2f", area));
            total += area;
        }

        return total;
    }

    // 🔄 MÉTODO POLIMÓRFICO: Desenha todas as formas
    public static void drawAllShapes(List<Shape> shapes) {
        System.out.println("🎨 Desenhando " + shapes.size() + " formas:");
        for (Shape shape : shapes) {
            shape.draw(); // 🔄 POLIMORFISMO: cada forma desenha do seu jeito
        }
    }

    // 🔍 MÉTODO COM INSTANCEOF E CASTING
    public static void analyzeShape(Shape shape) {
        System.out.println("🔍 Analisando forma detalhadamente:");

        // 🔄 POLIMORFISMO: Comportamento comum
        shape.displayInfo();

        // 🔍 INSTANCEOF + CASTING: Acesso a comportamentos específicos
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            System.out.println("   🔵 Informação específica - Raio: " + circle.getRadius());

        } else if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            System.out.println("   🔲 Informações específicas - Largura: " + rectangle.getWidth() +
                    ", Altura: " + rectangle.getHeight());

        } else if (shape instanceof Triangle) {
            Triangle triangle = (Triangle) shape;
            System.out.println("   🔺 Informações específicas - Base: " + triangle.getBase() +
                    ", Altura: " + triangle.getHeight());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("=== POLIMORFISMO COM FORMAS GEOMÉTRICAS ===\n");

        // 🔄 POLIMORFISMO BÁSICO: Referência Shape → objetos específicos
        System.out.println("--- POLIMORFISMO BÁSICO ---");
        Shape circle = new Circle("Circle", "Red", 5.0);        // ✅ Circle IS-A Shape
        Shape rectangle = new Rectangle("Rectangle", "Blue", 4.0, 6.0); // ✅ Rectangle IS-A Shape
        Shape triangle = new Triangle("Triangle", "Green", 3.0, 4.0);   // ✅ Triangle IS-A Shape

        // 🔄 CHAMADAS POLIMÓRFICAS: Mesmo código, comportamentos diferentes
        System.out.println("=== DEMONSTRAÇÃO INDIVIDUAL ===");

        // Círculo
        circle.displayInfo();   // Polimorfismo: chama versão que inclui área e perímetro
        circle.draw();          // Polimorfismo: chama draw() do Circle
        System.out.println();

        // Retângulo
        rectangle.displayInfo(); // Polimorfismo: chama versão que inclui área e perímetro
        rectangle.draw();        // Polimorfismo: chama draw() do Rectangle
        System.out.println();

        // Triângulo
        triangle.displayInfo();  // Polimorfismo: chama versão que inclui área e perímetro
        triangle.draw();         // Polimorfismo: chama draw() do Triangle
        System.out.println();

        // 🔄 ARRAY POLIMÓRFICO: Mistura diferentes tipos
        System.out.println("--- ARRAY POLIMÓRFICO ---");
        Shape[] shapes = {
                new Circle("Circle1", "Purple", 3.0),
                new Rectangle("Rectangle1", "Yellow", 5.0, 2.0),
                new Triangle("Triangle1", "Orange", 4.0, 6.0),
                new Circle("Circle2", "Pink", 7.0),
                new Rectangle("Rectangle2", "Brown", 3.0, 3.0)
        };

        // 🔄 LOOP POLIMÓRFICO: Cada forma se comporta do seu jeito
        for (Shape shape : shapes) {
            shape.displayInfo(); // Método polimórfico
        }
        System.out.println();

        // 🔄 LIST POLIMÓRFICA: Como você mencionou!
        System.out.println("--- LIST POLIMÓRFICA ---");
        List<Shape> shapeList = new ArrayList<>();  // ✅ POLIMORFISMO: List ref → ArrayList obj
        shapeList.add(new Circle("CircleList", "Cyan", 4.0));
        shapeList.add(new Rectangle("RectangleList", "Magenta", 6.0, 4.0));
        shapeList.add(new Triangle("TriangleList", "Gold", 5.0, 7.0));

        drawAllShapes(shapeList); // Método polimórfico com List
        System.out.println();

        // 🔄 MÉTODOS POLIMÓRFICOS EM AÇÃO
        System.out.println("--- MÉTODOS POLIMÓRFICOS ---");
        processShape(new Circle("ProcessCircle", "Silver", 6.0));
        processShape(new Rectangle("ProcessRect", "Bronze", 7.0, 3.0));

        // 📊 CÁLCULO POLIMÓRFICO
        System.out.println("--- CÁLCULO POLIMÓRFICO ---");
        double totalArea = calculateTotalArea(
                new Circle("CalcCircle", "White", 2.0),
                new Rectangle("CalcRect", "Black", 4.0, 5.0),
                new Triangle("CalcTriangle", "Gray", 6.0, 4.0)
        );
        System.out.println("🔢 Área total: " + String.format("%.2f", totalArea));
        System.out.println();

        // 🔍 INSTANCEOF E CASTING
        System.out.println("--- INSTANCEOF E CASTING ---");
        Shape[] mixedShapes = {
                new Circle("AnalyzeCircle", "Violet", 3.0),
                new Rectangle("AnalyzeRect", "Turquoise", 2.0, 8.0),
                new Triangle("AnalyzeTriangle", "Coral", 5.0, 6.0)
        };

        for (Shape shape : mixedShapes) {
            analyzeShape(shape); // Usa instanceof + casting
        }

        // 🎯 DEMONSTRAÇÃO FINAL: O PODER DO POLIMORFISMO
        System.out.println("=== O PODER DO POLIMORFISMO ===");
        System.out.println("✅ UM MESMO CÓDIGO funciona com DIFERENTES TIPOS");
        System.out.println("✅ ADICIONAR NOVOS TIPOS é fácil (ex: Pentagon, Hexagon)");
        System.out.println("✅ FLEXIBILIDADE para trabalhar com coleções mistas");
        System.out.println("✅ REUTILIZAÇÃO de métodos que trabalham com Shape");

        System.out.println("\n--- TENTATIVAS QUE NÃO FUNCIONARIAM ---");
        System.out.println("❌ circle.getRadius(); // ERRO! Shape não tem getRadius()");
        System.out.println("❌ rectangle.getWidth(); // ERRO! Shape não tem getWidth()");
        System.out.println("❌ Shape s = new Shape(); // ERRO! Não pode instanciar classe abstrata");
        System.out.println("✅ Circle c = (Circle) circle; // OK! Casting permite acesso específico");

        System.out.println("\n🔄 POLIMORFISMO = Flexibilidade + Extensibilidade + Reutilização!");
    }
}