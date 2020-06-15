package week2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> hashMap;
    public WordsInFiles(){
        hashMap = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile(File f){
        FileResource fileResource = new FileResource(f);
        String fileName = f.getName();
        ArrayList<String> arrayList = new ArrayList<String>();
        for (String word : fileResource.words()) {
            if (hashMap.containsKey(word)) {
                ArrayList<String> fileList = hashMap.get(word);
                // fileList cannot be null.
                assert(fileList != null);
                if (! fileList.contains(fileName)) {
                    fileList.add(fileName);
                    // we changed it, must save it.
                    hashMap.put(word, fileList);
                }
            } else {
                ArrayList<String> fileList = new ArrayList<String>();
                fileList.add(fileName);
                hashMap.put(word, fileList);
            }
        }
    }

    private void buildWordFileMap(){
        hashMap.clear();
        DirectoryResource directoryResource = new DirectoryResource();
        for(File f : directoryResource.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    private int maxNumber(){
        int max = 0;
        for(String s : hashMap.keySet()){
            if(hashMap.get(s).size() > max){
                max = hashMap.get(s).size();
            }
        }
        return max;
    }

    private ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> arrayList = new ArrayList<String>();
        for(String s : hashMap.keySet()){
            if(hashMap.get(s).size() == number){
                arrayList.add(s);
            }
        }
        return  arrayList;
    }

    private void printFilesIn(String word){
        for(int i=0; i<hashMap.get(word).size(); i++){
            System.out.println(hashMap.get(word).get(i));
        }
    }

    public void tester(){
        buildWordFileMap();
        //testing maxNumber
        System.out.println("The maximum number of files any word appears in: " + maxNumber());
        //testing wordsInNumFiles
        ArrayList<String> arrayList = wordsInNumFiles(4);
        System.out.println("Words in number of files: " + arrayList.size());
        System.out.println("Words in num files");
        for(int i=0; i<arrayList.size(); i++){
            System.out.println(arrayList.get(i));
        }
        //testing printFilesIn
        System.out.println("The word 'tree' appear in: ");
        printFilesIn("tree");
    }

    public static void main(String[] args) {
        WordsInFiles wf = new WordsInFiles();
        wf.tester();
    }
}

//
//import edu.duke.*;
//
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class WordsInFiles {
//
//    private HashMap<String, ArrayList<String>> wordLists;
//
//    public WordsInFiles() {
//        wordLists = new HashMap<String, ArrayList<String>>();
//    }
//
//    private boolean hasValue (String s) { return s != null && ! s.isEmpty(); }
//
//    private void addWordsFromFile (File f) {
//        FileResource fr = new FileResource(f);
//        String filename = f.getName();
//        for (String word : fr.words()) {
//            if (wordLists.containsKey(word)) {
//                ArrayList<String> fileList = wordLists.get(word);
//                // fileList cannot be null.
//                assert(fileList != null);
//                if (! fileList.contains(filename)) {
//                    fileList.add(filename);
//                    // we changed it, must save it.
//                    wordLists.put(word, fileList);
//                }
//            } else {
//                ArrayList<String> fileList = new ArrayList<String>();
//                fileList.add(filename);
//                wordLists.put(word, fileList);
//            }
//        }
//    }  // addWordsFromFile
//
//    public void buildWordFileMap() {
//        wordLists.clear();
//        DirectoryResource dr = new DirectoryResource();
//        for (File f : dr.selectedFiles()) {
//            addWordsFromFile(f);
//        }
//    }  // buildWordFileMap
//
//    public int maxNumber() {
//        if (wordLists.isEmpty()) return -1;
//
//        // we have stuff, so find the largest number of files and return that.
//        // The number is the largest value.size(), where value is the ArrayList<String>
//        // in the map.
//        int max = 0;
//        for (String word : wordLists.keySet()) {
//            int size = wordLists.get(word).size();
//            if (size > max) max = size;
//        }
//        return max;
//    }  // maxNumber
//
//    public ArrayList<String> wordsInNumFiles (int number) {
//        ArrayList<String> exactly = new ArrayList<String>();
//
//        // No sense in doing anything for negative or zero requests, cuz
//        // there won't be any cases of those.
//        if (number > 0) {
//            for (String word : wordLists.keySet()) {
//                if (wordLists.get(word).size() == number) {
//                    exactly.add(word);
//                }
//            }
//        }
//
//        return exactly;
//    }  // wordsInNumFiles
//
//    public void printFilesIn (String word) {
//        if (hasValue(word)) {
//            if (wordLists.containsKey(word)) {
//                System.out.println("'"+word+"' appears in:");
//                for (String filename : wordLists.get(word))
//                    System.out.println("  "+filename);
//            } else {
//                System.out.println("word '"+word+"' not found in any scanned file");
//            }
//        }
//    }
//
//    void showMap() {
//        for (String word : wordLists.keySet()) {
//            System.out.print(word + ":\t");
//            for (String filename : wordLists.get(word)) {
//                // I'm not worrying about the trailing comma at the end of each line.
//                System.out.print(filename+", ");
//            }
//            System.out.println();
//        }
//    }  // showMap
//
//    public static void main(String[] args) {
//        WordsInFiles wf = new WordsInFiles();
//        wf.buildWordFileMap();
//        System.out.println(wf.wordsInNumFiles(5).size());
//    }
//}  // WordsInFiles