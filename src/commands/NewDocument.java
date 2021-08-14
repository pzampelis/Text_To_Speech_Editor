package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewDocument implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentDocument.getIsDocumentSaved() ||
            gui.getConfirmationWindowResponse() == JOptionPane.NO_OPTION ||
            gui.getTextArea().getText().equals(""))
        {
            gui.getTextArea().setText("");
            gui.getMainWindowFrame().setTitle("New Unsaved Document - Text To Speech Editor");
            currentDocument.setFileName(null);
            currentDocument.setFilePath(null);
            currentDocument.setIsDocumentSaved(false);
            currentDocument.setOriginalText("");
            gui.resetUndoManager();
        }
        else {
            gui.displayConfirmationWindow("The document you are trying to erase is not saved / has undergone changes. Do you want to save it?");

            if (gui.getConfirmationWindowResponse() == JOptionPane.YES_OPTION)
                gui.getCommandsFactory().createCommand("SaveDocument").actionPerformed(e);
            else if (gui.getConfirmationWindowResponse() == JOptionPane.NO_OPTION)
                gui.getCommandsFactory().createCommand("NewDocument").actionPerformed(e);
            gui.resetConfirmationWindowResponse();
        }
    }

}
