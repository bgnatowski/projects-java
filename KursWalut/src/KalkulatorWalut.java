import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KalkulatorWalut extends JFrame implements ActionListener
{
	//tworzenie zmiennych
	JTextField fieldPLN, fieldPrzeliczone;
	JLabel labelPLN, labelNa, labelPrzeliczone;
	JButton buttonUSD, buttonEUR, buttonGBP, buttonJPY;
	double pieniadzePLN, przeliczonePLN;


	public KalkulatorWalut()
	{
	setTitle("Kalkulator PLN to INNE"); //tytuł na pasku windowsa
	setSize(600, 350); //rozmiar okna
	setLayout(null); //ukladanie od lewej do prawej
	setResizable(false); //nie mozna zmieniac wielkosci
		
	labelPLN = new JLabel("Podaj ile PLN chcesz zamienić:"); //napis
	labelPLN.setBounds(25,25,250,30); //miejsce napisu
	labelPLN.setFont(new Font("Arial", Font.BOLD, 16)); //czcionka i rozmiar
	add(labelPLN); //dodanie obiektu do okienka z aplikacja
		
	labelNa = new JLabel("Wybierz walute na jaką chcesz przeliczyć:");
	labelNa.setHorizontalAlignment(SwingConstants.CENTER); //wycentrowanie
	labelNa.setBounds(25,60,550,30);
	labelNa.setFont(new Font("Arial", Font.BOLD, 16)); //czcionka i rozmiar
	add(labelNa);
		
	fieldPLN = new JTextField(); //okienko na wpisanie wartosci PLN
	fieldPLN.setBounds(325,25,250,30);
	fieldPLN.setFont(new Font("Arial", Font.BOLD, 15)); //czcionka i rozmiar
	add(fieldPLN);
	
	//przyciski
	buttonUSD = new JButton("USD");
	buttonUSD.setBounds(25, 100, 100 , 60);
	add(buttonUSD);
	buttonUSD.addActionListener(this); //dodanie akcji na przycisku
		
	buttonEUR = new JButton("EUR");
	buttonEUR.setBounds(175, 100, 100 , 60);
	add(buttonEUR);
	buttonEUR.addActionListener(this);
		
	buttonGBP = new JButton("GBP");
	buttonGBP.setBounds(325, 100, 100 , 60);
	add(buttonGBP);
	buttonGBP.addActionListener(this);
	
	buttonJPY = new JButton("JPY");
	buttonJPY.setBounds(475, 100, 100 , 60);
	add(buttonJPY);
	buttonJPY.addActionListener(this);
		
	labelPrzeliczone = new JLabel("Przeliczona wartość PLN na wybraną walutę(kurs z dnia 08.06.20): ");
	labelPrzeliczone.setHorizontalAlignment(SwingConstants.CENTER);
	labelPrzeliczone.setFont(new Font("Arial", Font.BOLD, 15)); //czcionka i rozmiar
	labelPrzeliczone.setBounds(25,175,550,30);
	add(labelPrzeliczone);
	   
	fieldPrzeliczone = new JTextField(); //okienko w ktorym sie pojawi przeliczona wartosc
	fieldPrzeliczone.setBounds(20, 200, 550, 50);
	fieldPrzeliczone.setHorizontalAlignment(SwingConstants.CENTER);
	fieldPrzeliczone.setFont(new Font("Arial", Font.BOLD, 15));
	add(fieldPrzeliczone);
	}

	public static void main(String[] args)
	{
			
		KalkulatorWalut frame = new KalkulatorWalut();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Zdefiniowana ramka
		frame.setVisible(true); //wyswietla frame
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{  
		pieniadzePLN = Double.parseDouble(fieldPLN.getText()); //przechwytanie wartosci PLN do zmiennej typu double
		Object source = e.getSource(); //przechwytanie kliknietego obiektu
		if(source == buttonUSD) //intrukcje warunkowe sprawdzające jaki przycisk kliknięty
		{
			przeliczonePLN = pieniadzePLN * 0.26; //przeliczone po aktualnym kursie
			//wyswietlenie przeliczonej wartosci po danym kursie + zmiana double na string
			fieldPrzeliczone.setText("W przeliczeniu na USD bedziesz mieć: " + String.valueOf(przeliczonePLN) + "$");
		}
		else if(source == buttonEUR)
		{
			przeliczonePLN = pieniadzePLN * 0.23;
			fieldPrzeliczone.setText("W przeliczeniu na EUR bedziesz mieć: " + String.valueOf(przeliczonePLN)+ "€");
		}
		else if(source == buttonGBP)
		{
			przeliczonePLN = pieniadzePLN * 0.22;
			fieldPrzeliczone.setText("W przeliczeniu na GBP bedziesz mieć: " + String.valueOf(przeliczonePLN) + "£");
		}
		else if(source == buttonJPY)
		{
			przeliczonePLN = pieniadzePLN * 2.64;
			fieldPrzeliczone.setText("W przeliczeniu na JPY bedziesz mieć: " + String.valueOf(przeliczonePLN) + "¥");
		}
	}

}

