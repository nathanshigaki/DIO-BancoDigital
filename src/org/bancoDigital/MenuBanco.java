package org.bancoDigital;

import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Conta;
import org.bancoDigital.model.ContaCorrente;
import org.bancoDigital.service.BancoService;
import org.bancoDigital.service.ClienteService;
import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.InputScanner;

public class MenuBanco {

    public static void main(String[] args) {
        MenuBanco menu = new MenuBanco();
        menu.iniciar();    
        InputScanner.fecharScanner();
    }

    public void iniciar(){
        Banco banco = new Banco();
        ClienteService clienteService = new ClienteService(banco);
        BancoService bancoService = new BancoService(banco);
        System.out.println("==== Sistema bancário ====");
        int opcao;

        do {
            opcao = exibirMenu();
            executarMenu(opcao, clienteService, bancoService, banco);
        } while (opcao !=0);
    }

    public int exibirMenu(){
        return InputScanner.lerInt("""

                [ 1 ] Cadastrar cliente.
                [ 2 ] Remover cliente.
                [ 3 ] Criar conta corrente.
                [ 4 ] Criar conta poupança.
                [ 5 ] Listar clientes.
                [ 6 ] Listar contas.
                [ 7 ] Gerenciar conta.
                [ 0 ] Sair.
                
                Escolha uma opção: 
                """);
    }

    public void executarMenu(int opcao,  ClienteService clienteService, BancoService bancoService, Banco banco){
        switch (opcao) {
            case 1 -> clienteService.cadastrarCliente();
            case 2 -> System.out.println(banco.removerCliente(CPFUtils.recebeCPF(InputScanner.lerString("CPF:"))) ? "Cliente removido com sucesso" : "Cliente não encontrado");
            case 3 -> bancoService.abrirConta("CORRENTE");
            case 4 -> bancoService.abrirConta("POUPANÇA");
            case 5 -> System.out.println(banco.listarClientes());
            case 6 -> filtroContas(banco);
            case 7 -> {
                while (opcao != 0){
                    opcao = gerenciarContas();
                    executarGerenciarContas(opcao, banco, bancoService);
                }
            }
            case 0 -> System.out.println("Saiu do sistema bancário.");
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    public void executarGerenciarContas(int opcao, Banco banco, BancoService bancoService){
        boolean contaDefinida = false;
        String cpf = null;
        String tipo = null;
        do {
            if (!contaDefinida){
                cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
                tipo = InputScanner.lerTipoConta("Tipo da conta(CORRENTE OU POUPANÇA): ");
                contaDefinida = true;
            }
            Conta origem = banco.definirConta(cpf, tipo).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
            switch (opcao) {
                case 1 -> origem.depositar(InputScanner.lerDouble("Valor: "));
                case 2 -> origem.sacar(InputScanner.lerDouble("Valor: "));
                case 3 -> origem.getSaldo();
                case 4 -> {
                    cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
                    tipo = InputScanner.lerTipoConta("Tipo da conta(CORRENTE OU POUPANÇA): ");
                    Conta destino = banco.definirConta(cpf, tipo).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
                    bancoService.transferir(origem, destino, InputScanner.lerDouble("Valor: "));
                }
                case 5 -> contaDefinida = false;
                case 6 -> {
                    if (tipo.equalsIgnoreCase("CORRENTE")){
                        ContaCorrente contaCorrente = origem;
                    }
                    System.out.println("A conta tem que ser do tipo corrente.");
                }
                case 8 -> System.out.println(banco.removerContaEspecifica(cpf, tipo) ? "Conta removida com sucesso" : "Conta não encontrada");
                case 9 -> System.out.println(banco.removerTodasAsContasCliente(cpf) ? "Conta removida com sucesso" : "Conta não encontrada");
                case 0 -> System.out.println("Saindo do gerenciador de conta.");
                default -> System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao !=0);
    }

    public int gerenciarContas(){
        return InputScanner.lerInt("""

                [ 1 ] Depositar.
                [ 2 ] Sacar.
                [ 3 ] Consultar saldo.
                [ 4 ] Realizar transferência.
                [ 5 ] Trocar conta.
                [ 6 ] Ações para conta corrente.
                [ 7 ] Ações para conta poupança
                [ 8 ] Remover conta especifica.
                [ 9 ] Remover todas as contas do cliente.
                [ 0 ] Sair.
                
                Escolha uma opção: 
                """);
    }

    public void filtroContas(Banco banco){
        int opcao = InputScanner.lerInt("""
                
                [ 1 ] Todas as contas.
                [ 2 ] Contas Corrente.
                [ 3 ] Contas Poupança.
                
                Escolha uma opção
                """);
        switch (opcao) {
            case 1 -> System.out.println(banco.listarContas());
            case 2 -> System.out.println(banco.listarContasPorTipo("CORRENTE"));
            case 3 -> System.out.println(banco.listarContasPorTipo("POUPANÇA"));
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
