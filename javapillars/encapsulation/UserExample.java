
package javapillars.encapsulation;

import java.time.LocalDate;

public class UserExample {
    // 🔒 ENCAPSULAMENTO: Todos os atributos são PRIVADOS
    // Diferentes tipos de acesso demonstram flexibilidade do encapsulamento
    private String nome;           // Mutável: tem getter + setter
    private String email;          // Mutável: tem getter + setter
    private String password;       // 🔐 SEGURANÇA: só setter, nunca getter!
    private final String cpf;      // 🔒 IMUTÁVEL: final = não pode ser alterado
    private boolean isAtiva;       // Mutável: tem getter + setter
    private final LocalDate dataCriacao; // 🔒 IMUTÁVEL: final + valor automático

    // 🏗️ CONSTRUTOR: Recebe dados essenciais, define valores automáticos
    public UserExample(String nome, String email, String password, String cpf) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;

        // Valores definidos automaticamente (não vêm do usuário)
        this.isAtiva = true;              // Todo usuário começa ativo
        this.dataCriacao = LocalDate.now(); // Data atual automaticamente
    }

    // 📖 GETTERS: Métodos para LER dados privados
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf; // CPF é final - só leitura após criação
    }

    public boolean isAtiva() {
        return isAtiva;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao; // Data criação é final - só leitura
    }

    // ⚠️ IMPORTANTE: Não há getPassword() por questão de SEGURANÇA!
    // A senha nunca deve ser exposta, apenas alterada

    // ✏️ SETTERS: Métodos para ALTERAR dados privados (quando permitido)
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password; // Pode alterar senha, mas nunca ler
    }

    public void setAtiva(boolean ativa) {
        isAtiva = ativa;
    }

    // ⚠️ IMPORTANTE: Não há setCpf() nem setDataCriacao()
    // pois são campos FINAL (imutáveis)

    // 🔐 MÉTODO DE AUTENTICAÇÃO: Usa password sem expô-la
    public boolean autenticar(String senhaDigitada) {
        return this.password.equals(senhaDigitada);
    }

    // 📄 toString(): Exibe informações SEM mostrar a senha
    @Override
    public String toString() {
        return "UserExample{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTEGIDA]'" +  // Nunca mostra senha real
                ", cpf='" + cpf + '\'' +
                ", isAtiva=" + isAtiva +
                ", dataCriacao=" + dataCriacao +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DE ENCAPSULAMENTO AVANÇADO ===\n");

        // ✅ CRIANDO USUÁRIO: Usando construtor público
        UserExample user = new UserExample("Kaue", "kaue@teste.com", "senha123", "123.456.789-00");
        System.out.println("Usuário criado: " + user);

        System.out.println("\n--- ACESSANDO DADOS COM GETTERS ---");
        // ✅ LENDO DADOS: Usando getters (quando disponíveis)
        System.out.println("Nome: " + user.getNome());
        System.out.println("Email: " + user.getEmail());
        System.out.println("CPF: " + user.getCpf());
        System.out.println("Ativo: " + user.isAtiva());
        System.out.println("Data de criação: " + user.getDataCriacao());

        System.out.println("\n--- TENTANDO ACESSAR SENHA ---");
        System.out.println("❌ user.getPassword() - MÉTODO NÃO EXISTE! (Segurança)");
        System.out.println("✅ Autenticação: " + user.autenticar("senha123")); // true
        System.out.println("✅ Senha errada: " + user.autenticar("123456"));   // false

        System.out.println("\n--- MODIFICANDO DADOS COM SETTERS ---");
        // ✅ ALTERANDO DADOS: Usando setters (quando permitidos)
        user.setNome("Kaue Silva");
        user.setEmail("kaue.silva@teste.com");
        user.setPassword("novaSenha456");
        user.setAtiva(false);

        System.out.println("Nome atualizado: " + user.getNome());
        System.out.println("Email atualizado: " + user.getEmail());
        System.out.println("Status atualizado: " + (user.isAtiva() ? "Ativo" : "Inativo"));
        System.out.println("Nova senha funciona: " + user.autenticar("novaSenha456"));

        System.out.println("\n--- O QUE O ENCAPSULAMENTO IMPEDE ---");
        System.out.println("❌ As linhas abaixo NÃO COMPILAM:");
        System.out.println("// user.nome = \"João\";           // ERRO! Campo privado");
        System.out.println("// user.password = \"123\";       // ERRO! Campo privado");
        System.out.println("// user.cpf = \"999.999.999-99\"; // ERRO! Campo final");
        System.out.println("// user.dataCriacao = LocalDate.now(); // ERRO! Campo final");
        System.out.println("// String senha = user.getPassword(); // ERRO! Método não existe");

        System.out.println("\n--- TIPOS DE ENCAPSULAMENTO DEMONSTRADOS ---");
        System.out.println("🔓 MUTÁVEL: nome, email, isAtiva (getter + setter)");
        System.out.println("🔒 IMUTÁVEL: cpf, dataCriacao (só getter, campos final)");
        System.out.println("🔐 PROTEGIDO: password (só setter, sem getter por segurança)");
        System.out.println("⚡ AUTOMÁTICO: isAtiva, dataCriacao (valores definidos automaticamente)");

        System.out.println("\nUsuário final: " + user);
    }
}