/*
 * Martin Spassov
 * mspassov 
 * 250901340
 */

/*
 * Class creates a binary node, to be stored in a BST
 */
public class BinaryNode {
	/*
	 * Declaring the variables
	 */
	private Pixel bValue;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	/*
	 * Constructor initializes the variables with the given input
	 * @param val, l, r, par
	 */
	public BinaryNode(Pixel val, BinaryNode l, BinaryNode r, BinaryNode par) {
		/*
		 * Initializing the variables
		 */
		bValue=val;
		left=l;
		right=r;
		parent=par;
	}
	
	/*
	 * Second constructor initializing the variables to null if there is no input
	 * Essentially creates a leaf node
	 */
	public BinaryNode() {
		bValue=null;
		left=null;
		right=null;
		parent=null;
	}
	
	/*
	 * Method returns the parent node of this node
	 * @return parent
	 */
	public BinaryNode getParent() {
		return this.parent;
	}
	
	/*
	 * Method sets the parent of this node
	 * @param par
	 */
	 public void setParent(BinaryNode par) {
		 this.parent=par;
	 }
	 
	 /*
	  * Method sets the left child of this node
	  * @param p
	  */
	 public void setLeft (BinaryNode p) {
		 this.left=p;
	 }
	 
	 /*
	  * Method sets the right child of this node
	  * @param p
	  */
	 public void setRight (BinaryNode p) {
		 this.right=p;
	 }
	
	 /*
	  * Method sets the data to be stored in this node
	  * @param value
	  */
	 public void setData (Pixel value) {
		 this.bValue=value;
	 }
	 
	 /*
	  * Method checks to see if this is node if a leaf
	  * @return true, false
	  */
	 public boolean isLeaf() {
		 
		 /*
		  * if everything in the node is null, means that it must be a leaf
		  */
		 if(left == null && right == null && bValue == null) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 
	 /*
	  * Returns the data stores in the node
	  * @return bValue
	  */
	 public Pixel getData () {
		 return bValue;
	 }
	 
	 /*
	  * Method returns left child of this node
	  * @return left
	  */
	 public BinaryNode getLeft() {
		 return left;
	 }
	 
	 /*
	  * Method returns right child of this node
	  * @return right
	  */
	 public BinaryNode getRight() {
		 return right;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
