package com.varikoz272.nahrendb.input;

import java.util.List;

public abstract class ClassToken extends Token {

    public ClassToken(String word) {
        super(word);
    }

    public abstract boolean hasMethod(String name);

    public abstract void executeQuiet(MethodToken method, List<ValueToken> args) throws ClassTokenException;

    public abstract String execute(MethodToken method, List<ValueToken> args) throws ClassTokenException;

}
