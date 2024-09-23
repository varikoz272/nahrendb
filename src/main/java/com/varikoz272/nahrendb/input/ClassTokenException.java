package com.varikoz272.nahrendb.input;

public class ClassTokenException extends TokenException {

    public ClassTokenException(String message) {
        super(message);
    }

    public ClassTokenException(Reason reason) {
        super(reason.getMessage());
    }

    public static enum Reason {
        NoSuchMethod;

        public String getMessage() {
            if (this == NoSuchMethod)
                return "no such method";

            return "";
        }

    }
}
