package Step_4_5;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class MovieRunnerSimilarRatings {
    //print average ratings for movies with filters
    public void printAverageRatings(){
        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        RaterDatabase.initialize("files/ratings_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-----------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " number of raters");
        System.out.println("-----------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatings(minimalRaters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"'.");
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
        FourthRatings fourthRatings = new FourthRatings();
//        ThirdRatings secondRatings = new ThirdRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmovies_short.csv");
        RaterDatabase.initialize("files/ratings_short.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average ratings for movies with a minimum number of raters
        int minimalRaters = 1;
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Average rating of movies with " +
                minimalRaters + " with YearAfter and genre filters applied");
        System.out.println("-----------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatringByFilter(minimalRaters, allFilters);
        for(Rating rating : avgRatings){
            System.out.println("Average rating is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' released in "
                    + MovieDatabase.getYear(rating.getItem()) +" having genre(s) '"
                    + MovieDatabase.getGenres(rating.getItem()) + "'.");
        }
    }

    //methods to print similar ratings according to users
    //print similar ratings for movies
    public void printSimilarRatings(){
        //set rater id, minimalRaters and top similar raters here
        String raterID = "65";
        int minimalRaters = 5;
        int similarRaters = 20;
        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmoviesfull.csv");
        RaterDatabase.initialize("files/ratings.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average ratings for movies with a minimum number of raters and similar raters
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Similar rating of movies with " +
                minimalRaters + " number of minimum raters and "
                + similarRaters + " number of similar raters for the rater ID " + raterID);
        System.out.println("----------------------------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getSimilarRatings(raterID, similarRaters, minimalRaters);
        for(Rating rating : avgRatings){
            System.out.println("Similarity value is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"'.");
        }
    }

    //printing average similar rating by genre
    public void printSimilarRatingsByGenre(){
        //set rater id, minimalRaters and top similar raters here
        String raterID = "65";
        int minimalRaters = 5;
        int similarRaters = 20;
        //set genre here
        String genre = "Action";
        GenreFilter genreFilter = new GenreFilter(genre);
        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmoviesfull.csv");
        RaterDatabase.initialize("files/ratings.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average similar ratings for movies with a minimum and similar number of raters
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Similar rating of movies with " +
                minimalRaters + " number of raters and " + similarRaters + " number of similar raters for the rater ID " + raterID + " with '" + genre + "' genre.");
        System.out.println("----------------------------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarRaters, minimalRaters, genreFilter);
        for(Rating rating : avgRatings){
            System.out.println("Similarity value is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' having genre(s) '"
                    + MovieDatabase.getGenres(rating.getItem()) +"'.");
        }
    }

    //printing average similar rating by directors
    public void printSimilarRatingsByDirectors(){
        //set rater id, minimalRaters and top similar raters here
        String raterID = "1034";
        int minimalRaters = 3;
        int similarRaters = 10;
        //set name of directors here, separated by comma
        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);
        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmoviesfull.csv");
        RaterDatabase.initialize("files/ratings.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average ratings for movies with a minimum and similar number of raters and specific directors
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Similar rating of movies with " +
                minimalRaters + " number of minimum raters and "
                + similarRaters + " number of similar raters for the rater ID " + raterID + "with anyone of the following directors");
        System.out.println(directors);
        System.out.println("----------------------------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarRaters, minimalRaters, directorsFilter);
        for(Rating rating : avgRatings){
            System.out.println("Similarity value is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' directed by '"
                    + MovieDatabase.getDirector(rating.getItem()) +"'.");
        }
    }

    //printing average similar rating by minutes and genre
    public void printSimilarRatingsByGenreAndMinutes(){
        //set minimum and maximum minutes here
        int min = 100;
        int max = 200;
        //set rater id, minimalRaters and top similar raters here
        String raterID = "65";
        int minimalRaters = 5;
        int similarRaters = 10;
        //set genre here
        String genre = "Adventure";
        GenreFilter genreFilter = new GenreFilter(genre);
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        //creating allfilters object
        AllFilters allFilters = new AllFilters();
        //adding filters
        allFilters.addFilter(genreFilter);
        allFilters.addFilter(minutesFilter);
        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmoviesfull.csv");
        RaterDatabase.initialize("files/ratings.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average similar ratings for movies with a minimum and similar number of raters
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Similar rating of movies with " +
                minimalRaters + " number of raters and " + similarRaters + " number of similar raters for the rater ID " +
                raterID + " with '" + genre + "' genre and having runtime between " + min
                + " and " + max);
        System.out.println("----------------------------------------------------------------------------------------------");

        ArrayList<Rating> avgRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarRaters, minimalRaters, allFilters);
        for(Rating rating : avgRatings){
            System.out.println("Similarity value is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' having running time "
                    + MovieDatabase.getMinutes(rating.getItem()) +" and genre(s) '"
                    + MovieDatabase.getGenres(rating.getItem()) + "'.");
        }
    }

    //printing average rating by year and minutes
    public void printSimilarRatingsByYearAfterAndMinutes(){
        //set year here
        int year = 2000;
        //set minimum and maximum minutes here
        int min = 80;
        int max = 100;
        //set rater id, minimalRaters and top similar raters here
        String raterID = "65";
        int minimalRaters = 5;
        int similarRaters = 10;
        //creating filters object
        YearAfterFilter yearAfterFilter = new YearAfterFilter(year);
        MinutesFilter minutesFilter = new MinutesFilter(min, max);
        //creating allfilters object
        AllFilters allFilters = new AllFilters();
        //adding filters
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(minutesFilter);

        //creating fourth ratings object
        FourthRatings fourthRatings = new FourthRatings();
        //calling moviedatabase and raterdatabase initialize method
        MovieDatabase.initialize("files/ratedmoviesfull.csv");
        RaterDatabase.initialize("files/ratings.csv");
        //printing number of movies
        System.out.println("Number of movies is/are: " + MovieDatabase.size());
        //printing number of raters
        System.out.println("Number of raters is/are: " + RaterDatabase.size());

        //printing average similar ratings for movies with a minimum and similar number of raters
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("Similar rating of movies with " +
                minimalRaters + " number of raters and " + similarRaters + " number of similar raters for the rater ID " +
                raterID + " released in " + year + " and having runtime between " + min
                + " and " + max);
        System.out.println("----------------------------------------------------------------------------------------------");
        ArrayList<Rating> avgRatings = fourthRatings.getSimilarRatingsByFilter(raterID, similarRaters, minimalRaters, allFilters);
        for(Rating rating : avgRatings){
            System.out.println("Similarity value is '" +
                    rating.getValue() + "' for the movie '" +
                    MovieDatabase.getTitle(rating.getItem()) +"' released in "
                    + MovieDatabase.getYear(rating.getItem()) +" having runtime '"
                    + MovieDatabase.getMinutes(rating.getItem()) + "'.");
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings();
//        movieRunnerSimilarRatings.printAverageRatings();
//        movieRunnerSimilarRatings.printAverageRatingsByYearAfterAndGenre();
//        movieRunnerSimilarRatings.printSimilarRatings();
//        movieRunnerSimilarRatings.printSimilarRatingsByGenre();
//        movieRunnerSimilarRatings.printSimilarRatingsByDirectors();
//        movieRunnerSimilarRatings.printSimilarRatingsByGenreAndMinutes();
        movieRunnerSimilarRatings.printSimilarRatingsByYearAfterAndMinutes();
    }
}
