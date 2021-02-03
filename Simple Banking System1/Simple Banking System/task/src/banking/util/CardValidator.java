package banking.util;

public final class CardValidator {

    public static boolean isCardNoValid(String number) {
        String withoutChecksum = number.substring(0, number.length() - 1);
        int checksum = Integer.parseInt(number.substring(number.length() - 1));
        String[] numbs = withoutChecksum.split("");
        int sum = 0;
        for (int i = 0; i < numbs.length; i++) {
            int numb = Integer.parseInt(numbs[i]);
            if (i % 2 == 0) {
                numb = numb * 2;
                if (numb > 9) {
                    numb -= 9;
                }
            }
            sum += numb;
        }
        return (sum + checksum) % 10 == 0;
    }
}
