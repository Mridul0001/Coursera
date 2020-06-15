package week1;

public class WordPlay {
    public boolean isVowel(char ch){
        String vowel = "aeiou";
        String c = ("" + ch).toLowerCase();
        if(vowel.indexOf(c) != -1)
            return true;
        return false;
    }
    public String replaceVowels(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0; i<phrase.length(); i++){
            if(isVowel(phrase.charAt(i)))
                sb.setCharAt(i, ch);
        }
        return sb.toString();
    }

    public String empahsize(String phrase, char ch){
        StringBuilder sb = new StringBuilder(phrase);
        for(int i=0; i<phrase.length(); i++){
            if(phrase.charAt(i) == ch && i%2 == 0)
                sb.setCharAt(i, '+');
            else if(phrase.charAt(i) == ch)
                sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //testing methods
        WordPlay w = new WordPlay();
        System.out.println("Emphasize: " + w.empahsize("dna ctgaaactga", 'a'));
    }
}
