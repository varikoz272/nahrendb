package com.varikoz272.nahrendb.input;

import java.util.List;

public class ProgramToken extends Token {

    public ProgramToken(String word) {
        super(word);
    }

    public void checkCorrectCommand(List<Token> command, int thisId) throws TokenException {
        if (getOutput() == null)
            throw new ProgramTokenException(ProgramTokenException.Reason.NoSuchCommand);
    }

    public String getOutput() {
        if (word.equals("q"))
            return "exiting ...";

        if (word.equals("help"))
            return """
                    : help! ':q'    for quit
                    : help! ':help' for help
                    """;

        return null;
    }

}
