/*
 * Martin Spassov
 * mspassov
 * 250901340
 */

/*
 * Class creates a pixel object which will be stores in a BST
 */
public class Pixel {
	/*
	 * Creating the variables
	 */
	private Location pix;
	private int pColour;
	
	/*
	 * Constructor, initializes variables
	 * @param p, color
	 */
	public Pixel(Location p, int color) {
		/*
		 * Initialization of variables
		 */
		pix=p;
		pColour=color;
	}
	
	/*
	 * Returns location key of Pixel 
	 * @return pix
	 */
	public Location getLocation() {
		return pix;
	}
	
	/*
	 * Returns colour stored in Pixel
	 * @return pColour
	 */
	public int getColor() {
		return pColour;
	}

}
