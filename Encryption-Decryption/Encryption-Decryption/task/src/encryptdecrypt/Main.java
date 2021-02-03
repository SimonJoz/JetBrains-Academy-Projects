package encryptdecrypt;

import encryptdecrypt.algorithms.EncryptDecrypt;
import encryptdecrypt.algorithms.ShiftAlgorithm;
import encryptdecrypt.algorithms.UnicodeAlgorithm;

import static encryptdecrypt.console.ConsoleCommands.*;
import static encryptdecrypt.console.file.ConsoleFileManager.exportData;
import static encryptdecrypt.console.file.ConsoleFileManager.importData;

public class Main {
    public static final int INITIAL_KEY = 0;
    public static final String INITIAL_VALUE = "";

    public static void main(String[] args) {
        String mode = ENCRYPT; // default mode
        long key = INITIAL_KEY;
        String data = INITIAL_VALUE;
        String importPath = INITIAL_VALUE;
        String exportPath = INITIAL_VALUE;
        String algorithmType;
        boolean executeExport = false;
        boolean executeImport = false;
        boolean inputDataExist = false;

        EncryptDecrypt cypher = new EncryptDecrypt();
        cypher.setAlgorithm(new ShiftAlgorithm());  // default Algorithm
        try {
            for (int i = 0; i < args.length; i += 2) {
                String next = args[i + 1];
                switch (args[i]) {
                    case MODE_SPECIFIER -> mode = next;
                    case KEY_SPECIFIER -> key = Long.parseLong(next);
                    case OUT_SPECIFIER -> {
                        exportPath = next;
                        executeExport = true;
                    }
                    case DATA_SPECIFIER -> {
                        data = next;
                        inputDataExist = true;
                    }
                    case IN_SPECIFIER -> {
                        importPath = next;
                        executeImport = true;
                    }
                    case ALGORITHM -> {
                        algorithmType = next;
                        if (algorithmType.equals(UNICODE_ALG))
                            cypher.setAlgorithm(new UnicodeAlgorithm());
                    }
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Not valid format.");
            System.exit(1);
        }

        String importedData;
        switch (mode) {
            case ENCRYPT -> {
                importedData = inputDataExist ? data : importData(importPath, executeImport);
                String encryptedData = cypher.encrypt(importedData, key);
                exportData(exportPath, encryptedData, executeExport);
                if (!executeExport)
                    System.out.println(encryptedData);
            }
            case DECRYPT -> {
                importedData = inputDataExist ? data : importData(importPath, executeImport);
                String decryptedData = cypher.decrypt(importedData, key);
                exportData(exportPath, decryptedData, executeExport);
                if (!executeExport)
                    System.out.println(decryptedData);
            }
        }
    }
}