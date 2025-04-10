package org.bancoDigital.factory;

import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.InputScanner;
import org.bancoDigital.model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(){
        String nome = InputScanner.lerString("Nome: ");
        String cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
        String dataNascimento = InputScanner.lerString("Data de nascimento:");

        return new Cliente(nome, cpf, dataNascimento);
    }
}
