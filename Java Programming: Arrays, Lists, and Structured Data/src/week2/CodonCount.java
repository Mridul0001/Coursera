package week2;

import edu.duke.FileResource;

import javax.imageio.stream.ImageInputStream;
import java.util.HashMap;

public class CodonCount {
    private HashMap<String, Integer> hashMap;
    public CodonCount(){
        hashMap = new HashMap<String, Integer>();
    }

    private void buildCodonMap(int start, String dna){
        hashMap.clear();
        String codon = "";
        for(int i = start; i<dna.length()-3; i+=3){
            codon = dna.substring(i,i+3);
            if(hashMap.keySet().contains(codon)){
                hashMap.put(codon, hashMap.get(codon)+1);
            }else{
                hashMap.put(codon, 1);
            }
        }
    }

    private String getMostCommonCodon(){
        int max = 0;
        String codon = "";
        for(String s : hashMap.keySet()){
            if(max<hashMap.get(s)){
                max = hashMap.get(s);
                codon = s;
            }
        }
        return codon;
    }

    private void printCodonCounts(int start, int end){
        System.out.println("Count of codons between " + start + " and " + end + ": ");
        for(String s : hashMap.keySet()){
            if(hashMap.get(s) >= start && hashMap.get(s) <= end){
                System.out.println(s + "\t" + hashMap.get(s));
            }
        }
    }

    public void tester(){
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString().trim().toUpperCase();
        for(int i = 0; i<3; i++){
            buildCodonMap(i, dna);
            String common = getMostCommonCodon();
            System.out.println("Reading frame starting with " + i + " results" +
                    " in " + hashMap.size() + " unique codon");
            System.out.println("And most common codon is " + common + " with" +
                    " count " + hashMap.get(common));
            printCodonCounts(5, 7);
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
         cc.tester();
    }
}
