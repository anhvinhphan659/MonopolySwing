package UI.Util;

import Model.Land;
import Model.Player;
import UI.Screen.MainGameScreen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RankPanel extends JPanel {
    private static int CORNER_RADIUS=50;
    private ArrayList<Player> playerArrayList;
    public RankPanel(ArrayList<Player> playerArrayList)
    {
        this.playerArrayList = playerArrayList;
        initComponents();
    }
    private void initComponents() {
        setSize(new Dimension(300, 300));
        setPreferredSize(new Dimension(300, 310));
        setLayout(new BorderLayout());
        RoundPanel r = new RoundPanel();
        r.setRoundTopLeft(CORNER_RADIUS);
        r.setRoundTopRight(CORNER_RADIUS);
        r.setRoundBottomLeft(CORNER_RADIUS);
        r.setRoundBottomRight(CORNER_RADIUS);
        r.setLayout(new BorderLayout());

        RoundPanel topPanel = new RoundPanel();
        topPanel.setPreferredSize(new Dimension(300, 50));
        topPanel.setRoundTopRight(CORNER_RADIUS);
        topPanel.setRoundTopLeft(CORNER_RADIUS);
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.YELLOW);
        JLabel landName = new JLabel("END GAME");
        landName.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        landName.setHorizontalAlignment(SwingConstants.CENTER);
        landName.setForeground(Color.WHITE);
        landName.setFont(new Font("Stencil Std", Font.BOLD, 24));
        topPanel.add(landName, BorderLayout.CENTER);

        RoundPanel centerPanel = new RoundPanel();
        centerPanel.setRoundBottomLeft(CORNER_RADIUS);
        centerPanel.setRoundBottomRight(CORNER_RADIUS);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));

        int i = 1;
        for(Player player: playerArrayList){
            JLabel lb =createLabel();

            String str = String.valueOf(i);

            switch (i%10){
                case 1:
                    str += "ST: ";
                    break;
                case 2:
                    str += "ND: ";
                    break;
                case 3:
                    str += "RD: ";
                    break;
                default:
                    str += "TH: ";
            }

            lb.setText( str + player.getTotalAssets() + "$ - " + player.getName());
            lb.setFont(new java.awt.Font("Leelawadee UI", Font.BOLD, 24));

            centerPanel.add(lb);
            centerPanel.add(new JLabel("        "));
            i++;
        }

        r.add(centerPanel, BorderLayout.CENTER);
        r.add(topPanel, BorderLayout.NORTH);
        add(r, BorderLayout.CENTER);
    }

    private JLabel createLabel()
    {
        JLabel lb=new JLabel();
        lb.setHorizontalAlignment(SwingConstants.CENTER);
        lb.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        lb.setFont(new java.awt.Font("Leelawadee UI", Font.PLAIN, 16));
        lb.setForeground(new Color(140,140,150));
        return lb;
    }

//    private ArrayList<Integer> ranking(ArrayList<Player> playerArrayList){
//        ArrayList<Integer> rankIndex = new ArrayList<>();
//        for(int i = 0; i < playerArrayList.size(); i++){
//            int maxMoney = 0;
//            int index = 0;
//
//            for(int j = 0; j < playerArrayList.size(); j++) {
//                if (!rankIndex.contains(Integer.valueOf(i))) {
//                    if (playerArrayList.get(j).getTotalAssets() > maxMoney) {
//                        maxMoney = playerArrayList.get(j).getTotalAssets();
//                        index = j;
//                    }
//                }
//
//            }
//        }
//
//        return rankIndex;
//    }

}
