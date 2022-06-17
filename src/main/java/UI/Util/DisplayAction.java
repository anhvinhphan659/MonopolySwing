package UI.Util;

import Model.Land;
import Model.Player;
import UI.Item.PlayerItem;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class DisplayAction {
    public static void showLandInformation(Land land)
    {
        LandInformationPanel informationPane=new LandInformationPanel(land);
        JOptionPane.showMessageDialog(null,informationPane,land.getName()+"'s Information",JOptionPane.UNDEFINED_CONDITION);
    }

    public static void movePlayer(JLayeredPane desPanel, PlayerItem pi,int step)
    {
        int fstep=step;
        Thread t=new Thread(()-> {
            int final_step=fstep;
            Rectangle r = pi.getBounds();
            int partial_x = (r.x - 25) / 50;
            int partial_y = (r.y - 25) / 50;
            while (final_step > 0) {
                int step_size=50;
                boolean step_x=true;
                int direction=1;
                if(partial_x<10&&partial_y<2)
                {
                    if(partial_x==9||partial_x<2)
                        step_size=100;
                }
                else if(partial_x>9&&partial_y<10)
                {
                    step_x=false;
                    if(partial_y<2||partial_y==9)
                        step_size=100;
                }
                else if(partial_y>9&&partial_x>1)
                {
                    direction=-1;
                    if(partial_x>10||partial_x==2)
                        step_size=100;
                }
                else if(partial_x<2&&partial_y>1)
                {
                    direction=-1;
                    step_x=false;
                    if(partial_y>10||partial_y==2)
                        step_size=100;
                }
                while(step_size>0)
                {
                    --step_size;
                    if(step_x)
                    {
                        r.x+=direction;
                    }
                    else
                    {
                        r.y+=direction;
                    }
                    try {
                        SwingUtilities.invokeAndWait(new Runnable() {
                            @Override
                            public void run() {
                                pi.setBounds(r);
                                desPanel.repaint();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(""+r.x+"-"+r.y);

                partial_x = (r.x - 25) / 50;
                partial_y = (r.y - 25) / 50;

                final_step--;
            }
        });
        t.start();
    }

    public static int randomDice()
    {
        Random r=new Random();
        return r.nextInt(6)%6+1;
    }


}
