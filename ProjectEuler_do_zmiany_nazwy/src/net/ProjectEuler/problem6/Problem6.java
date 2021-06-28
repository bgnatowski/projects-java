package net.ProjectEuler.problem6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem6 {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int doIlu = 0;
        try{
            System.out.print("Do ilu liczb: ");
            doIlu = Integer.valueOf(reader.readLine());

            reader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        int sumaKwadratow = znajdzSumeKwadratow(doIlu);
        int kwadratSumy = znajdzKwadratSumy(doIlu);

        pokazRoznice(kwadratSumy, sumaKwadratow);
    }

    private static int znajdzKwadratSumy(int doIlu) {
        int wynik = 0;

        for(int i = 1; i <= doIlu; i++){
            wynik +=i;
        }
        return (int) Math.pow(wynik, 2);
    }

    private static int znajdzSumeKwadratow(int doIlu) {
        int wynik = 0;

        for(int i = 1; i<=doIlu; i++){
            wynik += (int) Math.pow(i, 2);
        }

        return wynik;
    }

    private static void pokazRoznice(int kwadratSumy, int sumaKwadratow) {
        System.out.println("Różnica kwadrat sumy - suma kwadratów = " + (kwadratSumy-sumaKwadratow));
    }




}
