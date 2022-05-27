package UI.Item;

import javax.swing.*;
import java.awt.*;

public class CornerItem extends LandItem{
    public static final int WIDTH_ITEM=100;
    public static final int HEIGHT_ITEM=100;
    public CornerItem()
    {
        initComponents();
    }


    private void initComponents() {

        imageLb=new JLabel();

        setLayout(new BorderLayout());
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        add(imageLb,BorderLayout.CENTER);
    }

    public void setImageItem()
    {
        // TODO: set image for land size
    }

    private JLabel imageLb;
}
