package UI.Screen;

import UI.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    public final static int WIDTH_SCREEN=600;
    public final static int HEIGHT_SCREEN=400;

    public StartScreen()
    {
        initComponents();
    }

    private void initComponents(){
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 15), new java.awt.Dimension(30, 15), new java.awt.Dimension(30, 15));
        startBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15));
        settingBtn = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15), new java.awt.Dimension(15, 15));
        exitBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Adobe Heiti Std R", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MONOPOLY");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setMaximumSize(new java.awt.Dimension(67, 80));
        jLabel1.setPreferredSize(new java.awt.Dimension(67, 80));
        add(jLabel1, java.awt.BorderLayout.NORTH);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 1, 1, 1));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        jPanel1.add(filler3);

        startBtn.setText("Start Game");
        startBtn.setAlignmentX(0.5F);
        startBtn.setMargin(new java.awt.Insets(10, 14, 10, 14));
        startBtn.setMaximumSize(new java.awt.Dimension(150, 40));
        startBtn.setPreferredSize(new java.awt.Dimension(75, 40));

        jPanel1.add(startBtn);
        jPanel1.add(filler1);

        settingBtn.setText("Settings");
        settingBtn.setAlignmentX(0.5F);
        settingBtn.setMargin(new java.awt.Insets(10, 14, 10, 14));
        settingBtn.setMaximumSize(new java.awt.Dimension(150, 40));
        settingBtn.setPreferredSize(new java.awt.Dimension(75, 40));
        jPanel1.add(settingBtn);
        jPanel1.add(filler2);

        exitBtn.setText("Exit Game");
        exitBtn.setAlignmentX(0.5F);
        exitBtn.setMargin(new java.awt.Insets(10, 14, 10, 14));
        exitBtn.setMaximumSize(new java.awt.Dimension(150, 40));
        exitBtn.setPreferredSize(new java.awt.Dimension(75, 40));
        jPanel1.add(exitBtn);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        //add actions
        setUpActions();
        
    }


    private void setUpActions()
    {
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(StartScreen.this);
                gameFrame.setPreferredSize(new Dimension(ChooseNPlayerScreen.WIDTH_SCREEN,ChooseNPlayerScreen.HEIGHT_SCREEN));
                gameFrame.changeGamePanel(new ChooseNPlayerScreen());
            }
        });
    }

    private javax.swing.JButton exitBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton settingBtn;
    private javax.swing.JButton startBtn;
}
