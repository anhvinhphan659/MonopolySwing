package UI;

import UI.Screen.StartScreen;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame gameFrame=null;

    private GameFrame()
    {
        initComponents();
    }

    public static GameFrame getGameDisplay()
    {
        if(gameFrame==null) {
            gameFrame = new GameFrame();
        }
        return gameFrame;
    }

    private void initComponents() {
        setSize(new Dimension(600,400));
        setPreferredSize(new Dimension(600,400));
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Monopoly Swing");
        setResizable(false);
        add(new StartScreen());
        setVisible(true);
    }

    public void changeGamePanel(JPanel p)
    {
        getContentPane().removeAll();
        add(p);
        setVisible(true);
    }


}
