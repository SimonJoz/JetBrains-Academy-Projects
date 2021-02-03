package banking.enums;

import java.util.Arrays;

public enum AccountOptions {
    BALANCE(1, "Balance"),
    LOGOUT(2, "Log out"),
    EXIT(0, "Exit"),
    WRONG(3, "No such option !");

    private final int id;
    private final String desc;

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
        switch (id) {
            case 0:
                return EXIT;
            case 1:
                return BALANCE;
            case 2:
                return LOGOUT;
            default:
                return WRONG;
        }
    }
}
