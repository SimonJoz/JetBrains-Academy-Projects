package flashcards.app.model;


import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private final Map<String, Definition> flashcards = new HashMap<>();

    public void incrementErrorCount(String key) {
        Definition definition = flashcards.get(key);
        definition.updateCount();

        // increment definition mistake counter by 1;
    }

    public void resetErrorCount() {
        flashcards.keySet()
                .forEach(key -> flashcards.get(key).resetCount());

        // replace all current definition mistake stats with 0.
    }

    public String getCardKeyByValue(String description) {
        return flashcards.keySet()
                .stream()
                .filter(k -> flashcards.get(k).getDescription().equals(description))
                .reduce("", (str, next) -> str = next);

        /* .filter search for value description equal to given description.
               toString() return assigned key */
    }

    public String getRandomCard() {
        Random random = new Random();
        String[] keys = flashcards.keySet().toArray(String[]::new);
        return keys[random.nextInt(keys.length)];

        // generate random card from given map converted to array.
    }

    public List<String> getHardestCardsList() {
        if (flashcards.size() == 0) {
            return new ArrayList<>(0);
        } else {
            int max = getMaxMistakeCount();
            return flashcards.keySet()
                    .stream()
                    .filter(k -> flashcards.get(k).getMistakeCount() == (max))
                    .collect(Collectors.toList());

            // -- collect return list of Cards which left after filtration

        }
    }

    public void updateMap(Map<String, Definition> tempFlashcardsMap) {
        flashcards.putAll(tempFlashcardsMap);

        /*
          -- putAll adds all entrySets from given map
             if map contains key from given entrySet
             current one will be replaced
           */
    }

    public Map<String, Definition> getFlashcards() {
        return flashcards;
    }

    public int getMaxMistakeCount() {
        Definition max = Collections.max(flashcards.values(),
                Comparator.comparingInt(Definition::getMistakeCount));
        return max.getMistakeCount();

        // Collection.max return the biggest Definition;

    }


}
