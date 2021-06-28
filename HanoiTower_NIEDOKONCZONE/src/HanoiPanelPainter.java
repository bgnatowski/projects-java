import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.ArrayList;
import java.util.HashMap;

class HanoiPanelPainter extends JPanel {
    int quantity;
    int xA = 130;
    int xB = 490;
    int xC = 850;
    int[] y = {400,380,360,340,320};

    Image podstawa;
    HashMap<Integer,Image> listaKrazkow = new HashMap<>();

    HanoiPanelPainter(int q){
        quantity = q;
        for(int i = --q; i>=0; i--){
            System.out.println(i);
            listaKrazkow.put(i,new ImageIcon("img\\krazek" + i + ".png").getImage());
        }
        podstawa = new ImageIcon("img\\podstawa.png").getImage();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(podstawa,0,0,this);
        for(int i = 0;i<quantity;i++, xA+=25){
            g2.drawImage(listaKrazkow.get(i),xA,y[i], this);
        }

    }

}
