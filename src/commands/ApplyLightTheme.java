package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ApplyLightTheme implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().setBackground(Color.WHITE);
        gui.getTextArea().setForeground(Color.decode("#333333"));
        gui.getTextArea().setCaretColor(Color.decode("#333333"));

        gui.applyRightClickPopupMenuTheme(gui.getMYLIGHTBACKGROUND(), gui.getMYLIGHTFOREGROUND());
    }

}
