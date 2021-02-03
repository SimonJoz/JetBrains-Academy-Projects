package sorting.app;

import sorting.io.DataTypes;
import sorting.io.file.FileManager;
import sorting.sort.SortMethods;
import sorting.sort.SortTypes;

import java.util.List;
import java.util.Map;

import static sorting.io.DataTypes.LINE;
import static sorting.io.DataTypes.WORD;
import static sorting.sort.SortTypes.BY_COUNT;
import static sorting.utils.Printer.printList;
import static sorting.utils.Printer.printMapStats;

public class Main {
    public static final String DATA_TYPE = "-dataType";
    public static final String SORT_TYPE = "-sortingType";
    public static final String IMPORT = "-inputFile";
    public static final String EXPORT = "-outputFile";

    public static void main(final String[] args) {
        FileManager fileManager = new FileManager();
        ArgsChecker checker = new ArgsChecker();
        SortMethods sortMethods = new SortMethods();
        checker.check(args);

        SortTypes sortingType = checker.getSortingType();
        DataTypes dataType = checker.getDataType();

        int totalCount;
        Map<String, Integer> sorted;
        switch (dataType) {
            case LINE:
                List<String> linesList = fileManager.importIfRequested(checker, LINE);
                totalCount = linesList.size();
                fileManager.exportIfRequested(checker);
                if (BY_COUNT.equals(sortingType)) {
                    sorted = sortMethods.sortByCount(linesList);
                    System.out.printf("Total lines: %d\n", totalCount);
                    printMapStats(sorted, totalCount);
                } else {
                    System.out.printf("Total lines: %d.\nSorted data:\n", totalCount);
                    printList(linesList, "\n");
                }
                break;

            case WORD:
                List<String> wordsList = fileManager.importIfRequested(checker, WORD);
                totalCount = wordsList.size();
                fileManager.exportIfRequested(checker);
                if (BY_COUNT.equals(sortingType)) {
                    sorted = sortMethods.sortByCount(wordsList);
                    System.out.printf("Total words: %d\n", totalCount);
                    printMapStats(sorted, totalCount);
                } else {
                    System.out.printf("Total words: %d.\nSorted data: ", totalCount);
                    printList(wordsList, " ");
                }
                break;

            case LONG:
                List<Long> numbsList = fileManager.importIfRequested(checker);
                totalCount = numbsList.size();
                fileManager.exportIfRequested(checker);
                if (BY_COUNT.equals(sortingType)) {
                    Map<Long, Integer> sortedNumbs = sortMethods.sortByCount(numbsList);
                    System.out.printf("Total numbers: %d\n", totalCount);
                    printMapStats(sortedNumbs, totalCount);
                } else {
                    System.out.printf("Total numbers: %d\nSorted data: ", totalCount);
                    printList(numbsList, " ");
                }
                break;
        }
    }
}
