package week1.filteringdata;

public class DistanceFilter implements Filter{
    private Location location;
    private double distance;
    private String name = "Distance";

    public DistanceFilter(Location location, double distance){
        this.location = location;
        this.distance = distance;
    }

    public DistanceFilter(Location location, double distance, String name){
        this.location = location;
        this.distance = distance;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(qe.getLocation().distanceTo(location) < distance)
            return true;
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
