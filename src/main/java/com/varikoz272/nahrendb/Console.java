package com.varikoz272.nahrendb;

import java.util.Scanner;

public final class Console {

    public void open() {
        printGreetings();

        Scanner scanner = new Scanner(System.in);

        String cmd = "";
        while (!cmd.equals(":q")) {
            System.out.print("$ ");

            cmd = scanner.nextLine();
        }

        scanner.close();
    }

    private void printGreetings() {
        System.out.println("Welcome to NahrenDB");
        System.out.println("To quit type :q");
    }
}
