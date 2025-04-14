package org.bancoDigital.service;

import org.bancoDigital.factory.ContaFactory;
import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Cliente;
import org.bancoDigital.model.Conta;
import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.InputScanner;

import java.util.Optional;

public class BancoService {
    private final Banco banco;
    private final ContaFactory contaFactory;

    public BancoService(Banco banco) {
        this.banco = banco;
        this.contaFactory = new ContaFactory();
    }

    public Conta abrirConta(String tipo){
        try {
            String cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF: "));
            Optional<Cliente> clienteOptional = banco.buscarClienteCPf(cpf);
            if (clienteOptional.isEmpty()){
                System.out.println("Cliente não encontrado. Verifique o CPF ou cadastre o cliente primeiro.");
                return null;
            }
            Cliente cliente = clienteOptional.get();
            Conta novaConta = contaFactory.criarConta(cliente, tipo);
            banco.adicionarConta(novaConta);
            return novaConta;
        } catch (IllegalArgumentException e){
            System.out.println("Erro ao criar conta: " + e.getMessage());
            return null;
        }
    }

    public void transferir(Conta origem, Conta destino, double valor){
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (origem == destino) throw new IllegalArgumentException("Transferência para a mesma conta não permitida");
        if (origem.getSaldo() < valor) throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("\nTransferencia concluida.");
    }
}
