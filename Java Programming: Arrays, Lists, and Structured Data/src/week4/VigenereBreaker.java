package week4;

import java.io.File;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {

    public String sliceString(String message, int whichSlice, int totalSlices){
        String slice = "";
        for(int i=whichSlice; i<message.length(); i+=totalSlices){
            slice += message.charAt(i);
        }
        return slice;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon){
        int[] keyLengths = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0; i<klength; i++){
            int key = cc.getKey(sliceString(encrypted, i, klength));
            keyLengths[i] = key;
        }
        return keyLengths;
    }


    public void breakVigenere () {
        FileResource fileResource = new FileResource();
        String file = fileResource.asString();
//        FileResource fileResource1 = new FileResource();
//        HashSet<String> dictionary = readDictionary(fileResource1);
//        int[] keyLengths = tryKeyLength(file, 38, 'e');
//        VigenereCipher vg = new VigenereCipher(keyLengths);
//        for(int i=0; i<keyLengths.length; i++){
//            System.out.print(keyLengths[i] + ",");
//        }
//        System.out.println(vg.decrypt(file));
//        String decryption = breakForLanguage(file, dictionary);
//        System.out.println(decryption);
//        String decrypt = vg.decrypt(file);
//        System.out.println("For key length 38 : " + countWords(decrypt, dictionary));

        //changes for breakForAllLanguages
        HashMap<String, HashSet<String>> hashMap = new HashMap<String, HashSet<String>>();
        DirectoryResource directoryResource = new DirectoryResource();
        for(File f : directoryResource.selectedFiles()){
            FileResource fileResource2 = new FileResource(f);
            HashSet<String> dictionaryT = readDictionary(fileResource2);
            String language = f.getName();
            hashMap.put(language, dictionaryT);
        }
        breakForAllLanguages(file, hashMap);
    }


    //methods for unkonwn key length
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hashSet = new HashSet<String>();
        for(String s: fr.lines()){
            s = s.toLowerCase();
            hashSet.add(s);
        }
        return hashSet;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] split = message.split("\\W+");
        int count = 0;
        for(int i=0; i<split.length; i++){
            String word = split[i].toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        return count;
    }

    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int best = 0;
        int keyLength = 0;
        String result = "";
        for(int i=1; i<=100; i++){
            char mostCommon = mostCommomnCharIn(dictionary);
            int[] keyLengths = tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher vc = new VigenereCipher(keyLengths);
            String decrypt = vc.decrypt(encrypted);
            int newBest = countWords(decrypt, dictionary);
            if(newBest>best){
                result = decrypt;
                best = newBest;
                keyLength = i;
            }
        }
        System.out.println("Keylength used to decrypt is : " + keyLength);
        System.out.println("Valid words are : " + best);
        return result;
    }

    //methods for unknown languages
    public Character mostCommomnCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        for(String s : dictionary){
            s = s.toLowerCase();
            for(int i=0; i<s.length(); i++){
                if(!hashMap.containsKey(s.charAt(i))){
                    hashMap.put(s.charAt(i), 1);
                }else{
                    hashMap.put(s.charAt(i), hashMap.get(s.charAt(i))+1);
                }
            }
        }
        char max = ' ';
        int maxOccurence = 0;
        for(Character c : hashMap.keySet()){
            if(hashMap.get(c) > maxOccurence){
                maxOccurence = hashMap.get(c);
                max = c;
            }
        }
        return max;
    }

    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages){
        int maxWords = 0;
        String bestDecryption = "";
        String lang = "";
        for(String language : languages.keySet()){
            HashSet<String> dictionary = languages.get(language);
            String decryption = breakForLanguage(encrypted, dictionary);
            int countWords = countWords(decryption, dictionary);
            if(maxWords < countWords){
                bestDecryption = decryption;
                maxWords = countWords;
                lang = language;
            }
        }
        System.out.println("_________________________________________");
        System.out.println("Language is : " + lang);
        System.out.println(bestDecryption);
    }
    public static void main(String[] args) {
        VigenereBreaker vb = new VigenereBreaker();
        vb.breakVigenere();

    }
}
