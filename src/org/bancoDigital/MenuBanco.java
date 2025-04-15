package org.bancoDigital;

import org.bancoDigital.model.Banco;
import org.bancoDigital.model.Conta;
import org.bancoDigital.model.ContaCorrente;
import org.bancoDigital.model.ContaPoupanca;
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
            case 7 -> gerenciarContas(banco, bancoService);
            case 0 -> System.out.println("Saiu do sistema bancário.");
            default -> System.out.println("Opção inválida, tente novamente.");
        }
    }

    public void executarGerenciarContas(int opcaoInicial, Banco banco, BancoService bancoService, Conta origem){
        switch (opcaoInicial) {
            case 1 -> origem.depositar(InputScanner.lerDouble("Valor: "));
            case 2 -> origem.sacar(InputScanner.lerDouble("Valor: "));
            case 3 -> origem.getSaldo();
            case 4 -> realizarTransferencia(banco, bancoService, origem);
            case 6 -> gerenciarContaCorrente(origem);
            case 7 -> gerenciarContaPoupanca(origem);
            case 8 -> System.out.println(banco.removerContaEspecifica(origem.getCliente().getCpf(), origem.getTipoConta()) ? "Conta removida com sucesso" : "Conta não encontrada");
            case 9 -> System.out.println(banco.removerTodasAsContasCliente(origem.getCliente().getCpf()) ? "Conta removida com sucesso" : "Conta não encontrada");
            case 0 -> System.out.println("Saindo do gerenciador de conta.");
            default -> System.out.printf("Opção %s inválida, tente novamente.\n", opcaoInicial);
        }
    }
    

    public void gerenciarContas(Banco banco, BancoService bancoService){
        int resultado = 0;
        boolean contaDefinida = false;
        String cpf = null;
        String tipo = null;
        Conta origem = null;
        do{
            int opcaoInicial = InputScanner.lerInt("""

                [ 1 ] Depositar.
                [ 2 ] Sacar.
                [ 3 ] Consultar saldo.
                [ 4 ] Realizar transferência.
                [ 5 ] Trocar conta.
                [ 6 ] Ações da conta corrente.
                [ 7 ] Ações da conta poupança
                [ 8 ] Remover conta especifica.
                [ 9 ] Remover todas as contas do cliente.
                [ 0 ] Voltar.
                
                Escolha uma opção: 
                """);

            if (!contaDefinida && opcaoInicial != 0){
                cpf = CPFUtils.recebeCPF(InputScanner.lerString("CPF:"));
                tipo = InputScanner.lerTipoConta("Tipo da conta(CORRENTE OU POUPANÇA): ");
                if (!banco.listarContasPorCPF(cpf).isEmpty()){
                    origem = banco.definirConta(cpf, tipo).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
                    contaDefinida = true;
                }
            }
            if (origem == null){
                System.out.println("Conta indefinida(crie uma conta ou digite corretamente o cpf). Voltando ao menu anterior.");
                continue;
            }
            if (opcaoInicial == 5){ 
                contaDefinida = false;
            } else {
                executarGerenciarContas(opcaoInicial, banco, bancoService, origem);
            }
        } while (resultado !=0);
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

    private void realizarTransferencia(Banco banco, BancoService bancoService, Conta origem){
        String cpfDestino = CPFUtils.recebeCPF(InputScanner.lerString("CPF do destinatário: "));
        String tipoDestino = InputScanner.lerTipoConta("Tipo da conta destino (CORRENTE ou POUPANÇA): ");
        if (!banco.listarContasPorCPF(cpfDestino).isEmpty()){
            System.out.println("Conta não encontrada, falha na transferência");
        } else {
            double valor = InputScanner.lerDouble("Valor para transferência: ");
        
            Conta destino = banco.definirConta(cpfDestino, tipoDestino)
                .orElseThrow(() -> new RuntimeException("Conta destino não encontrada!"));
            
            bancoService.transferir(origem, destino, valor);
            System.out.println("Transferência realizada com sucesso.");
        }
    }

    public void gerenciarContaCorrente(Conta conta) {
        if (!(conta instanceof ContaCorrente)) {
            System.out.println("Tipo de conta inválido: somente 'CORRENTE' é permitido.");
            return;
        }
    
        ContaCorrente cc = (ContaCorrente) conta;
        int opcao;
        
        do {
            opcao = InputScanner.lerInt("""
                
            [ 1 ] Pedir empréstimo.
            [ 2 ] Pagar emprestimo.
            [ 3 ] Verificar valor do emprestimo.
            [ 0 ] Voltar.

            Escolha uma opção:
            """);
            
            switch (opcao) {
                case 1 -> cc.pedirEmprestimo();
                case 2 -> cc.pagarEmprestimos();
                case 3 -> cc.valorEmprestimo();
                case 4 -> System.out.println("Voltando para o gerenciador de contas.");
                default -> System.out.println("Opção inválida");
            }
        } while (opcao != 0 && opcao != 4);
    }

    public void gerenciarContaPoupanca(Conta conta) {
        if (!(conta instanceof ContaPoupanca)) {
            System.out.println("Tipo de conta inválido: somente 'POUPANÇA' é permitido.");
            return;
        }
    
        ContaPoupanca cp = (ContaPoupanca) conta;
        cp.simulacaoPoupanca();
    }
}