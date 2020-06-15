package week3;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fileResource = new FileResource(filename);
         for(String s : fileResource.lines()){
             LogEntry logEntry = WebLogParser.parseEntry(s);
             records.add(logEntry);
         }
     }

     public int countUniqueIPs(){
         ArrayList<String> arrayList = new ArrayList<String>();
         for(LogEntry le: records){
             String ipAddr = le.getIpAddress();
             if(!arrayList.contains(ipAddr))
                 arrayList.add(ipAddr);
         }
         return arrayList.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             if(le.getStatusCode() > num)
                 System.out.println(le);
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4,10);
             String ipAddr = le.getIpAddress();
             if(date.equals(someday) && !unique.contains(ipAddr)){
                 unique.add(ipAddr);
             }
         }
         return unique;
     }

     public ArrayList<String> countUniqueIPsInRange(int low, int high){
         ArrayList<String> unique = new ArrayList<String>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(le.getStatusCode() >= low && le.getStatusCode()<= high && !unique.contains(ipAddr)){
                 unique.add(ipAddr);
             }
         }
         return unique;
     }

     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!counts.containsKey(ipAddr)){
                 counts.put(ipAddr, 1);
             }else{
                 counts.put(ipAddr, counts.get(ipAddr) +1);
             }
         }
         return counts;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> hashMap){
         int max = 0;
         for (String s : hashMap.keySet()){
             if(hashMap.get(s) > max)
                 max = hashMap.get(s);
         }
         return max;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hashMap){
         ArrayList<String> arrayList = new ArrayList<String>();
         int max = mostNumberVisitsByIP(hashMap);
         for(String s : hashMap.keySet()){
             if(hashMap.get(s) == max)
                 arrayList.add(s);
         }
         return arrayList;
     }

     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> hashMap = new HashMap<String, ArrayList<String>>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString().substring(4,10);
             if(!hashMap.containsKey(date)){
                 ArrayList<String> arrayList = new ArrayList<String>();
                 arrayList.add(le.getIpAddress());
                 hashMap.put(date, arrayList);
             }else {
                 ArrayList<String> arrayList = hashMap.get(date);
                 arrayList.add(le.getIpAddress());
                 hashMap.put(date, arrayList);
             }
         }
         return hashMap;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hashMap){
         String day = "";
         int max = 0;
         for(String s : hashMap.keySet()){
             if(hashMap.get(s).size() > max){
                 max = hashMap.get(s).size();
                 day = s;
             }
         }
         return day;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hashMap, String day){
         HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>();
         ArrayList<String> arrayList = hashMap.get(day);
         for(int i=0; i<arrayList.size(); i++){
             if(!hashMap1.containsKey(arrayList.get(i))){
                 hashMap1.put(arrayList.get(i), 1);
             }else{
                 hashMap1.put(arrayList.get(i), hashMap1.get(arrayList.get(i)) + 1);
             }
         }
         arrayList = iPsMostVisits(hashMap1);
         return arrayList;
     }
}


