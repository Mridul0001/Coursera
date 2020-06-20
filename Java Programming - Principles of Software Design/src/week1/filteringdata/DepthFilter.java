package week1.filteringdata;

public class DepthFilter implements Filter{
    private double minDep;
    private double maxDep;
    private String name = "Depth";
    public DepthFilter(double min, double max){
        minDep = min;
        maxDep = max;
    }

    public DepthFilter(double min, double max, String name){
        minDep = min;
        maxDep = max;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getDepth() >= minDep && qe.getDepth() <= maxDep)
            return true;
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
