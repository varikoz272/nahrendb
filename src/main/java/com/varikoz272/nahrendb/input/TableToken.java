package com.varikoz272.nahrendb.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        }

        throw new MethodTokenException(MethodTokenException.Reason.NoSuchMethod);
    }

    public void executeQuiet(MethodToken.ExecutableMethod executable) throws MethodTokenException {
        execute(executable);
    }

}
