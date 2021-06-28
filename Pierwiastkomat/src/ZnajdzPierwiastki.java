import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ZnajdzPierwiastki extends JFrame implements ActionListener
{
	//zmienne których używam w aplikacji
	JTextField aField, bField, cField, deltaField, x1Field, x2Field; //zmienne typu JTextField
	JLabel napisGlowny, napisA, napisB, napisC, napisDelta; //zmienne typu JLabel
	JButton obliczButton; //zmienna typu JButton
	double a, b, c, delta, pierDelta, x1, x2; //zmienne typu double
	
	public ZnajdzPierwiastki() //ustawianie wszystkich obiektow aplikacji(ramki, textfieldy, jlabele, buttony)
	{
		setTitle("Pierwiastkomat"); //tytuł okienka
		setSize(800, 450); //rozmiar okienka
		setLayout(null); //ukladanie od lewej do prawej
		setResizable(false); //nie mozna zmieniac wielkosci onka
		
		napisGlowny = new JLabel("Licze pierwiastki równania ax^2+bx+c."); //napis na środku
		napisGlowny.setFont(new Font("Times New Roman", Font.BOLD, 35)); //czcionka, jej rozmiar i wyboldowanie
		napisGlowny.setHorizontalAlignment(SwingConstants.CENTER); //wycentrowanie
		napisGlowny.setBounds(80, 20, 640, 50); //pozycja obiektu w ramce aplikacji
		add(napisGlowny); //dodanie obiektu do ramki
		
		napisA = new JLabel("a:");
		napisA.setFont(new Font("Times New Roman", Font.BOLD, 30));
		napisA.setHorizontalAlignment(SwingConstants.CENTER);
		napisA.setBounds(80, 90, 40, 40);
		add(napisA);
		
		aField = new JTextField();
		aField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		aField.setBounds(120, 90, 150, 40);
		add(aField);
		
		napisB = new JLabel("b:");
		napisB.setFont(new Font("Times New Roman", Font.BOLD, 30));
		napisB.setHorizontalAlignment(SwingConstants.CENTER);
		napisB.setBounds(270, 90, 40, 40);
		add(napisB);
		
		bField = new JTextField();
		bField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		bField.setBounds(310, 90, 150, 40);
		add(bField);
		
		napisC = new JLabel("c:");
		napisC.setFont(new Font("Times New Roman", Font.BOLD, 30));
		napisC.setHorizontalAlignment(SwingConstants.CENTER);
		napisC.setBounds(460, 90, 40, 40);
		add(napisC);
		
		cField = new JTextField();
		cField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		cField.setBounds(500, 90, 150, 40);
		add(cField);
		
		obliczButton = new JButton("Znajdz pierwiastki"); //przycisk z napisem "znajdz pierwiastki"
		obliczButton.setHorizontalAlignment(SwingConstants.CENTER);
		obliczButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		obliczButton.setBounds(250, 150, 300, 40);
		obliczButton.addActionListener(this); //dodanie akcji na przyciusku(ze jak się kliknie to coś się wykona)
		add(obliczButton);
		
		deltaField = new JTextField();
		deltaField.setBounds(80,220,640,50);
		deltaField.setHorizontalAlignment(SwingConstants.CENTER);
		deltaField.setFont(new Font("Times New Roman", Font.BOLD, 30));
		deltaField.setVisible(false); //narazie ustawione niewidzialne, pojawia sie odpowiednie po obliczeniu delty i kliknieciu przycisku
		add(deltaField);
		
		x1Field = new JTextField();
		x1Field.setBounds(80,280,640,50);
		x1Field.setHorizontalAlignment(SwingConstants.CENTER);
		x1Field.setFont(new Font("Times New Roman", Font.BOLD, 30));
		x1Field.setVisible(false);
		add(x1Field);
		
		x2Field = new JTextField();
		x2Field.setBounds(80,340,640,50);
		x2Field.setHorizontalAlignment(SwingConstants.CENTER);
		x2Field.setFont(new Font("Times New Roman", Font.BOLD, 30));
		x2Field.setVisible(false);
		add(x2Field);
	}
	
	public static void main(String[] args)
	{	
		ZnajdzPierwiastki frame = new ZnajdzPierwiastki();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // z pomocą tej metody ustalamy, co dzieje się z naszą aplikacją
		//po wciśnięciu krzyżyka w prawym górnym rogu. Z argumentem EXIT_ON_CLOS ustalamy, że wraz z naciśnięciem krzyżyka program się zamyka
		frame.setVisible(true); //widzialnosc aplikacji
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource(); //przechwytanie kliknietego przycisku
		if(source == obliczButton) //instrukcja co jeśli kliknięty przycisk 
		{
			x1Field.setVisible(false); //to ustawione na niewidzialne aby ładniej wyswietlac kolejne wyniki
			x2Field.setVisible(false);
			deltaField.setVisible(false);
			
			a = Double.parseDouble(aField.getText()); //pobranie wartosci typu double z obiektu typu aField
			b = Double.parseDouble(bField.getText()); //to samo dla b
			c = Double.parseDouble(cField.getText()); //to samo dla c
			delta = (b*b) - (4*a*c); //obliczenie delty
			pierDelta = Math.sqrt(delta); //obliczenie pierwiastka z delty
			
			//obliczenie pierwiastków trójmianu kwadratowego
			x1 = (-b - pierDelta) / (2*a); 
			x2 = (-b + pierDelta) / (2*a);
			//instrukcje warunkowe sprawdzające wartość delty
			if(delta > 0) 
			{
				deltaField.setVisible(true); //wyswielenie deltaField w ktorym bedzie napisana wartosc delty
				deltaField.setText("Δ = " + delta); //napisanie wartosci delty
				x1Field.setVisible(true); //wyswietlenie x1Field
				x1Field.setText("x1 = " + x1); //napisanie w nim wartosci x1
				x2Field.setVisible(true); //wyswietlenie x2Field
				x2Field.setText("x1 = " + x2); //napisanie w nim wartosci x2

			}
			else if(delta == 0) 
			{
				deltaField.setVisible(true);
				deltaField.setText("Δ = " + delta);
				x1Field.setVisible(true); //tu wystarczy wyswietlic x1 bo x1=x2
				x1Field.setText("x1 = x2 = " + x1);
			}
			else if(delta < 0) 
			{
				deltaField.setVisible(true);
				deltaField.setText("Δ = " + delta);
				x1Field.setVisible(true);
				x1Field.setText("Delta < 0. Brak rozwiązań rzeczywistych");
			}
		}
	}
	
}
