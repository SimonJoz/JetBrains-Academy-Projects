package flashcards.app.IO.file;

import flashcards.app.IO.MyPrinter;
import flashcards.app.model.Definition;
import flashcards.app.model.Library;
import flashcards.app.model.Log;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private Library library;

    public FileManager(Library library) {
        this.library = library;
    }

    public void exportData(String fileName) {
        try (var fos = new FileOutputStream(fileName);
             var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(library.getFlashcards());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // serialize and export list of maps to file.
    }

    @SuppressWarnings("unchecked")
    public Map<String, Definition> importData(String fileName) {
        Map<String, Definition> map = new HashMap<>(0);
        try (var fis = new FileInputStream(fileName);
             var ois = new ObjectInputStream(fis)) {
            map = (Map<String, Definition>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            MyPrinter.fileNotFoundMessage();
        }
        return map;
        // deserialize and import map from file.
    }

    public void exportLog(String fileName) {
        try (var fos = new BufferedWriter(new FileWriter(fileName))) {
            for (String s : Log.getLogger()) {
                fos.write(s + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // export logList containing all output and input from console.
    }
}