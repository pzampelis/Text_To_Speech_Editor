package commands;

import java.awt.event.ActionEvent;

public class OpenAboutWindow implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.displayAboutWindow();
    }

}
