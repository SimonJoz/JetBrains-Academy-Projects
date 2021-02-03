package sorting.sort;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SortMethods {

    public <T> Map<T, Integer> sortByCount(List<T> list) {
        Map<T, Integer> unsorted = mapData(list);
        Map<T, Integer> sorted = new LinkedHashMap<>();
        unsorted.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(ent -> sorted.put(ent.getKey(), ent.getValue()));
        return sorted;
    }

    public <T> Map<T, Integer> mapData(List<T> list) {
        Map<T, Integer> map = new LinkedHashMap<>();
        for (T t : list) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return map;
    }

}
