public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String res = "";
        if(!dna.equals(dna.toLowerCase())){
            dna = dna.toUpperCase();
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }else {
            dna = dna.toLowerCase();
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        //System.out.println(startIndex);
        int endIndex = dna.indexOf(stopCodon, startIndex+3);
        //System.out.println(endIndex);
        if(startIndex < 0 || endIndex < 0)
            return "";
        if((endIndex+3 - startIndex)%3==0){
            res = dna.substring(startIndex, endIndex+3);
        }
        return res;
    }

    public void testSimpleGene(){
        System.out.println("DNA: atgffgcffjcctaa\nGene is: " + findSimpleGene("atgffgcffjcctaa", "ATG", "TAA"));
    }

    public static void main(String[] args) {
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}
