package net.ProjectEuler.problem10;

import net.ProjectEuler.problem7.Problem7;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;


public class Problem10 {
    public static void main(String[] args) {
        ArrayList<Long> listaLiczbPierwszych = znajdzLiczbyPierwsze(2_000_000);
        //System.out.println(listaLiczbPierwszych);
        Long sum = listaLiczbPierwszych.stream().mapToLong(i -> i.longValue()).sum();
        System.out.println(sum);
    }

    private static ArrayList<Long> znajdzLiczbyPierwsze(int doIlu) {
        ArrayList<Long> listaLP = new ArrayList<>();
        listaLP.add(2L);
        listaLP.add(3L);
        long N = 5;

        while (N < doIlu) {
            listaLP = Problem7.getN(listaLP, N);
            N++;
            //System.out.println(listaLP);
        }
        return listaLP;
    }
}
