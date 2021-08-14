package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

    public String encode(String s) {
        StringBuilder str = new StringBuilder();
        char[] chars = s.toCharArray();

        for (char c : chars)
            str.append(mapCharacter(c));
        return str.toString();
    }

    protected char mapCharacter(char c) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String reverseAlphabet = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm";

        int index = alphabet.indexOf(c);

        if (index != -1)
            return reverseAlphabet.charAt(index);
        else
            return c;
    }

}
