package commands;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import java.awt.event.ActionEvent;

public class ApplyEncodingToAll implements ActionListener {

    private final EncodingStrategy encodingStrategy;


    public ApplyEncodingToAll(String encoding) {
        StrategiesFactory strategiesFactory = new StrategiesFactory();
        this.encodingStrategy = strategiesFactory.createStrategy(encoding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String originalText = gui.getTextArea().getText();
        gui.getTextArea().setText(encodingStrategy.encode(originalText));
    }

}
