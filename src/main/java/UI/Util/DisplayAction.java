package UI.Util;

import Model.Land;
import Model.Player;
import UI.Item.HouseItem;
import UI.Item.PlayerItem;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class DisplayAction {
    public static void showLandInformation(Land land)
    {
        LandInformationPanel informationPane=new LandInformationPanel(land);
//        RankPanel rankPanel = new RankPanel();
        JOptionPane.showMessageDialog(null,informationPane,land.getName()+"'s Information",JOptionPane.UNDEFINED_CONDITION);
    }

    public static void showRank(ArrayList<Player> playerArrayList)
    {
        RankPanel rankPanel = new RankPanel(playerArrayList);
        JOptionPane.showMessageDialog(null,rankPanel,"Rank",JOptionPane.UNDEFINED_CONDITION);
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
        return r.nextInt(6) + 1;
    }

    //calculate position of house to add
    /*
    landth: th of land
    houseth: th of house in land, range from 1..4
    typehouse: house=0, hotel=1 see more in HouseItem
    */
    public static Point getHousePosition(int landTH,int houseTh,int typeHouse)
    {
        Point p=new Point();
        int start_x=100+25;
        int start_y=100+25;
        // TODO: calculate startx,starty of land
        if(typeHouse==HouseItem.HOUSE)
        {
            if(landTH<9||(landTH>18&&landTH<27))
            {
                int tempTh=landTH<9?landTH:landTH-18;
                start_x+=(tempTh-1)*50;
                start_y-=10;
                if(houseTh>2)
                    start_y-=24;
                if (houseTh%2==0)
                    start_x+=24+2;
                if(landTH>18)
                {
                    start_x=325*2-(start_x+24);
                    start_y=325*2-(start_y+24);
                }
            }
            else
            {
                int tempTh=landTH<18?landTH-9:landTH-27;
                start_x+=400-14;
                start_y+=(tempTh-1)*50;
                if(houseTh>2)
                    start_y+=24+2;
                if(houseTh%2==0)
                    start_x+=24;
                if(landTH>27)
                {
                    start_x=325*2-(start_x+24);
                    start_y=325*2-(start_y+24);
                }

            }
        }
        else {
            if(landTH<9||(landTH>18&&landTH<27))
            {
                int tempTh=landTH<9?landTH:landTH-18;
                start_x+=(tempTh-1)*50+13;
                start_y-=(24-2);
                if(landTH>18)
                {
                    start_x=325*2-(start_x);
                    start_y=325*2-(start_y+24);
                }
            }
            else
            {
                int tempTh=landTH<18?landTH-9:landTH-27;
                start_x+=400-2;
                start_y+=(tempTh-1)*50+13;
                if(landTH>27)
                {
                    start_x=325*2-(start_x+24);
                    start_y=325*2-(start_y+24);
                }
            }
        }
        p.x=start_x;
        p.y=start_y;
        return p;
    }


}
