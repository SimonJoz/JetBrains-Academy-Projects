package search.strategy;

import search.dataBase.PeopleData;

import java.util.List;

public interface SearchStrategy {
    List<String> search(String query, PeopleData data);

}
