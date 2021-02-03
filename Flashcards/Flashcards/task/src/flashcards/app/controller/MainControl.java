package flashcards.app.controller;

import flashcards.app.IO.DataReader;
import flashcards.app.IO.file.FileManager;
import flashcards.app.model.Definition;
import flashcards.app.model.Library;

import java.util.Map;

import static flashcards.app.IO.MyPrinter.*;


public class MainControl {
    private final Library library = new Library();
    private final DataReader reader = new DataReader();
    private final FileManager fileManager = new FileManager(library);
    private final ConsoleControl consoleControl = new ConsoleControl(library, fileManager);
    private final LibraryControl libraryControl = new LibraryControl(reader, library);

    public void mainLoop(String[] args) {
        consoleImport(args);
        String option;
        do {
            option = getOptionChoice();
            switch (option.toUpperCase()) {
                case "EXIT":
                    exit(args);
                    break;
                case "ADD":
                    add();
                    break;
                case "REMOVE":
                    remove();
                    break;
                case "ASK":
                    ask();
                    break;
                case "EXPORT":
                    export();
                    break;
                case "IMPORT":
                    importData();
                    break;
                case "LOG":
                    exportLog();
                    break;
                case "HARDEST CARD":
                    hardestCard();
                    break;
                case "RESET STATS":
                    resetStats();
                    break;
            }
            printEnter();
        } while (!option.equals("EXIT"));
    }

    private void importData() {
        String fileName = reader.readFileName();
        Map<String, Definition> tempMap = fileManager.importData(fileName);
        if (!tempMap.isEmpty()) {
            int importedCardsNo = tempMap.size();
            library.updateMap(tempMap);
            importedCardsMessage(importedCardsNo);
        }
    }

    private void export() {
        String fileName = reader.readFileName();
        fileManager.exportData(fileName);
        Map<String, Definition> map = library.getFlashcards();
        int exportedCardsNo = map.size();
        cardsExportedMessage(exportedCardsNo);
    }

    private void exit(String[] args) {
        reader.scClose();
        consoleControl.consoleExport(args);
        System.exit(0);

        /*
         *   consoleControl.consoleExport(args) will be executed
         *   only if [-export <fileName>] command was initialized
         *   in console at start of app.
         *
         */
    }

    private void exportLog() {
        String fileName = reader.readFileName();
        fileManager.exportLog(fileName);
        logSavedMessage();
    }

    private void resetStats() {
        library.resetErrorCount();
        statsResetMessage();
    }

    private String getOptionChoice() {
        return reader.readOptionChoice();
    }

    private void hardestCard() {
        libraryControl.hardestCard();
    }

    private void ask() {
        libraryControl.ask();
    }

    private void remove() {
        libraryControl.remove();
    }

    private void add() {
        libraryControl.add();
    }

    private void consoleImport(String[] args) {
        consoleControl.consoleImport(args);
    }
}

