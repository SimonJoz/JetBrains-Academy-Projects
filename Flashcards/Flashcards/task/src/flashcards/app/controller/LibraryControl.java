package flashcards.app.controller;

import flashcards.app.IO.DataReader;
import flashcards.app.model.Definition;
import flashcards.app.model.Library;

import java.util.List;
import java.util.Map;

import static flashcards.app.IO.MyPrinter.*;

class LibraryControl {

    private final DataReader reader;
    private final Library library;

    LibraryControl(DataReader reader, Library library) {
        this.reader = reader;
        this.library = library;
    }

    void add() {
        Map<String, Definition> flashcards = library.getFlashcards();
        String card = getCard();
        if (flashcards.containsKey(card)) {
            mapContainsKeyMessage(card);
        } else {
            Definition definition = reader.readCardDefinition();
            if (flashcards.containsValue(definition)) {
                mapContainsValueMessage(definition.getDescription());
            } else {
                flashcards.put(card, definition);
                addedToMapMessage(card, definition.getDescription());
            }
        }
    }

    void remove() {
        String card = getCard();
        if (library.getFlashcards().containsKey(card)) {
            library.getFlashcards().remove(card);
            cardRemovedMessage();
        } else {
            cannotRemoveMessage(card);
        }
    }

    void ask() {
        Map<String, Definition> flashcards = library.getFlashcards();
        int number = reader.readNoOfQuestions();
        for (int i = 0; i < number; i++) {
            String randomKey = library.getRandomCard();
            askForRandomValue(randomKey);
            String answer = reader.readString();
            String definition = flashcards.get(randomKey).getDescription();
            if (!answer.equals(definition)) {
                library.incrementErrorCount(randomKey);
                if (flashcards.containsValue(new Definition(answer, 0))) {
                    String card = library.getCardKeyByValue(answer);
                    definitionAlreadyExistMessage(definition, card);
                } else {
                    wrongAnswerMessage(definition);
                }
            } else {
                correctAnswerMessage();
            }
        }

        /*
         *  -- ask about random card definition n times --- (n = userInput)
         *  -- check answer, update mistakes count.
         *  -- print suitable messages
         */
    }

    void hardestCard() {
        List<String> hardestCards = library.getHardestCardsList();
        if (!hardestCards.isEmpty()) {
            int mistakesMaxNo = library.getMaxMistakeCount();
            String cards = getHardestCardsAsString(hardestCards);
            if (mistakesMaxNo != 0) {
                hardestCardsMessage(cards, mistakesMaxNo);
            } else noHardestCardsMessage();
        } else noHardestCardsMessage();
    }

    private static String getHardestCardsAsString(List<String> hardestCards) {
        String cards = hardestCards.stream()
                .reduce("", (str, next) -> str += "\"" + next + "\" ");
        return cards.trim().replaceAll(" ", ", ");

        // concat all strings from given list and return it in required format.
    }

    private String getCard() {
        return reader.readCardName();
    }
}

