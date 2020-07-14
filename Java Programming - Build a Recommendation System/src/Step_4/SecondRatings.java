package Step_4;
/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("files/ratedmoviesfull.csv", "files/ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRater(ratingsfile);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    //getAverageRatings if a movie is rated by at least some number of raters
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        //arraylist to store rating object of movies with minimalRaters ratings
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();

        //Variables to store information temporarily
        String mov = "";
        double avg = 0;
        for(Movie movie : myMovies){
            mov = movie.getID();
            avg = getAverageById(mov, minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(mov, avg);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }

    //get title of a movie from its ID
    public String getTitle(String id){
        for(Movie movie : myMovies){
            if(movie.getID().equals(id)){
                return movie.getTitle();
            }
        }
        return "The ID was not found";
    }

    //get ID of a movie from its title
    public String getID(String title){
        for(Movie movie : myMovies){
            if(movie.getTitle().equals(title))
                return movie.getID();
        }
        return "NO SUCH TITLE";
    }

    //get average rating for one movie
    public double getAverageForOneMovie(String id){
        double totalRating = 0;
        double raters = 0;
        for(Rater rater : myRaters){
            if(rater.getItemsRated().contains(id)){
                totalRating += rater.getRating(id);
                raters++;
            }
        }
        return totalRating/raters;
    }

    //helper methods
    private double getAverageById(String id, int minimalRaters){
        double totalrating = 0;
        int raterscount = 0;
        for(Rater rater : myRaters){
            if(rater.getItemsRated().contains(id)){
                totalrating += rater.getRating(id);
                raterscount++;
            }
        }
        if(raterscount >= minimalRaters){
            return totalrating/raterscount;
        }
        return 0.0;
    }
}
