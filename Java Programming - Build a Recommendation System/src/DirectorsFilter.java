/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

public class DirectorsFilter implements Filter{
    String[] directors;
    public DirectorsFilter(String directors){
        this.directors = directors.split(",");
    }
    @Override
    public boolean satisfies(String id) {
        for(int i=0; i<directors.length; i++){
            if(MovieDatabase.getDirector(id).contains(directors[i])){
                return true;
            }
        }
        return false;
    }
}
