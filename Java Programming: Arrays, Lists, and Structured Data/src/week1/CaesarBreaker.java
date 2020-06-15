package week1;

import edu.duke.FileResource;

public class CaesarBreaker {
    public int[] countLetters(String encrypted){
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
    public int maxIndex(int[] counts){
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

    public int getKey(String s){
        int[] freq = countLetters(s);
        int maxDex = maxIndex(freq);
        int dKey = maxDex - 4;
        if(maxDex < 4){
            dKey = 26 - (4-maxDex);
        }
        System.out.println(dKey);
        return dKey;
    }

    public String halfOfString(String message, int start){
        String half = "";
        for(int i=start; i<message.length(); i+=2){
            half += message.charAt(i);
        }
        System.out.println(half);
        return half;
    }

    public String decrypt(String encrypted, int key){
        CaesarCipher cc = new CaesarCipher(26-key);
        String res = cc.encrypt(encrypted);
        return res;
    }

    public String decryptTwoKeys(String encrypted){
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        int key1 = getKey(firstHalf);
        int key2 = getKey(secondHalf);
        String result = "";
        CaesarCipher cc = new CaesarCipher(0); //0 is passed because something needs to be passed and it will not be used
        result = cc.encryptTwoKeys(encrypted, 26-key1, 26-key2);
        return result;
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource();
        String message = fr.asString();
        CaesarBreaker td = new CaesarBreaker();
        System.out.println(td.decryptTwoKeys(message));
    }

}
