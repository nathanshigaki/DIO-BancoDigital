package org.bancoDigital.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    public static String receberData(String data){
        while (!verificarData(data)) {
            System.out.println("Data de nascimento inválida.");
            data = InputScanner.lerString("Data de nascimento no formato ddMMyyyy: ");
        }
        return data;
    }

    public static boolean verificarData(String data){
        if (data == null || !data.matches("\\d{8}")) {
            return false;
        }

        int dia = Integer.parseInt(data.substring(0, 2));
        int mes = Integer.parseInt(data.substring(2, 4));
        int ano = Integer.parseInt(data.substring(4));

        int anoAtual = LocalDate.now().getYear();
        if (ano < 1900 || ano > anoAtual) return false;

        if (mes < 1 || mes > 12) return false;

        if (dia < 1 || dia > 31) return false;
        
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) return false;

        if (mes == 2) {
            boolean isBissexto = (ano % 400 == 0) || (ano % 100 != 0 && ano % 4 == 0);
            if (dia > (isBissexto ? 29 : 28)) {
                return false;
            }
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate dataCerta = LocalDate.parse(data, formatter);
            
            // Verifica se a data não é no futuro
            return !dataCerta.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static String formatarData(String data){
        return data.substring(0, 2) + "/" + 
               data.substring(2, 4) + "/" + 
               data.substring(4);
    }

    public static boolean verificarMaiorIdade(String data) {
        String dataFormatada = formatarData(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        try {
            LocalDate dataNasc = LocalDate.parse(dataFormatada, formatter);
            LocalDate hoje = LocalDate.now();
            
            Period periodo = Period.between(dataNasc, hoje);
            return periodo.getYears() >= 18;
            
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + data);
        }
    }
}
