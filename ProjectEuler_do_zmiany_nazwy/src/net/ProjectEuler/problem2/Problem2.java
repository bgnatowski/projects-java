package net.ProjectEuler.problem2;

import java.util.ArrayList;
import java.util.Iterator;

public class Problem2 {
    private static final long UPPER_BORDER = 4_000_000;
    private static long suma;
    public static void main(String[] args) {
        ArrayList<Long> fibonaciList = znajdzCiag(UPPER_BORDER);
        ArrayList<Long> fibonaciParzysteLista  = wezParzyste(fibonaciList);
        sumuj(fibonaciParzysteLista);
        System.out.println(suma);
    }

    private static void sumuj(ArrayList<Long> fibonaciParzysteLista) {
        for(Long fibNumber : fibonaciParzysteLista){
            suma+=fibNumber;
        }
    }

    private static ArrayList<Long> wezParzyste(ArrayList<Long> fibonaciList) {
        Iterator<Long> it = fibonaciList.iterator();
        while (it.hasNext()){
            if(it.next()%2!=0){
                it.remove();
            }
        }
        //System.out.println(fibonaciList);
        return fibonaciList;
    }

    private static ArrayList<Long> znajdzCiag(long border) {
        ArrayList<Long> fibonaciNumbers = new ArrayList<>();
        fibonaciNumbers.add(1L);
        fibonaciNumbers.add(2L);
        for(int i = 1; i < border; i++){
            long fibNum = fibonaciNumbers.get(i-1) + fibonaciNumbers.get(i);
            if(fibNum>=border){
                break;
            }else{
                fibonaciNumbers.add(fibNum);
            }

        }
        //System.out.println(fibonaciNumbers);
        return fibonaciNumbers;
    }


}
