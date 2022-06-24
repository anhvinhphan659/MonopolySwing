package UI.Item;

import Model.Land;

import javax.swing.*;
import java.awt.*;

public class CornerItem extends LandItem{
    public static final int WIDTH_ITEM=100;
    public static final int HEIGHT_ITEM=100;

    public CornerItem(Land land)
    {
        super(land);
        initComponents();

        imageLb.setText(land.getName());
    }


    private void initComponents() {

        imageLb=new JLabel();

        setLayout(new BorderLayout());
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setSize(new Dimension(HEIGHT_ITEM,WIDTH_ITEM));
        setPreferredSize(new Dimension(HEIGHT_ITEM,WIDTH_ITEM));
        setImageItem();
        add(imageLb,BorderLayout.CENTER);
    }

    public void setImageItem()
    {
        // TODO: set image for land size
        String type=land.getName();
        String path="/UI/assets/";
        if(type.equalsIgnoreCase("Start"))
            path+="start.png";
        else if(type.equalsIgnoreCase("Prison"))
            path+="visitjail.png";
        else if(type.equalsIgnoreCase("Park"))
            path+="freeparking.png";
        else if(type.equalsIgnoreCase("Go to jail"))
            path+="gotojail.png";
        ImageIcon imageIcon =new ImageIcon(this.getClass().getResource(path));
        Image image=imageIcon.getImage();
        imageIcon=new ImageIcon(image.getScaledInstance(WIDTH_ITEM,HEIGHT_ITEM,Image.SCALE_SMOOTH));
        imageLb.setIcon(imageIcon);

    }

    private JLabel imageLb;
}
