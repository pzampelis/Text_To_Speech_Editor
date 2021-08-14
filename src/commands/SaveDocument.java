package commands;

import java.awt.event.ActionEvent;
import java.io.FileWriter;

public class SaveDocument implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String fileName = currentDocument.getFileName();
        String filePath = currentDocument.getFilePath();

        if (fileName == null)
            gui.getCommandsFactory().createCommand("SaveDocumentAs").actionPerformed(e);
        else {
            try {
                FileWriter fileWriter = new FileWriter(filePath + fileName);
                fileWriter.write(gui.getTextArea().getText());
                fileWriter.close();
                currentDocument.setOriginalText(gui.getTextArea().getText());
            } catch (Exception ex) {
                gui.displayProblemWindow("The file could not be saved");
            }
        }
    }

}
