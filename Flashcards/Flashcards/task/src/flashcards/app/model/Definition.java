package flashcards.app.model;

import java.io.Serializable;
import java.util.Objects;

public class Definition implements Serializable {
    public static final  int INITIAL_COUNT = 0;
    private static final int INCREMENTER = 1;
    private final String description;
    private int mistakeCount;

    public Definition(String description, int mistakeCount) {
        this.description = description;
        this.mistakeCount = mistakeCount;
    }

    public String getDescription() {
        return description;
    }

    public int getMistakeCount() {
        return mistakeCount;
    }

    public void updateCount() {
        mistakeCount += INCREMENTER;
    }

    public void resetCount() {
        mistakeCount = 0;
    }

    @Override
    public String toString() {
        return  description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Definition that = (Definition) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
