package org.bancoDigital.service;

import org.bancoDigital.factory.ClienteFactory;
import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Cliente;

public class ClienteService {
    private final Banco banco;
    private final ClienteFactory clienteFactory;

    public ClienteService(Banco banco){
        this.banco = banco;
        this.clienteFactory = new ClienteFactory(banco);
    }

    public void cadastrarCliente(){
        Cliente novoCliente = clienteFactory.criarCliente();
        if (novoCliente != null){
            banco.adicionarCliente(novoCliente);
        } else {
            System.out.println("Cliente não adicionado.");
        }
    }
}
