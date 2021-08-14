package commands;

import java.awt.event.ActionEvent;

public class CopyText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getTextArea().getSelectedText() != null)
            gui.setCopiedOrCutText(gui.getTextArea().getSelectedText());
    }

}
