package banking.util;

import java.util.Random;

import static java.lang.String.format;

public final class CardGenerator {
    // Issuer Identification Number (IIN)
    private static final int IIN = 400000;
    private static final int CUSTOMER_ACCOUNT_NO_SIZE = 9;
    private static final Random rnd = new Random();

    public static String generateCardNo() {
        String cardNo = format("%d%s", IIN, generateCustomerAccountNo());
        int checksum = calculateChecksum(cardNo);
        return format("%s%d", cardNo, checksum);
    }

    public static String generatePin() {
        return String.valueOf(rnd.nextInt((9999 - 1000) + 1) + 1000);
    }

    private static String generateCustomerAccountNo() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CUSTOMER_ACCOUNT_NO_SIZE; i++) {
            sb.append(rnd.nextInt(9));
        }
        return sb.toString();
    }

    private static int calculateChecksum(String number) {
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
}
