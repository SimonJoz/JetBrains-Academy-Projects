package search.strategy;

import search.dataBase.PeopleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContainAnySearch implements SearchStrategy {

    @Override
    public List<String> search(String query, PeopleData data) {
        Map<String, List<Integer>> indexesMap = data.getIndexesMap();
        List<String> result = new ArrayList<>();
        for (String word : query.split(" ")) {
            if (indexesMap.containsKey(word)) {
                indexesMap.get(word).stream()
                        .map(data.getPeopleList()::get)
                        .forEach(result::add);
            }
        }
        return result;
    }
}
