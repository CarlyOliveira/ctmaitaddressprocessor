package br.com.ctmait.addressprocessor;

import java.math.BigDecimal;
import java.util.Optional;

public class Teste {
    public static void main(String[] args) {
        String valor =  null;
//        System.out.println(valor.matches("(?:\\.|[0-9])*"));
//        System.out.println(valor.matches("[+]?[0-9]+\\.[0-9]+[0-9]+"));
        System.out.println(valor);
        System.out.println("É um valor monetário válido para o pix ? " + isValidValor(valor));
        Optional.ofNullable(valor)
                .filter(Teste::isValidValor)
                .map(BigDecimal::new)
                .ifPresent(System.out::println);

        var teste = !(true && true && true);
        System.out.println(teste);
    }

    private static boolean isValidValor(String valor){
        return valor != null ? valor.matches("[+]?\\d+\\.\\d\\d") : false;
    }
}
