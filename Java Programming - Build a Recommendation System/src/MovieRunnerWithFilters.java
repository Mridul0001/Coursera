/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

import java.util.ArrayList;

public class MovieRunnerWithFilters {
    //print average ratings for movies with filters
    public void printAverageRatings(){
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-----------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters");
        System.out.println("-----------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatings(minimalRaters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"'.");
        }
    }

    //printing average rating by year
    public void printAverageRatingsByYear(){
        //set year here
        YearAfterFilter yearAfterFilter = new YearAfterFilter(2000);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters with yearafterfilter");
        System.out.println("-------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, yearAfterFilter);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' release in '"
            + MovieDatabase.getYear(rating.getItem()) +"'.");
        }
    }

    //printing average rating by genre
    public void printAverageRatingsByGenre(){
        //set genre here
        String genre = "Crime";
        GenreFilter genreFilter = new GenreFilter(genre);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters and '" + genre + "' genre.");
        System.out.println("-------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, genreFilter);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' having genre(s) '"
                    + MovieDatabase.getGenres(rating.getItem()) +"'.");
        }
    }

    //printing average rating by minutes
    public void printAverageRatingsByMinutes(){
        //set minimum and maximum minutes here
        int min = 110;
        int max = 170;
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters and running time between " + min +
                " and " + max + ".");
        System.out.println("-------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, minutesFilter);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' having running time "
                    + MovieDatabase.getMinutes(rating.getItem()) +".");
        }
    }

    //printing average rating by directors
    public void printAverageRatingsByDirectors(){
        //set name of directors here, separated by comma
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters and directed by one of following directors");
        System.out.println(directors);
        System.out.println("-------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, directorsFilter);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' directed by '"
                    + MovieDatabase.getDirector(rating.getItem()) +"'.");
        }
    }

    //printing average rating by year and genre
    public void printAverageRatingsByYearAfterAndGenre(){
        //set year here
        int year = 1980;

        //set genre here
        String genre = "Romance";
        //creating filters object
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        GenreFilter genreFilter = new GenreFilter(genre);
        //creating allfilters object
        AllFilters allFilters = new AllFilters();
        //adding filters
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " with YearAfter and genre filters applied");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, allFilters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' released in "
                    + MovieDatabase.getYear(rating.getItem()) +" having genre(s) '"
            + MovieDatabase.getGenres(rating.getItem()) + "'.");
        }
    }

    //printing average rating by minutes and directors
    public void printAverageRatingsByDirectorsAndMinutes(){
        //set minimum and maximum minutes here
        int min = 110;
        int max = 170;

        //set name of directors here, separated by comma
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        //creating filters object
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        //creating allfilters object
        AllFilters allFilters = new AllFilters();
        //adding filters
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);
        ThirdRatings thirdRatings = new ThirdRatings("files/ratings_short.csv");
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + thirdRatings.getRaterSize());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " with minutes and director filters applied");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = thirdRatings.getAverageRatringByFilter(minimalRaters, allFilters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' having length "
                    + MovieDatabase.getMinutes(rating.getItem()) +" directed by '"
                    + MovieDatabase.getDirector(rating.getItem()) + "'.");
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
//        movieRunnerWithFilters.printAverageRatings();
//        movieRunnerWithFilters.printAverageRatingsByYear();
//        movieRunnerWithFilters.printAverageRatingsByGenre();
//        movieRunnerWithFilters.printAverageRatingsByMinutes();
//        movieRunnerWithFilters.printAverageRatingsByDirectors();
//        movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes();
        movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre();
    }
}
