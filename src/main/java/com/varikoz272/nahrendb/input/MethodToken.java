package com.varikoz272.nahrendb.input;

import java.util.ArrayList;
import java.util.List;

public class MethodToken extends Token {

    public MethodToken(String word) {
        super(word);
    }

    public void checkCorrectCommand(List<Token> tokens, int thisId) throws TokenException {
        checkCorrectName();

        if (thisId - 1 < 0)
            throw new MethodTokenException(MethodTokenException.Reason.NoMethodInvoker);

        Token prevToken = tokens.get(thisId - 1);
        if (!(prevToken instanceof TableToken) && !(prevToken instanceof VariableToken))
            throw new MethodTokenException(MethodTokenException.Reason.NoMethodInvoker);

        var classToken = (ClassToken) prevToken;

        if (!classToken.hasMethod(word))
            throw new MethodTokenException(MethodTokenException.Reason.NoSuchMethod);
    }

    public static class ExecutableMethod {

        public final ClassToken invoker;
        public final MethodToken method;
        private List<ValueToken> args;

        public ExecutableMethod(ClassToken invoker, MethodToken method) {
            this.invoker = invoker;
            this.method = method;
            this.args = new ArrayList<>();
        }

        public ExecutableMethod(ClassToken invoker, MethodToken method, List<ValueToken> args) {
            this.invoker = invoker;
            this.method = method;
            this.args = args;

        }

        public void addArg(ValueToken arg) {
            this.args.add(arg);
        }

        public List<ValueToken> getArgs() {
            return args;
        }

    }
}
