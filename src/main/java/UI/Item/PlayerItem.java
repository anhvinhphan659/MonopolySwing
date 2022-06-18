package UI.Item;

import Model.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerItem extends JLabel {
    public final static Color[] ownColors={
            new Color(252,136,136),
            new Color(247,244,155),
            new Color(187,242,201),
            new Color(153,191,247),
            new Color(245,188,122),
            new Color(250,185,243),

    };
    public final static Color[] mortageColors={
            new Color(150,6,6),
            new Color(166,166,6),
            new Color(29,130,56),
            new Color(7,60,140),
            new Color(143,74,0),
            new Color(117,5,106),
    };
    private final static String[] images={
            "player_red",
            "player_yellow",
            "player_green",
            "player_blue",
            "player_orange",
            "player_pink",
    };
    private int playerColor;
    private Player player;

    public PlayerItem(Player p)
    {
        player=p;
        playerColor=0;

        initComponents();
    }

    private void initComponents()
    {
        // TODO: add image player to player
        ImageIcon image = new ImageIcon(this.getClass().getResource("/UI/assets/" +
                images[playerColor] +
                ".png"));
        setIcon(image);
    }

    public void setPlayerColor(int color)
    {
        playerColor=color%6;
        player.setPlayerColor(ownColors[color]);
        initComponents();
    }

    public int getPlayerColor() {
        return playerColor;
    }
}
