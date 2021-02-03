package com.company;

import java.util.Scanner;

public final class DataReader {
    private final Scanner scanner = new Scanner(System.in);

    public final String readText() {
        return scanner.nextLine();
    }

    public final double readDouble() {
        return scanner.nextDouble();
    }
}
