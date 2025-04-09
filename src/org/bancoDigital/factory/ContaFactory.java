package org.bancoDigital.factory;

import org.bancoDigital.model.Cliente;
import org.bancoDigital.model.Conta;
import org.bancoDigital.model.ContaCorrente;
import org.bancoDigital.model.ContaPoupanca;

public class ContaFactory {
    public static Conta criarConta(String tipo, Cliente cliente){
        return switch (tipo.toUpperCase()) {
            case "CORRENTE" -> new ContaCorrente(cliente, tipo);
            case "POUPANÇA" -> new ContaPoupanca(cliente, tipo);
            default -> throw new IllegalArgumentException("Tipo de conta inválida: "+ tipo);
        };
    }
}