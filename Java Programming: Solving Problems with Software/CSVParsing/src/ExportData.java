import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExportData {
    public String countryInfo(CSVParser csvParser, String countryName){
        for(CSVRecord getCountry : csvParser){
            if(getCountry.get("Country").equals(countryName)){
                return getCountry.get("Country") + "  " + getCountry.get("Exports") + "  " + getCountry.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    public void listTwoExportItems(CSVParser csvParser, String exportitem1, String exportitem2){
        for (CSVRecord csvRecord: csvParser){
            if(csvRecord.get("Exports").contains(exportitem1) && csvRecord.get("Exports").contains(exportitem2)){
                System.out.println(csvRecord.get("Country"));
            }
        }
    }

    public int numberOfExporter(CSVParser csvParser, String exportitem1){
        int count = 0;
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get("Exports").contains(exportitem1))
                count++;
        }
        return count;
    }

    public void bigExporters(CSVParser csvParser, String value){
        for(CSVRecord csvRecord : csvParser){
            if(csvRecord.get("Value (dollars)").length() > value.length())
                System.out.println(csvRecord.get("Country") + "  " + csvRecord.get("Value (dollars)"));
        }
    }

    public void tester(){
        FileResource fileResource = new FileResource("data/exportdata.csv");
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println(countryInfo(csvParser, "Nauru"));
        csvParser = fileResource.getCSVParser();
        listTwoExportItems(csvParser, "cotton", "flowers");
        csvParser = fileResource.getCSVParser();
        System.out.println(numberOfExporter(csvParser, "cocoa"));
        csvParser = fileResource.getCSVParser();
        bigExporters(csvParser, "$999,999,999,999");
    }

    public static void main(String[] args) {
        ExportData ed = new ExportData();
        ed.tester();
    }

}
