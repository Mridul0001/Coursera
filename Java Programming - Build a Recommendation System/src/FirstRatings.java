/**Capstone Project
* Java Programming - Building a Recommendation System
* @author Mridul Mahajan*/
import edu.duke.*;

import java.io.File;
import java.util.*;
import org.apache.commons.csv.*;

//First Rating class to process movie and rating data
public class FirstRatings {
    //String variable to store file path
    String path = "files/ratings.csv";

    //loadMovies method
    public ArrayList<Movie> loadMovies(String filename){
        //ArrayList to store Movie object
        ArrayList<Movie> movies = new ArrayList<Movie>();

        //Parsing CSV file
        FileResource fileResource = new FileResource(filename);
        CSVParser csvParser = fileResource.getCSVParser();
        //variables to store movie information
        String id;
        String title;
        String year;
        String genres;
        String director;
        String country;
        String poster;
        int minutes;
        for(CSVRecord cr : csvParser){
            //Storing details for each movie in variables
            id = cr.get("id");
            title = cr.get("title");
            year = cr.get("year");
            genres = cr.get("genre");
            director = cr.get("director");
            country = cr.get("country");
            poster = cr.get("poster");
            minutes = Integer.parseInt(cr.get("minutes"));

            //Creating new Movie object with above data
            Movie movie = new Movie(id, title, year, genres, director, country, poster, minutes);

            //adding movie object to arraylist
            movies.add(movie);
        }
        return movies;
    }

    //testing loadMovies method
    public void testLoadMovies(){
        ArrayList<Movie> movies = loadMovies(path);
        //Printing movie names
        System.out.println("--------------------------------");
        System.out.println("Movies in the file");
        System.out.println("--------------------------------");
        for(Movie m : movies){
            System.out.println(m.getTitle());
        }

        //Movies with comedy genre
        System.out.println("--------------------------------");
        System.out.println("Movies with comedy genre");
        System.out.println("--------------------------------");
        int totalComedy = 0;
        for(Movie m : movies){
            if(m.getGenres().contains("Comedy")){
                System.out.println(m.getTitle());
                totalComedy++;
            }
        }
        System.out.println("Total : " + totalComedy);

        //Movies with length greater than 150
        int totalLonger = 0;
        System.out.println("--------------------------------");
        System.out.println("Movies with length > 150");
        System.out.println("--------------------------------");
        for(Movie m : movies){
            if(m.getMinutes() > 150){
                System.out.println(m.getTitle());
                totalLonger++;
            }
        }
        System.out.println("Total longer movies: " + totalLonger);

        //Maximum number of movies by any director
        System.out.println("------------------------------------------");
        System.out.println("Maximum number of movies by any director");
        System.out.println("------------------------------------------");
        HashMap<String, Integer> numDir = new HashMap<String, Integer>();
        for(Movie m : movies){
            String[] directors = m.getDirector().split(",");
            for(int i=0; i<directors.length; i++){
                if(!numDir.containsKey(directors[i])){
                    numDir.put(directors[i], 1);
                }else{
                    numDir.put(directors[i], numDir.get(directors[i])+1);
                }
            }
        }
        int maxDirected = 0;
        ArrayList<String> directors = new ArrayList<String>();
        for(String s : numDir.keySet()){
            //extracting directors with maximum movies
            if(numDir.get(s)>maxDirected){
                directors.clear();
                directors.add(s);
                maxDirected = numDir.get(s);
            }else if(maxDirected == numDir.get(s)){
                directors.add(s);
            }
            //printing each director name and number of movies directed by him
            System.out.println(s + " : " + numDir.get(s));
        }
        System.out.println("Directors with maximum films");

        for(String s : directors){
            System.out.println(s + " : " + numDir.get(s));
        }
    }

    //loadRater method
    public ArrayList<Rater> loadRater(String filename){
        //ArrayList to store Rater object
        ArrayList<Rater> raters = new ArrayList<Rater>();

        //Parsing CSV file
        FileResource fileResource = new FileResource(filename);
        CSVParser csvParser = fileResource.getCSVParser();

        HashMap<String, Rater> raterMap = new HashMap<String, Rater>();
        //Variables to store file data
        String myId;
        String item;
        double rating;
        for(CSVRecord cr : csvParser){
            //getting values from file to variables for each record
            myId = cr.get("rater_id");
            item = cr.get("movie_id");
            rating = Double.parseDouble(cr.get("rating"));

            //Storing details of each rater
            if(!raterMap.containsKey(myId)){
                Rater rater = new EfficientRater(myId);
                rater.addRating(item, rating);
                raterMap.put(myId, rater);
                raters.add(rater);
            }else {
                Rater rater = raterMap.get(myId);
                rater.addRating(item, rating);
            }
        }
        return raters;
    }

    //testing loadRater method
    public void testLoadRater(){
        ArrayList<Rater> raters = loadRater(path);

        //printing total number of raters
        System.out.println("--------------------------------");
        System.out.println("Total number of raters");
        System.out.println("--------------------------------");
        System.out.println(raters.size());
        for(Rater rater : raters){
            System.out.println("Rating ID : " + rater.getID() +
                            ", Number of ratings : " +
                            rater.getItemsRated().size());
            for(String rated : rater.getItemsRated()){
                System.out.println(rated + " : " + rater.getRating(rated));
            }
        }

        //finding ratings for a particular rater
        System.out.println("-----------------------------------------");
        System.out.println("Number of ratings for a particular user");
        System.out.println("-----------------------------------------");
        String rater_id = "193";
        for(Rater rater : raters){
            if(rater.getID().equals(rater_id)){
                System.out.println("Number of ratings for user "
                        + rater_id + " is/are : " + rater.numRatings());
                break;
            }
        }

        //finding maximum number of ratings by any rater
        System.out.println("-----------------------------------------");
        System.out.println("Maximum number of ratings by any rater");
        System.out.println("-----------------------------------------");
        int max = 0;
        String users = "";
        for(Rater rater : raters){
            //if new maximum rating found
            if(rater.numRatings()>max){
                max = rater.numRatings();
                users = rater.getID();
            }else if(rater.numRatings()==max){ //if another rating found to current maximux
                users += ", " + rater.getID();
            }
        }
        System.out.println("Maximum number of ratings is/are " + max +
                " by user(s) " + users);

        //finding number of ratings for a particular movie
        int numRating = 0;
        String movie = "1798709";
        for(Rater rater : raters){
            if(rater.hasRating(movie)){
                numRating++;
            }
        }
        System.out.println("Total number of ratings for movie " + movie
        + " is/are " + numRating);

        //finding how many different movies have been rated by all these raters
        HashSet<String> differentMovies = new HashSet<String>();
        ArrayList<String> moviesRated = new ArrayList<String>();
        for(Rater rater : raters){
            moviesRated = rater.getItemsRated();
            differentMovies.addAll(moviesRated);
        }
        System.out.println("Different movies rated by all raters are : "
        + differentMovies.size());
        //System.out.println(differentMovies);
    }
    public static void main(String[] args) {
        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.testLoadMovies();
        firstRatings.testLoadRater();
    }
}
