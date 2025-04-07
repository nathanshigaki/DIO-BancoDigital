package org.bancoDigital.service;

import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Conta;

public class BancoService {
    private final Banco banco;

    public BancoService(Banco banco) {
        this.banco = banco;
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
