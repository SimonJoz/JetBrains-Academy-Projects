package flashcards.app.controller;

import flashcards.app.IO.MyPrinter;
import flashcards.app.IO.file.FileManager;
import flashcards.app.model.Definition;
import flashcards.app.model.Library;

import java.util.Map;

public class ConsoleControl {
    private static final int INITIAL_LENGTH = 2;
    private static final String INITIAL_FILE_NAME = "./";
    private static final String EXPORT_COMMAND = "-export";
    private static final String IMPORT_COMMAND = "-import";


    private final Library library;
    private final FileManager fileManager;

    ConsoleControl(Library library, FileManager fileManager) {
        this.library = library;
        this.fileManager = fileManager;
    }

    void consoleImport(String[] args) {
        String filePath = getFilePath(args, IMPORT_COMMAND);
        if (filePath.length() > INITIAL_FILE_NAME.length()) {
            Map<String, Definition> tempMap = fileManager.importData(filePath);
            if (!tempMap.isEmpty()) {
                int importedCardsNo = tempMap.size();
                library.updateMap(tempMap);
                MyPrinter.importedCardsMessage(importedCardsNo);
            }
        }
    }

    void consoleExport(String[] args) {
        String filePath = getFilePath(args, EXPORT_COMMAND);
        if (filePath.length() > INITIAL_LENGTH) {
            fileManager.exportData(filePath);
            int exportCardsNo = library.getFlashcards().size();
            MyPrinter.cardsExportedMessage(exportCardsNo);
        }
    }

    private String getFilePath(String[] args, String command) {
        String filePath = INITIAL_FILE_NAME;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(command))
                filePath = filePath.concat(args[i + 1]);
        }
        return filePath;
    }

    /*
     * methods:
     * --- consoleExport(String[] args)
     * --- consoleImport(String[] args)
     *
     * -import correct command format [-import <file_Name>]
     * -export correct command format [-export <file_Name>]
     * e.g java flashcards.app.Main -export myFile.txt
     *
     * combining commands allowed:
     * e.g java flashcards.app.Main -import myFile.txt -export myFile.txt
     * e.g java flashcards.app.Main -export myFile.txt -import myFile.txt
     */

}

