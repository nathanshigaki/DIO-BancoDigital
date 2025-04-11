package org.bancoDigital.model;

import org.bancoDigital.util.InputScanner;

public class ContaCorrente extends Conta{
    private double emprestimos, pagaEmprestimos = 0;

    public ContaCorrente(Cliente cliente, String tipoConta) {
        super(cliente, tipoConta);
    }

    public void pedirEmprestimo(){
        if (emprestimos == 0){
            if (this.saldo >= 2000){
                System.out.println("""
                    
                    O emprestimo é de 10% do valor total da sua conta.
                    Emprestimo = R$ """ + this.saldo / 10);
                int pedir = InputScanner.lerInt("""
                    [ 1 ] Pegar emprestimo.
                    [ 2 ] Negar emprestimo.

                    Escolha uma opção: 
                """);
                if (pedir == 1) {
                    emprestimos = this.saldo / 10;
                    this.saldo += emprestimos;
                    System.out.println("Pegou emprestimo.");
                } else {
                    System.out.println("Negou emprestimo.");
                }
            } else {
                System.out.println("Não foi possivel adquirir um emprestimo.");
            }
        } else {
            System.out.println("Precisa paga o emprestimo primeiro para poder pedir emprestimo novamente");
        }
    }

    public void valorEmprestimo(){
        pagaEmprestimos = emprestimos * 1.1;
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
