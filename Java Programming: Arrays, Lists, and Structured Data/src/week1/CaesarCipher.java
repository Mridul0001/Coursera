package week1;

public class CaesarCipher {
    private String alphabet;
    private String alphabetSmall;
    private String shiftedAlphabet;
    private String shiftedAlphabetSmall;
    private int key;

    public CaesarCipher(int key){
        this.key = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetSmall = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        shiftedAlphabetSmall = alphabetSmall.substring(key) + alphabetSmall.substring(0, key);
    }

    public String encrypt(String input){
        //modify to handle lower and upper cases
        StringBuilder encrypted = new StringBuilder(input);

        for(int i = 0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            if(idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }else{
                idx = alphabetSmall.indexOf(currChar);
                if(idx != -1){
                    char newChar = shiftedAlphabetSmall.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return  encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2){
        //modify to handle lower and upper cases
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetKey1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetSmallKey1 = "abcdefghijklmnopqrstuvwxyz";
        String alphabetKey2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetSmallKey2 = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetKey1 = alphabetKey1.substring(key1) + alphabetKey1.substring(0, key1);
        String shiftedAlphabetSmallKey1 = alphabetSmallKey1.substring(key1) + alphabetSmallKey1.substring(0, key1);
        String shiftedAlphabetKey2 = alphabetKey2.substring(key2) + alphabetKey2.substring(0, key2);
        String shiftedAlphabetSmallKey2 = alphabetSmallKey2.substring(key2) + alphabetSmallKey2.substring(0, key2);
        for(int i = 0; i<encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if(i%2==0){
                int idx = alphabetKey1.indexOf(currChar);
                if(idx != -1) {
                    char newChar = shiftedAlphabetKey1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }else{
                    idx = alphabetSmallKey1.indexOf(currChar);
                    if(idx != -1){
                        char newChar = shiftedAlphabetSmallKey1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }else {
                int idx = alphabetKey2.indexOf(currChar);
                if(idx != -1) {
                    char newChar = shiftedAlphabetKey2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }else{
                    idx = alphabetSmallKey2.indexOf(currChar);
                    if(idx != -1){
                        char newChar = shiftedAlphabetSmallKey2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return  encrypted.toString();
    }

    public String decrypt(String input){
        CaesarCipher ccd = new CaesarCipher(26-key);
        return ccd.encrypt(input);
    }

    public static void main(String[] args) {
        //testing methods
        CaesarCipher c = new CaesarCipher(15);
        System.out.println("Encrypt: " + c.encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!\n" +
                "\n", 8, 21));


    }
}
