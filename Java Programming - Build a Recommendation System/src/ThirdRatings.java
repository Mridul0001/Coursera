/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

import java.util.ArrayList;

public class ThirdRatings{
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        // default constructor
        this("files/ratings.csv");
    }

    public ThirdRatings(String ratingsfile){
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRater(ratingsfile);
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    //getAverageRatings if a movie is rated by at least some number of raters
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        //arraylist to store rating object of movies with minimalRaters ratings
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        //calling moviedatabase with filter
        //change filters here
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //Variables to store information temporarily
        String mov = "";
        double avg = 0;
        for(String s : movies){
            mov = s;
            avg = getAverageById(mov, minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(mov, avg);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
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

    //get average rating based on filter passed
    public ArrayList<Rating> getAverageRatringByFilter(int minimalRaters, Filter filterCriteria){
        //arraylist to store rating object of movies with minimalRaters ratings
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        //calling moviedatabase with filter
        //change filters here
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        //Variables to store information temporarily
        String mov = "";
        double avg = 0;
        for(String s : movies){
            mov = s;
            avg = getAverageById(mov, minimalRaters);
            if(avg != 0.0){
                Rating rating = new Rating(mov, avg);
                averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
}
