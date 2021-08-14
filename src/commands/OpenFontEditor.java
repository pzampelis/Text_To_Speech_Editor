package commands;

import java.awt.event.ActionEvent;

public class OpenFontEditor implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gui.getIsFontEditorOpen())
            gui.displayFontEditor();
        else {
            gui.getFontSizeSpinner().setValue(gui.getTextArea().getFont().getSize());
            gui.getFontEditorFrame().setVisible(true);
        }
    }

}
