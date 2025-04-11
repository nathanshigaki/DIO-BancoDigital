package org.bancoDigital.model;

import org.bancoDigital.util.CPFUtils;
import org.bancoDigital.util.DateUtils;

public class Cliente {
    private String nome, cpf, dataNascimento;

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
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "nome ='" + nome + '\'' +
               ", cpf ='" + CPFUtils.formatarCPF(cpf) + '\'' +
               ", data de nascimento ='" + DateUtils.formatarData(dataNascimento) + '\'' +
               '}';
    }
}
