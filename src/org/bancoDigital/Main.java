package org.bancoDigital;

import org.bancoDigital.model.Cliente;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("eu", "12345678912", "12082004");

        System.out.println(cliente.getCpf());
    }
}
