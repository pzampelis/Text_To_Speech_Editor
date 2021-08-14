package model.texttospeechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import view.TextToSpeechEditorView;

public class FreeTTSAdapter implements TextToSpeechAPI{

    private final Voice voice;
    private float volume;
    private int pitch;
    private int rate;


    public FreeTTSAdapter() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        this.voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null)
            this.voice.allocate();
    }

    public void play(String s) {
        try {
            voice.setVolume(volume);
            voice.setPitch(pitch);
            voice.setRate(rate);
            voice.speak(s);
        }
        catch(Exception e) {
            TextToSpeechEditorView.getSingletonView().displayProblemWindow("The text you requested could not be read aloud");
        }
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

}
