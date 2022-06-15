package UI.Item;

import Model.Player;

import javax.swing.*;

public class PlayerItem extends JLabel {
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
