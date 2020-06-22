package week3.wordgramclass;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for(int i=0; i<myWords.length; i++){
            ret += myWords[i] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if(this.length() != other.length()){
            return false;
        }
        for(int i=0; i<myWords.length; i++){
            if(!myWords[i].equals(other.wordAt(i))){
                return false;
            }
        }
        return true;

    }

    public int hashCode() { return myHash; }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        if (word == null) word = "";
        // shift all words one spot towards 0 and add word at the end.
        // you lose the first word
        System.arraycopy(out.myWords, 1, out.myWords, 0, out.myWords.length-1);
        out.myWords[out.myWords.length-1] = word;

        out.myHash = out.toString().hashCode();
        // TODO: Complete this method
        return out;
    }

}