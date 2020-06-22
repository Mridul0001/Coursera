package week3.interfaceandabstractclass;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
			System.out.println("Time taken: " + System.nanoTime());
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 2;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}

	public void testHashMap(){
		FileResource fileResource = new FileResource();
		String st = fileResource.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 615;
    	EfficientMarkovModel em = new EfficientMarkovModel(5);
    	runModel(em, st, size, seed);
	}

	public void compareMethods(){
    	FileResource fileResource = new FileResource();
		String st = fileResource.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
		EfficientMarkovModel em = new EfficientMarkovModel(2);
		MarkovModel mm = new MarkovModel(2);
		runModel(em, st, size, seed);
		runModel(mm, st, size, seed);
	}

	public static void main(String[] args) {
		MarkovRunnerWithInterface mri = new MarkovRunnerWithInterface();
//		mri.runMarkov();
		mri.testHashMap();
//    	mri.compareMethods();
    }
}
