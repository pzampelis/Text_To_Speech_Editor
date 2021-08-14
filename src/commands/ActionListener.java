package commands;

import model.Document;
import model.texttospeechapis.TextToSpeechAPI;
import model.texttospeechapis.TextToSpeechAPIFactory;
import view.TextToSpeechEditorView;
import java.awt.event.ActionEvent;

public interface ActionListener extends java.awt.event.ActionListener {

    TextToSpeechEditorView gui = TextToSpeechEditorView.getSingletonView();
    Document currentDocument = gui.getCurrentDocument();

    TextToSpeechAPIFactory ttsAPIFactory = new TextToSpeechAPIFactory();
    TextToSpeechAPI audioManager = ttsAPIFactory.createTTSAPI("FreeTTSAdapter");


    void actionPerformed(ActionEvent e);

}
