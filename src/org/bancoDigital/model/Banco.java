package org.bancoDigital.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bancoDigital.factory.ContaFactory;

public class Banco {
    private final Map<Integer, Conta> contasPorID;
    
    public Banco() {
        this.contasPorID = new HashMap<>();
    }

    public Conta criarConta(String tipo, Cliente cliente){
        Conta novaConta = ContaFactory.criarConta(tipo, cliente);
        contasPorID.put(novaConta.getidConta(), novaConta);
        return novaConta;
    }

    public List<Conta> listarContas(){
        List<Conta> contasOrdenadas = new ArrayList<>(contasPorID.values());
        contasOrdenadas.sort(Comparator.comparing(Conta::getidConta));
        return contasOrdenadas;
    }

    public Optional<Conta> buscarContaPorID(int id){
        return Optional.ofNullable(contasPorID.get(id));
    }

    public List<Conta> buscarContasPorCPF(String cpf){
        return contasPorID.values().stream()
        .filter(c -> c.getCliente().getCpf().equalsIgnoreCase(cpf))
        .collect(Collectors.toList());
    }

    public List<Conta> buscarContasPorTipo(String tipo){
        return contasPorID.values().stream()
                .filter(c -> c.getTipoConta().equalsIgnoreCase(tipo))
                .collect(Collectors.toList());
    }

    public void transferir(int idOrigem, int idDestino, double valor){
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (idOrigem == idDestino) throw new IllegalArgumentException("Transferência para a mesma conta não permitida");

        Conta origem = Optional.ofNullable(contasPorID.get(idOrigem))
                               .orElseThrow(() -> new IllegalArgumentException("Conta origem não encontrada: "+ idOrigem));
        Conta destivo = Optional.ofNullable(contasPorID.get(idDestino))
                                .orElseThrow(() -> new IllegalArgumentException("Conta destino não encontrada: "+ idDestino));

        if (origem.getSaldo() < valor) throw new IllegalArgumentException("Saldo insuficiente na conta de origem.");
        origem.sacar(valor);
        destivo.depositar(valor);
    }
}
