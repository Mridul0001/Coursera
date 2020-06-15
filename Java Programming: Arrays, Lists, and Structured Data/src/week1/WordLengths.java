package week1;

import edu.duke.FileResource;

import java.util.Arrays;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        String[] words = new String[counts.length];
        Arrays.fill(words, "");
        for(String word : resource.words()){
            if(word.length()<31){
                counts[word.length()]++;
                words[word.length()] += word + " ";
            }
            else{
                counts[30]++;
                words[30] += word + " ";
            }
        }

        for(int i = 1; i < counts.length; i++){
            if(counts[i]!=0)
                System.out.println(counts[i] + " words of length " +i+ ":"  + words[i]);
        }
    }

    public void testCountWordsLenghts(){
        FileResource fileResource = new FileResource();
        int counts[] = new int[31];
        countWordLengths(fileResource, counts);
    }

    public static void main(String[] args) {
        WordLengths wl = new WordLengths();
        wl.testCountWordsLenghts();
    }
}
