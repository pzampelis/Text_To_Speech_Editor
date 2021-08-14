package commands;

import java.awt.event.ActionEvent;

public class PasteText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getCopiedOrCutText() != null)
            gui.getTextArea().replaceSelection(gui.getCopiedOrCutText());
    }

}
