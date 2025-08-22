package com.kaue.toolbox.javapillars.encapsulation;

/**
 * 📋 MANUAL COMPLETO: SINTAXE E ANATOMIA DO ENCAPSULAMENTO
 *
 * Este arquivo é seu DICIONÁRIO DE REFERÊNCIA para encapsulamento!
 * Aqui você encontra:
 * - Onde usar cada modificador de acesso
 * - Como criar getters e setters corretamente
 * - Convenções de nomenclatura
 * - Padrões e anti-padrões
 * - Sintaxe completa comentada linha por linha
 *
 * 🎯 OBJETIVO: Entender CADA decisão de design antes dos exemplos práticos
 */
public class EncapsulationSyntax {

    // ========================================
    // 🔒 MODIFICADORES DE ACESSO - QUANDO USAR?
    // ========================================

    /**
     * 🔴 PRIVATE - "Só EU posso acessar"
     *
     * QUANDO USAR:
     * - Dados sensíveis (senha, saldo, etc.)
     * - Implementação interna da classe
     * - Atributos que precisam de validação
     * - Estados que não devem ser alterados diretamente
     */
    private String dadoSensivel;        // ← Ninguém de fora pode acessar
    private int contadorInterno;        // ← Lógica interna da classe
    private boolean statusValidado;     // ← Só a classe controla esse estado

    /**
     * 🟡 PROTECTED - "Eu e meus filhos podem acessar"
     *
     * QUANDO USAR:
     * - Atributos que classes filhas precisam acessar
     * - Métodos utilitários para herança
     * - Dados compartilhados em hierarquia de classes
     */
    protected String configuracaoBase;  // ← Classes filhas podem usar
    protected int limiteOperacao;       // ← Herdeiros podem modificar

    /**
     * 🟢 PUBLIC - "Todo mundo pode acessar"
     *
     * QUANDO USAR:
     * - Constantes universais
     * - Métodos da interface pública da classe
     * - Dados que realmente podem ser públicos
     *
     * ⚠️ CUIDADO: Use com parcimônia! Quebra encapsulamento!
     */
    public static final String VERSAO = "1.0";    // ← Constante pública OK
    // public int saldo;  ← ❌ NUNCA! Saldo deve ser privado!

    /**
     * 📦 PACKAGE-PRIVATE (sem modificador) - "Só classes do mesmo package"
     *
     * QUANDO USAR:
     * - Classes auxiliares do mesmo módulo
     * - Dados compartilhados dentro do package
     * - Testes no mesmo package
     */
    String dadoInterno;                 // ← Só classes do mesmo package

    // ========================================
    // 🏗️ CONSTRUTORES - INICIALIZAÇÃO SEGURA
    // ========================================

    /**
     * 🎯 CONSTRUTOR: Primeiro momento para garantir estado válido!
     *
     * RESPONSABILIDADES:
     * 1. Inicializar atributos obrigatórios
     * 2. Validar parâmetros recebidos
     * 3. Garantir que objeto nasça em estado consistente
     * 4. Configurar valores padrão seguros
     */
    public EncapsulationSyntax(String dadoObrigatorio, int valorInicial) {
        // ✅ SEMPRE validar parâmetros no construtor
        if (dadoObrigatorio == null || dadoObrigatorio.trim().isEmpty()) {
            throw new IllegalArgumentException("Dado obrigatório não pode ser nulo ou vazio");
        }
        if (valorInicial < 0) {
            throw new IllegalArgumentException("Valor inicial deve ser positivo");
        }

        // ✅ Inicializar atributos de forma segura
        this.dadoSensivel = dadoObrigatorio;
        this.contadorInterno = valorInicial;
        this.statusValidado = false;        // Estado inicial seguro
        this.configuracaoBase = "PADRAO";   // Valor padrão sensato
        this.limiteOperacao = 1000;         // Limite seguro inicial
        this.dadoInterno = "INICIALIZADO";  // Estado conhecido
    }

    // ========================================
    // 📖 MÉTODOS GETTERS - "LEITURA CONTROLADA"
    // ========================================

    /**
     * 🔍 PADRÃO GETTER: get + NomeAtributo (com primeira letra maiúscula)
     *
     * RESPONSABILIDADES:
     * 1. Fornecer acesso SEGURO aos dados privados
     * 2. Aplicar formatações se necessário
     * 3. Fazer validações de estado se necessário
     * 4. Retornar cópias defensivas para objetos mutáveis
     *
     * 📝 NOMENCLATURA:
     * - Atributo: dadoSensivel → Getter: getDadoSensivel()
     * - Atributo: statusValidado → Getter: isStatusValidado() (para boolean)
     */

    // ✅ GETTER SIMPLES - para dados imutáveis/primitivos
    public String getDadoSensivel() {
        // ⚠️ Pode aplicar validações de estado
        if (!statusValidado) {
            return "DADOS_NAO_VALIDADOS";
        }
        return dadoSensivel;  // Retorna diretamente (String é imutável)
    }

    // ✅ GETTER PARA BOOLEAN - usa "is" ao invés de "get"
    public boolean isStatusValidado() {
        return statusValidado;
    }

    // ✅ GETTER COM FORMATAÇÃO - pode processar antes de retornar
    public String getDadoSensivelFormatado() {
        return dadoSensivel != null ? dadoSensivel.toUpperCase() : "N/A";
    }

    // ✅ GETTER CALCULADO - não precisa ter atributo correspondente
    public String getResumoStatus() {
        return String.format("Contador: %d, Validado: %s",
                contadorInterno, statusValidado ? "SIM" : "NÃO");
    }

    // ========================================
    // ✏️ MÉTODOS SETTERS - "ESCRITA CONTROLADA"
    // ========================================

    /**
     * 🔧 PADRÃO SETTER: set + NomeAtributo (com primeira letra maiúscula)
     *
     * RESPONSABILIDADES:
     * 1. VALIDAR todos os parâmetros recebidos
     * 2. Manter estado consistente do objeto
     * 3. Aplicar regras de negócio
     * 4. Lançar exceções para valores inválidos
     * 5. Atualizar estados dependentes se necessário
     *
     * 📝 NOMENCLATURA:
     * - Atributo: dadoSensivel → Setter: setDadoSensivel(String valor)
     */

    // ✅ SETTER COM VALIDAÇÃO COMPLETA
    public void setDadoSensivel(String novoDado) {
        // 🛡️ VALIDAÇÃO 1: Null/vazio
        if (novoDado == null || novoDado.trim().isEmpty()) {
            throw new IllegalArgumentException("Dado sensível não pode ser nulo ou vazio");
        }

        // 🛡️ VALIDAÇÃO 2: Tamanho/formato
        if (novoDado.length() < 3) {
            throw new IllegalArgumentException("Dado sensível deve ter pelo menos 3 caracteres");
        }

        // 🛡️ VALIDAÇÃO 3: Caracteres válidos
        if (!novoDado.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Dado sensível deve conter apenas letras e números");
        }

        // ✅ ATUALIZAÇÃO SEGURA
        this.dadoSensivel = novoDado.trim().toUpperCase();

        // ✅ ATUALIZAR ESTADOS DEPENDENTES
        this.statusValidado = true;  // Marca como validado após alteração
    }

    // ✅ SETTER CONDICIONAL - só altera se estado permitir
    public void setContadorInterno(int novoValor) {
        // 🛡️ VALIDAÇÃO DE ESTADO
        if (!statusValidado) {
            throw new IllegalStateException("Não é possível alterar contador antes da validação");
        }

        // 🛡️ VALIDAÇÃO DE VALOR
        if (novoValor < 0) {
            throw new IllegalArgumentException("Contador não pode ser negativo");
        }

        if (novoValor > limiteOperacao) {
            throw new IllegalArgumentException("Valor excede limite de operação: " + limiteOperacao);
        }

        this.contadorInterno = novoValor;
    }

    // ❌ ANTI-PADRÃO: SETTER SEM VALIDAÇÃO
    /*
    public void setContadorInternoMalFeito(int valor) {
        this.contadorInterno = valor;  // ❌ Aceita qualquer valor!
    }
    */

    // ========================================
    // 🚫 QUANDO NÃO CRIAR GETTERS/SETTERS
    // ========================================

    /**
     * 🔒 ATRIBUTOS SEM SETTER - "Read-Only após inicialização"
     *
     * CASOS:
     * - IDs únicos
     * - Timestamps de criação
     * - Configurações que não devem mudar
     * - Estados calculados
     */
    private final String id = java.util.UUID.randomUUID().toString();
    private final long timestampCriacao = System.currentTimeMillis();

    // ✅ Só getter, sem setter - dados imutáveis após criação
    public String getId() {
        return id;
    }

    public long getTimestampCriacao() {
        return timestampCriacao;
    }

    // ❌ NÃO criar setters para estes atributos!
    // public void setId(String id) { ... }  ← NUNCA!

    /**
     * 🔐 ATRIBUTOS SEM GETTER - "Write-Only" ou internos
     *
     * CASOS:
     * - Senhas/tokens
     * - Estados internos temporários
     * - Contadores de controle
     */
    private String senhaHash;  // Nunca expor diretamente

    // ✅ Setter para senha (com hash)
    public void setSenha(String senhaPlana) {
        if (senhaPlana == null || senhaPlana.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
        // Simulação de hash (em produção use BCrypt, etc.)
        this.senhaHash = "HASH_" + senhaPlana.hashCode();
    }

    // ✅ Método para verificar senha (sem expor o hash)
    public boolean verificarSenha(String senhaPlana) {
        if (senhaPlana == null) return false;
        String hashTentativa = "HASH_" + senhaPlana.hashCode();
        return hashTentativa.equals(this.senhaHash);
    }

    // ❌ NUNCA criar getter para senha/hash!
    // public String getSenhaHash() { return senhaHash; }  ← NUNCA!

    // ========================================
    // 🛠️ MÉTODOS UTILITÁRIOS E VALIDAÇÃO
    // ========================================

    /**
     * 🔧 MÉTODOS DE NEGÓCIO - Operações controladas nos dados
     */
    public void incrementarContador() {
        if (contadorInterno < limiteOperacao) {
            contadorInterno++;
        } else {
            throw new IllegalStateException("Contador atingiu limite máximo");
        }
    }

    public void resetarContador() {
        if (statusValidado) {
            contadorInterno = 0;
        } else {
            throw new IllegalStateException("Não é possível resetar antes da validação");
        }
    }

    /**
     * 🔍 MÉTODO DE VALIDAÇÃO GERAL
     */
    public boolean isEstadoValido() {
        return statusValidado &&
                dadoSensivel != null &&
                !dadoSensivel.trim().isEmpty() &&
                contadorInterno >= 0 &&
                contadorInterno <= limiteOperacao;
    }

    // ========================================
    // 📋 MÉTODOS ESSENCIAIS (toString, equals, hashCode)
    // ========================================

    @Override
    public String toString() {
        return String.format("EncapsulationSyntax{id='%s', validado=%s, contador=%d}",
                id.substring(0, 8) + "...", statusValidado, contadorInterno);
    }

    // ========================================
    // 🧪 DEMONSTRAÇÃO PRÁTICA
    // ========================================

    public static void main(String[] args) {
        System.out.println("📋 DEMONSTRAÇÃO: SINTAXE DO ENCAPSULAMENTO");
        System.out.println("=" + "=".repeat(50));

        try {
            // ✅ CRIAÇÃO VÁLIDA
            EncapsulationSyntax exemplo = new EncapsulationSyntax("DADOS_TESTE", 42);
            System.out.println("✅ Objeto criado: " + exemplo);

            // ✅ USANDO GETTERS
            System.out.println("📖 Dado sensível: " + exemplo.getDadoSensivel());
            System.out.println("📖 Status validado: " + exemplo.isStatusValidado());
            System.out.println("📖 Resumo: " + exemplo.getResumoStatus());

            // ✅ USANDO SETTERS COM VALIDAÇÃO
            exemplo.setDadoSensivel("NOVOS_DADOS_123");
            System.out.println("✅ Dado alterado com sucesso!");

            // ✅ MÉTODOS DE NEGÓCIO
            exemplo.incrementarContador();
            System.out.println("✅ Contador incrementado: " + exemplo.getResumoStatus());

            // ✅ VALIDAÇÃO DE SENHA
            exemplo.setSenha("minhasenhasecreta");
            System.out.println("✅ Senha verificada: " + exemplo.verificarSenha("minhasenhasecreta"));
            System.out.println("❌ Senha incorreta: " + exemplo.verificarSenha("senhaerrada"));

        } catch (Exception e) {
            System.err.println("❌ Erro: " + e.getMessage());
        }

        // ❌ DEMONSTRANDO VALIDAÇÕES
        System.out.println("\n🛡️ TESTANDO VALIDAÇÕES:");
        try {
            new EncapsulationSyntax(null, 10);  // Deve dar erro
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Validação funcionou: " + e.getMessage());
        }

        try {
            new EncapsulationSyntax("dados", -5);  // Deve dar erro
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Validação funcionou: " + e.getMessage());
        }
    }
}

/*
 * 📚 RESUMO DOS PADRÕES:
 *
 * 🔴 PRIVATE: Use para TUDO que é interno da classe
 * 🟡 PROTECTED: Use apenas para herança planejada
 * 🟢 PUBLIC: Use apenas para interface pública e constantes
 * 📦 PACKAGE: Use para colaboração entre classes do mesmo módulo
 *
 * 📖 GETTERS: get + Nome (ou is + Nome para boolean)
 * ✏️ SETTERS: set + Nome (com validações SEMPRE!)
 *
 * 🛡️ VALIDAÇÃO: No construtor E nos setters
 * 🔒 READ-ONLY: Só getter, sem setter
 * 🔐 WRITE-ONLY: Só setter (para dados sensíveis)
 *
 * 🎯 REGRA DE OURO: Se duvidar, deixe PRIVATE e crie métodos controlados!
 */