package com.kaue.toolbox.javapillars.encapsulation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public class BankAccountExample {
    public static enum TipoConta{
        CORRENTE, POUPANCA
    }

    private String nome;
    private final String cpf;
    private final String nConta;
    private BigDecimal saldo;
    private final LocalDate dataCriacao;
    private boolean ativa;
    private TipoConta tipo;

    public BankAccountExample(String nome, String cpf, TipoConta tipo) {
        if (nome == null || nome.trim().isEmpty() ||
                cpf == null || cpf.trim().isEmpty() ||
                tipo == null) {
            throw new IllegalArgumentException("Parâmetros inválidos");
        }
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.nConta = "CONTA-" + UUID.randomUUID().toString().substring(0, 8);;
        this.saldo = BigDecimal.ZERO;
        this.dataCriacao = LocalDate.now();
        this.ativa = true;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getnConta() {
        return nConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    //METODOS
    //DEPOSITAR E SACAR DA CONTA
    public boolean validarValor(BigDecimal valor) {
        return valor != null && valor.signum() > 0;
    }
    public void depositar(BigDecimal valor){
        if (!ativa) throw new IllegalStateException("Conta inativa");
        if (!validarValor(valor)) throw new IllegalArgumentException("Valor inválido para depósito");
        BigDecimal novoSaldo =saldo.add(valor);
        this.saldo = novoSaldo;

    }

    public void sacar (BigDecimal valor){
        if (!ativa) throw new IllegalStateException("Conta inativa");
        if(valor.compareTo(saldo) == 1 )throw new IllegalStateException("Saldo insuficiente");
        if (!validarValor(valor)) throw new IllegalArgumentException("Valor inválido para saque");

        BigDecimal novoSaldo= saldo.subtract(valor);
        this.saldo = novoSaldo;
    }

    @Override
    public String toString() {
        return "BankAccountExample{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", nConta='" + nConta + '\'' +
                ", saldo=" + saldo +
                ", dataCriacao=" + dataCriacao +
                ", ativa=" + ativa +
                ", tipo=" + tipo +
                '}';
    }

    public static void main(String[] args) {
        BankAccountExample conta1 = new BankAccountExample("Kaue", "12345678900", TipoConta.CORRENTE);
        System.out.println(conta1);
        conta1.depositar(new BigDecimal("1000.00"));
        System.out.println("Após depósito: " + conta1.getSaldo());
        conta1.sacar(new BigDecimal("200.00"));
        System.out.println("Após saque: " + conta1.getSaldo());
        conta1.setNome("Kaue Silva");
        System.out.println("Nome atualizado: " + conta1.getNome());
        conta1.setAtiva(false);
        System.out.println("Conta inativa: " + conta1.isAtiva());
        conta1.setTipo(TipoConta.POUPANCA);
        System.out.println("Tipo de conta atualizado: " + conta1.getTipo());
        System.out.println(conta1);

    }
}
