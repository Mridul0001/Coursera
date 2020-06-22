package week3.interfaceandabstractclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap<String, ArrayList<String>> followsMap;

    public EfficientMarkovModel (int order) {
        N = order;
        myRandom = new Random();
        myText = "";  // no frickin' nulls
        followsMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - N);
        String key = myText.substring(index, index+N);
        sb.append(key);
        for(int k=0; k < numChars-N; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0)
                break;
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(next.length())+next;
        }

        return sb.toString();
    }

    protected ArrayList<String> getFollows (String key) {
        return followsMap.getOrDefault(key, new ArrayList<String>());
    }

    private void buildMap () {
        followsMap.clear();
        if (N <= 0) return;
        for (int k = 0; k < myText.length()-N+1; k++) {
            String key = myText.substring(k, k+N);
            if (! followsMap.containsKey(key))
                followsMap.put(key, super.getFollows(key));
        }
        printHashMapInfo();
    }

    private int largestValue () {
        int largestSize = 0;
        for (String key : followsMap.keySet()) {
            int thisSize = followsMap.get(key).size();
            if (thisSize > largestSize)
                largestSize = thisSize;
        }
        return largestSize;
    }

    void printHashMapInfo() {
        // we're told to print contents only if map is small.
        if (followsMap.keySet().size() < 15) {
            for (String key : followsMap.keySet())
                System.out.println(key + ": " + followsMap.get(key));
        }

        System.out.println("map has "+followsMap.keySet().size()+" keys.");
        int biggest = largestValue();
        System.out.println("size of largest array = "+biggest);
        System.out.println("keys of that size:");
        for (String key : followsMap.keySet()) {
            if (biggest == followsMap.get(key).size())
                System.out.println("'"+key+"'");
        }
        System.out.println("==============");
    }

    @Override
    public String toString(){
        return "EfficientMarkovModel of order " + N + ".";
    }
}
