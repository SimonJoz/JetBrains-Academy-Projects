package banking.util;

import java.util.Random;

import static java.lang.String.format;

public class CardGenerator {
    // Issuer Identification Number (IIN)
    public static final int IIN = 400000;
    public static int customerAccountNumber = 100000000;
    private static final Random rnd = new Random();
    private static int id = 1;

    public static int getId() {
        return id++;
    }

    public static String generateCardNo() {
        String cardNo = format("%d%d", IIN, customerAccountNumber);
        customerAccountNumber++;
        int checksum = calculateChecksum(cardNo);
        return format("%s%d", cardNo, checksum);
    }

    public static int calculateChecksum(String number) {
        String[] numbs = number.split("");
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
        int checksum = 10 - (sum % 10);
        return checksum == 10 ? 0 : checksum;
    }

    public static String generatePin() {
        return String.valueOf(rnd.nextInt((9999 - 1000) + 1) + 1000);
    }
}
