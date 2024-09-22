package com.varikoz272.nahrendb.input;

import java.util.List;

public abstract class Token {

    public final String word;

    public Token(String word) {
        this.word = word;
    }

    public abstract void checkCorrectCommand(List<Token> command, int thisId) throws TokenException;

    public static final class Fabric {

        public static final Fabric instance = new Fabric();

        public Token tokenFromString(String str) {
            if (str.length() < 2)
                return null;

            if (str.charAt(0) == '$')
                return new SpaceToken(str.substring(1));

            if (str.charAt(0) == '#')
                return new VariableToken(str.substring(1));

            if (str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'')
                return new ValueToken(str.substring(1, str.length() - 1));

            return new MethodToken(str);
        }
    }
}
