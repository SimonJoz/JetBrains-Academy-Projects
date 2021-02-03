package search.app;

import search.dataBase.PeopleData;
import search.dataReader.DataReader;
import search.strategy.ContainAnySearch;
import search.strategy.ContainsAllSearch;
import search.strategy.ContainsNoneSearch;
import search.strategy.Searcher;

import java.util.Arrays;
import java.util.List;

public class SearchControl {
    private final DataReader reader;
    private final PeopleData peopleData;
    private final Searcher searcher = new Searcher();

    public SearchControl(DataReader reader, PeopleData peopleData) {
        this.reader = reader;
        this.peopleData = peopleData;
    }

    public void chooseSearchOption(){
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String input = reader.readString().toUpperCase();
        SearchOption option = SearchOption.getOptionByString(input);
        switch (option){
            case ALL:
                searcher.setSearchMethod(new ContainsAllSearch());
                search();
                break;
            case ANY:
                searcher.setSearchMethod(new ContainAnySearch());
                search();
                break;
            case NONE:
                searcher.setSearchMethod(new ContainsNoneSearch());
                search();
                break;
            case NO_SUCH_OPTION:
                System.out.println(SearchOption.NO_SUCH_OPTION.description);
                break;
        }
    }

    private void search() {
        System.out.println("Enter a name or email to search all suitable people.");
        String searchKey = reader.readString().toUpperCase();
        List<String> result = searcher.search(searchKey, peopleData);
        if (result.size() > 0) {
            System.out.printf("%d persons found:\n", result.size());
            for (String person : result) {
                System.out.println(person);
            }
        } else {
            System.out.println("No matching people found.");
        }
    }

    private enum SearchOption{
        ALL("ALL"),
        ANY("ANY"),
        NONE("NONE"),
        NO_SUCH_OPTION("Incorrect search option !");

        private String description;

        SearchOption(String description) {
            this.description = description;
        }

        public static SearchOption getOptionByString(String option){
            return Arrays.stream(SearchOption.values())
                    .filter(value -> value.description.equals(option))
                    .findFirst()
                    .orElse(NO_SUCH_OPTION);
        }
    }
}
