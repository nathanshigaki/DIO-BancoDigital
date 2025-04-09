package org.bancoDigital.factory;

import org.bancoDigital.util.InputScanner;
import org.bancoDigital.model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(){
        String nome = InputScanner.lerString("Nome: ");
        String cpf = InputScanner.lerString("CPF:");
        String dataNascimento = InputScanner.lerString("Data de nascimento:");

        return new Cliente(nome, cpf, dataNascimento);
    }
}
