package search.dataReader.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    public List<String> importData(String filePath) {
        List<String> data = new ArrayList<>();
        try {
            data = Files.newBufferedReader(Path.of(filePath))
                    .lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Fail to import from " + filePath);
            System.exit(0);
        }
        return data;


    }

}
