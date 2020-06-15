public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if(startIndex != -1){
            return stringb.indexOf(stringa,startIndex+stringa.length()) != -1;
        }
        return false;
    }
    public String lastPart(String stringa, String stringb){
        int startIndex = stringb.indexOf(stringa);
        if(startIndex!=-1){
            return stringb.substring(startIndex+stringa.length());
        }
        return stringb;
    }
    public void testing(){
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("a", "banana"));
        System.out.println(twoOccurrences("atg", "catsatgab"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }

    public static void main(String[] args) {
        Part3 p = new Part3();
        p.testing();
    }
}
