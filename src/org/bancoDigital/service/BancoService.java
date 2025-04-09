package org.bancoDigital.service;

import org.bancoDigital.factory.ContaFactory;
import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Cliente;
import org.bancoDigital.model.Conta;
import org.bancoDigital.util.InputScanner;
import org.bancoDigital.MenuBanco;

public class BancoService {
    private Banco banco;

    public BancoService(Banco banco) {
        this.banco = banco;
    }

    public Conta abrirConta(String tipo){
        Cliente cliente = null;

        while (cliente == null){
            int opcao = MenuBanco.nomeOuCpf();
            switch(opcao){
                case 1 -> {
                    String nome = InputScanner.lerString("Nome: ");
                    cliente = banco.buscarClienteNome(nome)
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                }
                case 2 -> {
                    String cpf = InputScanner.lerString("CPF: ");
                    cliente = banco.buscarClienteCPf(cpf)
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));;
                }
                default -> System.out.println("Tente novamente");
            }
        }

        Conta novaConta = ContaFactory.criarConta(tipo, cliente);
        banco.adicionarConta(novaConta);
        return novaConta;
    }

    public void transferir(int idOrigem, int idDestino, double valor){
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (idOrigem == idDestino) throw new IllegalArgumentException("Transferência para a mesma conta não permitida");

        Conta origem = banco.buscarContaPorID(idOrigem)
                               .orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada: "+ idOrigem));
        Conta destino = banco.buscarContaPorID(idDestino)
                                .orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada: "+ idDestino));

        if (origem.getSaldo() < valor) throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        origem.sacar(valor);
        destino.depositar(valor);
    }
}
