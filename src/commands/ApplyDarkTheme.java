package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ApplyDarkTheme implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().setBackground(Color.decode("#2b2b2b"));
        gui.getTextArea().setForeground(Color.decode("#f8f8f2"));
        gui.getTextArea().setCaretColor(Color.decode("#f8f8f2"));

        gui.applyRightClickPopupMenuTheme(gui.getMYDARKBACKGROUND(), gui.getMYDARKFOREGROUND());
    }

}
