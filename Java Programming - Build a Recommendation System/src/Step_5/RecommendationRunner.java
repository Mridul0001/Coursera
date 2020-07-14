package Step_5;

import java.util.ArrayList;
import java.util.Random;

/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/
public class RecommendationRunner implements Recommender{
    //path to both files
    private String moviesFilePath = "files/ratedmoviesfull.csv";
    private String ratingsFilePath = "files/ratings.csv";
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> moviesToRate = new ArrayList<String>();
        ArrayList<String> movieId = MovieDatabase.filterBy(new TrueFilter());
        Random random = new Random();
        int rnd = 0;
        int totalMovies = movieId.size();
        for(int i=0; i<20; i++){
            rnd = random.nextInt(totalMovies);
            if(!moviesToRate.contains(movieId.get(rnd))){
                moviesToRate.add(movieId.get(rnd));
            }
        }
        return moviesToRate;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize(moviesFilePath);
        RaterDatabase.initialize(ratingsFilePath);
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 20, 5);
        //System.out.println("Found ratings for movies : " + ratingList.size());
        if (ratingList.size() == 0) {
            System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
        } else {
            ArrayList<String> movieToBeRate = getItemsToRate();
            ArrayList<Rating> outID = new ArrayList<>();
            int count = 0;
            for (int i = 0; outID.size() + count != ratingList.size() && outID.size() < 10; i++) {
                if (!movieToBeRate.contains(ratingList.get(i).getItem())) {
                    outID.add(ratingList.get(i));
                    //System.out.println("i = " + i + " id = " + ratingList.get(i).getItem());

                } else {
                    count++;
                }
            }
            System.out.println("outid size = " + outID.size());


            System.out.println("<style>");
            System.out.println("h2,h3{");
            System.out.println("  text-align: center;");
            System.out.println("  height: 50px;");
            System.out.println("  line-height: 50px;");
            System.out.println("  font-family: Arial, Helvetica, sans- serif;");
            System.out.println("  background-color: black;");
            System.out.println("   color:  #ff6600 }");

            System.out.println(" table {");
            System.out.println("   border-collapse: collapse;");
            System.out.println("   margin: auto;}");
            System.out.println("table, th, td {");
            System.out.println("    border: 2px solid white;");
            System.out.println("    font-size: 15px;");

            System.out.println("    padding: 2px 6px 2px 6px; }");
            System.out.println(" td img{");
            System.out.println("    display: block;");
            System.out.println("    margin-left: auto;");
            System.out.println("    margin-right: auto; }");
            System.out.println("th {");
            System.out.println("    height: 40px;");
            System.out.println("    font-size: 18px;");

            System.out.println("  background-color: black;");
            System.out.println(" color: white;");
            System.out.println("text-align: center; }");

            System.out.println(" tr:nth-child(even) {");
            System.out.println("     background-color: #f2f2f2; }");
            System.out.println("  tr:nth-child(odd) {");
            System.out.println("background-color: #cccccc; }");
            System.out.println(" tr:hover {");
            System.out.println(" background-color: #666666; ");
            System.out.println("  color:white;}");

            System.out.println("table td:first-child {");
            System.out.println(" text-align: center; }");

            System.out.println(" tr {");
            System.out.println(" font-family: Arial, Helvetica, sans-serif; }");
            System.out.println(".rating{");
            System.out.println("    color:#ff6600;");
            System.out.println("    padding: 0px 10px;");
            System.out.println("   font-weight: bold; }");
            System.out.println("</style>");


            System.out.println("<h2>Wei Xu Brings Best Movies for You! Enjoy!^^</h2>");
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");

            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");

            //https://www.imdb.com/title/tt0780622/
            //make title chickable
            //<td><a href="https://developer.mozilla.org/en-US/docs/Web/CSS/Reference" >Hello World</a></td>
            //"<a href="https://www.imdb.com/title/tt"+movie.id+"\">"+ movie.title+"</a></td>
            int rank = 1;
            for (Rating i : outID) {
                System.out.println("<tr><td>" + rank + "</td>" +

                        "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()) + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                        + String.format("%.1f", i.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(i.getItem()) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(i.getItem()) + "</td>" +
                        "</tr> ");
                rank++;
            }
        }
        System.out.println("</table>");
        System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>");
    }
        public static void main(String[] args) {
            RecommendationRunner a = new RecommendationRunner();
            a.getItemsToRate();
            a.printRecommendationsFor("65");
        }

}
