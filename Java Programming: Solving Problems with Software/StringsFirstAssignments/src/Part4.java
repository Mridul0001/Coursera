import edu.duke.*;
/*This java class uses two external jar files named apache-csv.jar and courserajava.jar
* These are included in this project but you might need to add them to path*/
public class Part4 {
    URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    public void read(){
        for(String s: url.words()){
            String st = s.toLowerCase();
            if(st.indexOf("youtube.com")!=-1){
                System.out.println(s.substring(s.indexOf("\"")+1,s.lastIndexOf("\">")));
            }
        }
    }

    public static void main(String[] args) {
        Part4 p = new Part4();
        p.read();
    }
}
