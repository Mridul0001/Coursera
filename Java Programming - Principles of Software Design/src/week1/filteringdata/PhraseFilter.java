package week1.filteringdata;

public class PhraseFilter implements Filter{
    private String where;
    private String phrase;
    private String name = "Phrase";
    public PhraseFilter(String where, String phrase, String name){
        this.where = where;
        this.phrase = phrase;
        this.name = name;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        if(where.equals("start") && qe.getInfo().startsWith(phrase)){
            return true;
        }else if(where.equals("end") && qe.getInfo().endsWith(phrase)){
            return true;
        }else if(where.equals("any") && qe.getInfo().contains(phrase)){
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
