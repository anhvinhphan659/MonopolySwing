package UI.Screen;

import Main.GameFrame;
import Model.GameParameter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChooseNPlayerScreen extends JPanel {
    public final static int WIDTH_SCREEN=600;
    public final static int HEIGHT_SCREEN=400;

    public ChooseNPlayerScreen()
    {
        initComponents();
    }

    //add action for buttons in panel
    private void setUpActions()
    {
        nPlayerTf.setEnabled(false);
        nPlayerTf.setDisabledTextColor(Color.BLACK);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(ChooseNPlayerScreen.this);
                gameFrame.setSize(new Dimension(StartScreen.WIDTH_SCREEN,StartScreen.HEIGHT_SCREEN));
                gameFrame.changeGamePanel(new StartScreen());
            }
        });

        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(ChooseNPlayerScreen.this);
                gameFrame.setResizable(true);
                gameFrame.setSize(new Dimension(MainGameScreen.WIDTH_SCREEN,MainGameScreen.HEIGHT_SCREEN));
                try {
                    gameFrame.changeGamePanel(new MainGameScreen());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        nPlayerSl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = nPlayerSl.getValue();
                nPlayerSl.setValue(value);
                nPlayerTf.setText(String.valueOf(value));
                GameParameter.setnPlayer(value);
            }
        });
    }

    private void initComponents() {
        jPanel2 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 30));
        jLabel2 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 20));
        nPlayerSl = new javax.swing.JSlider();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 20));
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nPlayerTf = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 20));
        startGameBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 400));
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(613, 50));
        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        backBtn.setText("Back");

        jPanel2.add(backBtn);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jPanel1.add(filler1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("CHOOSE NUMBER OF PLAYER");
        jLabel2.setAlignmentX(0.5F);
        jPanel1.add(jLabel2);
        jPanel1.add(filler2);

        nPlayerSl.setMaximum(5);
        nPlayerSl.setValue(2);
        nPlayerSl.setMaximumSize(new java.awt.Dimension(400, 20));
        nPlayerSl.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel1.add(nPlayerSl);
        jPanel1.add(filler3);

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 50));

        jLabel1.setText("Number of Player: ");
        jPanel3.add(jLabel1);

        nPlayerTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nPlayerTf.setText("2");
        nPlayerTf.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel3.add(nPlayerTf);

        jPanel1.add(jPanel3);
        jPanel1.add(filler4);

        startGameBtn.setText("Start Game");
        startGameBtn.setAlignmentX(0.5F);
        startGameBtn.setMaximumSize(new java.awt.Dimension(100, 30));
        jPanel1.add(startGameBtn);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        //set up actions
        setUpActions();
    }



    private javax.swing.JButton backBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSlider nPlayerSl;
    private javax.swing.JTextField nPlayerTf;
    private javax.swing.JButton startGameBtn;
}
