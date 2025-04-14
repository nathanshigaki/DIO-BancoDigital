package org.bancoDigital.factory;

import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.DateUtils;
import org.bancoDigital.util.InputScanner;
import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Cliente;

public class ClienteFactory {
    private final Banco banco;

    public ClienteFactory(Banco banco) {
        this.banco = banco;
    }

    public Cliente criarCliente(){
        String nome = InputScanner.lerString("Nome: ");
        String cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
        if (banco.buscarClienteCPf(cpf).isPresent()){
            System.out.println("CPF já cadastrado, já é um cliente.");
            return null;
        }
        String dataNascimento = DateUtils.receberData(InputScanner.lerString("Data de nascimento no formato ddMMyyyy: "));
        if (!DateUtils.verificarMaiorIdade(dataNascimento)){
            System.out.println("Cliente deve ser maior de 18 anos.");
            return null;
        }
        return new Cliente(nome, cpf, dataNascimento);
    }
}
