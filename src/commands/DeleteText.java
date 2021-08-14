package commands;

import java.awt.event.ActionEvent;

public class DeleteText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().replaceSelection("");
    }

}
