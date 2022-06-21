package UI.Item;

import javax.swing.*;
import java.awt.*;

public class HouseItem extends JLabel {

    public final static int HOUSE=0;
    public final static int HOTEL=1;
    private int type;


    public HouseItem()
    {
        type=0;
        initComponents();
    }

    public HouseItem(int type)
    {
        this.type=type;
        initComponents();
    }


    private void initComponents(){
        if(type==0)
            setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/house.png")));
        else
        {
            ImageIcon imageIcon =new ImageIcon(this.getClass().getResource("/UI/assets/hotel.png"));
            Image image=imageIcon.getImage();
            imageIcon=new ImageIcon(image.getScaledInstance(24*2,24*2,Image.SCALE_SMOOTH));
            setIcon(imageIcon);

        }


    }
}
