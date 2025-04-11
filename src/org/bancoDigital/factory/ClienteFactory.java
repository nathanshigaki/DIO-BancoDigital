package org.bancoDigital.factory;

import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.DateUtils;
import org.bancoDigital.util.InputScanner;
import org.bancoDigital.model.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(){
        String nome = InputScanner.lerString("Nome: ");
        String cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
        String dataNascimento = DateUtils.receberData(InputScanner.lerString("Data de nascimento no formato ddMMyyyy: "));
        if (!DateUtils.verificarData(dataNascimento)){
            throw new IllegalArgumentException("Cliente deve ser maior de 18 anos");
        }
        return new Cliente(nome, cpf, dataNascimento);
    }
}
