package search.strategy;

import search.dataBase.PeopleData;

import java.util.ArrayList;
import java.util.List;

public class ContainsNoneSearch implements SearchStrategy {

    @Override
    public List<String> search(String query, PeopleData data) {
        List<String> result = new ArrayList<>();
        boolean doesNotContains = true;
        for (String person : data.getPeopleList()) {
            for (String word : query.split(" ")) {
                if (person.toUpperCase().contains(word)) {
                    doesNotContains = false;
                    break;
                } else {
                    doesNotContains = true;
                }
            }
            if (doesNotContains) {
                result.add(person);
            }
        }
        return result;
    }
}
