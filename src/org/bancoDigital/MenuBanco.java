package org.bancoDigital;

import org.bancoDigital.model.Banco;
import org.bancoDigital.service.BancoService;
import org.bancoDigital.service.ClienteService;
import org.bancoDigital.util.InputScanner;

public class MenuBanco {

    public static void main(String[] args) {
        MenuBanco menu = new MenuBanco();
        menu.iniciar();    
    }

    public void iniciar(){
        Banco banco = new Banco();
        ClienteService clienteService = new ClienteService(banco);
        BancoService bancoService = new BancoService(banco);
        System.out.println("==== Sistema bancário ====");
        int opcao;

        do {
            opcao = exibirMenu();
            fazer(opcao, clienteService, bancoService, banco);
        } while (opcao !=0);
    }

    public int exibirMenu(){
        return InputScanner.lerInt("""

                [ 1 ] Cadastrar cliente.
                [ 2 ] Criar conta corrente.
                [ 3 ] Criar conta poupança.
                [ 4 ] Realizar transferência.
                [ 5 ] Consultar saldo.
                [ 6 ] Listar clientes.
                [ 7 ] Listar contas.
                [ 0 ] Sair.
                
                Escolha uma opção: 
                """);
    }

    public void fazer(int opcao,  ClienteService clienteService, BancoService bancoService, Banco banco){
        switch (opcao) {
            case 1 -> clienteService.cadastrarCliente();
            case 2 -> bancoService.abrirConta("CORRENTE");
            case 3 -> bancoService.abrirConta("POUPANÇA");
            case 6 -> banco.listarClientes();
            case 7 -> filtroContas(banco);
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    public void filtroContas(Banco banco){
        int opcao = InputScanner.lerInt("""
                
                [ 1 ] Todas as contas.
                [ 2 ] Contas Corrente.
                [ 3 ] Contas Poupança.
                
                Escolha uma opção
                """);
        switch (opcao) {
            case 1 -> banco.listarContas();
            case 2 -> banco.buscarContasPorTipo("CORRENTE");
            case 3 -> banco.buscarContasPorTipo("POUPANÇA");
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    public static int nomeOuCpf(){
        return InputScanner.lerInt("""

                [ 1 ] Procurar por nome.
                [ 2 ] Procurar por CPF.

                Escolha uma opção: 
                """);
    }
}
