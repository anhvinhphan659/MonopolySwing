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
    }


    private void initComponents() {

        imageLb=new JLabel();

        setLayout(new BorderLayout());
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        setSize(new Dimension(HEIGHT_ITEM,WIDTH_ITEM));
        setPreferredSize(new Dimension(HEIGHT_ITEM,WIDTH_ITEM));

        add(imageLb,BorderLayout.CENTER);
    }

    public void setImageItem()
    {
        // TODO: set image for land size
    }

    private JLabel imageLb;
}
