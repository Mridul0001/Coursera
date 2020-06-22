package week3.generatingrandomtext;

import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne markovOne = new MarkovOne();
        markovOne.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = markovOne.getFollows("t");
        for(String s : follows){
            System.out.println(s);
        }
    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        ArrayList<String> follows = markov.getFollows("th");
        System.out.println(follows.size());
    }

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testGetFollows();
        tester.testGetFollowsWithFile();
    }
}
