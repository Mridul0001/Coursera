package week2.sortingatscale;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String[] words1 = q1.getInfo().split(" ");
        String[] words2 = q2.getInfo().split(" ");
        if(words1[words1.length -1].compareTo(words2[words2.length - 1]) ==0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return words1[words1.length -1].compareTo(words2[words2.length - 1]);
    }
}
