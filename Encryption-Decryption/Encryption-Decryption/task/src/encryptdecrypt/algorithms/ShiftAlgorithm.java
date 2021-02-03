package encryptdecrypt.algorithms;

public final class ShiftAlgorithm implements CypherAlgorithm {
    private static final int LENGTH = 26;
    private static final int LOWER_CASE_START = 97;
    private static final int UPPER_CASE_START = 65;
    private static final int LOWER_CASE_END = 122;
    private static final int UPPER_CASE_END = 90;

    @Override
    public String encrypt(String text, long key) {
        StringBuilder sb = new StringBuilder();
        for (char aChar : text.toCharArray()) {
            if (Character.isLetter(aChar))
                aChar = Character.isLowerCase(aChar) ?
                        (char) (((aChar - LOWER_CASE_START + key) % LENGTH) + LOWER_CASE_START) :
                        (char) (((aChar - UPPER_CASE_START + key) % LENGTH) + UPPER_CASE_START);
            sb.append(aChar);
        }
        return sb.toString();

        // e.g ('d' = 100 - current char) - (LOWER_CASE_START = 97) + (key = 5)
        // 100 - 97 + 5 = (8 % 26) + 97 = 105;

        // e.g ('A' = 65 - current char) - (UPPER_CASE_START = 65) + (key = 5)
        // 65 - 65 + 5 = (5 % 26) + 65 = 70;
    }

    @Override
    public String decrypt(String cipher, long key) {
        StringBuilder sb = new StringBuilder();
        for (char aChar : cipher.toCharArray()) {
            if (Character.isLetter(aChar))
                aChar = Character.isLowerCase(aChar) ?
                        (char) (((aChar - LOWER_CASE_END - key) % LENGTH) + LOWER_CASE_END) :
                        (char) (((aChar - UPPER_CASE_END - key) % LENGTH) + UPPER_CASE_END);
            sb.append(aChar);
        }
        return sb.toString();

        // e.g ('h' = 105 - current char) - (LOWER_CASE_END = 122) + (key = 5)
        // 105 - 122 - 5 = (-22 % 26) + 122 = 100;

        // e.g ('F' = 70 - current char) - (UPPER_CASE_END = 90) + (key = 5)
        // 70 - 90 - 5 = (-25 % 26) + 90 = 65;
    }
}
