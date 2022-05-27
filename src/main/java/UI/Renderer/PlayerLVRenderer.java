package UI.Renderer;

import Model.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class PlayerLVRenderer extends JPanel implements ListCellRenderer<Player> {
    private JPanel contentPanel;
    private JLabel contentLabel;
    public PlayerLVRenderer()
    {
        // TODO: decorate item here
        contentPanel=new JPanel();
        contentLabel=new JLabel();
        setLayout(new BorderLayout());
        setOpaque(true);
        setSize(new Dimension(150,50));
        setPreferredSize(new Dimension(150,50));
        setOpaque(true);
        setBackground(Color.YELLOW);
        setBorder(new EmptyBorder(5,5,5,5));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(new LineBorder(Color.BLACK));

        int r=new Random().nextInt(100);
        System.out.println(r);
        contentLabel.setText(String.valueOf(r));
        contentPanel.add(contentLabel,BorderLayout.CENTER);

        add(contentPanel,BorderLayout.CENTER);
    }
    @Override
    public Component getListCellRendererComponent(JList list, Player value, int index, boolean isSelected, boolean cellHasFocus) {

        // TODO: customize item here
        return this;
    }
}
