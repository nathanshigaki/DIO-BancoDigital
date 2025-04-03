package org.bancoDigital.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente {
    private String nome, cpf, dataNascimento;
    private static final DateTimeFormatter ENTRADA_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy");
    private static final DateTimeFormatter SAIDA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Cliente(String nome, String cpf, String dataNascimento) {
        this.setNome(nome);
        this.setCpf(cpf);
        this.setDataNascimento(dataNascimento);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        cpf = cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        LocalDate date = LocalDate.parse(dataNascimento, ENTRADA_FORMATTER);
        this.dataNascimento = date.format(SAIDA_FORMATTER);
    }
}
