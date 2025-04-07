package org.bancoDigital;

import org.bancoDigital.util.InputScanner;

public class MenuBanco {

    public static void main(String[] args) {
        MenuBanco menu = new MenuBanco();
        menu.iniciar();    
    }

    public void iniciar(){
        System.out.println("==== Sistema bancário ====");
        int opcao;

        do {
            opcao = exibirMenu();
            fazer(opcao);
        } while (opcao !=0);
    }

    public int exibirMenu(){
        return InputScanner.lerInt("""

                [ 1 ] Cadastrar cliente.
                [ 2 ] Criar conta corrente.
                [ 3 ] Criar conta poupança.
                [ 4 ] Realizar transferência.
                [ 5 ] Consultar saldo.
                [ 6 ] Listar contas.
                [ 0 ] Sair.
                
                Escolha uma opção: 
                """);
    }

    public void fazer(int opcao){
        switch (opcao) {
            case 1 -> ;
            default -> System.out.println("asdasd");
        }
    }
}
