package net.ProjectEuler.problem8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class LargestProductInSeries {
    public static void main(String[] args) {
        String liczba = odczytajLiczbeZPliku();
        znajdzNajwiekszyIloczyn(liczba, 13);
    }

    public static String odczytajLiczbeZPliku() {
        String liczba = "";
        try{
            FileInputStream fis = new FileInputStream("src/net/ProjectEuler/problem8/problem8Content.txt");
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNext()){
                liczba = liczba.concat(scanner.nextLine());
            }
            scanner.close();
            fis.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return liczba;
    }

    private static void znajdzNajwiekszyIloczyn(String liczba, int doIlu) {
        long max = 1L;
        for(int i = 0; i < doIlu; i++){
            max *= liczba.charAt(i)-48;
        }

        for(int i = 0; i < liczba.length()-doIlu; i++){
            long tempMax = 1;
            for(int j = i; j < doIlu+i; j++){
                tempMax *= liczba.charAt(j)-48;
            }
            //System.out.println(max + " > " + tempMax);
            if(tempMax > max){
                max = tempMax;
            }
        }

        System.out.println(max);
    }
}
