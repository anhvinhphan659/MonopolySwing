package UI.Renderer;

import Model.Land;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LandLVRenderer extends JPanel implements ListCellRenderer<Land> {
    private JPanel contentPanel;
    public LandLVRenderer()
    {
        contentPanel=new JPanel();

        contentPanel.setBorder(new LineBorder(Color.BLACK));

        setLayout(new BorderLayout());
        setBackground(Color.GREEN);
        setBorder(new EmptyBorder(5,5,5,5));
        add(contentPanel,BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Land value, int index, boolean isSelected, boolean cellHasFocus) {
        return this;
    }
}
