package UI.Util;

import Model.Land;

import javax.swing.*;
import java.awt.*;

public class DisplayAction {
    public static void showLandInformation(Land land)
    {
        LandInformationPanel informationPane=new LandInformationPanel(land);
        JOptionPane.showMessageDialog(null,informationPane,land.getName()+"'s Information",JOptionPane.INFORMATION_MESSAGE);
    }
}
