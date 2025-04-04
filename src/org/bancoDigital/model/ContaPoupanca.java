package org.bancoDigital.model;

public class ContaPoupanca extends Conta{
    private static final double TAXA_RENDIMENTO = 0.04;

    public ContaPoupanca(Cliente cliente, String tipoConta) {
        super(cliente, tipoConta);
    }

    public void simulacaoPoupanca(int dias){
        if (dias > 0){
            double ganho = this.saldo * TAXA_RENDIMENTO * dias;
            System.out.println("renderia "+ganho);
        }
    }
}
