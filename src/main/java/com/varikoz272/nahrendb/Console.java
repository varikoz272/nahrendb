package com.varikoz272.nahrendb;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.varikoz272.nahrendb.input.SpaceToken;
import com.varikoz272.nahrendb.input.Token;
import com.varikoz272.nahrendb.input.TokenException;
import com.varikoz272.nahrendb.input.Tokenizer;
import com.varikoz272.nahrendb.input.VariableToken;

public final class Console {

    public static final String inputPrefix = " $ ";

    public void open() {
        printGreetings();

        Scanner scanner = new Scanner(System.in);

        String cmd = "";
        while (!cmd.equals(":q")) {
            System.out.print(inputPrefix);

            cmd = scanner.nextLine();
            System.out.println(readAndGetMessage(cmd));
        }

        scanner.close();
    }

    private String readAndGetMessage(String cmd) {
        List<Token> tokens = Tokenizer.instance.split(cmd);

        for (int i = 0; i < tokens.size(); i++) {
            try {
                if (tokens.get(i) == null) {
                    System.out.println("tokens size = " + tokens.size() + " | i = " + i);
                    continue;
                }
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

            if (token instanceof SpaceToken) {
                var varToken = (SpaceToken) token;
                try {
                    varToken.init();
                }

                catch (IOException e) {
                    return "Something went wrong ..." + e.getMessage();
                }

                message.append("Table " + token.word + " successfully added (" + varToken.file.getPath() + ")");
            }
        }

        return message.toString();
    }

    private void printGreetings() {
        System.out.println("Welcome to NahrenDB");
        System.out.println("To quit type :q");
    }
}
