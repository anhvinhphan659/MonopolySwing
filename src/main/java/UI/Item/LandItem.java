package UI.Item;

import Model.Land;
import Model.Player;
import com.github.weisj.darklaf.listener.MouseClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class LandItem extends JPanel implements MouseClickListener {
    public static final int WIDTH_ITEM=100;
    public static final int HEIGHT_ITEM=50;
    //color of land
    private Color landColor;
    //store position (x,y) of item
    //land consists of land for house and land for utilities
    //direction of land item
    protected Land land;
    private Player owner;
    private boolean isMortgage;
    private int nHouse;


    public LandItem(Land land)
    {
        this.land=land;
        this.isMortgage = false;
        this.nHouse = 0;

        setPreferredSize(new Dimension(WIDTH_ITEM,HEIGHT_ITEM));
        initComponents();
        nameLb.setText(land.getName());
        priceLb.setText(String.valueOf(land.getPrice()));
        requestFocus();


    }
//    public LandItem(String name){
//        land = new Land();
//    }
    private void initComponents() {
        colorpanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        nameLb = new javax.swing.JLabel();
        priceLb = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setLayout(new java.awt.BorderLayout());

//        colorpanel.setBackground(new java.awt.Color(255, 255, 51));
        if(land.getPriority() > 0){
            colorpanel.setBackground(Land.colors[land.getPriority() - 1]);
        }
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
        jPanel2.setBackground(Color.WHITE);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }

//    public Land getLand() {
//        return land;
//    }




    private javax.swing.JPanel colorpanel;
    private javax.swing.JPanel jPanel2;
    protected javax.swing.JLabel nameLb;
    private javax.swing.JLabel priceLb;




    public JPanel getColorpanel() {
        return colorpanel;
    }

    public void setColorpanel(JPanel colorpanel) {
        this.colorpanel = colorpanel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        System.out.println("Click on "+land.getName());
    }

    public int getRent(){
        int rent = land.getPrice()/10;

        switch (nHouse){
            case 0:
                rent *= 1;
                break;
            case 1:
                rent *= 5;
                break;
            case 2:
                rent *= 5*3;
                break;
            case 3:
                rent *= 5*5;
                break;
            case 4:
                rent *= 5*7;
                break;
            case 5:
                rent *= 5*9;
                break;
        }
        return rent;
    }

    public int getPriceOfHouseWhenSell(){
        return (int) (land.getPrice() * 0.4 * nHouse);
    }

    public int getPriceOfLandWhenMortage(){
        return (int) (land.getPrice() * 0.5);
    }

    public int getPriceOfLandAndHourseWhenSell(){
        return (int) (land.getPrice() * 3 + land.getPrice() * 1.5 * nHouse);
    }
    public Land getLand() {
        return land;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isMortgage() {
        return isMortgage;
    }

    public void setMortgage(boolean mortgage) {
        isMortgage = mortgage;
    }

    public int getnHouse() {
        return nHouse;
    }

    public void setnHouse(int nHouse) {
        this.nHouse = nHouse;
    }

}
