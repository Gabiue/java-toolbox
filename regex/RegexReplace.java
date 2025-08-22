
package regex;

public class RegexReplace {
    public static void main(String[] args) {

        // 🔹 Variáveis base (máx. 3)
        String email   = "kaue.silva@example.com";
        String cpf     = "123.456.789-00";
        String celular = "(11) 98765-4321";

        // ---------------- REPLACEALL BÁSICO ----------------
        System.out.println("--- REPLACEALL BÁSICO ---");
        System.out.println("cpf só com * dígitos: " + cpf.replaceAll("\\d", "*"));     // ***.***.***-**
        System.out.println("celular só números: "   + celular.replaceAll("\\D+", "")); // 11987654321
        System.out.println();

        // ---------------- REPLACEFIRST ----------------
        System.out.println("--- REPLACEFIRST ---");
        String frase = "Java é massa. Java é incrível.";
        System.out.println(frase.replaceFirst("Java", "Kotlin")); // só a 1ª ocorrência
        System.out.println();

        // ---------------- MASCARAMENTO (LOOKAHEAD) ----------------
        System.out.println("--- MASCARAMENTO ---");
        // mantém só os 2 últimos dígitos do CPF
        System.out.println("cpf mascarado (***-**): " + cpf.replaceAll("\\d(?=\\d{2})", "*"));
        // mantém só os 4 últimos do celular
        System.out.println("celular mascarado: " + celular.replaceAll("\\d(?=\\D*\\d{4}\\D*$)", "*"));
        // email: mostra 2 primeiras letras do usuário e todo o domínio
        System.out.println("email mascarado: " + email.replaceAll("(?<=..).(?=[^@]*@)", "*"));
        System.out.println();

        // ---------------- REORDENAR COM GRUPOS (DATA) ----------------
        System.out.println("--- REORDENAR COM GRUPOS ---");
        String info = "Data da compra: 19/08/2025.";
        // dd/mm/yyyy -> yyyy-mm-dd
        System.out.println(info.replaceAll("(\\d{2})/(\\d{2})/(\\d{4})", "$3-$2-$1"));
        System.out.println();

        // ---------------- NORMALIZAÇÃO DE ESPAÇOS ----------------
        System.out.println("--- NORMALIZAÇÃO DE ESPAÇOS ---");
        String baguncado = "  texto    com   espaços   demais   ";
        String normal = baguncado.trim().replaceAll("\\s+", " ");
        System.out.println("'" + normal + "'"); // 'texto com espaços demais'
        System.out.println();

        // ---------------- REMOVER CONTEÚDO (HTML SIMPLES) ----------------
        System.out.println("--- REMOVER TAGS HTML ---");
        String html = "<p>Olá <b>mundo</b>!</p>";
        System.out.println(html.replaceAll("<[^>]+>", "")); // Olá mundo!
        System.out.println();

        // ---------------- FLAGS INLINE (CASE-INSENSITIVE) ----------------
        System.out.println("--- FLAGS INLINE ---");
        String textoCi = "java Java jAvA";
        System.out.println(textoCi.replaceAll("(?i)java", "JAVA")); // substitui ignorando maiúsc/minúsc
        System.out.println();

        // ---------------- MULTILINE (ANCORAR INÍCIO DE LINHA) ----------------
        System.out.println("--- MULTILINE ---");
        String multi = "linha 1\nlinha 2\nlinha 3";
        // adiciona "> " no começo de cada linha
        System.out.println(multi.replaceAll("(?m)^", "> "));
        System.out.println();

        // ---------------- LIMPAR APENAS O QUE NÃO É DÍGITO ----------------
        System.out.println("--- SOMENTE DÍGITOS ---");
        String doc = "RG: 12.345.678-9";
        System.out.println(doc.replaceAll("\\D+", "")); // 123456789
    }
}

