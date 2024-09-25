package com.varikoz272.nahrendb;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.varikoz272.nahrendb.input.MethodToken;
import com.varikoz272.nahrendb.input.ProgramToken;
import com.varikoz272.nahrendb.input.TableToken;
import com.varikoz272.nahrendb.input.Token;
import com.varikoz272.nahrendb.input.TokenException;
import com.varikoz272.nahrendb.input.Tokenizer;

public final class Console {

    public static final String inputPrefix = " $ ";
    public static final String javaExceptionMessage = "something went wrong...";

    public void open() {
        printGreetings();

        Scanner scanner = new Scanner(System.in);

        String cmd = "";
        while (!cmd.contains(":q")) {
            System.out.print(inputPrefix);

            cmd = scanner.nextLine();
            System.out.println(readAndGetMessage(cmd));
            System.out.println(executeAndGetMessage(cmd));
        }

        scanner.close();
    }

    private String executeAndGetMessage(String cmd) {
        List<Token> tokens = Tokenizer.instance.split(cmd);

        MethodToken.ExecutableMethod executable = Token.Fabric.instance.executableFromTokensUnsafe(tokens);
        return executable.invoker.execute(executable);
    }

    private String readAndGetMessage(String cmd) {
        List<Token> tokens = Tokenizer.instance.split(cmd);

        for (int i = 0; i < tokens.size(); i++) {
            try {
                tokens.get(i).checkCorrectCommand(tokens, i);
            }

            catch (TokenException e) {
                return tokens.get(i).getPointerToLocation(tokens, i) + e.getMessage();
            }
        }

        return executeAndGetMessage(tokens);
    }

    private String executeAndGetMessage(List<Token> cmd) {
        StringBuilder message = new StringBuilder("");

        for (var token : cmd) {

            if (token instanceof TableToken) {
                var varToken = (TableToken) token;
                try {
                    varToken.init();
                }

                catch (IOException e) {
                    return "Something went wrong ..." + e.getMessage();
                }

                message.append("table " + token.word + " successfully added (" + varToken.file.getPath() + ")\n");
            }

            if (token instanceof ProgramToken) {
                var progToken = (ProgramToken) token;

                message.append(progToken.getOutput());
            }
        }

        return message.toString();
    }

    private void printGreetings() {
        System.out.println("Welcome to NahrenDB");
        System.out.println("To quit type :q");
    }
}
