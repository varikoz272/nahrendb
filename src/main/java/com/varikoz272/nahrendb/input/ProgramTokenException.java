package com.varikoz272.nahrendb.input;

public class ProgramTokenException extends TokenException {

    public ProgramTokenException(String message) {
        super(message);
    }

    public ProgramTokenException(Reason reason) {
        super(reason.getMessage());
    }

    public static enum Reason {
        NoSuchCommand;

        public String getMessage() {
            return "no such command";
        }
    }

}
