package encryptdecrypt.algorithms;

public final class UnicodeAlgorithm implements CypherAlgorithm {
    private static final int UNI_START = 32;
    private static final int UNI_END = 127;
    private static final int LENGTH = UNI_END - UNI_START + 1; // 97

    @Override
    public String encrypt(String text, long key) {
        StringBuilder sb = new StringBuilder();
        for (char aChar : text.toCharArray()) {
            aChar = (char) (((aChar - UNI_START + key) % LENGTH) + UNI_START);
            sb.append(aChar);
        }
        return sb.toString();
    }

    @Override
    public String decrypt(String cipher, long key) {
        StringBuilder sb = new StringBuilder();
        for (char aChar : cipher.toCharArray()) {
            aChar = (char) (((aChar - UNI_END - key) % LENGTH) + UNI_END);
            sb.append(aChar);
        }
        return sb.toString();
    }
}










