package week2;

import edu.duke.FileResource;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charCount;
    public CharactersInPlay(){
        charNames = new ArrayList<String>();
        charCount = new ArrayList<Integer>();
    }

    private void update(String person){
        int index = charNames.indexOf(person);
        if(index == -1){
            charNames.add(person);
            charCount.add(1);
        }else{
            int value = charCount.get(index);
            charCount.set(index, value+1);
        }
    }

    private void findAllCharacters(){
        FileResource fileResource = new FileResource();
        String temp = "";
        for(String s : fileResource.lines()){
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i) == '.'){
                    //System.out.println(temp);
                    update(temp);
                    temp = "";
                    continue;
                }
                temp += s.charAt(i);
            }
            update(temp);
            temp = "";
        }
    }

    private void charactersWithNumParts(int num1, int num2){
        System.out.println("Character with count >= " + num1 + " and <= " + num2);
        for(int i=0; i<charNames.size(); i++){
            if(charCount.get(i) >= num1 && charCount.get(i) <= num2){
                System.out.println(charNames.get(i) + " : " + charCount.get(i));
            }
        }
    }

    public void tester(){
        findAllCharacters();
        for(int i=0; i<charNames.size(); i++){
            if(charCount.get(i) >=50){
                System.out.println(charNames.get(i) + " : " + charCount.get(i));
            }
        }
    }

    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
        cip.charactersWithNumParts(10, 15);
    }
}
