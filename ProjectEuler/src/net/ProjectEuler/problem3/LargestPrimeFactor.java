package net.ProjectEuler.problem3;

import java.util.*;

public class LargestPrimeFactor {
    private static long liczba = 28L;
    private ArrayList<Long> listaDzielnikowPierwszych;
    public static void main(String[] args) {
        LargestPrimeFactor largestPrimeFactor = new LargestPrimeFactor();
        largestPrimeFactor.doRoboty();
    }

    private void doRoboty(){
        listaDzielnikowPierwszych = szukajDzielnikowPierwszych(liczba);
        System.out.println(listaDzielnikowPierwszych);
        sortujIWypiszNajwiekszy();
    }

    private void sortujIWypiszNajwiekszy() {
        Collections.sort(listaDzielnikowPierwszych);
        System.out.println(listaDzielnikowPierwszych.get(listaDzielnikowPierwszych.size()-1));
    }

    public ArrayList<Long> szukajDzielnikowPierwszych(long liczba) {
        ArrayList<Long> dzielnikiPierwszeLista = new ArrayList<>();
        long dzielnik = 2;

        while (liczba!=1){
            if(liczba%dzielnik==0){
                dzielnikiPierwszeLista.add(dzielnik);
                liczba /= dzielnik;
            }else{
                dzielnik++;
            }
        }
        return dzielnikiPierwszeLista;
    }




}
