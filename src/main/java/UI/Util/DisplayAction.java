package UI.Util;

import Model.Land;
import Model.Player;
import UI.Item.PlayerItem;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class DisplayAction {
    public static void showLandInformation(Land land)
    {
        LandInformationPanel informationPane=new LandInformationPanel(land);
        JOptionPane.showMessageDialog(null,informationPane,land.getName()+"'s Information",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void movePlayer(JLayeredPane desPanel, PlayerItem pi)
    {
        Thread t=new Thread(()->{
            Rectangle r= pi.getBounds();

            for (int i=0;i<50;i++)
            {
                r.x++;
                pi.setBounds(r);
                try {
                    Thread.sleep(10 );
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                try {
                    SwingUtilities.invokeAndWait(()->
                    {
                        desPanel.repaint();
                    });
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                }

            }
        });
        t.start();
    }


}
