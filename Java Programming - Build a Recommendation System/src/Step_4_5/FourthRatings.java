package Step_4_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

public class FourthRatings {

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

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        //arraylist to store weighted ratings
        ArrayList<Rating> ratingArrayList = new ArrayList<Rating>();
        //get similar ratings
        ArrayList<Rating> similarratings = getSimilarities(id);
        //get movies
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String movie : movies){
            //variable to store weighted rating temporarily
            double weightedrating = 0;
            double sum = 0;
            int countRaters = 0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating rating = similarratings.get(i);
                double weight = rating.getValue();
                String raterID = rating.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movie)){
                    countRaters++;
                    sum += weight*myRater.getRating(movie);
                }
            }
            //check if number of raters with similar ratings are greater than minimal raters
            if (countRaters >= minimalRaters) {
                weightedrating = sum / countRaters;
                ratingArrayList.add(new Rating(movie, weightedrating));
            }
        }
        Collections.sort(ratingArrayList, Collections.reverseOrder());
        return ratingArrayList;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        //arraylist to store weighted ratings
        ArrayList<Rating> ratingArrayList = new ArrayList<Rating>();
        //get similar ratings
        ArrayList<Rating> similarratings = getSimilarities(id);
        //get movies
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String movie : movies){
            //variable to store weighted rating temporarily
            double weightedrating = 0;
            double sum = 0;
            int countRaters = 0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating rating = similarratings.get(i);
                double weight = rating.getValue();
                String raterID = rating.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(movie)){
                    countRaters++;
                    sum += weight*myRater.getRating(movie);
                }
            }
            //check if number of raters with similar ratings are greater than minimal raters
            if (countRaters >= minimalRaters) {
                weightedrating = sum / countRaters;
                ratingArrayList.add(new Rating(movie, weightedrating));
            }
        }
        Collections.sort(ratingArrayList, Collections.reverseOrder());
        return ratingArrayList;
    }

    //helper methods
    private double getAverageById(String id, int minimalRaters){
        double totalrating = 0;
        int raterscount = 0;
        for(Rater rater : RaterDatabase.getRaters()){
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

    private double dotProduct(Rater me, Rater r){
        //store all movies rated by both raters without duplicates
        HashSet<String> itemsRated = new HashSet<String>();
        itemsRated.addAll(me.getItemsRated());
        itemsRated.addAll(r.getItemsRated());
        //variable to store dotProduct
        double dot = 0;
        //variable to store rating given by both users
        double ratingme = 0;
        double ratingr = 0;
        for(String item : itemsRated){
            ratingme = me.getRating(item);
            ratingr = r.getRating(item);
            //if either of rater has not rated the movie
            if(ratingme == -1 || ratingr == -1){
                continue;
            }
            dot += (ratingme-5)*(ratingr-5);
        }
        return dot;
    }

    private ArrayList<Rating> getSimilarities(String id){
        //arraylist to store similar ratings
        ArrayList<Rating> similarratings = new ArrayList<Rating>();
        //getting rater with given id
        Rater me = RaterDatabase.getRater(id);
        //variable to store dotProduct
        double dotProduct = 0;
        for(Rater rater : RaterDatabase.getRaters()){
            if(rater.getID().equals(id))
                continue;
            dotProduct = dotProduct(me, rater);
            Rating rating = new Rating(rater.getID(), dotProduct);
            if (dotProduct > 0)
                similarratings.add(rating);
        }
        Collections.sort(similarratings, Collections.reverseOrder());
        return similarratings;
    }
}
