package commands;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitProgram implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((!currentDocument.getIsDocumentSaved() && !gui.getTextArea().getText().equals("")) ||
            (currentDocument.getIsDocumentSaved() && !gui.getTextArea().getText().equals(currentDocument.getOriginalText()))) &&
            gui.getConfirmationWindowResponse() != JOptionPane.NO_OPTION)
        {
            gui.displayConfirmationWindow("The document you are trying to close is not saved / has undergone changes. Do you want to save it?");

            if (gui.getConfirmationWindowResponse() == JOptionPane.YES_OPTION) {
                gui.getCommandsFactory().createCommand("SaveDocument").actionPerformed(e);

                if (currentDocument.getIsDocumentSaved() && gui.getTextArea().getText().equals(currentDocument.getOriginalText()))
                    System.exit(0);
                else
                    gui.resetConfirmationWindowResponse();
            }
            else if (gui.getConfirmationWindowResponse() == JOptionPane.NO_OPTION)
                System.exit(0);
        }
        else
            System.exit(0);
    }

}
