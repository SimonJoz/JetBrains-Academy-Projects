package search.strategy;

import search.dataBase.PeopleData;

import java.util.List;

public class Searcher {

    private SearchStrategy strategy;


    public List<String> search(String query, PeopleData data) {
        return this.strategy.search(query, data);
    }

    public void setSearchMethod(SearchStrategy strategy) {
        this.strategy = strategy;
    }


}
