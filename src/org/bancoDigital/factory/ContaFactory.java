package org.bancoDigital.factory;

import org.bancoDigital.model.Cliente;
import org.bancoDigital.model.Conta;
import org.bancoDigital.service.ContaCorrente;
import org.bancoDigital.service.ContaPoupanca;

public class ContaFactory {
    public static Conta criarConta(String tipo, Cliente cliente){
        return switch (tipo.toUpperCase()) {
            case "C" -> new ContaCorrente(cliente, tipo);
            case "P" -> new ContaPoupanca(cliente, tipo);
            default -> throw new IllegalArgumentException("Tipo de conta inválida: "+ tipo);
        };
    }
}