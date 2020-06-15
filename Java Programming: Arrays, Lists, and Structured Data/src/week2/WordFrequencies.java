package week2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    private void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fileResource = new FileResource();
        for(String word : fileResource.words()){
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if(index == -1){
                myWords.add(word);
                myFreqs.add(1);
            }else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }

    private  int findIndexOfMax(){
        int max = 0;
        int index = 0;
        for(int i=0; i<myFreqs.size(); i++){
            if(max < myFreqs.get(i)){
                max = myFreqs.get(i);
                index = i;
            }
        }
        return index;
    }

    public void tester(){
        findUnique();
        for(int i=0; i<myWords.size(); i++){
            System.out.println(myWords.get(i) + " : " + myFreqs.get(i));
        }
        System.out.println("Total number of unique words is: " + myWords.size());
        int indexOfMax = findIndexOfMax();
        System.out.println("The word " + myWords.get(indexOfMax) +" occurs the most with count of " + myFreqs.get(indexOfMax));
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
