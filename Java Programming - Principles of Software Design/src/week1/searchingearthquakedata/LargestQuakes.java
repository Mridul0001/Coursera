package week1.searchingearthquakedata;

import java.util.*;
public class LargestQuakes {
    private String source = "files/nov20quakedata.atom";
    public void findLargestQuakes(){
        EarthQuakeParser earthQuakeParser = new EarthQuakeParser();
        ArrayList<QuakeEntry> quakeEntries = earthQuakeParser.read(source);
//        System.out.println("All earthquakes in file");
//        for(QuakeEntry qe : quakeEntries){
//            System.out.println(qe);
//        }
        System.out.println("Total number of earthquakes:" + quakeEntries.size());
        System.out.println("Index of largest magnitude: " + indexOfLargest(quakeEntries));
        System.out.println("Largest magnitude with howMany");
        ArrayList<QuakeEntry> howMany = getLargest(quakeEntries, 50);
        for(QuakeEntry qe : howMany){
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int index = 0;
        double max = 0;
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > max){
                max = qe.getMagnitude();
                index = quakeData.indexOf(qe);
            }
        }
        return index;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int j=0; j < howMany; j++) {
            double max = 0;
            int index = 0;
            for(int k=1; k < copy.size(); k++){
                QuakeEntry quake = copy.get(k);
                double mag = quake.getMagnitude();
                if (quake.getMagnitude() > max){
                    max = quake.getMagnitude();
                    index = k;
                }
            }

            largest.add(copy.get(index));
            copy.remove(index);
        }
        return largest;
    }

    public static void main(String[] args) {
        LargestQuakes largestQuakes = new LargestQuakes();
        largestQuakes.findLargestQuakes();
    }
}
