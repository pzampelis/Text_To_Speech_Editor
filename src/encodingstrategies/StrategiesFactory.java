package encodingstrategies;

public class StrategiesFactory {

    public EncodingStrategy createStrategy(String strategy) {
        if (strategy.equals("Atbash"))
            return new AtbashEncoding();
        else if (strategy.equals("Rot13"))
            return new Rot13Encoding();
        return null;
    }

}
