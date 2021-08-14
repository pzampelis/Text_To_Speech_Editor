package model.texttospeechapis;

public class TextToSpeechAPIFactory {

    public TextToSpeechAPI createTTSAPI(String ttsAPI) {
        if (ttsAPI.equals("FreeTTSAdapter"))
            return new FreeTTSAdapter();
        else if (ttsAPI.equals("FakeTextToSpeechAPI"))
            return new FakeTextToSpeechAPI();
        return null;
    }

}
