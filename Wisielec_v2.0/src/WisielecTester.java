public class WisielecTester {
    public static void main(String[] args){
        Wisielec wisielecTester = new Wisielec();

        for(int i = 0; i < 3; i++){
            wisielecTester.utworzGre();
            System.out.println("Wylosowane haslo = " + wisielecTester.getHaslo());
            System.out.println("Wylosowane haslo = " + wisielecTester.getHasloZakreskowane());
            System.out.println("Wylosowana kategoria = " + wisielecTester.getKategoria());
            System.out.println("Wylosowane dlugoscHasla = " + wisielecTester.getDlugoscHasla());
        }
    }
}
