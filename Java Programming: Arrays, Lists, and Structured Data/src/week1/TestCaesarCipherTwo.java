package week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {

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

    private String halfOfString(String message, int start){
        String half = "";
        for(int i=start; i<message.length(); i+=2){
            half += message.charAt(i);
        }
        System.out.println(half);
        return half;
    }

    private int getKey(String s){
        int[] freq = countLetters(s);
        int maxDex = maxIndex(freq);
        int dKey = maxDex - 4;
        if(maxDex < 4){
            dKey = 26 - (4-maxDex);
        }
        System.out.println(dKey);
        return dKey;
    }

    private String breakCaesarCipher(String input){
            String firstHalf = halfOfString(input, 0);
            String secondHalf = halfOfString(input, 1);
            int key1 = getKey(firstHalf);
            int key2 = getKey(secondHalf);
            String result = "";
            CaesarCipher cc = new CaesarCipher(0); //0 is passed because something needs to be passed and it will not be used
            result = cc.encryptTwoKeys(input, 26-key1, 26-key2);
            return result;
    }

    public void simpleTests(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
        String encrypted = cc.encrypt(message);
        System.out.println("keys are " + 21 + ", " + 8 + "\n" + encrypted);

        //decrypting
        System.out.println("Decrypted text is: " + cc.decrypt(encrypted));

        //automatic decryption
        System.out.println("Automatic decryption:  " + breakCaesarCipher(encrypted
        ));
    }

    public static void main(String[] args) {
        TestCaesarCipherTwo tt = new TestCaesarCipherTwo();
        tt.simpleTests();
    }
}
