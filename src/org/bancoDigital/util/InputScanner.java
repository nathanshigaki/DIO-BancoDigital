package org.bancoDigital.util;

import java.text.Normalizer;
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
            String tipo = scanner.nextLine().trim().toUpperCase();
            tipo = Normalizer.normalize(tipo, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "Ç");
            switch (tipo) {
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
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido.");
            }
        }
    }
    
    public static double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double num = scanner.nextDouble();
                scanner.nextLine();
                return num;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal.");
            }
        }
    }
    
    public static void fecharScanner() {
        scanner.close();
    }
}
