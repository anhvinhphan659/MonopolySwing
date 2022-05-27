package UI.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandItem extends JPanel implements ActionListener {
    public static final int WIDTH_ITEM=100;
    public static final int HEIGHT_ITEM=50;
    //color of land
    private Color landColor;
    //store position (x,y) of item
    private int X_POSITION=-1;
    private int Y_POSTION=-1;
    //land consists of land for house and land for utilities
    private boolean isLand=true;
    //direction of land item
    private int direction=0;

    public LandItem()
    {
        initComponents();
    }

    private void initComponents() {
        colorpanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nameLb = new javax.swing.JLabel();
        priceLb = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setLayout(new java.awt.BorderLayout());

        colorpanel.setBackground(new java.awt.Color(255, 255, 51));
        colorpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        colorpanel.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout colorpanelLayout = new javax.swing.GroupLayout(colorpanel);
        colorpanel.setLayout(colorpanelLayout);
        colorpanelLayout.setHorizontalGroup(
                colorpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 16, Short.MAX_VALUE)
        );
        colorpanelLayout.setVerticalGroup(
                colorpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 43, Short.MAX_VALUE)
        );

        add(colorpanel, java.awt.BorderLayout.EAST);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        nameLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLb.setText("jLabel1");
        nameLb.setMaximumSize(new java.awt.Dimension(37, 30));
        nameLb.setPreferredSize(new java.awt.Dimension(37, 25));
        jPanel2.add(nameLb, java.awt.BorderLayout.CENTER);

        priceLb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceLb.setText("jLabel2");
        priceLb.setAlignmentX(0.5F);
        priceLb.setPreferredSize(new java.awt.Dimension(37, 25));
        jPanel2.add(priceLb, java.awt.BorderLayout.PAGE_END);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JPanel colorpanel;
    private javax.swing.JLabel imageLb;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel nameLb;
    private javax.swing.JLabel priceLb;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(nameLb);
    }
}
