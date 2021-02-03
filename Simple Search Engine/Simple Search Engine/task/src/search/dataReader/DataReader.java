package search.dataReader;

import java.util.List;
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

    public String readString() {
        return scanner.nextLine();
    }

    public void scClose() {
        scanner.close();
    }

    public void readPeople(List<String> peoples) {
        System.out.println("Enter the number of people:");
        int noOfPeople = readInt();
        System.out.println("Enter all people:");
        for (int i = 0; i < noOfPeople; i++) {
            String person = readString();
            peoples.add(person);
        }
    }
}
