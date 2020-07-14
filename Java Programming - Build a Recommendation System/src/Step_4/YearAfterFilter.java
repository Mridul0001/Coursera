package Step_4;

/**Capstone Project
 * Java Programming - Building a Recommendation System
 * @author Mridul Mahajan*/

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
