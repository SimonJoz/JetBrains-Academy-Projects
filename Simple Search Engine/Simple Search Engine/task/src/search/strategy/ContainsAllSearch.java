package search.strategy;

import search.dataBase.PeopleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContainsAllSearch implements SearchStrategy {


    @Override
    public List<String> search(String query, PeopleData data) {
        List<String> result = new ArrayList<>();
        Map<String, List<Integer>> indexesMap = data.getIndexesMap();
        if (hasMapContainsAll(query, data)) {
            for (String word : query.split(" ")) {
                indexesMap.get(word)
                        .stream()
                        .map(data.getPeopleList()::get)
                        .forEach(value -> {
                            if (value.contains(query.toLowerCase()) && !result.contains(value)) {
                                result.add(value);
                            }
                        });
            }
        }
        return result;
    }

    private boolean hasMapContainsAll(String query, PeopleData data) {
        for (String word : query.split(" ")) {
            if (!data.getIndexesMap().containsKey(word)) {
                return false;
            }
        }
        return true;
    }
}
