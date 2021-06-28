package net.ProjectEuler.problem16;

import java.math.BigDecimal;

public class Problem16 {
    public static void main(String[] args) {
        BigDecimal liczba = new BigDecimal(Math.pow(2, 1000));
        System.out.println(znajdzSumeLiczb(liczba.toPlainString()));
    }

    private static BigDecimal znajdzSumeLiczb(String liczba) {
        BigDecimal suma = new BigDecimal(0);
        for(int i = 0; i < liczba.length(); i++){
                suma = suma.add(new BigDecimal(liczba.charAt(i)-48));
        }
        return suma;
    }
}
