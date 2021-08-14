package commands;

import java.awt.event.ActionEvent;

public class SelectedTextToSpeech implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        audioManager.setVolume(gui.getVolume());
        audioManager.setPitch(gui.getPitch());
        audioManager.setRate(gui.getRate());

        String selectedText = gui.getTextArea().getSelectedText();

        if (selectedText == null)
            gui.displayProblemWindow("Nothing was selected to be read aloud");
        else
            audioManager.play(selectedText);
    }

}
