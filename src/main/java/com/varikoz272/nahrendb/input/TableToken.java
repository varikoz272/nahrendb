package com.varikoz272.nahrendb.input;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.varikoz272.nahrendb.Console;
import com.varikoz272.nahrendb.FileSystem;

public class TableToken extends ClassToken {

    public final File file;

    public void checkCorrectCommand(List<Token> tokens, int thisId) throws TokenException {
        checkCorrectName();
    }

    public TableToken(String word) {
        super(word);
        this.file = new File(FileSystem.getFileSystem().getWorkspaceFolder().getPath() + "/" + word + ".txt");
    }

    public void init() throws IOException {
        file.createNewFile();
    }

    public boolean hasMethod(String name) {
        if (name.equals("of"))
            return true;

        return false;
    }

    public String execute(MethodToken.ExecutableMethod executable) throws MethodTokenException {

        if (executable.method.word.equals("of")) {
            if (executable.getArgs().size() == 0)
                throw new MethodTokenException(MethodTokenException.Reason.AmountOfArgs);
            return execute_of(executable.getArgs());
        }

        throw new MethodTokenException(MethodTokenException.Reason.NoSuchMethod);
    }

    public String execute_of(List<ValueToken> args) {

        try {
            this.init();
        }

        catch (IOException e) {
            return Console.javaExceptionMessage + e.getMessage() + "\n";
        }

        File tableFile = new File(FileSystem.getFileSystem().getWorkspaceFolder().getPath() + "/" + word);
        StringBuilder newHeading = new StringBuilder("");
        for (int i = 0; i < args.size(); i++) {
            var arg = args.get(i);
            if (i == args.size() - 1) {
                newHeading.append(arg.word);
                continue;
            }

            newHeading.append(arg.word + " | ");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(tableFile));
            writer.write(newHeading.toString());
            writer.close();
        }

        catch (IOException e) {
            return Console.javaExceptionMessage + e.getMessage() + "\n";
        }

        return """
                : success! added new table (%s) with columns:
                : success! %s
                """.formatted(tableFile.getPath(), newHeading.toString());
    }

    public void executeQuiet(MethodToken.ExecutableMethod executable) throws MethodTokenException {
        execute(executable);
    }

}
