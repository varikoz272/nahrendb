package com.varikoz272.nahrendb.input;

import java.util.ArrayList;
import java.util.List;

import com.varikoz272.nahrendb.Console;
import com.varikoz272.nahrendb.input.MethodToken.ExecutableMethod;

public abstract class Token {

    public final String word;

    public Token(String word) {
        this.word = word;
    }

    public void checkCorrectName() throws TokenException {
        if (word.length() == 0)
            throw new TokenException(TokenException.Reason.NoName);
    }

    public abstract void checkCorrectCommand(List<Token> command, int thisId) throws TokenException;

    public String getPointerToLocation(List<Token> cmd, int thisId) {
        int wordLength = word.length();
        int beforeLength = Fabric.instance.getCmdLength(cmd.subList(0, thisId));
        int afterLength = (thisId == cmd.size() - 1) ? 0
                : Fabric.instance.getCmdLength(cmd.subList(thisId + 1, cmd.size()));

        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < Console.inputPrefix.length() + wordLength / 2 + beforeLength; i++)
            sb.append(' ');

        sb.append("^");

        for (int i = 0; i < wordLength / 2 + afterLength + 3; i++)
            sb.append(' ');

        return sb.toString();
    }

    public String getPointerToLocation(List<Token> cmd) throws RuntimeException {
        for (int i = 0; i < cmd.size(); i++)
            if (cmd.get(i) == this)
                return getPointerToLocation(cmd, i);

        throw new RuntimeException("Not in list");
    }

    public static final class Fabric {

        public static final Fabric instance = new Fabric();

        public Token tokenFromString(String str) {
            if (str.charAt(0) == '$')
                return new TableToken(str.substring(1));

            if (str.charAt(0) == '#')
                return new VariableToken(str.substring(1));

            if (str.charAt(0) == ':')
                return new ProgramToken(str.substring(1));

            if (str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'')
                return new ValueToken(str.substring(1, str.length() - 1));

            return new MethodToken(str);
        }

        public int getCmdLength(List<Token> cmd) {
            int len = 0;
            for (var curCmd : cmd) {
                if (curCmd instanceof MethodToken)
                    len += curCmd.word.length();

                else if (curCmd instanceof ValueToken)
                    len += curCmd.word.length() + 2;

                else
                    len += curCmd.word.length() + 1;
            }

            return len;
        }

        public ExecutableMethod executableFromTokensUnsafe(List<Token> tokens) {
            MethodToken methodToken = null;
            ClassToken classToken = null;
            List<ValueToken> valueTokens = new ArrayList<>();

            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i) instanceof ValueToken) {
                    valueTokens.add((ValueToken) tokens.get(i));
                    continue;
                }

                if (!(tokens.get(i) instanceof MethodToken))
                    continue;

                methodToken = (MethodToken) tokens.get(i);
                classToken = (ClassToken) tokens.get(i - 1);
            }

            return new ExecutableMethod(classToken, methodToken, valueTokens);
        }

    }
}
