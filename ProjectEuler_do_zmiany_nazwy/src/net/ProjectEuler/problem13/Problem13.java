package net.ProjectEuler.problem13;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Problem13 {
    private static final int N = 10;
    public static void main(String[] args) {
        BigDecimal suma = odczytajIZsumujLiczbeZPliku("E:\\Java\\Problem13.txt");
        String pierwszeDziesiecCyfr = znajdzPierwszeNCyfr(N, suma);
        System.out.println(pierwszeDziesiecCyfr);
    }

    private static String znajdzPierwszeNCyfr(int n, BigDecimal suma) {
        String liczba = String.valueOf(suma);
        return liczba.substring(0, n);
    }

    private static BigDecimal odczytajIZsumujLiczbeZPliku(String file) {
        BigDecimal bigDecimalSum = new BigDecimal(0);
        try{
            FileInputStream fis = new FileInputStream(file);
            Scanner scanner = new Scanner(fis);
            while(scanner.hasNext()){
                bigDecimalSum = bigDecimalSum.add(new BigDecimal(scanner.nextLine()));
            }
            scanner.close();
            fis.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return bigDecimalSum;
    }


}
