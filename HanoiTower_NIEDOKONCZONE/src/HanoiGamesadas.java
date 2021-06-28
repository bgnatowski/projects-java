import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class HanoiGamesadas {
    private static final String[] postNames = {"A", "B", "C"};
    private JFrame frame;
    private JPanel backgroundPanel, buttonsPanel;
    private JButton switchButton, quantityChooserButton;
    private HashMap<String,JButton> buttonList;
    private JComboBox quantityChooser;
    private HanoiPanelPainter hanoiPainter;

    public HanoiGamesadas() {
        frame = new JFrame("HanoiTower_v0.1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setVisible(true);

        BorderLayout layoutBorder = new BorderLayout();
        backgroundPanel = new JPanel(layoutBorder);
        backgroundPanel.setBackground(Color.darkGray);
        backgroundPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        frame.getContentPane().add(BorderLayout.CENTER, backgroundPanel);
    }

    public static void main(String[] args){
        new HanoiGamesadas().createGame();
    }

    private void createGame(){
        backgroundPanel.removeAll();
        prepareStartGui();
        repaintAndPackComponents();
    }

    private void prepareStartGui() {
        JPanel gameMenuPanel = new JPanel();
        gameMenuPanel.setBackground(Color.darkGray);
        JLabel quantityLabel = new JLabel("Wybierz ilość krążków:");
        quantityLabel.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 22));
        quantityLabel.setForeground(Color.WHITE);
        String[] quantityOptions = {"2","3","4","5"};
        quantityChooser = new JComboBox(quantityOptions);
        quantityChooserButton = new JButton("Zacznij grę");
        quantityChooserButton.addActionListener(new mySwitcButtonListener());

        gameMenuPanel.add(quantityLabel);
        gameMenuPanel.add(quantityChooser);
        gameMenuPanel.add(quantityChooserButton);
        backgroundPanel.add(BorderLayout.NORTH, gameMenuPanel);
    }

    private void prepareGameGui(String quantityChoosed) {
        hanoiPainter = new HanoiPanelPainter(Integer.parseInt(quantityChoosed));

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.DARK_GRAY);
        buttonList = new HashMap<>();
        for(int i = 0; i<3; i++){
            switchButton = new JButton("Weź z " + postNames[i]);
            switchButton.setBorder(BorderFactory.createEmptyBorder(20,100,20,100));
            switchButton.setFont(new Font("DejaVu Sans Mono", Font.BOLD, 22));
            buttonsPanel.add(switchButton);
            buttonList.put(postNames[i],switchButton);
        }

        backgroundPanel.add(BorderLayout.CENTER, hanoiPainter);
        backgroundPanel.add(BorderLayout.SOUTH, buttonsPanel);
    }

    private void repaintAndPackComponents() {
        frame.repaint();
        backgroundPanel.repaint();
        frame.pack();
    }

    private class mySwitcButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String choosed = (String) quantityChooser.getSelectedItem();
            quantityChooserButton.setText("Restartuj");
            prepareGameGui(choosed);
            repaintAndPackComponents();
        }

    }
}
