package Step_4_5;

/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

public class GenreFilter implements Filter{

    private String genre;
    public GenreFilter(String genre){
        this.genre = genre;
    }
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(genre);
    }
}
