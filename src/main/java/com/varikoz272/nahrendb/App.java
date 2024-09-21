package com.varikoz272.nahrendb;

import java.io.File;

public class App {

    public static void main(String[] args) {
        initWorkspace();
        NahrenDB.instance.openConsole();
    }

    public static void initWorkspace() {
        FileSystem fs = FileSystem.getFileSystem();
        File workspaceFolder = fs.getWorkspaceFolder();

        if (!workspaceFolder.exists()) {
            System.out.print("Creating workspace folder ...");
            workspaceFolder.mkdirs();
            System.out.println(" Done at " + workspaceFolder.getPath());
        }
    }
}
