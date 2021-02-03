package search.dataBase;

import java.util.*;

public class PeopleData {

    private final List<String> peopleList = new ArrayList<>();

    protected Map<String, List<Integer>> indexesMap = new HashMap<>();

    public List<String> getPeopleList() {
        return peopleList;
    }

    public Map<String, List<Integer>> getIndexesMap() {
        return indexesMap;
    }

    public void printPeopleList() {
        peopleList.forEach(System.out::println);
    }

    public void mapWordsWithIndex() {
        for (int i = 0; i < peopleList.size(); i++) {
            String[] words = peopleList.get(i).split(" ");
            int index = i;
            Arrays.stream(words).forEach(word -> addOrUpdate(index, word.toUpperCase()));
        }
    }

    private void addOrUpdate(int index, String word) {
        if (!indexesMap.containsKey(word)) {
            List<Integer> indexes = new ArrayList<>();
            indexesMap.put(word, indexes);
            indexes.add(index);
        } else
            indexesMap.get(word).add(index);
    }
}
