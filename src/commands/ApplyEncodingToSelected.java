package commands;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;

import java.awt.event.ActionEvent;

public class ApplyEncodingToSelected implements ActionListener {

    private final EncodingStrategy encodingStrategy;


    public ApplyEncodingToSelected(String encoding) {
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        this.encodingStrategy = strategiesFactory.createStrategy(encoding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String originalSelectedText = gui.getTextArea().getSelectedText();
        if (originalSelectedText != null)
            gui.getTextArea().replaceSelection(encodingStrategy.encode(originalSelectedText));
        else
            gui.displayProblemWindow("Nothing was selected for encoding");
    }

}
