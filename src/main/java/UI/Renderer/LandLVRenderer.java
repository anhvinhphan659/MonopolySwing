package UI.Renderer;

import Model.Land;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LandLVRenderer extends JPanel implements ListCellRenderer<Land> {
    private JPanel contentPanel;

    private JLabel nameLabel;
    private JLabel houseLabel;

    public LandLVRenderer()
    {
        contentPanel=new JPanel();

        nameLabel = new JLabel();
        houseLabel = new JLabel();

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(5,5,5,5) ));

        contentPanel.add(nameLabel,BorderLayout.WEST);
        contentPanel.add(houseLabel,BorderLayout.EAST);

        setLayout(new BorderLayout());
        setBackground(Color.GREEN);
        setBorder(new EmptyBorder(5,5,5,5));

        add(contentPanel,BorderLayout.CENTER);

    }

    @Override
    public Component getListCellRendererComponent(JList list, Land value, int index, boolean isSelected, boolean cellHasFocus) {
        nameLabel.setText(value.getName());
        houseLabel.setText(String.valueOf(value.getPrice()));

        return this;
    }
}
