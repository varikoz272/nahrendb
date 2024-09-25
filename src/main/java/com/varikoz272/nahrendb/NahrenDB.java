package com.varikoz272.nahrendb;

import java.io.File;

public final class NahrenDB {

    public static final NahrenDB instance = new NahrenDB();

    public final File rootFolder;

    private NahrenDB() {
        this.rootFolder = FileSystem.getFileSystem().getWorkspaceFolder();
    }

    public void openConsole() {
        Console c = new Console();
        c.open();
    }

}
