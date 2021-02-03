package tictactoe.io;

import java.util.Scanner;

public class Reader {
    private final Scanner sc = new Scanner(System.in);

    public String readCoordinates() {
        System.out.println("Enter coordinates: ");
        return sc.nextLine();
    }
}
