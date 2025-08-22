
package strings;

public class StringSearching {
    public static void main(String[] args) {

        // Variáveis-base
        String texto  = "Java é incrível e Java é poderoso";
        String palavra = "Java";
        String outra   = "Python";

        // CONTAINS
        System.out.println("--- CONTAINS ---");
        System.out.println("texto contém 'Java'? " + texto.contains(palavra)); // true
        System.out.println("texto contém 'Python'? " + texto.contains(outra)); // false
        // case-insensitive (jeito simples)
        System.out.println("contains (ignore case) 'java'? " +
                texto.toLowerCase().contains("java".toLowerCase()));
        System.out.println();

        // INDEXOF
        System.out.println("--- INDEXOF ---");
        System.out.println("posição de 'Java' = " + texto.indexOf(palavra));       // 0
        System.out.println("posição de 'incrível' = " + texto.indexOf("incrível")); // 7
        System.out.println("posição de 'Python' = " + texto.indexOf(outra));        // -1
        System.out.println();

        // LASTINDEXOF
        System.out.println("--- LASTINDEXOF ---");
        System.out.println("última posição de 'Java' = " + texto.lastIndexOf(palavra)); // 18
        System.out.println();

        // STARTSWITH
        System.out.println("--- STARTSWITH ---");
        System.out.println("texto começa com 'Java'? " + texto.startsWith("Java")); // true
        // startsWith com offset (checa se 'Java' aparece começando no índice 18)
        System.out.println("texto.startsWith('Java', 18)? " + texto.startsWith("Java", 18)); // true
        System.out.println();

        // ENDSWITH
        System.out.println("--- ENDSWITH ---");
        System.out.println("texto termina com 'poderoso'? " + texto.endsWith("poderoso")); // true
        System.out.println("texto termina com 'Java'? " + texto.endsWith("Java"));         // false
        System.out.println();

        // EXEMPLOS REAIS
        System.out.println("--- EXEMPLOS REAIS ---");

        // 1) Email: separar usuário e domínio
        String email = "NomedoUsuario@gmail.com";
        int pos = email.indexOf("@");
        if (pos != -1) {
            String usuario = email.substring(0, pos);
            String dominio = email.substring(pos + 1); // sem o '@'
            System.out.println("Usuário: " + usuario); // NomedoUsuario
            System.out.println("Domínio: " + dominio); // gmail.com
            System.out.println("Email termina com .com? " + email.endsWith(".com")); // true
        } else {
            System.out.println("Email inválido (sem @)");
        }

        // 2) URL: pegar último segmento com lastIndexOf + substring
        String url = "https://site.com/produtos/camisetas/verao";
        int barra = url.lastIndexOf('/');
        String ultimoSegmento = barra == -1 ? url : url.substring(barra + 1);
        System.out.println("Último segmento da URL: " + ultimoSegmento); // verao
    }
}
