package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ZoomOut implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        int currentFontSize = gui.getTextArea().getFont().getSize();
        gui.getTextArea().setFont(new Font(gui.getTextArea().getFont().getFamily(), Font.PLAIN, currentFontSize - 10));
    }

}
