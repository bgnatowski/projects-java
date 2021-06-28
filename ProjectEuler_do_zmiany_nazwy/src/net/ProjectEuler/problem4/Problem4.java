package net.ProjectEuler.problem4;

import java.util.ArrayList;
import java.util.Collections;

public class Problem4 {
    public static void main(String[] args) {
        new Problem4().doRoboty();
    }

    private void doRoboty() {
        int palindrom = znajdzPalindrom();
        System.out.println(palindrom);
    }

    private int znajdzPalindrom() {
        ArrayList<Integer> listaWynikow = znajdzWynikiMnozen(100, 1000);
        ArrayList<Integer> listaPalindromow  = znajdzListePalindromow(listaWynikow);

        return znajdzNajwiekszyPalindrom(listaPalindromow);
    }



    private ArrayList<Integer> znajdzWynikiMnozen(int od, int doIlu) {
        ArrayList<Integer> wynikiMnozenLista = new ArrayList<>();

        for(int i = od; i < doIlu; i++){
            for (int j = od; j < doIlu; j++){
                wynikiMnozenLista.add(i*j);
            }
        }

        return wynikiMnozenLista;
    }

    private ArrayList<Integer> znajdzListePalindromow(ArrayList<Integer> listaWynikow) {
        ArrayList<Integer> listaPalindromow = new ArrayList<>();
        StringBuilder stringBuilder;

        //System.out.println(listaWynikow);

        for(int liczba : listaWynikow){
            stringBuilder = new StringBuilder();
            stringBuilder.append(liczba);
            StringBuilder palindrom = stringBuilder.reverse();

            //System.out.println(liczba + " " + palindrom);

            if(liczba == Integer.valueOf(String.valueOf(palindrom))){
                listaPalindromow.add(liczba);
            }
        }
        //System.out.println(listaPalindromow);

        return listaPalindromow;
    }

    private int znajdzNajwiekszyPalindrom(ArrayList<Integer> listaPalindromow) {
        return Collections.max(listaPalindromow);
    }



}
