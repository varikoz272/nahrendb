package com.varikoz272.nahrendb.input;

public class MethodTokenException extends TokenException {

    public MethodTokenException(Reason reason) {
        super(reason.getMessage());
    }

    public static enum Reason {
        NoMethodInvoker;

        public String getMessage() {
            if (this == NoMethodInvoker)
                return "no method invoker\n" + "\n" + getHints();

            return "Error";
        }

        public String getHints() {
            if (this == NoMethodInvoker)
                return """
                        : hint! method should have its invoker:
                        : hint!
                        : hint! $your_table add 'value1' 'value2'
                        : hint!     ^        ^     ^        ^
                        : hint!  invoker  method  arguments
                                    """;
            return "";
        }
    }

}
