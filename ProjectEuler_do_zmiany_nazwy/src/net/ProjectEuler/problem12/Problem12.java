package net.ProjectEuler.problem12;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem12 {
    private static final int N = 500;
    public static void main(String[] args) {
        szukajPierwszejLiczbyTrojkatnejKtoraMaWiecejNizNDzielnikow(N);
    }

    private static void szukajPierwszejLiczbyTrojkatnejKtoraMaWiecejNizNDzielnikow(int doIluDzielnikow) {
        int liczbaTrojkatna = 0;
        int iloscDzielnikow=0;
        long ktoraLiczbaTrojkatna = 0;

//        while (NumberOfDivisors(liczbaTrojkatna)<doIluDzielnikow){
//            liczbaTrojkatna += i;
//            i++;
//        }
        while(iloscDzielnikow<doIluDzielnikow+1){
            ktoraLiczbaTrojkatna++;
            liczbaTrojkatna = (int) szukajLiczbyTrojkatnej(ktoraLiczbaTrojkatna);
            ArrayList<Long> dzielniLiczby = szukajDzielnikowLiczby(liczbaTrojkatna);
            iloscDzielnikow = dzielniLiczby.size();
            System.out.println(liczbaTrojkatna + " " + iloscDzielnikow);
        }
        System.out.println(liczbaTrojkatna);
    }


    private static int NumberOfDivisors(int number) {
        int nod = 0;
        int sqrt = (int) Math.sqrt(number);

        for(int i = 1; i <= sqrt; i++){
            if(number % i == 0){
                nod += 2;
            }
        }
        //Correction if the number is a perfect square
        if (sqrt * sqrt == number) {
            nod--;
        }

        return nod;
    }
    private static long szukajIlosciDzielnikowLiczby(long liczba) {
        int ileDzielnikow = 0;
        long dzielnik = 1;
        while (dzielnik <= liczba) {
            if (liczba % dzielnik == 0) {
                ileDzielnikow++;
                dzielnik++;
            } else {
                dzielnik++;
            }
        }
        return ileDzielnikow;
    }

    public static long szukajLiczbyTrojkatnej(long ktoraLiczbaTrojkatna) {
        long liczbaTrojkatna = 0;
        for(int i = 1; i <= ktoraLiczbaTrojkatna; i++){
            liczbaTrojkatna+=i;
        }
        return liczbaTrojkatna;
    }

    public static void wypiszLinkedHashMapDzielnikowLiczb(LinkedHashMap<Long, ArrayList<Long>> mapaDzielnikow){
        for(Map.Entry para : mapaDzielnikow.entrySet()){
            Long liczba = (long) para.getKey();
            ArrayList<Long> listaDzielnikowLiczby = (ArrayList<Long>) para.getValue();

            System.out.print(liczba + ": ");
            for(long dzielnikPierwszy : listaDzielnikowLiczby){
                System.out.print(dzielnikPierwszy + ",");
            }
            System.out.println();
        }
    }

    public static ArrayList<Long> szukajDzielnikowLiczby(long liczba) {
        ArrayList<Long> dzielnikiLista = new ArrayList<>();
        long dzielnik = 1;
        while (dzielnik <= liczba) {
            if (liczba % dzielnik == 0) {
                dzielnikiLista.add(dzielnik);
                dzielnik++;
            } else {
                dzielnik++;
            }
        }
        return dzielnikiLista;
    }


    public static LinkedHashMap<Long, ArrayList<Long>> szukajDzielnikowZListy(ArrayList<Long> lista) {
        ArrayList<Long> dzielnikiLista;
        LinkedHashMap<Long, ArrayList<Long>> dzielnikiMapa = new LinkedHashMap<>();

        for(long liczbaZListy : lista){
            dzielnikiLista = new ArrayList<>();
            long dzielnik = 1;

            while (dzielnik<=liczbaZListy){
                if(liczbaZListy%dzielnik==0){
                    dzielnikiLista.add(dzielnik);
                    dzielnik++;
                }else{
                    dzielnik++;
                }
            }

            dzielnikiMapa.put(liczbaZListy, dzielnikiLista);
        }
        return dzielnikiMapa;
    }
}
