public class Part1 {
    public String findSimpleGene(String dna){
        String res = "";
        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex+3);
        if(startIndex < 0 || endIndex < 0)
            return "";
        if((endIndex+3 - startIndex)%3==0){
            res = dna.substring(startIndex, endIndex+3);
        }
        return res;
    }

    public void testSimpleGene(){
        System.out.println("DNA: ATGFFGCFFJCCTAA\nGene is: " + findSimpleGene("ATGFFGCFFJCCTAA"));
    }

    public static void main(String[] args) {
        Part1 p = new Part1();
        p.testSimpleGene();
    }
}
