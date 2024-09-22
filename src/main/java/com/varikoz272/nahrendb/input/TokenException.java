package com.varikoz272.nahrendb.input;

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Reason reason) {
        super(reason.getMessage());
    }

    public static enum Reason {
        NoName;

        public String getMessage() {
            if (this == NoName)
                return "no name\n" + "\n" + getHints();

            return "Error";
        }

        public String getHints() {
            if (this == NoName)
                return """
                        : hint! everyting should have name
                        : hint! add name after passing special characters
                        : hint! names cannot contain special characters: ' $ # and spaces
                        """;
            return "";
        }
    }
}
