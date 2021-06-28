package net.ProjectEuler.problem17;

import net.ProjectEuler.Matematyczne.EnglishNumberToWords;

public class Problem17 {
    private static final int DO_ILU = 1000;
    public static void main(String[] args) {
        zacznijSzukacDoN(DO_ILU);
//        String slowo = EnglishNumberToWords.convert(342);
//        if(slowo.contains(" ")){
//            //System.out.println(slowa);
//            slowo = slowo.replace(" ", "");
//            //System.out.println(slowa + "\n");
//        }
//        System.out.println(slowo + " " + slowo.length());
    }

    private static void zacznijSzukacDoN(int N) {
        String slowa = "";
        for(int i = 1; i <= N; i++){
            String slowo = EnglishNumberToWords.convertLessThanOneThousand(i);
            if(slowo.contains(" ")){
                //System.out.println(slowa);
                slowo = slowo.replace(" ", "");
                //System.out.println(slowa + "\n");
            }
            //System.out.println(slowo);
            slowa = slowa.concat(slowo);
            System.out.println(i + " " + slowo + " " + slowo.length());
        }
        System.out.println("calosc: " + slowa + " \nilosc liter: " + slowa.length());

    }
}
