
package regex;

public class RegexValidation {
    public static void main(String[] args) {
        String email = "usuario@gmail.com";
        String senha = "Kaue@2025";
        String cpf = "123.456.789-00";
        String telefone = "(11) 91234-5678";

        // ---------------- EMAIL ----------------
        System.out.println("--- EMAIL ---");
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        System.out.println("valida email? " + email.matches(regexEmail));
        System.out.println();

        // ---------------- SENHA FORTE ----------------
        System.out.println("--- SENHA ---");
        // >=8, minúscula, maiúscula, número, caractere especial
        String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{8,}$";
        System.out.println("valida senha? " + senha.matches(regexSenha));
        System.out.println();

        // ---------------- CPF ----------------
        System.out.println("--- CPF ---");
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        System.out.println("valida cpf? " + cpf.matches(regexCpf));
        System.out.println();

        // ---------------- TELEFONE ----------------
        System.out.println("--- TELEFONE ---");
        String regexTelefone = "^\\(\\d{2}\\) 9\\d{4}-\\d{4}$";
        System.out.println("valida telefone? " + telefone.matches(regexTelefone));
    }
}
