/*
 * Martin Spassov
 * mspassov
 * 250901340
 */

/*
 * Class creates a graphical object that stores its pixels in a hash table
 */
public class GraphicalObject implements GraphicalObjectADT{
	
	/*
	 * Variables are declared here
	 */
	private int ID;
	private int gWidth;
	private int gHeight;
	private String gType;
	private Location position; //the offset
	private BinarySearchTree tree;
	
	/*
	 * Constructor takes in input and sets the variables based on the inputs
	 * @param id, width, height, type, pos
	 */
	public GraphicalObject (int id, int width, int height, String type, Location pos) {
		/*
		 * Variables are initialzed and a new tree is created
		 */
		ID=id;
		gWidth=width;
		gHeight=height;
		gType=type;
		position=pos;
		tree= new BinarySearchTree();
	}
	
	 public void setType (String type) {
		 gType=type;
	 }
	 
	 /*
	  * Method returns width
	  * @return gWidth
	  */
	 public int getWidth () {
		 return gWidth;
	 }
	 
	 /*
	  * Method returns height 
	  * @return gHeight
	  */
	 public int getHeight() {
		 return gHeight;
	 }
	 
	 /*
	  * Method returns type
	  * @return gType
	  */
	 public String getType () {
		 return gType; 
	 }
	 
	 /*
	  * Method returns identifier of this object
	  * @return ID
	  */
	 public int getId() {
		 return ID;
	 }
	 
	 /*
	  * Method returns the offset
	  * @return position
	  */
	 public Location getOffset() {
		 return position;
	 }
	 
	 /*
	  * Method sets the offset
	  * @param value
	  */
	 public void setOffset(Location value) {
		 position=value;
	 }
	 
	 /*
	  * Method puts a pixel into the tree
	  * @param pix
	  */
	 public void addPixel(Pixel pix) throws DuplicatedKeyException {
		 try {
			 tree.put(tree.getRoot(), pix);
		 }
		 catch(DuplicatedKeyException e) {
			 e.getMessage();
		 }
	 }
	 
	 /*
	  * Helper method to access the tree 
	  * @return tree
	  */
	 private BinarySearchTree getTree() {
		 return tree;
	 }
	 
	 /*
	  * Helper method to locate a key in the given graphical object
	  * @param p, gobj
	  * @return boolean
	  */
	 private boolean findPixel(Location p, GraphicalObject gobj) {
		 Pixel pi=null;
		 boolean check=false;
		 
		 /*
		  * uses the get method to locate the pixel if it is in the tree
		  */
		 try {
			 pi=gobj.getTree().get(gobj.getTree().getRoot(), p);
		 }
		 catch(Exception e) {
			 e.getMessage();
		 }
		 
		 /*
		  * If it is not in the tree, false is returned, otherwise true is returned
		  */
		 if(pi == null) {
			 return false;
		 }
		 else {
			 return true;
		 }
	 }
	 
	 /*
	  * Method checks if two graphical objects intersect
	  *@param gobj
	  *@return boolean
	  */
	 public boolean intersects (GraphicalObject gobj) {
		 Pixel current=null;
		 
		 /*
		  * First starts from the smallest node in this tree
		  */
		 try {
		 current=tree.smallest(tree.getRoot()); 
		 }
		 catch(Exception e) {
			 e.getMessage();
		 }
		 
		 boolean checker=false;
		 
		 /*
		  * A while loop is run to check every single node in this tree
		  */
		 while(true) {
			 
			 /*
			  * The location is checked based on the equation provided to us
			  */
			 Location p= new Location(current.getLocation().xCoord()+this.getOffset().xCoord()-gobj.getOffset().xCoord(), current.getLocation().yCoord()+this.getOffset().yCoord()-gobj.getOffset().yCoord());
			 checker=findPixel(p, gobj);
			 
			 /*
			  * If the flag returns true this means there is an overlap and we can stop looking
			  */
			 if(checker==true) {
				 break;
			 }
			 else {
				 /*
				  * Otherwise the next node in line is checked which is the successor
				  */
				 current=tree.successor(tree.getRoot(), current.getLocation());
				 if(current==null) {
					 break;
				 }
			 }
		 }
		 
		 /*
		  * The appropriate value is returned based on if there is an overlap or not
		  */
		 if(checker==true) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	
	
}