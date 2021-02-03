package banking.enums;

import java.util.Arrays;
import java.util.Map;

public enum Options {
    CREATE(1, "Create an account"),
    LOGIN(2, "Log into account"),
    EXIT(0, "Exit"),
    WRONG(3, "No such option !");

    private final int id;
    private final String desc;
    private static final Map<Integer, Options> map = Map.of(0,EXIT,1, CREATE,2,LOGIN);
    Options(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static void printOptions() {
        Arrays.stream(Options.values())
                .filter(v -> !v.equals(WRONG))
                .forEach(option -> System.out.printf("%d. %s\n", option.id, option.desc));
    }

    public static Options getOption(int id) {
        if (map.containsKey(id)) {
            return map.get(id);
        }
        return WRONG;
    }
}
