package com.varikoz272.nahrendb.input;

import java.util.ArrayList;

public final class Tokenizer {

    public static final Tokenizer instance = new Tokenizer();

    private Tokenizer() {

    }

    public ArrayList<Token> split(String string) {
        String str = strToTokenizable(string);

        ArrayList<Token> tokens = new ArrayList<>();

        int wordLen = 0;
        boolean inMultiWord = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (!Character.isWhitespace(c) || inMultiWord)
                wordLen++;

            else {
                if (wordLen > 0)
                    tokens.add(Token.Fabric.instance.tokenFromString(str.substring(i - wordLen, i)));

                wordLen = 0;
            }

            if (c == '\'')
                inMultiWord = !inMultiWord;
        }

        return tokens;
    }

    private String strToTokenizable(String str) {
        if (str.charAt(str.length() - 1) == ' ')
            return str;

        StringBuilder sb = new StringBuilder(str);
        sb.append(' ');
        return sb.toString();
    }

}
