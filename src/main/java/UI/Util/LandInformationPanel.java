package UI.Util;

import Model.Land;
import com.github.weisj.darklaf.util.Alignment;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

// TODO: set up panel to display information of land in display action class
public class LandInformationPanel extends JPanel {
    private static int CORNER_RADIUS=50;
    private Land land;
    public LandInformationPanel(Land l)
    {
        land=l;
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
        topPanel.setBackground(Land.colors[land.getPriority()-1]);
        JLabel landName = new JLabel(land.getName().toUpperCase(Locale.ROOT));
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
        JLabel lb1=createLabel();
        int rentValue=land.getPrice()/10;
        lb1.setText("RENT "+String.valueOf(rentValue)+"$");
        lb1.setFont(new java.awt.Font("Leelawadee UI", Font.BOLD, 24));
        JLabel lb2=createLabel();
        lb2.setText("Price rent house(s) in land:");
        JLabel lb3=createLabel();
        lb3.setText("<html>1 house(s): " +String.valueOf(rentValue*5)+"$"+
                "<br>" +"<html>2 house(s): " +String.valueOf(rentValue*5*3)+"$"+
                "<br>" +"<html>3 house(s): " +String.valueOf(rentValue*5*5)+"$"+
                "<br>" +"<html>4 house(s): " +String.valueOf(rentValue*5*7)+"$"+
                "<br>" +"<html>1 hotel: " +String.valueOf(rentValue*5*9)+"$"+
                "</html>");
        JLabel lb4=createLabel();
        lb4.setText("Construction "+String.valueOf(rentValue*5)+"$ each house");
        JLabel lb5=createLabel();
        lb5.setText("Mortage: " + String.valueOf(land.getPrice() / 2) + "$");
        lb5.setFont(new java.awt.Font("Leelawadee UI", Font.BOLD, 24));

        centerPanel.add(lb1);
        centerPanel.add(lb2);
        centerPanel.add(lb3);
        centerPanel.add(new JLabel("        "));
        centerPanel.add(lb4);
        centerPanel.add(new JLabel("        "));
        centerPanel.add(lb5);


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

}
