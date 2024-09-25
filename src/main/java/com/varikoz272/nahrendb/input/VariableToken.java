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

    public void executeQuiet(MethodToken.ExecutableMethod executable) throws MethodTokenException {
        execute(executable);
    }

    public String execute(MethodToken.ExecutableMethod executable) throws MethodTokenException {
        return null;
    }

}
