import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HanoiGame {
    private static final String[] postNames = {"A", "B", "C"};
    private static HanoiGame game;

    private JFrame frame;
    private JPanel backgroundPanel;
    private JButton switchButton, quantityChooserButton;
    private JComboBox quantityChooser;
    private HanoiPanelPainter hanoiPainter;
    private HashMap<String,JButton> buttonList;

    public static void main(String[] args){
            game = new HanoiGame();
            game.createGame();
    }

    public HanoiGame() {
        frame = new JFrame("HanoiTower_v0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080);
        frame.setVisible(true);
        frame.setResizable(false);

        BorderLayout layoutBorder = new BorderLayout();
        backgroundPanel = new JPanel(layoutBorder);
        backgroundPanel.setBackground(Color.darkGray);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.getContentPane().add(BorderLayout.CENTER, backgroundPanel);
    }

    private void createGame(JPanel buttonsPanel, JPanel hanoiPainterPanel){
        backgroundPanel.removeAll();
        backgroundPanel.add(BorderLayout.NORTH, prepareStartGui());
        backgroundPanel.add(BorderLayout.SOUTH, buttonsPanel);
        backgroundPanel.add(BorderLayout.CENTER, hanoiPainterPanel);
        quantityChooserButton.setText("Restartuj");
        repaintAndPackComponents();
    }

    public void createGame(){
        backgroundPanel.add(backgroundPanel.add(BorderLayout.NORTH, prepareStartGui()));
        repaintAndPackComponents();
    }

    private JPanel prepareStartGui() {
        JPanel gameMenuPanel = new JPanel();
        gameMenuPanel.setBackground(Color.darkGray);

        JLabel quantityLabel = new JLabel("Wybierz ilość krążków:");
        quantityLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 22));
        quantityLabel.setForeground(Color.WHITE);
        String[] quantityOptions = {"2","3","4","5"};
        quantityChooser = new JComboBox(quantityOptions);
        quantityChooserButton = new JButton("Zacznij grę");
        quantityChooserButton.addActionListener(new myChooseButtonListener());

        gameMenuPanel.add(quantityLabel);
        gameMenuPanel.add(quantityChooser);
        gameMenuPanel.add(quantityChooserButton);

        return gameMenuPanel;
    }

    private JPanel prepareButtonGui(){
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.DARK_GRAY);
        buttonList = new HashMap<>();
        for(int i = 0; i<3; i++){
            switchButton = new JButton("Weź z " + postNames[i]);
            switchButton.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
            switchButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 22));
            switchButton.addActionListener(new mySwitchButtonListener());
            buttonsPanel.add(switchButton);
            buttonList.put(postNames[i],switchButton);
        }
        return buttonsPanel;
    }

    public JPanel getHanoiPainter(String quantityChoosed) {
        hanoiPainter = new HanoiPanelPainter(Integer.parseInt(quantityChoosed));
        Dimension dimension = new Dimension(1280,500);
        hanoiPainter.setPreferredSize(dimension);
        return hanoiPainter;
    }

    private void repaintAndPackComponents() {
        frame.repaint();
        backgroundPanel.repaint();
        frame.pack();
    }

    private class myChooseButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choosed = (String) quantityChooser.getSelectedItem();
            createGame(prepareButtonGui(),getHanoiPainter(choosed));
        }

    }

    private class mySwitchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i<3; i++){
                buttonList.get(postNames[i]).setText("Połóż na " + postNames[i]);
            }
            JButton clickedButton = (JButton) e.getSource();
            clickedButton.setText("Odstaw");
        }
    }
}
