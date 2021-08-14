package commands;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileWriter;

public class SaveDocumentAs implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        FileDialog fileDialog = new FileDialog(gui.getMainWindowFrame(), "SaveDocument", FileDialog.SAVE);
        fileDialog.setVisible(true);

        if (fileDialog.getFile() != null) {
            String fileName = fileDialog.getFile();
            String filePath = fileDialog.getDirectory();
            currentDocument.setFileName(fileName);
            currentDocument.setFilePath(filePath);
            gui.getMainWindowFrame().setTitle(fileName + " - Text To Speech Editor");

            try {
                FileWriter fileWriter = new FileWriter(filePath + fileName);
                fileWriter.write(gui.getTextArea().getText());
                fileWriter.close();
                currentDocument.setOriginalText(gui.getTextArea().getText());
                currentDocument.setIsDocumentSaved(true);
            } catch (Exception ex) {
                gui.displayProblemWindow("The file could not be saved-as you requested");
            }
        }
    }

}
