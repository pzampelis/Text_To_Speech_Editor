package commands;

import java.awt.event.ActionEvent;

public class UndoChange implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gui.getUndoManager().canUndo())
            gui.getUndoManager().undo();
    }

}
