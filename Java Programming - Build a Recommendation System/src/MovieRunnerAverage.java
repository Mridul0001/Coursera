import java.util.ArrayList;

/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

public class MovieRunnerAverage {
    //print average ratings for movies
    public void printAverageRatings(){
        SecondRatings secondRatings = new SecondRatings("files/ratedmovies_short.csv", "files/ratings_short.csv");
//        SecondRatings secondRatings = new SecondRatings();
        //printing number of movies
        System.out.println("Number of movies is/are: " + secondRatings.getMovieSize());
        //printing number of raters
        System.out.println("Number of raters is/are: " + secondRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 3;
        System.out.println("-----------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters");
        System.out.println("-----------------------------------------------");
        ArrayList<Rating> avgRatings = secondRatings.getAverageRatings(minimalRaters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    secondRatings.getTitle(rating.getItem()) +"'.");
        }
    }

    //
    public void getAverageRatingOneMovie(){
        SecondRatings secondRatings = new SecondRatings("files/ratedmovies_short.csv", "files/ratings_short.csv");
//        SecondRatings secondRatings = new SecondRatings();

        //movie name of which we want to get average rating
        String movie = "The Godfather";
        //get ID of the movie
        String id = secondRatings.getID(movie);

        //printing rating
        System.out.println("-------------------------------------------------------");
        System.out.println("Average rating for the movie '" + movie + "' is '" +
                secondRatings.getAverageForOneMovie(id) +"'.");
        System.out.println("-------------------------------------------------------");

    }

    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
//        mra.printAverageRatings();
        mra.getAverageRatingOneMovie();
    }
}
