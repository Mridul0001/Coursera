package week1.filteringdata;

public class MagnitudeFilter implements Filter{
    private double minMag;
    private double maxMag;
    private String name = "Magnitude";
    public MagnitudeFilter(double min, double max){
        minMag = min;
        maxMag = max;
    }

    public MagnitudeFilter(double min, double max, String name){
        minMag = min;
        maxMag = max;
        this.name = name;
    }


    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag)
            return true;
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
