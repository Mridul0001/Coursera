package week2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {

    private ArrayList<String> repeat = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> hashMap;
    private ArrayList<String> usedCategories;

    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "files/data";

    public GladLibMap(){
        hashMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedCategories = new ArrayList<String>();
    }

    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        hashMap = new HashMap<String, ArrayList<String>>();
        usedCategories = new ArrayList<String>();
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal",
        "timeFrame", "verb", "fruit"};
        for(int i=0; i<categories.length;i++){
            ArrayList<String> al = new ArrayList<String>();
            al = readIt(source+"/" + categories[i] +".txt");
            hashMap.put(categories[i], al);
            al.clear();
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private void addUsedCategory(String label) {
        if (usedCategories.indexOf(label) == -1){
            usedCategories.add(label);
        }
    }

    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        addUsedCategory(label);
        return randomFrom(hashMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if(!repeat.contains(sub)){
            repeat.add(sub);
        }else{
            while (repeat.contains(sub)){
                sub = getSubstitute(w.substring(first+1,last));
            }
            repeat.add(sub);
        }
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public void makeStory(){
        repeat.clear();
        System.out.println("\n");
        String story = fromTemplate("files/data/madtemplate2.txt");
        printOut(story, 60);
    }

    public int totalWordsInMap(){
        int totalWords = 0;
        for(String s : hashMap.keySet()){
            totalWords += hashMap.get(s).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered() {
        ArrayList<String> content = new ArrayList<String>();
        int sum = 0;
        System.out.println("\nCategories used in this story:");
        for (int index = 0; index < usedCategories.size(); index++) {
            String category = usedCategories.get(index);
            content = hashMap.get(category);
            System.out.println("Category: " + category + "\tWords in category: "
                    + content.size());
            sum += content.size();
        }
        System.out.println("sum of possible words: " + sum);
        return sum;
    }

    public static void main(String[] args) {
        GladLibMap gl = new GladLibMap();
        gl.makeStory();
        System.out.println("Total words in map: " + gl.totalWordsInMap());
        gl.totalWordsConsidered();
        gl.totalWordsConsidered();
    }

}
