package org.bancoDigital.model;

public abstract class Conta {
    private static int ID = 1;
    protected int idConta;
    protected Cliente cliente;
    protected String tipoConta;
    protected double saldo;

    public Conta(Cliente cliente, String tipoConta) {
        this.idConta = ID++;
        this.cliente = cliente;
        this.tipoConta = tipoConta;
        this.saldo = 0.0;
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
            System.out.println("Saque realizado com sucesso. Novo saldo: " + this.saldo);
        } else {
            System.out.println("Saldo insuficiente. Saldo atual: " + this.saldo);
        }
    }

    @Override
    public String toString(){
        return "Conta{" +
               "ID ='" + idConta + '\'' +
               ", cliente ='" + cliente + '\'' +
               ", tipo da conta ='" + tipoConta + '\'' +
               ", saldo ='" + saldo + '\'' +
               '}';
    }
}   
