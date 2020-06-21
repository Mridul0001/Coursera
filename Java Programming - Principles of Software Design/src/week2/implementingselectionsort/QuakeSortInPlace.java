package week2.implementingselectionsort;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    private String source = "files/earthQuakeDataWeekDec6sample1.atom";
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        int index = from;
        for(int i=from+1; i<quakeData.size(); i++){
            if(quakeData.get(i).getDepth() > quakeData.get(index).getDepth()){
                index = i;
            }
        }
        return index;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData){
        for(int i=0; i<quakeData.size(); i++) {
            int index = getLargestDepth(quakeData, i);
            QuakeEntry temp = quakeData.get(i);
            quakeData.set(i, quakeData.get(index));
            quakeData.set(index, temp);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i=0; i<quakeData.size()-numSorted-1; i++){
            double magnitude1 = quakeData.get(i).getMagnitude();
            double magnitude2 = quakeData.get(i+1).getMagnitude();
            if(magnitude1>magnitude2){
                QuakeEntry temp1 = quakeData.get(i);
                QuakeEntry temp2 = quakeData.get(i+1);
                quakeData.set(i, temp2);
                quakeData.set(i+1, temp1);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        int numSorted = 0;
        for(int i=0; i<in.size(); i++){
            onePassBubbleSort(in, numSorted);
            numSorted++;
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i=0; i<quakes.size()-1; i++){
            if(quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude())
                return false;
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int numSorted = 0;
        for(int i=0; i<in.size(); i++){
            if(checkInSortedOrder(in)){
                System.out.println("Number of passes needed: " + i);
                break;
            }
            onePassBubbleSort(in, numSorted);
            numSorted++;
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {

        for (int i=0; i< in.size(); i++) {
            if(checkInSortedOrder(in)){
                System.out.println("Number of passes needed: " + i);
                break;
            }
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }

    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
//        sortByLargestDepth(list);
//        sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
//        sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
	}

    public static void main(String[] args) {
        QuakeSortInPlace quakeSortInPlace = new QuakeSortInPlace();
        quakeSortInPlace.testSort();
    }
}
