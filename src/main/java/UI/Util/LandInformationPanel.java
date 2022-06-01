package UI.Util;

import Model.Land;

import javax.swing.*;
import java.awt.*;

// TODO: set up panel to display information of land in displayaction class
public class LandInformationPanel extends JPanel {
    private Land land;
    public LandInformationPanel(Land l)
    {
        land=l;
    }
    private void initComponents()
    {
        setSize(new Dimension(300,300));
        setPreferredSize(new Dimension(300,300));
        setBackground(Color.YELLOW);
    }

}
