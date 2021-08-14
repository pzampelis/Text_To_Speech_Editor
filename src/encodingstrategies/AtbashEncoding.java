package encodingstrategies;

public class AtbashEncoding extends TemplateEncoding {

    public String encode(String s) {
        StringBuilder str = new StringBuilder();
        char[] chars = s.toCharArray();

        for (char c : chars)
            str.append(mapCharacter(c));
        return str.toString();
    }

    protected char mapCharacter(char c) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String reverseAlphabet = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba";

        int index = alphabet.indexOf(c);

        if (index != -1)
            return reverseAlphabet.charAt(index);
        else
            return c;
    }

}
