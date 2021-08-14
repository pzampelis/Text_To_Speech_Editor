package commands;

import java.awt.event.ActionEvent;

public class ToggleWordWrap implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getWordWrapCheckBoxItem().getState()) {
            gui.getTextArea().setLineWrap(true);
            gui.getTextArea().setWrapStyleWord(true);
        }
        else {
            gui.getTextArea().setLineWrap(false);
            gui.getTextArea().setWrapStyleWord(false);
        }
    }

}
