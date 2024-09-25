package com.varikoz272.nahrendb.input;

public abstract class ClassToken extends Token {

    public ClassToken(String word) {
        super(word);
    }

    public abstract boolean hasMethod(String name);

    public abstract void executeQuiet(MethodToken.ExecutableMethod executable) throws ClassTokenException;

    public abstract String execute(MethodToken.ExecutableMethod executable) throws ClassTokenException;

}
