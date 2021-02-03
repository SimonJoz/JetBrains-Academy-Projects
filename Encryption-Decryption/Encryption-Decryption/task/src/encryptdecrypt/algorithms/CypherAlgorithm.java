package encryptdecrypt.algorithms;

interface CypherAlgorithm {
    String encrypt(String text, long key);
    String decrypt(String cipher, long key);

}
