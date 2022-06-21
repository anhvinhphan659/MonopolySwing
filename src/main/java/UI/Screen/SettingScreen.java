package UI.Screen;

import Main.GameFrame;
import Model.GameParameter;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingScreen extends JPanel {

    public final static int WIDTH_SCREEN=800;
    public final static int HEIGHT_SCREEN=500;

    public SettingScreen(){
        initComponents();
        setUpOthers();
    }

    private void setUpOthers()
    {
        int currentMoney=GameParameter.getMoney();
        moneySl.setValue(currentMoney);
        startMoneyTF.setText(String.valueOf(currentMoney));
        startMoneyTF.setBackground(Color.WHITE);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame=(GameFrame)SwingUtilities.getWindowAncestor(SettingScreen.this);
                gameFrame.setSize(new Dimension(StartScreen.WIDTH_SCREEN,StartScreen.HEIGHT_SCREEN));
                gameFrame.changeGamePanel(new StartScreen());
            }
        });
        moneySl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int money =moneySl.getValue();
                startMoneyTF.setText(String.valueOf(money));
            }
        });
    }

    private void initComponents()
    {
        jPanel1 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        startMoneyTF = new javax.swing.JTextField();
        moneySl = new javax.swing.JSlider();
        jPanel5 = new javax.swing.JPanel();
        saveBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        resetBtn = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(66, 65, 61));
        jPanel1.setPreferredSize(new java.awt.Dimension(150, 0));
        jPanel1.setLayout(null);

        backBtn.setText("BACK");
        jPanel1.add(backBtn);
        backBtn.setBounds(10, 10, 130, 30);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MONOPOLY SWING SETTINGS");
        jLabel1.setAlignmentX(0.5F);
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.NORTH);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 50, 0, 50));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 400));
        jPanel6.setPreferredSize(new java.awt.Dimension(562, 400));
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setText("Start Money:");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel6.add(jLabel3);

        startMoneyTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        startMoneyTF.setText("200");
        startMoneyTF.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel6.add(startMoneyTF);

        moneySl.setMaximum(1000);
        moneySl.setMinimum(100);
        moneySl.setPreferredSize(new java.awt.Dimension(300, 20));
        jPanel6.add(moneySl);

        jScrollPane1.setViewportView(jPanel6);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel5.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1)));
        jPanel5.setPreferredSize(new java.awt.Dimension(600, 50));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        saveBtn.setText("SAVE");
        saveBtn.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel5.add(saveBtn);
        jPanel5.add(filler1);

        resetBtn.setText("RESET");
        resetBtn.setMaximumSize(new java.awt.Dimension(72, 40));
        resetBtn.setMinimumSize(new java.awt.Dimension(72, 40));
        resetBtn.setPreferredSize(new java.awt.Dimension(80, 28));
        jPanel5.add(resetBtn);

        jPanel2.add(jPanel5, java.awt.BorderLayout.SOUTH);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JButton backBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider moneySl;
    private javax.swing.JButton resetBtn;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField startMoneyTF;
}

