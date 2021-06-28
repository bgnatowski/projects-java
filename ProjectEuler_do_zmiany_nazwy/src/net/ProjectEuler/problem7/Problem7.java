package net.ProjectEuler.problem7;

import java.util.ArrayList;

public class Problem7 {
    public static void main(String[] args) {
        ArrayList<Long> listaLiczbPierwszych = znajdzNLiczbPierwszch(10_001);
        wyswietlOstatnia(listaLiczbPierwszych);
    }

    private static void wyswietlOstatnia(ArrayList<Long> listaLiczbPierwszych) {
        System.out.println(listaLiczbPierwszych.get(listaLiczbPierwszych.size()-1));
    }

    private static ArrayList<Long> znajdzNLiczbPierwszch(int ileLiczbPierwszych) {
        ArrayList<Long> listaLP = new ArrayList<>();
        listaLP.add(2L);
        listaLP.add(3L);
        long N = 5;

        while (listaLP.size()<ileLiczbPierwszych){
            listaLP = getN(listaLP, N);
            N++;

        }
        System.out.println(listaLP);
        return listaLP;
    }

    public static ArrayList<Long> getN(ArrayList<Long> listaLP, long n) {
        ArrayList<Boolean> czyPodzielnaPrzezWszystkieLista = new ArrayList<>(listaLP.size());
        for(int i = 0; i < listaLP.size(); i++){
            long liczbaPierwsza = listaLP.get(i);

            if(liczbaPierwsza<=Math.sqrt(n)){
                if(n %liczbaPierwsza != 0){
                    czyPodzielnaPrzezWszystkieLista.add(false);
                }else if(n %liczbaPierwsza == 0){
                    czyPodzielnaPrzezWszystkieLista.add(true);
                }
            }
        }
        if(!czyPodzielnaPrzezWszystkieLista.contains(true)){
            listaLP.add(n);
            n++;
        }else{
            n++;
        }
        return listaLP;
    }
}
