package org.bancoDigital.model;

import org.bancoDigital.util.CPFUtils;

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
        return CPFUtils.formatarCPF(cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        LocalDate date = LocalDate.parse(dataNascimento, ENTRADA_FORMATTER);
        this.dataNascimento = date.format(SAIDA_FORMATTER);
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "nome ='" + nome + '\'' +
               ", cpf ='" + cpf + '\'' +
               ", data de nascimento ='" + dataNascimento + '\'' +
               '}';
    }
}
