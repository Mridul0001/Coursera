package week3;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.FileResource;

import java.io.File;
import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("files/log_files/short-test_log");
        logAnalyzer.printAll();
    }

    public void testUniqeIPs(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("files/log_files/weblog2_log");
        System.out.println(logAnalyzer.countUniqueIPs());
    }

    public void testAllHigherThanNum(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("files/log_files/weblog1_log");
        logAnalyzer.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("files/log_files/weblog2_log");
        ArrayList<String> unique = logAnalyzer.uniqueIPVisitsOnDay("Sep 27");
        for(String s : unique){
            System.out.println(s);
        }
        System.out.println(unique.size());
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("files/log_files/weblog2_log");
        ArrayList<String> unique = logAnalyzer.countUniqueIPsInRange(400,499);
        for(String s : unique){
            System.out.println(s);
        }
        System.out.println(unique.size());
    }

    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("files/log_files/short-test_log");
        HashMap<String, Integer> hashMap = la.countVisitsPerIP();
        System.out.println(hashMap);
    }

    public void testMostNumberVisitByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("files/log_files/weblog2_log");
        HashMap<String, Integer> hashMap = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(hashMap));
    }

    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("files/log_files/weblog2_log");
        HashMap<String, Integer> hashMap = la.countVisitsPerIP();
        System.out.println(la.iPsMostVisits(hashMap));
    }

    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("files/log_files/weblog2-short_log");
        System.out.println(la.iPsForDays());
    }

    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("files/log_files/weblog2_log");
        HashMap<String, ArrayList<String>> hashMap = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(hashMap));
    }

    public void testiPsWithMostVisitsOnDay(){
    LogAnalyzer la = new LogAnalyzer();
    la.readFile("files/log_files/weblog2_log");
    HashMap<String, ArrayList<String>> hashMap = la.iPsForDays();
    System.out.println(la.iPsWithMostVisitsOnDay(hashMap, "Sep 29"));
}

    public static void main(String[] args) {
        Tester tester = new Tester();
//        tester.testLogAnalyzer();
//        tester.testUniqeIPs();
//        tester.testAllHigherThanNum();
//        tester.testUniqueIPVisitsOnDay();
//        tester.testCountUniqueIPsInRange();
//        tester.testiPsWithMostVisitsOnDay();
//        tester.testMostNumberVisitByIP();
//        tester.testIPsMostVisits();
//        tester.testDayWithMostIPVisits();
        tester.testiPsWithMostVisitsOnDay();
    }

}
