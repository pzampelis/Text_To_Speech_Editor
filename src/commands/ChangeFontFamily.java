package commands;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ChangeFontFamily implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().setFont(new Font((String) gui.getFontBox().getSelectedItem(), Font.PLAIN, gui.getTextArea().getFont().getSize()));
    }

}
