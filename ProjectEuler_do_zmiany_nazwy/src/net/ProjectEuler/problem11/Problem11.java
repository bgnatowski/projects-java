package net.ProjectEuler.problem11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem11 {
    private static final int X = 20;
    private static final int Y = 20;

    public static void main(String[] args) {
        int doIlu = 4;
        int[][] tablica = odczytajTabliceZPliku(X,Y);
        //wypiszTabliceDwuWymiarowa(X,Y, tablica);

        int maxPion = znajdzMaxWPionie(tablica, doIlu);
        int maxPoziom = znajdzMaxWPoziomie(tablica, doIlu);
        int maxPrzekatnaPrawa = znajdzMaxPoPrzekatnejPrawej(tablica, doIlu);
        int maxPrzekatnaLewa = znajdzMaxPoPrzekatnejLewej(tablica, doIlu);

        ArrayList<Integer> listaMaximum = new ArrayList<>();
        listaMaximum.add(maxPion);
        listaMaximum.add(maxPoziom);
        listaMaximum.add(maxPrzekatnaPrawa);
        listaMaximum.add(maxPrzekatnaLewa);

        //System.out.println(listaMaximum);
        int maxIloczyn = znajdzIWypiszMaximumZListyIntegerow(listaMaximum);
        System.out.println(maxIloczyn);
    }

    public static int znajdzMaxPoPrzekatnejLewej(int[][] tablica, int doIlu) {
        int max = 1;

        int j = 0;
        for (int i = tablica.length-1; i > tablica.length-doIlu-1; i--, j++) {
            //System.out.println(tablica[j][i]);
            max *= tablica[j][i];
        }

        ArrayList<String> wspolrzedneTmp = new ArrayList<>();
        //[wiersze][kolumny]

        for (int wiersz = 0; wiersz < tablica.length - doIlu+1; wiersz++) {
            for (int kolumna = tablica.length-1; kolumna > doIlu-1 ; kolumna--) {
                int iteratorKolumn = kolumna;
                int tmpMax = 1;
                for (int iteratorWiersza = wiersz; iteratorWiersza < doIlu + wiersz; iteratorWiersza++, iteratorKolumn--) {
                    //System.out.print(tablica[iteratorWiersza][iteratorKolumn] + " ");
                    tmpMax *= tablica[iteratorWiersza][iteratorKolumn];
                    //wspolrzedneTmp.add(iteratorWiersza + " " + iteratorKolumn);
                }
                //System.out.println();
                if(tmpMax > max){
                    max = tmpMax;
                    //System.out.println(wspolrzedneTmp);
                }else{
                    wspolrzedneTmp.clear();
                }
            }
            //System.out.println();
        }
//        //System.out.println(max);

        return max;
    }

    public static int znajdzMaxPoPrzekatnejPrawej(int[][] tablica, int doIlu) {
        int max = 1;

        for (int i = 0; i < doIlu; i++) {
            int j = i;
            max *= tablica[i][j];
        }

        ArrayList<String> wspolrzedneTmp = new ArrayList<>();
        //[wiersze][kolumny]

        for (int wiersz = 0; wiersz < tablica.length - doIlu+1; wiersz++) {
            for (int kolumna = 0; kolumna < tablica.length - doIlu+1; kolumna++) {
                int iteratorKolumn = kolumna;
                int tmpMax = 1;
                for (int iteratorWiersza = wiersz; iteratorWiersza < doIlu + wiersz; iteratorWiersza++, iteratorKolumn++) {
                    //System.out.print(tablica[iteratorWiersza][iteratorKolumn] + " ");
                    tmpMax *= tablica[iteratorWiersza][iteratorKolumn];
                    //wspolrzedneTmp.add(iteratorWiersza + " " + iteratorKolumn);
                }
                //System.out.println();
                if(tmpMax > max){
                    max = tmpMax;
                    //System.out.println(wspolrzedneTmp);
                }else{
                    wspolrzedneTmp.clear();
                }
            }
            //System.out.println();
        }
        //System.out.println(max);

        return max;
    }

    public static int znajdzMaxWPoziomie(int[][] tablica, int doIlu) {
        int max = 1;

        for(int i = 0; i < doIlu; i++){
            int j = 0;
            max *= tablica[j][i];
        }
        ArrayList<String> wspolrzedneTmp = new ArrayList<>();
        for(int j = 0; j < tablica.length; j++){
            for(int pionowyIterator = 0; pionowyIterator < tablica.length-doIlu+1; pionowyIterator++){
                int tempMax = 1;
                for (int i = pionowyIterator; i < doIlu+pionowyIterator; i++){
                    //System.out.print(tablica[j][i] + " ");
                    tempMax *= tablica[j][i];
                    wspolrzedneTmp.add(j + " " + i);
                }
                //System.out.println();
                if(tempMax > max){
                    max = tempMax;
                    //System.out.println(wspolrzedneTmp);
                }else{
                    wspolrzedneTmp.clear();
                }
            }
            //System.out.println();
        }

        //System.out.println(max);

        return max;
    }


    public static int znajdzMaxWPionie(int[][] tablica, int doIlu) {
        int max=1;
        //i odpowiada za pion, j odpowiada za poziom
        //sprawdzenie pierwszego max w pionie
        for(int i = 0; i < doIlu; i++){
            int j = 0;
            max *= tablica[i][j];
        }
        ArrayList<String> wspolrzedneTmp = new ArrayList<>();
        for(int j = 0; j < tablica.length; j++){
            for(int pionowyIterator = 0; pionowyIterator < tablica.length-doIlu+1; pionowyIterator++){
                int tempMax = 1;
                for (int i = pionowyIterator; i < doIlu+pionowyIterator; i++){
                    //System.out.print(tablica[i][j] + " ");
                    tempMax *= tablica[i][j];
                    wspolrzedneTmp.add(i + " " + j);
                }
                //System.out.println();
                if(tempMax > max){
                    max = tempMax;
                    //System.out.println(wspolrzedneTmp);
                }else{
                    wspolrzedneTmp.clear();
                }
            }
            //System.out.println();
        }
        return max;
    }

    public static int znajdzIWypiszMaximumZListyIntegerow(ArrayList<Integer> lista) {
        return lista.stream().max(Integer::compare).get();
    }

    public static void wypiszTabliceDwuWymiarowa(int x, int y, int[][] tablica) {
        for(int i = 0; i < x; i ++){
            for(int j = 0; j < y; j++){
                System.out.print(tablica[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] odczytajTabliceZPliku(int x, int y) {
        int[][] tablica = new int[x][y];
        try{
            FileInputStream fis = new FileInputStream("E:\\Java\\Problem11.txt");
            Scanner scanner = new Scanner(fis);

            while (scanner.hasNext()){
                for(int i = 0; i < X; i ++){
                    for(int j = 0; j < Y; j++){
                        tablica[i][j] = Integer.valueOf(scanner.next());
                    }
                }
            }

            scanner.close();
            fis.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return tablica;
    }
}
