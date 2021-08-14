package commands;

import java.awt.event.ActionEvent;

public class CutText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getCommandsFactory().createCommand("CopyText").actionPerformed(e);
        gui.getCommandsFactory().createCommand("DeleteText").actionPerformed(e);
    }

}
