package UI.Renderer;

import Model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class PlayerLVRenderer extends JPanel implements ListCellRenderer<Player> {
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JLabel nameLabel;
    private JLabel moneyLabel;
    public PlayerLVRenderer()
    {
        // TODO: decorate item here
        contentPanel=new JPanel();
        nameLabel=new JLabel();
        moneyLabel = new JLabel();
        setLayout(new BorderLayout());
        setOpaque(true);
        setSize(new Dimension(150,50));
        setPreferredSize(new Dimension(150,50));
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY);
        setBorder(new EmptyBorder(5,5,5,5));
        leftPanel=new JPanel();
        leftPanel.setPreferredSize(new Dimension(20,20));

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5,5,5,5) ));
//        int r=new Random().nextInt(100);
//        System.out.println(r);
//        contentLabel.setText(String.valueOf(r));
        contentPanel.add(nameLabel,BorderLayout.CENTER);
        contentPanel.add(moneyLabel,BorderLayout.EAST);
        add(leftPanel,BorderLayout.WEST);
        add(contentPanel,BorderLayout.CENTER);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Player value, int index, boolean isSelected, boolean cellHasFocus) {

        // TODO: customize item here
        nameLabel.setText(value.getName());
        moneyLabel.setText(String.valueOf(value.getMoney()) + "$");
        leftPanel.setBackground(value.getPlayerColor());

        if(isSelected){
            contentPanel.setBackground(new Color(235,183,120));
        }
        else{
            contentPanel.setBackground(Color.WHITE);
        }

        return this;
    }

    public JLabel getMoneyLabel() {
        return moneyLabel;
    }
}
