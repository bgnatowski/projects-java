package net.ProjectEuler.problem9;

public class Problem9 {
    private static final int ile = 1000;
    public static void main(String[] args) {
        znajdzTryplet(ile);
    }

    private static void znajdzTryplet(int ile) {
       for(int a = 1; a <= ile/3; a++){
           for(int b = a+1; b <= ile/2; b++){
               int c = ile - a - b;
               if((a*a + b*b == c*c)){
                   System.out.println(a*b*c);
                }

           }
       }
    }
}
