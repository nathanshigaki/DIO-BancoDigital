package org.bancoDigital.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Banco {
    private final Map<Integer, Conta> contasPorID;
    private final List<Cliente> listaClientes;
    
    public Banco() {
        this.contasPorID = new HashMap<>();
        this.listaClientes = new ArrayList<>();
    }

    public void adicionarCliente(Cliente novoCliente){
        listaClientes.add(novoCliente);
    }

    public List<Cliente> listarClientes(){
        List<Cliente> clientesOrdenados = new ArrayList<>(listaClientes);
        clientesOrdenados.sort(Comparator.comparing(Cliente::getNome));
        return clientesOrdenados;
    }

    public Optional<Cliente> buscarClienteCPf(String cpf){
        return listaClientes.stream().filter(c -> c.getCpf().equalsIgnoreCase(cpf)).findFirst();
    }

    public Optional<Cliente> buscarClienteNome(String nome){
        return listaClientes.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public void adicionarConta(Conta conta){
        this.contasPorID.put(conta.getidConta(), conta);
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
}
