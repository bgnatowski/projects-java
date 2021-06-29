package net.ProjectEuler.problem14;

import java.util.*;

import static net.ProjectEuler.OtherFunctions.Even.isEven;

public class LongestCollatzSequence {
    private static final int N = 1_000_000;
    public static void main(String[] args) {
        zacznij();
    }

    private static void zacznij() {
        int odIlu = N;
        LinkedHashMap<Long, Long> mapaWielkosciSekwencji = new LinkedHashMap<>();

        for (long i = 1L; i <= odIlu; i++){
            mapaWielkosciSekwencji.put(i, znajdzSizeSekwencjiLiczby(i));
        }

        long max = znajdzNajwiekszaZMapy(mapaWielkosciSekwencji);
        System.out.println(max);
    }

    private static long znajdzNajwiekszaZMapy(LinkedHashMap<Long, Long> mapaWielkosciSekwencji) {
        long maxStartowe = 0;
        long maxSize = 0;
        for(Map.Entry<Long, Long> para : mapaWielkosciSekwencji.entrySet()){
            long tmpMaxStartowe = para.getKey();
            long tmpMaxSize = para.getValue();

            if(maxSize < tmpMaxSize){
                maxSize = tmpMaxSize;
                maxStartowe = tmpMaxStartowe;
            }
        }

        return maxStartowe;
    }

    private static long znajdzSizeSekwencjiLiczby(long n) {
        ArrayList<Long> sekwencja = new ArrayList<>();
        sekwencja.add(n);
        while (n != 1){
            if(isEven(n)){
                n = n/2;
                sekwencja.add(n);
            }else{
                n = 3*n+1;
                sekwencja.add(n);
            }
        }

        return sekwencja.size();
    }

}
