package org.bancoDigital.service;

import org.bancoDigital.model.Cliente;
import org.bancoDigital.model.Conta;

public class ContaCorrente extends Conta{
    private double emprestimos, pagaEmprestimos = 0;

    public ContaCorrente(Cliente cliente, String tipoConta) {
        super(cliente, tipoConta);
    }

    public void pedirEmprestimo(){
        if (emprestimos == 0){
            if (this.saldo >= 2000){
                emprestimos = this.saldo / 10;
                this.saldo += emprestimos;
            } else {
                System.out.println("Não foi possivel adquirir um emprestimo.");
            }
        } else {
            System.out.println("Precisa paga o emprestimo");
        }
    }

    public void valorEmprestimo(){
        pagaEmprestimos = emprestimos * 1.2;
        System.out.println("Valor do emprestimos a ser pago e "+ pagaEmprestimos);
    }

    public void pagarEmprestimos(){
        valorEmprestimo();
        if (this.saldo >= pagaEmprestimos){
            this.saldo -= pagaEmprestimos;
            System.out.println("Pago o emprestimo");
            emprestimos = 0;
        } else {
            double falta = pagaEmprestimos - this.saldo;
            System.out.println("Falta R$"+falta);
        }
    }
}
