package org.bancoDigital.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bancoDigital.factory.ContaFactory;

public class Banco {
    private Map<Integer, Conta> contasPorID;
    private Map<String, List<Conta>> contasPorCPF;
    
    public Banco() {
        this.contasPorID = new HashMap<>();
        this.contasPorCPF = new HashMap<>();
    }

    public Conta criarConta(String tipo, Cliente cliente){
        Conta novaConta = ContaFactory.criarConta(tipo, cliente);
        contasPorID.put(novaConta.getidConta(), novaConta);
        String cpf = cliente.getCpf();
        contasPorCPF.computeIfAbsent(cpf, k -> new ArrayList<>()).add(novaConta);
        return novaConta;
    }
}
