import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Wisielec implements Serializable {
    private int dlugoscHasla;
    private String haslo, hasloZakreskowane, kategoria;
    private ArrayList<String> tablicaHaselIKategorii = new ArrayList<String>();
    private String plikHaselIKategorii = "resource\\tajneHasla.txt";

    public void utworzGre() {
        ustawHasloIKategorie(); //ustawienie hasla i inicjacje zmiennych
        zakreskujHaslo(); //zakreskowanie hasla
    }

    public String getHaslo(){
        return haslo;
    }

    public String getKategoria(){
        return kategoria;
    }
    public String getHasloZakreskowane(){
        return hasloZakreskowane;
    }

    public int getDlugoscHasla(){
        return dlugoscHasla;
    }


    public void ustawHasloIKategorie() {
        odczytHaselIKategori();

        //losowanie hasla i kategorii
        Random rand1 = new Random();	//losowanie 0-6, czyli 7 kategorii
        int randomWiersz = rand1.nextInt(tablicaHaselIKategorii.size());
        String odczytanyWiersz[] = tablicaHaselIKategorii.get(randomWiersz).split("/");

        //ustawienie hasla i kategorii
        kategoria = odczytanyWiersz[0].toUpperCase();
        haslo = odczytanyWiersz[1].toUpperCase();
        dlugoscHasla = haslo.length(); //inicjacja dlugosci hasla
    }

    private void odczytHaselIKategori() {
        //ArrayList<String> tablicaHaselTymczasowa = new ArrayList<String>();
        String wiersz = null;
        try {
            BufferedReader is = new BufferedReader(new FileReader(new File(plikHaselIKategorii)));
            while((wiersz = is.readLine()) != null) {
                tablicaHaselIKategorii.add(wiersz);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void zakreskujHaslo() {
        //podmiana hasła na zakreskowane haslo
        hasloZakreskowane = "";
        for(int i=0; i<dlugoscHasla;i++) { //prosta petla znak po znaku hasla
            if (haslo.charAt(i) == ' ') { //jesli w hasle jest spacja to zastap na spacje
                hasloZakreskowane += " ";
            } else if (haslo.charAt(i) == ',') { //jeśli jest przecinek to zastąp na przecinkiem
                hasloZakreskowane += ", ";
            } else {
                hasloZakreskowane += "-"; //wszystko inne zastap myslnikiem
            }
        }
    }

    public boolean sprawdzHaslo(char znak)
    {
        boolean czyTrafiona = false; //poczatkowo zainicjowane trafienie na falsz
        for(int i = 0;i<dlugoscHasla;i++) //petla sprawdzajaca czy w hasle znajduje sie litera i podmienia - na litere
        {
            if(haslo.charAt(i) == znak) //jesli na pozycji znajduje sie litera
            {
                hasloZakreskowane = hasloZakreskowane.substring(0, i) + znak + hasloZakreskowane.substring(i+1);
                czyTrafiona = true; //zmien trafiona na true
            }
        }
        return czyTrafiona; //zwroc zmienna zawierajaca wynik trafienia
    }

    public boolean sprawdzHaslo(String hasloCale){
        boolean czyHasloSieZgadza = false;
        if (haslo.equals(hasloCale)){
            czyHasloSieZgadza = true;
            hasloZakreskowane = hasloCale;
        }
        return czyHasloSieZgadza;
    }

}
