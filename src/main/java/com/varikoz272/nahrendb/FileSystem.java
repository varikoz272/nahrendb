package com.varikoz272.nahrendb;

import java.io.File;

public enum FileSystem {
    Windows,
    Linux;

    public File getWorkspaceFolder() {
        String userHome = System.getProperty("user.home");

        if (this == Windows)
            return new File(userHome + "\\appdata\\Local\\NahrenDB");

        if (this == Linux)
            return new File(userHome + "/.NahrenDB");

        return null;
    }

    public static FileSystem getFileSystem() {
        String osName = System.getProperty("os.name");

        if (osName.equals("Windows"))
            return Windows;

        if (osName.equals("Linux"))
            return Linux;

        return null;
    }
}
