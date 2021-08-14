package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ResetFont implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().setFont(new Font(gui.getMYFONTFAMILY(), Font.PLAIN, gui.getMYFONTSIZE()));
        gui.getFontSizeSpinner().setValue(gui.getMYFONTSIZE());
        gui.getFontBox().setSelectedItem(gui.getMYFONTFAMILY());
    }

}
