package net.ProjectEuler.problem17;

import net.ProjectEuler.OtherFunctions.EnglishNumberToWords;

public class NumberLetterCounts {
    private static final int DO_ILU = 1000;
    public static void main(String[] args) {
        zacznijSzukacDoN(DO_ILU);
    }

    private static void zacznijSzukacDoN(int N) {
        String slowa = "";
        for(int i = 1; i <= N; i++){
            String slowo = EnglishNumberToWords.convertLessThanOneThousand(i);
            if(slowo.contains(" ")){
                slowo = slowo.replace(" ", "");
            }
            slowa = slowa.concat(slowo);
            System.out.println(i + " " + slowo + " " + slowo.length());
        }
        System.out.println("calosc: " + slowa + " \nilosc liter: " + slowa.length());

    }
}
