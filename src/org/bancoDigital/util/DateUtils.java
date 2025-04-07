package org.bancoDigital.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatarData(LocalDate data){
        return data.format(FORMATTER);
    }

    public static boolean isMaiorDeIdade(LocalDate dataNascimento){
        return LocalDate.now().minusYears(18).isAfter(dataNascimento);
    }
}
