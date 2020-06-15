package week1;

import edu.duke.FileResource;

public class TestCaesarCipher {

    private int[] countLetters(String encrypted){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i<encrypted.length(); i++){
            char ch = Character.toLowerCase(encrypted.charAt(i));
            int dex = alph.indexOf(ch);
            if(dex != -1){
                counts[dex]++;
            }
        }
        return counts;
    }
    private int maxIndex(int[] counts){
        int maxIndex = 0;
        int max = 0;
        for(int i = 0; i<counts.length; i++){
            if(max<counts[i]){
                maxIndex = i;
                max = counts[i];
            }
        }
        return maxIndex;
    }

    private String breakCaesarCipher(String input){
        int[] fields = countLetters(input);
        int maxDex = maxIndex(fields);
        int dKey = maxDex - 4;
        if(maxDex < 4){
            dKey = 26 - (4-maxDex);
        }
        System.out.println(dKey);
        CaesarCipher cc = new CaesarCipher(dKey);
        return cc.decrypt(input);
    }

    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);
        System.out.println("key is " + 18 + "\n" + encrypted);

        //decrypting
        System.out.println("Decrypted text is: " + cc.decrypt(message));

        //automatic decryption
        System.out.println("Automatic Decryption: " + breakCaesarCipher(message));
    }
}
