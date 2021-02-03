package flashcards.app.model;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private static final List<String> logger = new ArrayList<>();

    public static List<String> getLogger() {
        return logger;
    }

    public static void addToLog(String text) {
        logger.add(text);
    }
}
