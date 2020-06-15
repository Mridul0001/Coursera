import edu.duke.*;
import java.io.File;
import java.util.Iterator;
/*This java class requires two jar files named courserajava.jar and apache-csv.jar.
* These are included in this folder but you might need to add them to path*/
public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count = 0;
        for (Point p : s.getPoints())
            count++;
        return count;
    }

    public double getAverageLength(Shape s) {
        double avgLength = 0;
        Point prevPt = s.getLastPoint();
        for (Point p : s.getPoints()){
            avgLength+=p.distance(prevPt);
            prevPt = p;
        }
        return avgLength/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        for (Point p : s.getPoints()){
            if(largestSide<p.distance(prevPt))
                largestSide = p.distance(prevPt);
            prevPt = p;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        Point prev = s.getLastPoint();
        double largestX = prev.getX();
        for (Point p: s.getPoints()){
            if(largestX < p.getX())
                largestX = p.getX();
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        double largestP = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            double perimeter = getPerimeter(shape);
            if(largestP < perimeter){
                largestP = perimeter;
            }
        }
        return largestP;
    }

    public String getFileWithLargestPerimeter() {
        double largestP = 0;
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape shape = new Shape(fr);
            double perimeter = getPerimeter(shape);
            if(largestP < perimeter){
                largestP = perimeter;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        System.out.println("Average length is: " + getAverageLength(s));
        System.out.println("Largest side is: " + getLargestSide(s));
        System.out.println("Largest x: " + getLargestX(s));
        System.out.println("Number of points: " + getNumPoints(s));
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
        System.out.println("Select multiple files");
        double large = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter in multiple files is: " + large);
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("Select multiple files");
        String large = getFileWithLargestPerimeter();
        System.out.println("File with largest perimeter: " + large);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
