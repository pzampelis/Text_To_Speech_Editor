package commands;

import java.awt.event.ActionEvent;

public class SelectAllText implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getTextArea().selectAll();
    }

}
