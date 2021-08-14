package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ResetZoom implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().setFont(new Font(gui.getTextArea().getFont().getFamily(), Font.PLAIN, gui.getMYFONTSIZE()));
    }

}
