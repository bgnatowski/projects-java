//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.HashMap;
//
//public class Hanoi {
//    private static final String[] postNames = {"A", "B", "C"};
//    private HanoiPanelPainter hanoiPainter;
//    private HashMap<String,JButton> buttonList;
//    private JButton switchButton;
//    private static HanoiGame game = new HanoiGame();
//
//    public static void main(String[] args){
//        game.createGame();
//    }
//
//    public JPanel prepareButtonGui(){
//        JPanel buttonsPanel = new JPanel();
//        buttonsPanel.setBackground(Color.DARK_GRAY);
//        buttonList = new HashMap<>();
//        for(int i = 0; i<3; i++){
//            switchButton = new JButton("Weź z " + postNames[i]);
//            switchButton.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
//            switchButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 22));
//            buttonsPanel.add(switchButton);
//            buttonList.put(postNames[i],switchButton);
//        }
//        return buttonsPanel;
//    }
//
//    public JPanel getHanoiPainter(String quantityChoosed) {
//        hanoiPainter = new HanoiPanelPainter(Integer.parseInt(quantityChoosed));
//        return hanoiPainter;
//    }
//}
