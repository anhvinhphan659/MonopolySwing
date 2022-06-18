package UI.Item;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerItem extends JLabel {
    public final static Color[] ownColors={
            new Color(252,136,136),
            new Color(247,244,155)

    };
    public final static Color[] mortageColors={
            new Color(255,0,0 ),
            new Color(247,231,2)
    };
    private final static String[] images={};
    private int playerColor;
    private Player player;

    public PlayerItem(Player p)
    {
        player=p;
        initComponents();
    }

    private void initComponents()
    {
        // TODO: add image player to player
        ImageIcon image = new ImageIcon(this.getClass().getResource("/UI/assets/player_black.png"));
        setIcon(image);
    }
}
