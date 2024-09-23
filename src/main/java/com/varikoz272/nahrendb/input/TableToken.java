package com.varikoz272.nahrendb.input;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.varikoz272.nahrendb.FileSystem;

public class TableToken extends Token {

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
}
