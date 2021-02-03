package sorting.utils;

import java.util.List;
import java.util.Map;

public class Printer {

    public static <T> void printMapStats(Map<T, Integer> map, int totalCount ) {
        map.forEach((key, value) -> System.out.println(key + ": " + value +
                String.format(" time(s), %.0f%%", (float) value / totalCount * 100)));
    }

    public static <T> void printList(List<T> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t).append(separator);
        }
        System.out.println(sb.toString());
    }
}
