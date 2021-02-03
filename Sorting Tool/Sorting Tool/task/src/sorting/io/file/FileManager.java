package sorting.io.file;


import sorting.app.ArgsChecker;
import sorting.io.DataTypes;
import sorting.io.DataReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static sorting.io.DataTypes.LINE;


public class FileManager {
    private DataReader dataReader = new DataReader();

    public String importData(String filePath) {
        String data = "";
        try (var br = new BufferedReader(new FileReader(filePath))) {
            data = br.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            System.out.printf("Fail to import from \"%s\".\n", filePath);
        }
        return data;
    }

    public void exportData(String filePath) {
        try {
            PrintStream fileOut = new PrintStream(filePath);
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {
            System.out.printf("Fail to export to \"%s\".\n", filePath);
        }
    }

    public void exportIfRequested(ArgsChecker checker) {
        boolean hasExport = checker.hasExport();
        String outFilePath = checker.getOutFilePath();
        if (hasExport) {
            exportData(outFilePath);
        }
    }

    public List<String> importIfRequested(ArgsChecker checker, DataTypes type) {
        String inFilePath = checker.getInFilePath();
        boolean hasImport = checker.hasImport();
        String separator = "\\s+";
        if (LINE.equals(type)) {
            separator = "\n";
        }
        List<String> list;
        if (hasImport) {
            String data = importData(inFilePath);
            list = Arrays.asList(data.split(separator));
            Collections.sort(list);
        } else {
            list = dataReader.readString(type);
        }
        return list;
    }

    public List<Long> importIfRequested(ArgsChecker checker) {
        String inFilePath = checker.getInFilePath();
        boolean hasImport = checker.hasImport();
        List<Long> numbsList = new ArrayList<>();
        if (hasImport) {
            String data = importData(inFilePath);
            if (data.length() > 0) {
                List<String> stringsList = Arrays.asList(data.split("\\s+"));
                numbsList = stringsList.stream()
                        .mapToLong(Long::parseLong)
                        .sorted()
                        .boxed()
                        .collect(Collectors.toList());
            }
        } else {
            numbsList = dataReader.readLong();
        }
        return numbsList;
    }


}
