public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int foundIndex = dna.indexOf(stopCodon, startIndex);
        //System.out.println(foundIndex);
        if(foundIndex != -1 && (foundIndex - startIndex)%3==0)
            return  foundIndex;
        return Integer.MAX_VALUE;
    }
    public void testFindStopCodon(){
        System.out.println(findStopCodon("ATGLAABBCKLMTGA", 0, "TGA"));
    }
    public String findGene(String dna, int si){
        int startIndex = dna.indexOf("ATG", si);
        if(startIndex == -1)
            return "";
        int indexTAA = findStopCodon(dna, startIndex, "TAA");
        int indexTAG = findStopCodon(dna, startIndex, "TAG");
        int indexTGA = findStopCodon(dna, startIndex, "TGA");
        int min = Math.min(indexTAA, Math.min(indexTGA, indexTAG));
        if(min == -1 || min == Integer.MAX_VALUE)
            return "";
        return dna.substring(startIndex, min+3);
    }
    public void testFindGene(){
        System.out.println(findGene("ATGJHJHJHTAAHJHTGAJLM",0));
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    public void testPrintAllGenes(){
        printAllGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        printAllGenes("");
        printAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
    public static void main(String[] args) {
        Part1 p = new Part1();
        //p.testFindStopCodon();
        //p.testFindGene();
        p.testPrintAllGenes();
    }
}
