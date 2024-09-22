package com.varikoz272.nahrendb.input;

import java.util.List;

public class ValueToken extends Token {

    public ValueToken(String word) {
        super(word);
    }

    public void checkCorrectCommand(List<Token> tokens, int thisId) throws TokenException {
        checkCorrectName();
    }
}
