import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

//WISIELEC - GRA W ZGADYWANIE BARTOSZ GNATOWSKI
public class WisielecGra implements Serializable {
    //SKLADOWE STATYCZNE
    private final static Wisielec wisielec = new Wisielec();
    private final static JButton przyciskNextRundy = new JButton("KLIKNIJ ABY GRAC DALEJ");
    private final static char[] alfabet = {'A', 'Ą', 'B', 'C', 'Ć', 'D', 'E', 'Ę', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'Ł', 'M', 'N', 'Ń', 'O', 'Ó', 'P', 'Q', 'R', 'S', 'Ś', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ź', 'Ż'};

    //SKLADOWE GLOWNE
    private boolean czyTrafiono;
    private int iloscGraczy = 0;
    private int aktualnyGracz = 0;
    private int pokazowyAktualnyGracz = 1;
    private int blednychTrafien, zyc, koniec;
    private final ArrayList<JButton> listaPrzyciskow = new ArrayList<>();
    private final Font czcionkaPrzycisk = new Font("DejaVu Sans Mono", Font.BOLD, 22);

    private final HashMap<Integer, JLabel> mapaObrazkowZyc = new HashMap<>();
    private final HashMap<Integer, Integer> mapaIlosciZyc = new HashMap<>();

    //SCIEZKI DZWIEKOWE
//    private static String yesSound = "resouąrce\\yes.wav";
//    private static String noSound = "resource\\no.wav";
//    private static String deathSound = "resource\\death.wav";
//    private static String victorySound = "resource\\victory.wav";
//    private static String musicTheme = "resource\\loss.wav";

    //KOMPONENTY SWINGA
    private JFrame frame;
    private JPanel panelGorny ,panelHasla, panelKategorii, panelObrazka, panelWynikow, panelKlawiatury, panelWpisaniaHasla, panelPrzyciwkow1,panelPrzyciwkow2, panelPrzyciwkow3, panelPrawy, panelPrzyciskuHasla, panelKoncaGry;
    private JLabel iloscGraczyLabel;
    private JLabel hasloLabel;
    private JLabel kategoriaLabel;
    private JLabel obrazSkuchyLabel;
    private final JLabel[] obrazGraczaLabel = new JLabel[4];
    private JButton przycisk, przyciskHasla;
    private final ImageIcon[] zyciaGracza = new ImageIcon[4];
    private JTextField wpisaneHaslo;

    public WisielecGra() {
    }

    public static void main(String[] args){
        new WisielecGra().przygotujGre(); //uruchomienie gre
    }

    private void przygotujGre(){ //wywolanie modułów gry
        wisielec.utworzGre();
        prepareGUI(); //przygotowanie okna
        addComponents(); //dodanie obiektow na ekran gry
        guiRestartPassword();
        repaintAll(); //odswiezenie dodanych okienek(bez tego nie zawsze wyswietlalo poprawnie)
    }

    private void prepareGUI(){ //przygotowanie GUI
        JLabel backgroundLabel;
        ImageIcon tlo = new ImageIcon("img\\tlo.jpg");
        backgroundLabel = new JLabel("", tlo, JLabel.CENTER);
        backgroundLabel.setBounds(0,0,1200,800);

        frame = new JFrame("Wisielec V2");	//okienko gry
        ustawMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720); //rozmiar okna
//        try {
//            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("img\\tlo.jpg"))))); //ustawienie obrazka w tle
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        frame.setResizable(false); //nie da sie rozciagac
//        frame.setContentPane(backgroundLabel);
        frame.getContentPane().setBackground(Color.black); //gdyby problem z odczytaniem obrazka to zawsze czarne tło
        frame.setVisible(true);	//widzialnosc okna

        addPanels();
    }

    private void ustawMenu(){
        JMenuBar menuGlowne = new JMenuBar();
        JMenu menuGra = new JMenu("Gra");
        JMenu menuZapiszWczytaj = new JMenu("Zapisz/Wczytaj");
        JMenuItem zapiszGreMenu = new JMenuItem("Zapisz grę");
        JMenuItem wczytajGreMenu = new JMenuItem("Wczytaj grę");
        JMenuItem dodajGraczaMenu = new JMenuItem("Dodaj gracza(max 4)");
        JMenuItem usunGraczaMenu = new JMenuItem("Usuń gracza");
        JMenuItem zrestaGreMenu = new JMenuItem("Zrestartuj grę");

        zapiszGreMenu.addActionListener(new MojZapiszGreListener());
        wczytajGreMenu.addActionListener(new MojWczytajGreListener());
        dodajGraczaMenu.addActionListener(new MojDodajGraczaListener());
        usunGraczaMenu.addActionListener(new MojUsunGraczaListener());
        zrestaGreMenu.addActionListener(new MojZrestartujGreListener());

        menuZapiszWczytaj.add(zapiszGreMenu);
        menuZapiszWczytaj.add(wczytajGreMenu);
        menuGra.add(dodajGraczaMenu);
        menuGra.add(usunGraczaMenu);
        menuGra.add(zrestaGreMenu);
        menuGlowne.add(menuZapiszWczytaj);
        menuGlowne.add(menuGra);

        frame.setJMenuBar(menuGlowne);
    }

    private void addPanels() {
        panelGorny = new JPanel();
        panelGorny.setLayout(new BoxLayout(panelGorny, BoxLayout.Y_AXIS));
        panelGorny.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelGorny.setBackground(Color.black);

        panelHasla = new JPanel();
        panelHasla.setLayout(new GridBagLayout());
        panelHasla.setBackground(Color.black);

        panelKategorii = new JPanel();
        panelKategorii.setLayout(new GridBagLayout());
        panelKategorii.setBackground(Color.black);

        panelGorny.add(panelHasla);
        panelGorny.add(panelKategorii);

        panelWynikow = new JPanel();
        panelWynikow.setPreferredSize(new Dimension(409,41));
        panelWynikow.setLayout(new GridLayout(5,2, 5, 5));
        panelWynikow.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelWynikow.setBackground(Color.black);

        panelObrazka = new JPanel();
        panelObrazka.setLayout(new GridBagLayout());
        panelObrazka.setBackground(Color.black);

        panelKlawiatury = new JPanel();
        panelKlawiatury.setLayout(new BoxLayout(panelKlawiatury, BoxLayout.Y_AXIS));
        panelKlawiatury.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        panelKlawiatury.setBackground(Color.black);

        panelPrzyciwkow1 = new JPanel();
        panelPrzyciwkow2 = new JPanel();
        panelPrzyciwkow3 = new JPanel();

        panelPrzyciwkow1.setBackground(Color.black);
        panelPrzyciwkow2.setBackground(Color.black);
        panelPrzyciwkow3.setBackground(Color.black);

        panelKlawiatury.add(panelPrzyciwkow1);
        panelKlawiatury.add(panelPrzyciwkow2);
        panelKlawiatury.add(panelPrzyciwkow3);

        panelWpisaniaHasla = new JPanel();
        panelWpisaniaHasla.setBackground(Color.black);

        panelPrzyciskuHasla = new JPanel();
        panelPrzyciskuHasla.setBackground(Color.black);

        panelPrawy = new JPanel();
        panelPrawy.setPreferredSize(new Dimension(409,41));
        panelPrawy.setLayout(new BoxLayout(panelPrawy, BoxLayout.Y_AXIS));
        panelPrawy.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panelPrawy.setBackground(Color.black);

        panelKoncaGry = new JPanel();
        panelKoncaGry.setLayout(new GridBagLayout());
        panelKoncaGry.setBackground(Color.black);

        panelPrawy.add(panelWpisaniaHasla);
        panelPrawy.add(panelPrzyciskuHasla);
        panelPrawy.add(panelKoncaGry);


        frame.add(BorderLayout.WEST, panelWynikow);
        frame.add(BorderLayout.EAST, panelPrawy);
        frame.add(BorderLayout.NORTH, panelGorny);
        frame.add(BorderLayout.CENTER,panelObrazka);
        frame.add(BorderLayout.SOUTH, panelKlawiatury);
    }

    private void addComponents() {
        JLabel napisIloscGraczyLabel = new JLabel("Runda gracza: ", JLabel.CENTER);
        napisIloscGraczyLabel.setFont(czcionkaPrzycisk);
        napisIloscGraczyLabel.setForeground(Color.RED);

        iloscGraczyLabel = new JLabel(String.valueOf(pokazowyAktualnyGracz), JLabel.CENTER);
        iloscGraczyLabel.setFont(czcionkaPrzycisk);
        iloscGraczyLabel.setForeground(Color.RED);

        panelWynikow.add(napisIloscGraczyLabel);
        panelWynikow.add(iloscGraczyLabel);
        inicjujObrazZycGracza();
        ustawObrazekGraczaIZyc();


        hasloLabel = new JLabel();
        hasloLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        hasloLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 30));
        hasloLabel.setForeground(Color.WHITE);

        kategoriaLabel = new JLabel();
        kategoriaLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        kategoriaLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
        kategoriaLabel.setForeground(Color.WHITE);

        panelHasla.add(hasloLabel);
        panelKategorii.add(kategoriaLabel);

        wpisaneHaslo = new JTextField(40);
        wpisaneHaslo.setHorizontalAlignment(SwingConstants.CENTER);
        wpisaneHaslo.setEditable(true);
        wpisaneHaslo.setFont(czcionkaPrzycisk);
        wpisaneHaslo.setText("Strzelasz? Wpisz hasło tutaj!");
        panelWpisaniaHasla.add(wpisaneHaslo);

        przyciskHasla = new JButton("Prześlij");
        przyciskHasla.setFont(czcionkaPrzycisk);
        przyciskHasla.setEnabled(true);
        przyciskHasla.addActionListener(new MojSprawdzWpisaneHasloListener());
        panelPrzyciskuHasla.add(przyciskHasla);

        obrazSkuchyLabel = new JLabel();
        ustawObrazekSkuchy();
        utworzPrzyciskiIDodaj();

        przyciskNextRundy.addActionListener(new MojNextRundyPrzyciskListener());

        frame.pack();
    }

    private void ustawObrazekSkuchy() {
        ImageIcon obrazekSkuchy = new ImageIcon("img\\s" + blednychTrafien + ".jpg");
        obrazSkuchyLabel.setBorder(BorderFactory.createEmptyBorder(15,15,60,15));
        obrazSkuchyLabel.setIcon(obrazekSkuchy);
        panelObrazka.add(obrazSkuchyLabel);
    }

    private void inicjujObrazZycGracza() {
        for(int i = 0; i < 4; i++){
            zyciaGracza[i] = new ImageIcon("img\\zycia" + i + ".jpg");
        }
    }

    private void ustawObrazekGraczaIZyc() {
        for(int i = 0; i < 4 ; i++){
            ImageIcon obrazekGracza = new ImageIcon("img\\gracz" + i + ".jpg");
            obrazGraczaLabel[i] = new JLabel();
            obrazGraczaLabel[i].setHorizontalAlignment(JLabel.CENTER);
            obrazGraczaLabel[i].setIcon(obrazekGracza);
            JLabel zyciaGraczaLabel = new JLabel();
            zyciaGraczaLabel.setIcon(zyciaGracza[0]);
            if (i>0){
                obrazGraczaLabel[i].setVisible(false);
                zyciaGraczaLabel.setVisible(false);
            }
            mapaIlosciZyc.put(i, zyc);
            mapaObrazkowZyc.put(i, zyciaGraczaLabel);
            panelWynikow.add(obrazGraczaLabel[i]);
            panelWynikow.add(zyciaGraczaLabel);
        }
    }

    private void utworzPrzyciskiIDodaj() {
        for(int i = 0; i < 35; i++ ){
            przycisk = new JButton(String.valueOf(alfabet[i]));
            przycisk.setEnabled(true);
            przycisk.setFont(czcionkaPrzycisk);
            przycisk.setSize(50,50);
            listaPrzyciskow.add(przycisk);
            if(i<=13){
                panelPrzyciwkow1.add(przycisk);
            } else if (i<=26){
                panelPrzyciwkow2.add(przycisk);
            } else {
                panelPrzyciwkow3.add(przycisk);
            }
            przycisk.addActionListener(new MojPrzyciskListener());
        }
    }

    private void guiRestartPassword() {
        blednychTrafien = 0;
        przyciskNextRundy.setVisible(false);
        hasloLabel.setForeground(Color.white);
        hasloLabel.setBorder(null);
        hasloLabel.setText(wisielec.getHasloZakreskowane());
        kategoriaLabel.setText("Kategoria: " + wisielec.getKategoria());
        ustawObrazekSkuchy();
    }

    private void sprawdzCzyTrafiono() {
        if (czyTrafiono) {
            hasloLabel.setText(wisielec.getHasloZakreskowane()); //wyswietl podmienione haslo
            przycisk.setBackground(Color.green);
        }
        else {
            blednychTrafien++;
            przycisk.setBackground(Color.red);
            ustawObrazekSkuchy();
        }
    }

    private char StringNaChar(String znakString) {
        for (char zmiana : alfabet) {
            if(String.valueOf(zmiana).equals(znakString)) {
                return zmiana;
            }
        }
        return 0;
    }

    private void sprawdzKoniec() {
        if(wisielec.getHasloZakreskowane().equals(wisielec.getHaslo())) {
            String imgURL = "img\\wygrana.jpg"; //zmienna przechowujaca obrazek wygranej
            ImageIcon obraz = new ImageIcon(imgURL); //nowy obrazek w zmiennej
            obrazSkuchyLabel.setIcon(obraz); //ustawienie obrazka
            hasloLabel.setForeground(Color.green); //kolor czcionki hasla na zielony
            hasloLabel.setBorder(new LineBorder(Color.green,1)); //kolor obramowania
            hasloLabel.setText(wisielec.getHaslo()); //pokazanie hasla
            wylaczPrzyciski(); //wylaczenie przyciskow
            pokazRestartGui();
            wpisaneHaslo.setEditable(false);
        }
        if(blednychTrafien>=8) //jesli bledow wiecej niz 8 to
        {
            hasloLabel.setForeground(Color.red); //ustaw kolor hasla na czerwony
            hasloLabel.setBorder(new LineBorder(Color.red,1)); //kolor obramowania
            hasloLabel.setText(wisielec.getHaslo()); //pokaz haslo
            wylaczPrzyciski(); //wylacz przyciski
            //playSound(deathSound); //zagraj dzwiek przegranej
            pokazRestartGui();
            obierzZycie(aktualnyGracz);
            wpisaneHaslo.setEditable(false);
        }
    }

    private void pokazRestartGui() {
        przyciskNextRundy.setEnabled(true);
        przyciskNextRundy.setVisible(true);
        przyciskNextRundy.setBackground(Color.orange);
        przyciskNextRundy.setForeground(Color.black);
        przyciskNextRundy.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 20));
        panelKoncaGry.add(przyciskNextRundy);
    }

    private void restartujCalaGre() {
        aktualnyGracz = 0;
        iloscGraczy = 0;
        pokazowyAktualnyGracz = 1;
        wisielec.utworzGre();
        wpisaneHaslo.setEditable(true);
        wpisaneHaslo.setText("");
        iloscGraczyLabel.setText(String.valueOf(pokazowyAktualnyGracz));
        usunGraczy();
        odbudujObrzyZycGracza();
        guiRestartPassword();
        wlaczPrzyciski();
        repaintAll();
    }

    private void odbudujObrzyZycGracza() {
        zyc = 0;
        for(int i = 0; i < 4; i++){
            mapaObrazkowZyc.get(i).setIcon(zyciaGracza[0]);
            mapaIlosciZyc.replace(i, zyc);
        }
    }

    private void usunGraczy() {
        for(int i = 3; i>0;i--){
            obrazGraczaLabel[i].setVisible(false);
            mapaObrazkowZyc.get(i).setVisible(false);
        }

    }

    private void restartujGre() {
        wisielec.utworzGre();
        wpisaneHaslo.setEditable(true);
        wpisaneHaslo.setText("");
        iloscGraczyLabel.setText(String.valueOf(pokazowyAktualnyGracz));
        guiRestartPassword();
        wlaczPrzyciski();
        repaintAll();
    }

    private void sprawdzIleGraczy() {
        aktualnyGracz++;
        pokazowyAktualnyGracz++;
        if(aktualnyGracz>iloscGraczy){
            aktualnyGracz = 0;
            pokazowyAktualnyGracz = 1;
        }
        if(mapaIlosciZyc.get(aktualnyGracz) == 3){
            aktualnyGracz++;
            pokazowyAktualnyGracz++;
            koniec++;
            if(iloscGraczy==0) restartujCalaGre();
            if(koniec == iloscGraczy) restartujCalaGre();
        }
    }


    private void obierzZycie(int komuOdebrac) {
        int iloscZycAktualnie = mapaIlosciZyc.get(komuOdebrac);
        iloscZycAktualnie++;
        if(iloscZycAktualnie < 4){
            mapaObrazkowZyc.get(komuOdebrac).setIcon(zyciaGracza[iloscZycAktualnie]);
        }
        zyc = iloscZycAktualnie;
        mapaIlosciZyc.replace(komuOdebrac, zyc);
    }

    private void wylaczPrzyciski(){
        for (JButton przyciskDoWylaczenia : listaPrzyciskow) {
            przyciskDoWylaczenia.setEnabled(false);
        }
        przyciskHasla.setEnabled(false);
    }

    private void wlaczPrzyciski(){
        for (JButton przyciskDoWylaczenia : listaPrzyciskow) {
            przyciskDoWylaczenia.setEnabled(true);
            przyciskDoWylaczenia.setBackground(Color.white);
        }
        przyciskHasla.setEnabled(true);
    }

    private void repaintAll() {
        panelGorny.repaint();
        panelHasla.repaint();
        panelKategorii.repaint();
        panelObrazka.repaint();
        panelWynikow.repaint();
        panelKlawiatury.repaint();
        panelWpisaniaHasla.repaint();
        panelPrzyciwkow1.repaint();
        panelPrzyciwkow2.repaint();
        panelPrzyciwkow3.repaint();
        panelPrawy.repaint();
        panelPrzyciskuHasla.repaint();
        panelKoncaGry.repaint();
        frame.repaint();
    }

    //KLASY WEWNETRZNE ( ACTION LISTENERY )
    //KLASY MENU
    private class MojZapiszGreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    private class MojWczytajGreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }
    private class MojDodajGraczaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (iloscGraczy<3){
                iloscGraczy++;
                obrazGraczaLabel[iloscGraczy].setVisible(true);
                mapaObrazkowZyc.get(iloscGraczy).setVisible(true);
                odbudujObrzyZycGracza();
            }
            else{
                wyswietlErrorGraczy();
            }
        }
        private void wyswietlErrorGraczy(){
            JDialog uwaga = new JDialog();
            JLabel textUwaga = new JLabel("Nie mozesz dodać więcej graczy!!!");
            textUwaga.setHorizontalAlignment(SwingConstants.CENTER);
            textUwaga.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
            uwaga.add(textUwaga);
            uwaga.setSize(350,80);
            uwaga.setVisible(true);
            uwaga.repaint();
        }
    }
    private class MojUsunGraczaListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            if (iloscGraczy>0){
                obrazGraczaLabel[iloscGraczy].setVisible(false);
                mapaObrazkowZyc.get(iloscGraczy).setVisible(false);
                iloscGraczy--;
                odbudujObrzyZycGracza();
            }
            else{
                wyswietlErrorGraczy();
            }
        }
        private void wyswietlErrorGraczy(){
            JDialog uwaga = new JDialog();
            JLabel textUwaga = new JLabel("Nie mozesz odjąć więcej graczy!!!");
            textUwaga.setHorizontalAlignment(SwingConstants.CENTER);
            textUwaga.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 15));
            textUwaga.setForeground(Color.RED);
            uwaga.add(textUwaga);
            uwaga.setSize(350,80);
            uwaga.setVisible(true);
            uwaga.repaint();
        }
    }
    private class MojZrestartujGreListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            restartujCalaGre();
        }

    }

    //KLASY GRY
    private class MojPrzyciskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            przycisk = (JButton) e.getSource();
            przycisk.setEnabled(false);

            String znakString = przycisk.getText();
            char znak = StringNaChar(znakString);

            czyTrafiono = wisielec.sprawdzHaslo(znak);
            sprawdzCzyTrafiono();
            sprawdzKoniec();
        }

    }
    private class MojSprawdzWpisaneHasloListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            czyTrafiono = wisielec.sprawdzHaslo(wpisaneHaslo.getText().toUpperCase());
            if (!czyTrafiono) {
                wpisaneHaslo.setText("");
                blednychTrafien++;
                ustawObrazekSkuchy();
            }
            sprawdzKoniec();
        }
    }
    private class MojNextRundyPrzyciskListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sprawdzIleGraczy();
            restartujGre();
        }
    }
}
