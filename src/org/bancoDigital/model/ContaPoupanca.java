package org.bancoDigital.model;

import org.bancoDigital.util.InputScanner;

public class ContaPoupanca extends Conta{
    private static final double TAXA_RENDIMENTO = 0.04;

    public ContaPoupanca(Cliente cliente, String tipoConta) {
        super(cliente, tipoConta);
    }

    public void simulacaoPoupanca(){
        System.out.println("Simulação da poupança");
        
        while (true){
            int dias = InputScanner.lerInt("Digite a quantidade de dias rendendo na poupança (para sair aperte 0): ");
            if (dias < 0){
                System.out.println("Deve ser positivo a quantidade de dias");
            } else if (dias == 0){
                System.out.println("Saindo da simulação da poupança");
                break;
            }else {
                double ganho = this.saldo * TAXA_RENDIMENTO * dias;
                System.out.println("Renderia R$"+ganho);
            }
        }
    }
}
