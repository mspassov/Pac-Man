/*
 * Martin Spassov
 * mspassov
 * 250901340
 */
/*
 * Class stores the key object
 */
public class Location {
	/*
	 * Creating the two parameters for the key
	 */
	private int xcoord;
	private int ycoord;
	/*
	 * constructor for the class, initializes the variables (the two coordinates)
	 * @param x, y
	 */
	public Location(int x, int y) {
		xcoord=x;
		ycoord=y;
	}
	
	/*
	 * Returns the x coordinate for the location
	 * @return xcoord
	 */
	public int xCoord() {
		return xcoord;
	}
	
	/*
	 * Returns the y coordinate for the location
	 * @return ycoord
	 */
	public int yCoord() {
		return ycoord;
	}
	/*
	 * Method is used as a comparison between locations. Takes in a location of another pixel
	 * @param p
	 * @return int
	 * 
	 */
	public int compareTo (Location p) {
		/*
		 * Method compares coordinates based on provided inequalities. 
		 * First compares x coordinates, then compares y coordinates if the x coordinates are the same.
		 * Then moves on to compare solely the y coordinates.
		 * finally, 1 is returned if this location is bigger than p.
		 */
		if(this.xcoord<p.xCoord()) {
			return -1;
		}
		else if(this.xcoord==p.xCoord() && this.ycoord<p.yCoord()) {
			return -1;
		}
		else if(this.xcoord==p.xCoord() && this.ycoord==p.yCoord()) {
			return 0;
		}
		else {
			return 1;
		}
		
	}

}
