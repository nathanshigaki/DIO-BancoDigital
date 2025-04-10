package org.bancoDigital.util;

import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerString(String mensagem){
        System.out.println(mensagem);
        return scanner.nextLine();
    }

    public static String lerTipoConta(String mensagem){
        while (true) {
            System.out.println(mensagem);
            String tipo = scanner.next();
            switch (tipo.toUpperCase()) {
                case "CORRENTE" : return tipo;
                case "POUPANÇA" : return tipo;
                default : System.out.println("Tipo de conta inválida.");
            }
        }
    }

    public static int lerInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número de 0 a 9.");
            }
        }
    }
    
    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal.");
            }
        }
    }
    
    public static void fecharScanner() {
        scanner.close();
    }
}
