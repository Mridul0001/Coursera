package week3.interfaceandabstractclass;

import java.util.ArrayList;
import java.util.Random;

public class MarkovModel /*implements IMarkovModel*/ extends AbstractMarkovModel{
    private int N;

    public MarkovModel(int N) {
        myRandom = new Random();
        this.N = N;
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
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

    @Override
    public String toString(){
        return "MarkovModel of order " + N + ".";
    }
}
