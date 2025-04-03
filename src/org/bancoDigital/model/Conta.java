package org.bancoDigital.model;

public abstract class Conta {
    protected int idConta;
    protected String tipoConta;
    protected double saldo;
    protected Cliente cliente;
    private static int ID = 1;

    public Conta(Cliente cliente, String tipoConta) {
        this.idConta = ID++;
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
        this.cliente = cliente;
    }

    public int getidConta() {
        return idConta;
    }

    public void setidConta(int idConta) {
        this.idConta = idConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void depositar(double valor){
        if (valor > 0){
            this.saldo += valor;
        }
    }

    public void sacar(double valor){
        if (this.saldo >= valor){
            this.saldo -= valor;
        }
    }
}   
