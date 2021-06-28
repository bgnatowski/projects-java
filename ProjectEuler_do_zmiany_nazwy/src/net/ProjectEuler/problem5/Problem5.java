package net.ProjectEuler.problem5;

import java.util.ArrayList;

public class Problem5 {
    private static final int doIlu = 20;
    public static void main(String[] args) {
        long szukanaLiczba = znajdzLiczbe();
        System.out.println(szukanaLiczba);
    }

    private static long znajdzLiczbe() {
        ArrayList<Boolean> czyPrzezWszystkie = new ArrayList<>();
        int wynik = doIlu;

        while (czyPrzezWszystkie.size() != doIlu){
            for(int i = 1; i <= doIlu; i++){
                if(wynik%i == 0){
                    //System.out.println(wynik + " " + i);
                    czyPrzezWszystkie.add(true);
                }else{
                    czyPrzezWszystkie.clear();
                    wynik++;
                }
                if(czyPrzezWszystkie.size() == doIlu){
                    break;
                }
            }

        }


        return wynik;
    }
}
