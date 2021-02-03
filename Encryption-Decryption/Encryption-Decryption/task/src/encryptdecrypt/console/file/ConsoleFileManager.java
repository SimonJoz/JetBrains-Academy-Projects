package encryptdecrypt.console.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class ConsoleFileManager {
    public static void exportData(String filePath, String data, boolean execute) {
        if (execute) {
            try {
                Files.write(Path.of(filePath), data.getBytes());
                System.out.println("File created, all data saved.");
            } catch (IOException e) {
                System.err.printf("ERROR! Cannot create file in directory: \"%s\".", filePath);
            }
        }
    }

    public static String importData(String filePath, boolean execute) {
        if (execute) {
            StringBuilder data = new StringBuilder();
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    data.append(line);
                }
            } catch (FileNotFoundException e) {
                System.err.printf("ERROR! File \"%s\" not found", filePath);

            }
            return data.toString();
        }
        return "";
    }
}

