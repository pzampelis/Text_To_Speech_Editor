package commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class OpenDocument implements ActionListener {

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
                    openDialog();
                gui.resetConfirmationWindowResponse();
            }
            else if (gui.getConfirmationWindowResponse() == JOptionPane.NO_OPTION)
                openDialog();
        }
        else
            openDialog();
    }

    public void openDialog() {
        FileDialog fileDialog = new FileDialog(gui.getMainWindowFrame(), "OpenDocument", FileDialog.LOAD);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            String fileName = fileDialog.getFile();
            String filePath = fileDialog.getDirectory();
            currentDocument.setFileName(fileName);
            currentDocument.setFilePath(filePath);
            currentDocument.setIsDocumentSaved(true);
            gui.getMainWindowFrame().setTitle(fileName + " - Text To Speech Editor");

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + fileName));
                gui.getTextArea().setText("");
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    gui.getTextArea().append(line + "\n");
                }

                bufferedReader.close();
                currentDocument.setOriginalText(gui.getTextArea().getText());
                gui.resetUndoManager();
            } catch (Exception ex) {
                gui.displayProblemWindow("The file you selected could not be opened");
            }
        }
        gui.resetConfirmationWindowResponse();
    }

}
