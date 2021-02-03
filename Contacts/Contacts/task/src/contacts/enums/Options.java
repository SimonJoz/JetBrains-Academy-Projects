package contacts.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Options {
    ADD, REMOVE, EDIT, COUNT, INFO, EXIT, WRONG;

    public static String getOptions() {
        return Arrays.stream(Options.values())
                .filter(v -> !v.equals(WRONG))
                .map(v -> String.valueOf(v).toLowerCase())
                .collect(Collectors.joining(", "));
    }

    public static Options getOption(String option) {
        try {
            return Options.valueOf(option.toUpperCase());
        } catch (IllegalArgumentException e) {
            return WRONG;
        }
    }
}
