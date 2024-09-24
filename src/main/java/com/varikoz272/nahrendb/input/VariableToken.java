package com.varikoz272.nahrendb.input;

import java.util.List;

public class VariableToken extends ClassToken {

    public VariableToken(String word) {
        super(word);
    }

    public void checkCorrectCommand(List<Token> tokens, int thisId) throws TokenException {
        checkCorrectName();
    }

    public boolean hasMethod(String name) {
        return false;
    }

    public void executeQuiet(MethodToken method, List<ValueToken> args) throws MethodTokenException {
        // TODO: suka

    }

    public String execute(MethodToken method, List<ValueToken> args) throws MethodTokenException {
        return null;
    }

}
