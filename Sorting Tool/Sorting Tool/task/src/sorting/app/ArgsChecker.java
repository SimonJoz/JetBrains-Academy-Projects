package sorting.app;

import sorting.io.DataTypes;
import sorting.sort.SortTypes;

import static sorting.app.Main.*;
import static sorting.io.DataTypes.*;
import static sorting.sort.SortTypes.BY_COUNT;
import static sorting.sort.SortTypes.NATURAL;

public class ArgsChecker {
    private SortTypes sortingType;
    private DataTypes dataType;
    private String inFilePath;
    private String outFilePath;
    private boolean hasImport;
    private boolean hasExport;


    public void check(String[] args) {
        sortingType = NATURAL;
        dataType = WORD;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case IMPORT:
                    if (i + 1 < args.length) {
                        inFilePath = "./" + args[++i];
                        hasImport = true;
                    }
                    break;
                case EXPORT:
                    if (i + 1 < args.length) {
                        outFilePath = "./" + args[++i];
                        hasExport = true;
                    }
                    break;
                case SORT_TYPE:
                    if (i + 1 < args.length && (NATURAL.getType().equals(args[i + 1])
                            || BY_COUNT.getType().equals(args[i + 1]))) {
                        sortingType = SortTypes.getTypeByString(args[++i]);
                    } else {
                        System.out.println("No sorting type defined!");
                    }
                    break;
                case DATA_TYPE:
                    if (i + 1 < args.length && (LINE.getType().equals(args[i + 1])
                            || LONG.getType().equals(args[i + 1]) || WORD.getType().equals(args[i + 1]))) {
                        dataType = DataTypes.getTypeByString(args[++i]);
                    } else {
                        System.out.println("No data type defined!");
                    }
                    break;
                default:
                    System.out.printf("\"%s\" isn't a valid parameter. It's skipped.\n", args[i]);
                    break;
            }

        }
    }


    public SortTypes getSortingType() {
        return sortingType;
    }

    public DataTypes getDataType() {
        return dataType;
    }

    public String getInFilePath() {
        return inFilePath;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public boolean hasImport() {
        return hasImport;
    }

    public boolean hasExport() {
        return hasExport;
    }
}