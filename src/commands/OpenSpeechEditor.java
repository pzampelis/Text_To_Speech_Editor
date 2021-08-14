package commands;

import java.awt.event.ActionEvent;

public class OpenSpeechEditor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gui.getIsSpeechEditorOpen())
            gui.displaySpeechEditor();
        else
            gui.getSpeechEditorFrame().setVisible(true);
    }

}
