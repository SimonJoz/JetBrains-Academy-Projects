package flashcards.app.IO;

import static flashcards.app.model.Log.addToLog;

public class MyPrinter {

    /*
        *
        *   All MyPrinter methods are simple printing methods
        *   and could be replaced by single method e.g. printMsg(text).
        *   It might be considered as bad practise.
        *   It has been split only for clarity and better readability of controller classes
        *
     */

    public static void printEnter() {
        String message = "\n";
        System.out.print(message);
        addToLog(message);
    }

    public static void addedToMapMessage(String card, String definition) {
        String message = "The pair (\"" + card + "\":\"" + definition + "\") has been added.";
        System.out.println(message);
        addToLog(message);
    }

    public static void mapContainsValueMessage(String definition) {
        String message = "The definition \"" + definition + "\" already exists.";
        System.out.println(message);
        addToLog(message);
    }

    public static void mapContainsKeyMessage(String card) {
        String message = "The card \"" + card + "\" already exists.";
        System.out.println(message);
        addToLog(message);
    }

    public static void cardRemovedMessage() {
        String message = "The card has been removed.";
        System.out.println(message);
        addToLog(message);
    }

    public static void cannotRemoveMessage(String card) {
        String message = "Can't remove \"" + card + "\": there is no such card.";
        System.out.println(message);
        addToLog(message);
    }

    public static void askForRandomValue(String randomKey) {
        String message = "Print the definition of \"" + randomKey + "\":";
        System.out.println(message);
        addToLog(message);
    }

    public static void wrongAnswerMessage(String definition) {
        String message = "Wrong answer. The correct one is \"" + definition + "\".";
        System.out.println(message);
        addToLog(message);
    }

    public static void definitionAlreadyExistMessage(String definition, String card) {
        String message = "Wrong answer, The correct one is \"" + definition + "\"," +
                " you've just written the definition of \"" + card + "\".";
        System.out.println(message);
        addToLog(message);
    }

    public static void correctAnswerMessage() {
        String message = "Correct answer.";
        System.out.println(message);
        addToLog(message);
    }

    public static void cardsExportedMessage(int exportedCardsNo) {
        String message = exportedCardsNo + " cards have been saved.";
        System.out.println(message);
        addToLog(message);
    }

    public static void importedCardsMessage(int importedCardsNo) {
        if (importedCardsNo > 0) {
            String message = importedCardsNo + " cards have been loaded.";
            System.out.println(message);
            addToLog(message);
        }
    }

    public static void fileNotFoundMessage() {
        String message = "File not found.";
        System.out.println(message);
        addToLog(message);
    }

    public static void logSavedMessage() {
        String message = "The log has been saved.";
        System.out.println(message);
        addToLog(message);
    }

    public static void noHardestCardsMessage() {
        String message = "There are no cards with errors.";
        System.out.println(message);
        addToLog(message);
    }

    public static void hardestCardsMessage(String cards, int errors) {
        String str = "card is ";
        String str2 = "it.";
        if (cards.matches("\"\\w*\",.*")) {
            str = "cards are ";
            str2 = "them.";
        }
        String message = "The hardest " + str + cards + ". You have " + errors + " errors answering " + str2;
        System.out.println(message);
        addToLog(message);
    }

    public static void statsResetMessage() {
        String message = "Card statistics has been reset.";
        System.out.println(message);
        addToLog(message);
    }


}
