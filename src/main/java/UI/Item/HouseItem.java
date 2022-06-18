package UI.Item;

import javax.swing.*;

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
            setIcon(new ImageIcon(this.getClass().getResource("/UI/assets/hotel.png")));

    }
}
