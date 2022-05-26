package UI.Screen;

import javax.swing.*;

public class MainGameScreen extends JPanel {
    public final static int WIDTH_SCREEN=800;
    public final static int HEIGHT_SCREEN=600;
    public MainGameScreen()
    {
        initComponents();
    }

    private void initComponents() {
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playerList = new javax.swing.JList<>();
        jPanel9 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        moneyLb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        landList = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        gamePanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        buildBtn = new javax.swing.JButton();
        sellBtn = new javax.swing.JButton();
        mortageBtn = new javax.swing.JButton();
        tradeBtn = new javax.swing.JButton();

        jButton5.setText("BACK");


        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jPanel1.setMaximumSize(new java.awt.Dimension(300, 32767));
        jPanel1.setPreferredSize(new java.awt.Dimension(250, 601));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        backBtn.setText("BACK");
        jPanel10.add(backBtn);

        jPanel1.add(jPanel10);

        jPanel5.setMaximumSize(new java.awt.Dimension(200, 300));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 250));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 1, 10, 1));

        jScrollPane1.setViewportView(playerList);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5);

        jPanel9.setBackground(new java.awt.Color(153, 153, 153));
        jPanel9.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        jPanel9.setMaximumSize(new java.awt.Dimension(35222, 355522));

        jPanel12.setBackground(null);
        jPanel12.setMaximumSize(new java.awt.Dimension(32767, 50));
        jPanel12.setPreferredSize(new java.awt.Dimension(200, 50));

        jLabel1.setText("MONEY");
        jPanel12.add(jLabel1);

        moneyLb.setText("000$");
        jPanel12.add(moneyLb);

        jPanel9.add(jPanel12);

        jLabel3.setText("LAND LIST");
        jPanel9.add(jLabel3);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(200, 350));

        landList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jScrollPane2.setViewportView(landList);

        jPanel9.add(jScrollPane2);

        jPanel1.add(jPanel9);

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setLayout(new java.awt.BorderLayout());

        gamePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        gamePanel.setMaximumSize(new java.awt.Dimension(550, 32767));
        gamePanel.setPreferredSize(new java.awt.Dimension(650, 650));

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 628, Short.MAX_VALUE)
        );
        gamePanelLayout.setVerticalGroup(
                gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 648, Short.MAX_VALUE)
        );

        jPanel2.add(gamePanel, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25));
        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 20, 0));

        buildBtn.setText("BUILD");
        jPanel4.add(buildBtn);

        sellBtn.setText("SELL");
        sellBtn.setMaximumSize(new java.awt.Dimension(50, 22));
        sellBtn.setPreferredSize(new java.awt.Dimension(50, 22));
        jPanel4.add(sellBtn);

        mortageBtn.setText("MORTAGE");
        jPanel4.add(mortageBtn);

        tradeBtn.setText("TRADE");
        jPanel4.add(tradeBtn);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JButton backBtn;
    private javax.swing.JButton buildBtn;
    private javax.swing.JPanel gamePanel;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> landList;
    private javax.swing.JLabel moneyLb;
    private javax.swing.JButton mortageBtn;
    private javax.swing.JList<String> playerList;
    private javax.swing.JButton sellBtn;
    private javax.swing.JButton tradeBtn;

}
