package banking.enums;

import java.util.Arrays;
import java.util.Map;

public enum AccountOptions {
    BALANCE(1, "Balance"),
    INCOME(2, "Add income"),
    TRANSFER(3, "Do transfer"),
    CLOSE(4, "Close account"),
    LOGOUT(5, "Log out"),
    EXIT(0, "Exit"),
    WRONG(6, "No such option !");

    private final int id;
    private final String desc;
    private static final Map<Integer, AccountOptions> map = Map.of(
            0, EXIT, 1, BALANCE, 2, INCOME, 3, TRANSFER, 4, CLOSE, 5, LOGOUT);


    AccountOptions(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static void printOptions() {
        Arrays.stream(AccountOptions.values())
                .filter(v -> !v.equals(WRONG))
                .forEach(option -> System.out.printf("%d. %s\n", option.id, option.desc));
    }

    public static AccountOptions getOption(int id) {
        if (map.containsKey(id)) {
           return map.get(id);
        }
        return WRONG;
    }
}
