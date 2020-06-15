import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class WeatherData {
    private String path;
    public CSVRecord coldestHourInFile(CSVParser csvParser){
        CSVRecord cr = null;
        double coldestTemp = Double.MAX_VALUE;
        for(CSVRecord csvRecord: csvParser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            if(currTemp < coldestTemp && currTemp != -9999){
                cr = csvRecord;
                coldestTemp = currTemp;
            }
        }
        return cr;
    }

    public CSVRecord lowestHumidityInFile(CSVParser csvParser){
        CSVRecord cr = null;
        double lowestHumidity = Double.MAX_VALUE;
        for(CSVRecord csvRecord: csvParser){
            String currHumidity = csvRecord.get("Humidity");
            if(!currHumidity.equals("N/A") && Double.parseDouble(currHumidity) < lowestHumidity){
                cr = csvRecord;
                lowestHumidity = Double.parseDouble(currHumidity);
            }
        }
        return cr;
    }

    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord cr = null;
        double lowestHumidity = Double.MAX_VALUE;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()){
            FileResource fr = new FileResource(file);
            CSVRecord csvRecord = lowestHumidityInFile(fr.getCSVParser());
            String currHumidity = csvRecord.get("Humidity");
            if(!currHumidity.equals("N/A") && Double.parseDouble(currHumidity) < lowestHumidity){
                cr = csvRecord;
                lowestHumidity = Double.parseDouble(currHumidity);
            }
        }
        return cr;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource("data/nc_weather/2014/weather-2014-05-01.csv");
        CSVParser csvParser = fr.getCSVParser();
        CSVRecord csvRecord = coldestHourInFile(csvParser);
        System.out.println(csvRecord.get("TimeEDT") + "  " + csvRecord.get("TemperatureF") + "  " + csvRecord.get("Conditions"));

    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " on " + csv.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + csv.get("Humidity") + " on " + csv.get("DateUTC"));
    }

    public String fileWithColdestTemperature(){
        String fileName = "";
        DirectoryResource dr = new DirectoryResource();
        double minTemp = Double.MAX_VALUE;
        for (File f : dr.selectedFiles()){
            FileResource fileResource = new FileResource(f);
            CSVRecord csvRecord = coldestHourInFile(fileResource.getCSVParser());
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            if(currTemp<minTemp){
                minTemp = currTemp;
                path = f.getPath();
                fileName = f.getName();
            }
        }
        return fileName;
    }

    public void testFileWithColdestTemperature(){
        System.out.println("Coldest day was in file " + fileWithColdestTemperature());
        //System.out.println(path);
        FileResource fileResource = new FileResource(path);
        CSVParser csvParser = fileResource.getCSVParser();
        CSVRecord csvRecord = coldestHourInFile(csvParser);
        System.out.println("Coldest temperature on that day was " + csvRecord.get("TemperatureF"));
        System.out.println("All the temperatures on that day were:");
        csvParser = fileResource.getCSVParser();
        for (CSVRecord csvRecord1 : csvParser){
            System.out.println(csvRecord1.get("DateUTC") + " " + csvRecord1.get("TemperatureF"));
        }
    }

    public double averageTemperatureInFile(CSVParser parser){
        double avgTemp = 0;
        int count = 0;
        for (CSVRecord csvRecord : parser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            if(currTemp != -9999){
                count++;
                avgTemp += currTemp;
            }
        }
        return avgTemp/count;
    }

    public void testAverageTemperatureInFile(){
        FileResource fileResource = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fileResource.getCSVParser()));
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value){
        double avgTemp = 0;
        int count = 0;
        double humidity = 0;
        for(CSVRecord csvRecord : parser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            String hum = csvRecord.get("Humidity");
            if(!hum.equals("N/A")){
                humidity = Double.parseDouble(hum);
                if(currTemp != -9999 && humidity>=value){
                    count++;
                    avgTemp += currTemp;
                }
            }
        }
        return avgTemp/count;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fileResource = new FileResource();
        double temp = averageTemperatureWithHighHumidityInFile(fileResource.getCSVParser(), 80);
        if(temp == 0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is " + temp);
    }

    public static void main(String[] args) {
        WeatherData wd = new WeatherData();
//        wd.testColdestHourInFile();
        wd.testFileWithColdestTemperature();
//        wd.testLowestHumidityInFile();
//        wd.testLowestHumidityInManyFiles();
//        wd.testAverageTemperatureInFile();
//        wd.testAverageTemperatureWithHighHumidityInFile();
    }
}
