import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {
    public void totalBirths(){
        int totalBirth = 0;
        int girlBirths = 0;
        int boyBirths = 0;
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        for(CSVRecord csvRecord : csvParser){
            int num = Integer.parseInt(csvRecord.get(2));
            if(csvRecord.get(1).equals("M"))
                boyBirths += num;
            else
                girlBirths += num;
            totalBirth += num;
        }
        System.out.println("Total births: " + totalBirth);
        System.out.println("Girl births: " + girlBirths);
        System.out.println("Boy births: " + boyBirths);
    }

    public int getRank(int year, String name, String gender){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        int rank = 0;
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get(1).equals(gender)){
                rank++;
                if(csvRecord.get(0).equals(name))
                    return rank;
            }else if(rank!=0){
                return -1;
            }
        }
        return -1;
    }

    public String getName(int year, int rank, String gender){
        int rankCheck = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File f : directoryResource.selectedFiles()){
            String fileName = f.getName();
            if(!fileName.contains(Integer.toString(year)))
                continue;
            else{
                FileResource fileResource = new FileResource(f);
                CSVParser csvParser = fileResource.getCSVParser();
                for(CSVRecord csvRecord : csvParser){
                    if(csvRecord.get(1).equals(gender)){
                        rankCheck++;
                        if(rankCheck == rank)
                            return csvRecord.get(0);
                    }else if(rankCheck != 0){
                        return "NO NAME";
                    }
                }
            }
        }
        return "NO NAME";
    }

    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        DirectoryResource dc = new DirectoryResource();
        String old = "";
        String newY = "";
        for (File f : dc.selectedFiles()){
            if(f.getName().contains(Integer.toString(year)))
                old = f.getPath();
            else if(f.getName().contains(Integer.toString(newYear)))
                newY = f.getPath();
            if(!old.isEmpty() && !newY.isEmpty())
                break;
        }
        if(old.isEmpty() || newY.isEmpty())
            return "NO NAME";
        int tempRank = rank(name, year, gender, old);
        if(tempRank == -1)
            return "NO NAME";
        return name(tempRank, newY, gender);
    }

    public int yearOfHighestRank(String name, String gender){
        int year = -1;
        int tempYear = 0;
        int highestRank = Integer.MAX_VALUE;
        int tempRank = 0;
        String path = "";
        DirectoryResource directoryResource = new DirectoryResource();
        for (File f : directoryResource.selectedFiles()){
            tempYear = Integer.parseInt(f.getName().substring(3,7));
            path = f.getPath();
            tempRank = rank(name, tempYear, gender, path);
            if(highestRank > tempRank){
                highestRank = tempRank;
                year = tempYear;
            }
        }
        return year;
    }

    public double getAverageRank(String name, String gender){
        double averageRank = 0;
        int count = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            count++;
            String path = f.getPath();
            int year = Integer.parseInt(f.getName().substring(3,7));
            int rank = rank(name, year, gender, path);
            if(rank == -1)
                return -1.0;
            averageRank += rank;
        }
        return averageRank/count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int total = 0;
        FileResource file = new FileResource();
        CSVParser csvParser = file.getCSVParser();
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get(1).equals(gender)){
                if(csvRecord.get(0).equals(name))
                    break;
                total += Integer.parseInt(csvRecord.get(2));
            }
        }
        return total;
    }

    //helper methods
    public int rank(String name, int year, String gender, String path){
        FileResource fileResource = new FileResource(path);
        CSVParser csvParser = fileResource.getCSVParser();
        int rank = 0;
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get(1).equals(gender)){
                rank++;
                if(csvRecord.get(0).equals(name))
                    return rank;
            }else if(rank!=0){
                return -1;
            }
        }
        return -1;
    }

    public String name(int rank, String path, String gender){
        int rankCheck = 0;
        FileResource fileResource = new FileResource(path);
        CSVParser csvParser = fileResource.getCSVParser();
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get(1).equals(gender)){
                rankCheck++;
                if(rankCheck == rank)
                    return csvRecord.get(0);
            }else if(rankCheck != 0){
                return "NO NAME";
            }
        }
        return "NO NAME";
    }

    public static void main(String[] args) {
        BabyNames b = new BabyNames();
//        System.out.println(b.whatIsNameInYear("Owen", 1974, 2014, "M"));
//        System.out.println(b.getRank(2008, "Frank", "M"));
//        System.out.println(b.yearOfHighestRank("Mich", "M"));
//        System.out.println(b.getAverageRank("Robert", "M"));
        System.out.println(b.getTotalBirthsRankedHigher(1990,"Drew", "M"));
//        System.out.println(b.getName(1982, 450, "M"));

//        b.totalBirths();

    }
}
