import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PrzelicznikDlugosci extends JFrame implements ActionListener{
	//tworze zmienne
	JTextField ileField, odpField;
	JLabel naLabel, ileLabel, przeliczoneLabel;
	JButton calButton, stopaButton, jardButton, milaButton;
	double metry, przeliczone;
	
	public PrzelicznikDlugosci(){
	setTitle("Przelicz m na inne jednostki!"); //ustalam tytuł okienka
	setSize(550, 450); //rozmiar okienka
	setLayout(null); //ukladanie od lewej do prawej
	setResizable(false); //nie mozna zmieniac wielkosci
	
	ileLabel = new JLabel("Ile metrów:"); //label z napisem "ile metrow"
	ileLabel.setBounds(30,30,120,40); //położenie obiektu ileLabel
	ileLabel.setFont(new Font("Times New Roman", Font.BOLD, 22)); //ustawienie czcionki i jej rozmiaru
	add(ileLabel); //dodanie tego obiektu do okienka z moja aplikacja
	
	naLabel = new JLabel("Wybierz na:");
	naLabel.setBounds(30,80,120,40);
	naLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
	add(naLabel);
	
	przeliczoneLabel = new JLabel("Przeliczona wartość:");
	przeliczoneLabel.setBounds(255,180,200,40); 
	przeliczoneLabel.setFont(new Font("Times New Roman", Font.BOLD, 22)); 
	add(przeliczoneLabel);
	
	ileField = new JTextField(); //miejsce na wpisanie "ile metrów"
	ileField.setBounds(160,30,350,40);
	ileField.setFont(new Font("Times New Roman", Font.BOLD, 22));
	add(ileField);
	
	odpField = new JTextField(); //miejsce w ktorym pojawi sie przeliczona wartość
	odpField.setBounds(220,250,260,40);
	odpField.setFont(new Font("Times New Roman", Font.BOLD, 22));
	add(odpField);
	
	//przyciski
	calButton = new JButton("cale (\")");
	calButton.setBounds(30, 150, 120 , 50);
	add(calButton);
	calButton.addActionListener(this); //dodanie akcji przy wcisnieciu przycisku
	
	stopaButton = new JButton("stopa (ft)");
	stopaButton.setBounds(30, 210, 120 , 50);
	add(stopaButton);
	stopaButton.addActionListener(this);
	
	jardButton = new JButton("jard (yd)");
	jardButton.setBounds(30, 270, 120 , 50);
	add(jardButton);
	jardButton.addActionListener(this); 
	
	milaButton = new JButton("mila (mi)");
	milaButton.setBounds(30, 330, 120 , 50);
	add(milaButton);
	milaButton.addActionListener(this);
	}

	public static void main(String[] args){	
		PrzelicznikDlugosci frame = new PrzelicznikDlugosci();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // z pomocą tej metody ustalamy, co dzieje się z naszą aplikacją
		//po wciśnięciu krzyżyka w prawym górnym rogu. Z argumentem EXIT_ON_CLOS ustalamy, że wraz z naciśnięciem krzyżyka program się zamyka
		frame.setVisible(true); //widzialnosc aplikacji
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		metry = Double.parseDouble(ileField.getText()); //pobranie wartosci double metrów z obiektu ileField
		Object source = e.getSource(); //przechwytanie aktualnie kliknietego przycisku
		if(source == calButton){
			przeliczone = metry * 39.37;
			odpField.setText("Na cale: " + String.valueOf(przeliczone) + "\""); //zmiana double na string i wyswietlenie przeliczonej wartosci na odpowiednia jednostke
		}else if(source == stopaButton){
			przeliczone = metry * 3.28;
			odpField.setText("Na stopy: " + String.valueOf(przeliczone)+ "ft");
		}else if(source == jardButton){
			przeliczone = metry * 1.09;
			odpField.setText("Na jardy: " + String.valueOf(przeliczone) + "yd");
		}else if(source == milaButton){
			przeliczone = metry * 0.0062;
			odpField.setText("Na mile: " + String.valueOf(przeliczone) + "mi");
		}		
	}

	
}
