package week1;

public class CaesarCipherTwo {
    private String alphabetKey1;
    private String alphabetSmallKey1;
    private String alphabetKey2;
    private String alphabetSmallKey2;
    private String shiftedAlphabetKey1;
    private String shiftedAlphabetSmallKey1;
    private String shiftedAlphabetKey2;
    private String shiftedAlphabetSmallKey2;
    private int key1, key2;

    public CaesarCipherTwo(int key1, int key2){
        this.key1 = key1;
        this.key2 = key2;
        alphabetKey1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetSmallKey1 = "abcdefghijklmnopqrstuvwxyz";
        alphabetKey2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetSmallKey2 = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetKey1 = alphabetKey1.substring(key1) + alphabetKey1.substring(0, key1);
        shiftedAlphabetSmallKey1 = alphabetSmallKey1.substring(key1) + alphabetSmallKey1.substring(0, key1);
        shiftedAlphabetKey2 = alphabetKey2.substring(key2) + alphabetKey2.substring(0, key2);
        shiftedAlphabetSmallKey2 = alphabetSmallKey2.substring(key2) + alphabetSmallKey2.substring(0, key2);
    }

    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
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
        CaesarCipherTwo ccd = new CaesarCipherTwo(26-key1, 26-key2);
        return ccd.encrypt(input);
    }

}

