package model.texttospeechapis;

public interface TextToSpeechAPI {

    void play(String s);

    void setVolume(float volume);

    void setPitch(int pitch);

    void setRate(int rate);

}
