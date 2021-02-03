package machine.coffee.IO;

import java.util.Scanner;

public class DataReader {
    private final Scanner scanner = new Scanner(System.in);

    public int readInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }
    public void scClose() {
        scanner.close();
    }

}
