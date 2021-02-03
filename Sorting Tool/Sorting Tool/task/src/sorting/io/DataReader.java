package sorting.io;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static sorting.io.DataTypes.LINE;


public class DataReader {
    private Scanner scanner = new Scanner(System.in);

    public List<String> readString(DataTypes type) {
        List<String> strings = new ArrayList<>();
        if (LINE.equals(type)) {
            scanner.useDelimiter("\n");
        }
        while (scanner.hasNext()) {
            strings = scanner.tokens()
                    .collect(Collectors.toList());
        }
        Collections.sort(strings);
        scanner.close();
        return strings;
    }

    public List<Long> readLong() {
        List<Long> numbs = new ArrayList<>();
        while (scanner.hasNext()) {
            String str = scanner.next();
            try {
                long numb = Long.parseLong(str);
                numbs.add(numb);
            } catch (NumberFormatException e) {
                System.out.printf("\"%s\" isn't a long. It's skipped.\n", str);
            }
        }
        Collections.sort(numbs);
        scanner.close();
        return numbs;
    }
}
