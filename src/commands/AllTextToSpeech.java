package commands;

import java.awt.event.ActionEvent;

public class AllTextToSpeech implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        audioManager.setVolume(gui.getVolume());
        audioManager.setPitch(gui.getPitch());
        audioManager.setRate(gui.getRate());
        audioManager.play(gui.getTextArea().getText());
    }

}
