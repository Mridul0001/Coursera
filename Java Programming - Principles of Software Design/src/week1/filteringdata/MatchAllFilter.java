package week1.filteringdata;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filters;
    private String names = "";
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }


    @Override
    public boolean satisfies(QuakeEntry qe) {
        int count=0;
        for(int i=0; i<filters.size();i++){
            if(filters.get(i).satisfies(qe))
                count++;
        }
        if(count==filters.size())
            return true;
        return false;
    }

    @Override
    public String getName() {
        for(int i=0; i<filters.size(); i++){
            names += filters.get(i).getName() + " ";
        }
        return names;
    }
}
