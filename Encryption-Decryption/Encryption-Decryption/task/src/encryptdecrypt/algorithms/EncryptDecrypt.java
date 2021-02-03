package encryptdecrypt.algorithms;

public class EncryptDecrypt {
    public CypherAlgorithm algorithm;

    public void setAlgorithm(CypherAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String encrypt(String text, long key){
       return this.algorithm.encrypt(text,key);
    }

    public String decrypt(String cipher, long key){
       return this.algorithm.decrypt(cipher,key);
    }
}
