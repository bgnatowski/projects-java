package net.ProjectEuler.problem11;

import java.util.ArrayList;

import static net.ProjectEuler.problem11.Problem11.odczytajTabliceZPliku;
import static net.ProjectEuler.problem11.Problem11.wypiszTabliceDwuWymiarowa;

public class Problem11ZNeta {
    private static final int X = 20;
    private static final int Y = 20;

    public static void main(String[] args) {
        int[][] tablica = odczytajTabliceZPliku(X, Y);
        //wypiszTabliceDwuWymiarowa(X,Y, tablica);
        int maxProduct = znajdzMaxProduct(tablica);
        System.out.println(maxProduct);

    }

    private static int znajdzMaxProduct(int[][] tablica) {
        ArrayList<Integer> listaMaxow = new ArrayList<>();
        int diagonalProduct1 = 0;
        int horizontalProduct = 0;
        int diagonalProduct2 = 0;
        int verticalProduct = 0;
        int max = 0;
        for (int x = 0; x < tablica.length - 3; x++) {
            for (int y = 0; y < tablica.length - 3; y++) {
                verticalProduct = tablica[x][y] * tablica[x + 1][y] * tablica[x + 2][y] * tablica[x + 3][y];

                horizontalProduct = tablica[x][y] * tablica[x][y + 1] * tablica[x][y + 2] * tablica[x][y + 3];

                diagonalProduct1 = tablica[x][y] * tablica[x + 1][y + 1] * tablica[x + 2][y + 2] * tablica[x + 3][y + 3];


                listaMaxow.add(diagonalProduct1);
                listaMaxow.add(horizontalProduct);
                listaMaxow.add(verticalProduct);
                listaMaxow.add(max);
                max = listaMaxow.stream().max(Integer::compare).get();
            }
        }
        listaMaxow.clear();
        for (int x = 3; x < tablica.length - 3; x++) {
            for (int y = 3; y < tablica.length - 3; y++) {
                diagonalProduct2 = tablica[x][y] * tablica[x-1][y+1] * tablica[x-2][y+2] * tablica[x-3][y+3];
                listaMaxow.add(diagonalProduct2);
                listaMaxow.add(max);
                max = listaMaxow.stream().max(Integer::compare).get();
            }
        }
        return max;
    }
}
