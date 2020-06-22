package week3.wordgramclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel{
    private int myOrder;
    private String[] myText;
    private Random myRandom;
    private HashMap<WordGram, ArrayList<String>> followsMap;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        followsMap = new HashMap<WordGram, ArrayList<String>>();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        if (text != null && ! text.isEmpty()) {
            myText = text.trim().split("\\s+");
            buildMap();
        }
    }

    public String toString() { return "EfficientMarkovWord of order "+myOrder; }

    private void buildMap () {
        followsMap.clear();
        if (myOrder <= 0) return;
        for (int k = 0; k < myText.length-myOrder; k++) {
            WordGram kGram = new WordGram(myText, k, myOrder);
            ArrayList<String> follows = getFollows(kGram);
            follows.add(myText[k+myOrder]);
            //System.out.println(kGram+": "+follows);
            followsMap.put(kGram, follows);
        }
        WordGram kGram = new WordGram(myText, myText.length-myOrder, myOrder);
        if (! followsMap.containsKey(kGram))
            followsMap.put(kGram, new ArrayList<String>());
//        printHashMapInfo();
    }

    void printHashMapInfo() {
        if (followsMap.keySet().size() < 15) {
            for (WordGram kGram : followsMap.keySet())
                System.out.println(kGram + ": " + followsMap.get(kGram));
        }

        System.out.println("map has "+followsMap.keySet().size()+" keys.");
        int biggest = largestValue();
        System.out.println("size of largest array = "+biggest);
        System.out.println("keys of that size:");
        for (WordGram kGram : followsMap.keySet()) {
            if (biggest == followsMap.get(kGram).size())
                System.out.println("'"+kGram+"'");
        }
        System.out.println("==============");
    }

    private int largestValue () {
        int largestSize = 0;
        for (WordGram kGram : followsMap.keySet()) {
            int thisSize = followsMap.get(kGram).size();
            if (thisSize > largestSize)
                largestSize = thisSize;
        }
        return largestSize;
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);
        String[] initialKeys = new String[myOrder];
        for (int k=0; k < myOrder; k++) {
            initialKeys[k] = myText[index+k];
            sb.append(initialKeys[k]).append(" ");
        }
        WordGram key = new WordGram(initialKeys, 0, initialKeys.length);

        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            //System.out.println(key+": "+follows);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }

        return sb.toString().trim();
    }

    protected ArrayList<String> getFollows (WordGram kGram) {
        return followsMap.getOrDefault(kGram, new ArrayList<String>());
    }
}
