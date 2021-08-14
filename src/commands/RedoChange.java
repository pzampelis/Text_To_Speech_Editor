package commands;

import java.awt.event.ActionEvent;

public class RedoChange implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getUndoManager().canRedo())
            gui.getUndoManager().redo();
    }

}
