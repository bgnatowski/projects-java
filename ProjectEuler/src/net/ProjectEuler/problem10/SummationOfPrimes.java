package net.ProjectEuler.problem10;

import net.ProjectEuler.problem7.TenThousandFirstPrime;

import java.util.ArrayList;


public class SummationOfPrimes {
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
            listaLP = TenThousandFirstPrime.getN(listaLP, N);
            N++;
            //System.out.println(listaLP);
        }
        return listaLP;
    }
}
