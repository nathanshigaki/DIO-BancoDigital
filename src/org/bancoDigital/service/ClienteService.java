package org.bancoDigital.service;

import org.bancoDigital.factory.ClienteFactory;
import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Cliente;

public class ClienteService {
    private Banco banco;

    public ClienteService(Banco banco){
        this.banco = banco;
    }

    public void cadastrarCliente(){
        Cliente novoCliente = ClienteFactory.criarCliente();
        System.out.println(novoCliente.getNome());
        banco.adicionarCliente(novoCliente);
    }
}
