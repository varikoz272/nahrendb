package com.varikoz272.nahrendb.input;

import java.util.List;

public class MethodToken extends Token {

    public MethodToken(String word) {
        super(word);
    }

    public void checkCorrectCommand(List<Token> tokens, int thisId) throws TokenException {
        if (thisId - 1 < 0)
            throw new MethodTokenException(MethodTokenException.Reason.NoMethodInvoker);

        Token prevToken = tokens.get(thisId - 1);
        if (!(prevToken instanceof TableToken) && !(prevToken instanceof VariableToken))
            throw new MethodTokenException(MethodTokenException.Reason.NoMethodInvoker);
    }
}
