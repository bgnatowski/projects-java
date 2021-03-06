package net.ProjectEuler.problem1;

import java.util.ArrayList;

public class MultiplesOf3And5 {
    private static final int LICZB = 1000;
    private ArrayList<Integer> listaDzielonychNaTrzyLubPiec;

    public static void main(String[] args) {
        MultiplesOf3And5 problem = new MultiplesOf3And5();
        problem.doRoboty();
    }

    private void doRoboty(){
        listaDzielonychNaTrzyLubPiec = znajdzDzieloneNaTrzyLubPiec();
        wypiszSumeLiczb();
    }

    private ArrayList<Integer> znajdzDzieloneNaTrzyLubPiec() {
        ArrayList<Integer> lista = new ArrayList<>();

        for(int i = 0; i < LICZB; i++){
            if(i%3 == 0 || i%5 == 0){
                lista.add(i);
            }
        }

        return lista;
    }

    private void wypiszSumeLiczb() {
        int suma = 0;
        for(int liczba : listaDzielonychNaTrzyLubPiec){
            suma += liczba;
        }
        System.out.println(suma);
    }

}
