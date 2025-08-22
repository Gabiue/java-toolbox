package com.kaue.toolbox.javapillars.abstraction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PaymentExample {

    // 🏗️ CLASSE ABSTRATA: Define estrutura comum para todos os pagamentos
    static abstract class Pagamento {
        // 🔒 ENCAPSULAMENTO: Atributos protegidos para subclasses acessarem
        protected double valor;
        protected String descricao;
        protected LocalDateTime dataHora;
        protected String identificadorTransacao;
        protected boolean processado;

        // 🏗️ CONSTRUTOR: Inicializa dados comuns de qualquer pagamento
        public Pagamento(double valor, String descricao) {
            this.valor = valor;
            this.descricao = descricao;
            this.dataHora = LocalDateTime.now();
            this.identificadorTransacao = gerarIdTransacao();
            this.processado = false;
        }

        // 📖 GETTERS: Acesso controlado aos atributos
        public double getValor() {
            return valor;
        }

        public String getDescricao() {
            return descricao;
        }

        public LocalDateTime getDataHora() {
            return dataHora;
        }

        public String getIdentificadorTransacao() {
            return identificadorTransacao;
        }

        public boolean isProcessado() {
            return processado;
        }

        // 🔄 MÉTODO ABSTRATO: DEVE ser implementado por cada tipo de pagamento
        public abstract void processarPagamento();

        // 🔄 MÉTODO ABSTRATO: Cada pagamento tem uma taxa diferente
        public abstract double calcularTaxa();

        // 📋 MÉTODO CONCRETO: Comportamento comum para todos os pagamentos
        public void gerarRecibo() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("📄 ===== RECIBO DE PAGAMENTO =====");
            System.out.println("🆔 Transação: " + identificadorTransacao);
            System.out.println("💰 Valor: R$ " + String.format("%.2f", valor));
            System.out.println("📝 Descrição: " + descricao);
            System.out.println("🕒 Data/Hora: " + dataHora.format(formatter));
            System.out.println("💳 Tipo: " + this.getClass().getSimpleName());
            System.out.println("💸 Taxa: R$ " + String.format("%.2f", calcularTaxa()));
            System.out.println("✅ Status: " + (processado ? "Processado" : "Pendente"));
            System.out.println("================================");
        }

        // 📋 MÉTODO CONCRETO: Gera ID único para transação
        private String gerarIdTransacao() {
            return "TXN" + System.currentTimeMillis() % 100000;
        }

        // 📋 MÉTODO CONCRETO: Informações básicas do pagamento
        public void exibirInfo() {
            System.out.println("💳 " + this.getClass().getSimpleName() +
                    " - R$ " + String.format("%.2f", valor) +
                    " (" + descricao + ")");
        }

        // 📄 toString(): Representação textual do pagamento
        @Override
        public String toString() {
            return this.getClass().getSimpleName() + "{" +
                    "valor=" + valor +
                    ", descricao='" + descricao + '\'' +
                    ", processado=" + processado +
                    '}';
        }
    }

    // ========== INTERFACES: CAPACIDADES ESPECÍFICAS ==========

    // 🔄 INTERFACE: Capacidade de estornar pagamento
    interface Estornavel {
        void estornarPagamento();

        // ⚡ MÉTODO DEFAULT: Comportamento padrão para verificar se pode estornar
        default boolean podeEstornar() {
            return true; // Por padrão, pode estornar
        }

        // 📦 MÉTODO STATIC: Verifica se valor é válido para estorno
        static boolean valorValidoParaEstorno(double valor) {
            return valor > 0 && valor <= 10000; // Limite de R$ 10.000
        }
    }

    // 🔄 INTERFACE: Capacidade de parcelar pagamento
    interface Parcelavel {
        void pagarEmParcelas(int numeroParcelas);

        // ⚡ MÉTODO DEFAULT: Calcula valor da parcela
        default double calcularValorParcela(double valorTotal, int parcelas) {
            return valorTotal / parcelas;
        }

        // 📦 MÉTODO STATIC: Valida número de parcelas
        static boolean numeroParcelasValido(int parcelas) {
            return parcelas >= 1 && parcelas <= 24; // Máximo 24 parcelas
        }
    }

    // 🔄 INTERFACE: Capacidade de rastrear transação em tempo real
    interface Rastreavel {
        void rastrearTransacao();

        // ⚡ MÉTODO DEFAULT: Status genérico de rastreamento
        default String obterStatusRastreamento() {
            return "Transação em processamento...";
        }
    }

    // 🔄 INTERFACE: Capacidade de pagamento instantâneo
    interface PagamentoInstantaneo {
        void confirmarInstantaneamente();

        // ⚡ MÉTODO DEFAULT: Tempo de processamento
        default int tempoProcessamento() {
            return 0; // Instantâneo = 0 segundos
        }
    }

    // ========== IMPLEMENTAÇÕES CONCRETAS ==========

    // 💳 CARTÃO DE CRÉDITO: Herda de Pagamento + múltiplas capacidades
    static class CartaoCredito extends Pagamento implements Estornavel, Parcelavel {
        private String numeroCartao;
        private String nomePortador;
        private int cvv;

        // 🏗️ CONSTRUTOR: Chama super() + atributos específicos
        public CartaoCredito(double valor, String descricao, String numeroCartao, String nomePortador, int cvv) {
            super(valor, descricao); // ✅ HERANÇA: Reutiliza construtor do Pagamento
            this.numeroCartao = mascarearCartao(numeroCartao);
            this.nomePortador = nomePortador;
            this.cvv = cvv;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como cartão processa pagamento
        @Override
        public void processarPagamento() {
            System.out.println("💳 Processando pagamento no cartão de crédito...");
            System.out.println("🔍 Validando cartão: " + numeroCartao);
            System.out.println("👤 Portador: " + nomePortador);
            System.out.println("🔐 Verificando CVV...");

            // Simula processamento
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            this.processado = true;
            System.out.println("✅ Pagamento aprovado no cartão de crédito!");
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Taxa do cartão de crédito
        @Override
        public double calcularTaxa() {
            return valor * 0.035; // Taxa de 3.5%
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE Estornavel
        @Override
        public void estornarPagamento() {
            if (!processado) {
                System.out.println("❌ Não é possível estornar: pagamento não foi processado");
                return;
            }

            if (!Estornavel.valorValidoParaEstorno(valor)) {
                System.out.println("❌ Valor não válido para estorno");
                return;
            }

            System.out.println("🔄 Estornando pagamento no cartão de crédito...");
            System.out.println("💰 Valor estornado: R$ " + String.format("%.2f", valor));
            System.out.println("📱 O valor será creditado na próxima fatura");
            this.processado = false;
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE Parcelavel
        @Override
        public void pagarEmParcelas(int numeroParcelas) {
            if (!Parcelavel.numeroParcelasValido(numeroParcelas)) {
                System.out.println("❌ Número de parcelas inválido");
                return;
            }

            double valorParcela = calcularValorParcela(valor, numeroParcelas);
            System.out.println("📊 Pagamento parcelado em " + numeroParcelas + "x");
            System.out.println("💰 Valor da parcela: R$ " + String.format("%.2f", valorParcela));
            System.out.println("💳 Será debitado mensalmente do cartão " + numeroCartao);
        }

        // 🔧 MÉTODO AUXILIAR: Mascara número do cartão por segurança
        private String mascarearCartao(String numero) {
            if (numero.length() < 4) return numero;
            return "**** **** **** " + numero.substring(numero.length() - 4);
        }

        // 📖 GETTERS específicos
        public String getNumeroCartao() {
            return numeroCartao;
        }

        public String getNomePortador() {
            return nomePortador;
        }
    }

    // 🟢 PIX: Herda de Pagamento + capacidades específicas
    static class PIX extends Pagamento implements Estornavel, Rastreavel, PagamentoInstantaneo {
        private String chavePixOrigem;
        private String chavePixDestino;
        private String codigoAutenticacao;

        // 🏗️ CONSTRUTOR: Chama super() + atributos específicos
        public PIX(double valor, String descricao, String chavePixOrigem, String chavePixDestino) {
            super(valor, descricao); // ✅ HERANÇA: Reutiliza construtor do Pagamento
            this.chavePixOrigem = chavePixOrigem;
            this.chavePixDestino = chavePixDestino;
            this.codigoAutenticacao = "PIX" + System.currentTimeMillis() % 10000;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como PIX processa pagamento
        @Override
        public void processarPagamento() {
            System.out.println("🟢 Processando PIX...");
            System.out.println("📱 Origem: " + chavePixOrigem);
            System.out.println("📱 Destino: " + chavePixDestino);
            System.out.println("🔐 Código: " + codigoAutenticacao);

            // PIX é instantâneo
            confirmarInstantaneamente();
            this.processado = true;
            System.out.println("⚡ PIX processado instantaneamente!");
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Taxa do PIX
        @Override
        public double calcularTaxa() {
            return 0.0; // PIX não tem taxa
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE Estornavel
        @Override
        public void estornarPagamento() {
            if (!processado) {
                System.out.println("❌ Não é possível estornar: PIX não foi processado");
                return;
            }

            System.out.println("🔄 Estornando PIX...");
            System.out.println("⚡ Estorno processado instantaneamente");
            System.out.println("💰 Valor devolvido: R$ " + String.format("%.2f", valor));
            this.processado = false;
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE Rastreavel
        @Override
        public void rastrearTransacao() {
            System.out.println("🔍 Rastreando PIX: " + identificadorTransacao);
            System.out.println("📍 Status: " + obterStatusRastreamento());
            System.out.println("⏱️ Tempo de processamento: " + tempoProcessamento() + " segundos");
        }

        // 🔄 IMPLEMENTAÇÃO DA INTERFACE PagamentoInstantaneo
        @Override
        public void confirmarInstantaneamente() {
            System.out.println("⚡ Confirmação instantânea ativada");
            System.out.println("🔔 Notificação enviada para ambas as partes");
        }

        // 🔄 SOBRESCRITA: Status específico do PIX
        @Override
        public String obterStatusRastreamento() {
            return processado ? "PIX concluído com sucesso" : "PIX em processamento";
        }

        // 📖 GETTERS específicos
        public String getChavePixOrigem() {
            return chavePixOrigem;
        }

        public String getChavePixDestino() {
            return chavePixDestino;
        }
    }

    // 💵 DINHEIRO: Só herda de Pagamento (sem capacidades extras)
    static class Dinheiro extends Pagamento {
        private double valorRecebido;
        private double troco;

        // 🏗️ CONSTRUTOR: Chama super() + cálculo de troco
        public Dinheiro(double valor, String descricao, double valorRecebido) {
            super(valor, descricao); // ✅ HERANÇA: Reutiliza construtor do Pagamento
            this.valorRecebido = valorRecebido;
            this.troco = valorRecebido - valor;
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Como dinheiro processa pagamento
        @Override
        public void processarPagamento() {
            System.out.println("💵 Processando pagamento em dinheiro...");
            System.out.println("💰 Valor da compra: R$ " + String.format("%.2f", valor));
            System.out.println("💴 Valor recebido: R$ " + String.format("%.2f", valorRecebido));

            if (troco > 0) {
                System.out.println("💲 Troco: R$ " + String.format("%.2f", troco));
            } else if (troco < 0) {
                System.out.println("❌ Valor insuficiente! Faltam R$ " + String.format("%.2f", Math.abs(troco)));
                return;
            } else {
                System.out.println("✅ Valor exato - sem troco");
            }

            this.processado = true;
            System.out.println("✅ Pagamento em dinheiro concluído!");
        }

        // 🔄 IMPLEMENTAÇÃO OBRIGATÓRIA: Taxa do dinheiro
        @Override
        public double calcularTaxa() {
            return 0.0; // Dinheiro não tem taxa
        }

        // 📖 GETTERS específicos
        public double getValorRecebido() {
            return valorRecebido;
        }

        public double getTroco() {
            return troco;
        }
    }

    // ========== MÉTODOS POLIMÓRFICOS ==========

    // 🔄 MÉTODO POLIMÓRFICO: Processa qualquer tipo de pagamento
    public static void processarPagamento(Pagamento pagamento) {
        System.out.println("🔄 Processando pagamento polimorficamente:");
        pagamento.exibirInfo();        // Informações básicas
        pagamento.processarPagamento(); // Comportamento específico
        pagamento.gerarRecibo();        // Método comum
        System.out.println();
    }

    // 🔄 MÉTODO POLIMÓRFICO: Calcula total de pagamentos
    public static double calcularTotalPagamentos(List<Pagamento> pagamentos) {
        double total = 0;
        double totalTaxas = 0;

        System.out.println("💰 Calculando total de " + pagamentos.size() + " pagamentos:");
        for (Pagamento pagamento : pagamentos) {
            double taxa = pagamento.calcularTaxa(); // 🔄 POLIMORFISMO: cada um calcula diferente
            total += pagamento.getValor();
            totalTaxas += taxa;

            System.out.println("- " + pagamento.getClass().getSimpleName() +
                    ": R$ " + String.format("%.2f", pagamento.getValor()) +
                    " (taxa: R$ " + String.format("%.2f", taxa) + ")");
        }

        System.out.println("📊 Total bruto: R$ " + String.format("%.2f", total));
        System.out.println("💸 Total taxas: R$ " + String.format("%.2f", totalTaxas));
        System.out.println("💰 Total líquido: R$ " + String.format("%.2f", total - totalTaxas));

        return total;
    }

    // 🔍 MÉTODO COM INSTANCEOF E CASTING: Analisa capacidades específicas
    public static void analisarCapacidades(Pagamento pagamento) {
        System.out.println("🔍 Analisando capacidades de: " + pagamento.getClass().getSimpleName());

        // 🔄 POLIMORFISMO: Comportamento comum
        pagamento.exibirInfo();

        // 🔍 INSTANCEOF + CASTING: Acesso a capacidades específicas
        if (pagamento instanceof Estornavel) {
            System.out.println("   ✅ Pode ser estornado");
            if (((Estornavel) pagamento).podeEstornar()) {
                System.out.println("   💚 Status: Apto para estorno");
            }
        } else {
            System.out.println("   ❌ Não pode ser estornado");
        }

        if (pagamento instanceof Parcelavel) {
            System.out.println("   ✅ Pode ser parcelado");
            System.out.println("   📊 Parcelas válidas: 1 a 24x");
        } else {
            System.out.println("   ❌ Não pode ser parcelado");
        }

        if (pagamento instanceof Rastreavel) {
            System.out.println("   ✅ Pode ser rastreado em tempo real");
        } else {
            System.out.println("   ❌ Não possui rastreamento");
        }

        if (pagamento instanceof PagamentoInstantaneo) {
            System.out.println("   ⚡ Processamento instantâneo");
        } else {
            System.out.println("   🕒 Processamento convencional");
        }

        System.out.println();
    }

    // 🔄 MÉTODO POLIMÓRFICO: Executa estornos quando possível
    public static void executarEstornos(List<Pagamento> pagamentos) {
        System.out.println("🔄 Executando estornos para pagamentos elegíveis:");

        for (Pagamento pagamento : pagamentos) {
            if (pagamento instanceof Estornavel) {
                Estornavel estornavel = (Estornavel) pagamento;
                System.out.println("💳 Estornando: " + pagamento.getClass().getSimpleName());
                estornavel.estornarPagamento();
            } else {
                System.out.println("❌ " + pagamento.getClass().getSimpleName() + " não pode ser estornado");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== ABSTRAÇÃO COM SISTEMA DE PAGAMENTOS ===\n");

        // 🔄 POLIMORFISMO BÁSICO: Referência Pagamento → objetos específicos
        System.out.println("--- CRIANDO DIFERENTES TIPOS DE PAGAMENTO ---");
        Pagamento cartao = new CartaoCredito(250.00, "Compra no e-commerce",
                "1234567890123456", "João Silva", 123);
        Pagamento pix = new PIX(150.00, "Transferência para amigo",
                "joao@email.com", "maria@email.com");
        Pagamento dinheiro = new Dinheiro(80.00, "Compra na padaria", 100.00);

        System.out.println();

        // 🔄 PROCESSAMENTO POLIMÓRFICO
        System.out.println("--- PROCESSAMENTO POLIMÓRFICO ---");
        processarPagamento(cartao);
        processarPagamento(pix);
        processarPagamento(dinheiro);

        // 🔄 LIST POLIMÓRFICA: Mistura diferentes tipos
        System.out.println("--- LIST POLIMÓRFICA ---");
        List<Pagamento> pagamentos = new ArrayList<>();
        pagamentos.add(new CartaoCredito(500.00, "Notebook", "9876543210987654", "Ana Costa", 456));
        pagamentos.add(new PIX(75.00, "Divisão da conta", "ana@email.com", "pedro@email.com"));
        pagamentos.add(new Dinheiro(45.00, "Lanche", 50.00));
        pagamentos.add(new CartaoCredito(1200.00, "Smartphone", "1111222233334444", "Carlos Lima", 789));

        // 📊 CÁLCULO POLIMÓRFICO
        calcularTotalPagamentos(pagamentos);
        System.out.println();

        // 🔍 ANÁLISE DE CAPACIDADES COM INSTANCEOF
        System.out.println("--- ANÁLISE DE CAPACIDADES ---");
        for (Pagamento pagamento : pagamentos) {
            analisarCapacidades(pagamento);
        }

        // 🔄 DEMONSTRAÇÃO DE CAPACIDADES ESPECÍFICAS
        System.out.println("--- TESTANDO CAPACIDADES ESPECÍFICAS ---");

        // Testando estorno
        if (cartao instanceof Estornavel) {
            Estornavel estornavel = (Estornavel) cartao;
            estornavel.estornarPagamento();
        }
        System.out.println();

        // Testando parcelamento
        if (cartao instanceof Parcelavel) {
            Parcelavel parcelavel = (Parcelavel) cartao;
            parcelavel.pagarEmParcelas(6);
        }
        System.out.println();

        // Testando rastreamento
        if (pix instanceof Rastreavel) {
            Rastreavel rastreavel = (Rastreavel) pix;
            rastreavel.rastrearTransacao();
        }
        System.out.println();

        // 🔄 ESTORNOS EM MASSA
        System.out.println("--- ESTORNOS EM MASSA ---");
        executarEstornos(pagamentos);

        // 🎯 DEMONSTRAÇÃO FINAL
        System.out.println("=== O PODER DA ABSTRAÇÃO ===");
        System.out.println("✅ ABSTRACT CLASS: Código comum + estrutura obrigatória");
        System.out.println("✅ INTERFACES: Capacidades específicas + múltipla implementação");
        System.out.println("✅ POLIMORFISMO: Mesmo código funciona com tipos diferentes");
        System.out.println("✅ INSTANCEOF + CASTING: Acesso a capacidades específicas quando necessário");
        System.out.println("✅ FLEXIBILIDADE: Fácil adicionar novos tipos de pagamento");

        System.out.println("\n--- TENTATIVAS QUE NÃO FUNCIONARIAM ---");
        System.out.println("❌ Pagamento p = new Pagamento(); // ERRO! Classe abstrata");
        System.out.println("❌ cartao.estornarPagamento(); // ERRO! Pagamento não tem esse método");
        System.out.println("❌ dinheiro.pagarEmParcelas(); // ERRO! Dinheiro não implementa Parcelavel");
        System.out.println("✅ ((CartaoCredito) cartao).estornarPagamento(); // OK! Casting permite acesso");

        System.out.println("\n🏛️ ABSTRAÇÃO = Simplicidade + Flexibilidade + Extensibilidade!");
    }
}