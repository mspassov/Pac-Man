/*
 * Martin Spassov 
 * mspassov
 * 250901340
 */

/*
 * Class creates a Binary Search Tree
 */
public class BinarySearchTree implements BinarySearchTreeADT {
	
	/*
	 * Creating the root node
	 */
	BinaryNode root;
	
	/*
	 * Constructor initializes the root node to a leaf
	 */
	public BinarySearchTree() {
		root = new BinaryNode();
	}
	
	/*
	 * Method returns a pixel stored with the given key from the tree. Searches tree recursively
	 * @param r, key
	 * @return Pixel
	 */
	public Pixel get (BinaryNode r, Location key) {
		
		/*
		 * If the node is a leaf then null is returned
		 */
		if(r.isLeaf()) {
			return null;
		}
		else {
			
			/*
			 * Method compares the key using the given inequalities, and recursively searches the tree to 
			 * the left or to the right.
			 * If the key is not found then null is returned
			 */
			if(key.compareTo(r.getData().getLocation())==0) {
				return r.getData();
			}
			else if(key.compareTo(r.getData().getLocation())==-1) {
				return this.get(r.getLeft(), key);
			}
			else if(key.compareTo(r.getData().getLocation())==1) {
				return this.get(r.getRight(), key);
			}
			
			else {
				return null;
			}
		}
		
	}
	
	/*
	 * Helper method which returns a node with the given key. Searches tree recursively
	 * @param r, key
	 * @return BinaryNode
	 */
	private BinaryNode findNode(BinaryNode r, Location key) {
		/*
		 * Method compares the key using the given inequalities, and recursively searches the tree to 
		 * the left or to the right.
		 * If the key is not found then null is returned
		 */
		if(r.isLeaf()) {
			return r;
		}
		else {
			if(key.compareTo(r.getData().getLocation())==0) {
				return r;
			}
			else if(key.compareTo(r.getData().getLocation())==-1) {
				return this.findNode(r.getLeft(), key);
			}
			else if(key.compareTo(r.getData().getLocation())==1) {
				return this.findNode(r.getRight(), key);
			}
			
			else {
				return null;
			}
		}
	}
	
	/*
	 * Method takes the inputed pixel and stores it into the tree. 
	 * @param r, data 
	 */
	public void put (BinaryNode r, Pixel data) throws DuplicatedKeyException{
		
		/*
		 * First the helper method is used to find which node should store the pixel object
		 */
		BinaryNode node=this.findNode(r, data.getLocation());
		
		/*
		 * If the node is not a leaf, this means that the key already exists and thus an exception is thrown
		 */
		if(!node.isLeaf()) {
			throw new DuplicatedKeyException();
		}
		else {
			
			/*
			 * This node is definitely a leaf therefore the data is updated
			 */
			
			node.setData(data);
			
			/*
			 * Now the node becomes an internal node, therefore two leaves are created, and their parents are set to the
			 * found node
			 */
			BinaryNode leaf1=new BinaryNode();
			leaf1.setParent(node);
			BinaryNode leaf2=new BinaryNode();
			leaf2.setParent(node);
			
			
			/*
			 * Finally the node is linked to its children(which are leaves)
			 */
			node.setLeft(leaf1);
			node.setRight(leaf2);
			/*
			 * Special case if the root is a leaf, then the variable is updated and its parent is set to null
			 */
			if(root.isLeaf()) {
				node.setParent(null);
				root=node;
			}
			
			
		}

	}
	/*
	 * Method takes in a given key and removes the node stored with that key, or it throws an exception
	 * if the key does not exist
	 * 
	 * @param r, key
	 */
	public void remove (BinaryNode r, Location key) throws InexistentKeyException{
		
		/*
		 * First the node to be removed is found using the helper method
		 */
		BinaryNode node=findNode(root, key); //find node with key k that needs to be removed
		
		/*
		 * If the node is not a leaf then the algorithm continues, otherwise an exception is thrown
		 */
		if(node.isLeaf()==false) {
			
			/*
			 * If the the node has children which are both leaves, the removal occurs here
			 */
			if(node.getLeft().isLeaf() && node.getRight().isLeaf()) {
				
				/*
				 * Special case of the root needing to be removed, if this is true then the variable is updated
				 */
				if(node.getData().getLocation().compareTo(root.getData().getLocation())==0) {
					root= new BinaryNode();
				}
				
				/*
				 * If it's not a root, then the node is a standard internal node
				 */
				else {
					
					/*
					 * Depending on is it is smaller or bigger than the parent, the node is removed
					 * and the left or the right child of the parent is set to a leaf
					 */
					if(node.getData().getLocation().compareTo(node.getParent().getData().getLocation())==-1) {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
					else if(node.getData().getLocation().compareTo(node.getParent().getData().getLocation())==1) {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}
					/*
					 * Throws an exception if the node cannot be removed
					 */
					else {
						throw new InexistentKeyException();
					}
				}

			}
			/*
			 * If only one of the children is a leaf, then the node is removed here
			 */
			else if(node.getLeft().isLeaf() || node.getRight().isLeaf()) {
				
				/*
				 * Special case, if the node is to be removed
				 */
				if(node.getData().getLocation().compareTo(root.getData().getLocation())==0) {
					/*
					 * Determine which child of the root is the leaf
					 */
					BinaryNode child;
					if(node.getLeft().isLeaf()==false) {
						child=node.getLeft();
					}
					else {
						child=node.getRight();
					}
					
					/*
					 * Whichever child is not a leaf, the root variable is updated to be that node
					 */
					root=child;
					child.setParent(null);
					root.setParent(null);
					
				}
			
				/*
				 * Ordinary internal node is removed
				 */
				else {
					
					/*
					 * Check to see which child is not a leaf.
					 * Then the non-leaf child is linked to the parent and vice versa, removing the node
					 */
					BinaryNode child2;
					if(node.getLeft().isLeaf()==false) {
						child2=node.getLeft();
						child2.setParent(node.getParent());
						node.getParent().setLeft(child2);
					}
					else {
						child2=node.getRight();
						child2.setParent(node.getParent());
						node.getParent().setRight(child2);
					}
					
					
				}
			}
			/*
			 * Finally if both of the children are internal nodes, then the removal occurs here
			 */
			else {		//Both of the children are internal nodes
				
				BinaryNode small;
				
				/*
				 * The smallest node on the right is found here. The data is exchanged with this node
				 * Then the smallest is effectively removed
				 */
				try {
					small= smallestNode(node.getRight());
					node.setData(small.getData());
					small.getRight().setParent(small.getParent());
				}
				catch(EmptyTreeException e) {
					e.getMessage();
				}
				
			}
		}
		/*
		 * An exception is thrown if the key is not in the tree
		 */
		else {
			throw new InexistentKeyException();
		}
	
	}
	
	/*
	 * Method returns the pixel with the smallest key larger than the given one
	 * @param r, key
	 * @return Pixel
	 */
	public Pixel successor (BinaryNode r, Location key) {
		
		/*
		 * First the node with the provided key is found using a helper method
		 */
		BinaryNode node=findNode(r, key);
		BinaryNode pNode=null;
		Pixel lNode=null;
		
		/*
		 * Special cae, checks if it the largest node in the tree, if it is, returns null because there is no successor
		 */
		try {
			lNode= largest(root);
		}
		catch(Exception e) {
			e.getMessage();
		}
		
		if(key.compareTo(lNode.getLocation())==0) {
			return null;
		}
		
		/*
		 * If the node is an internal node and the node on the right is an internal node then the successor can be found
		 */
		if(!node.isLeaf() && !node.getRight().isLeaf()) {
			
			/*
			 * Returns the smallest to the right of the node
			 */
			try {
				return smallest(node.getRight());
			}
			catch(EmptyTreeException e) {
				e.getMessage();
			}
		}
		
		/*
		 * Otherwise goes up the tree until either the parent is null or the right child of the parent equals the node
		 */
		else {
			pNode=node.getParent();
			/*
			 * While loop checks the above conditions
			 */
			while(pNode!=null && pNode.getRight()==node) {
				node=pNode;
				pNode=pNode.getParent();
			}
			
			/*
			 * now if the node used for storage is null, then null is returned because there is no successor
			 */
			if(pNode==null) {
				return null;
			}
			/*
			 * Otherwise the data is returned
			 */
			else {
				return pNode.getData();
			}

		}
		
		return null;
	}
	
	/*
	 * Method returns the pixel with the largest key smaller than the given one
	 * @param r, key
	 * @return Pixel
	 */
	public Pixel predecessor (BinaryNode r, Location key) {
		
		/*
		 * Node is located
		 */
		BinaryNode node=findNode(r, key);
		BinaryNode pNode=null;
		Pixel sNode=null;
		
		/*
		 * Special case, does not have a predecessor if it is the smallest
		 */
		try {
			sNode=smallest(root);
		}
		catch(Exception e) {
			e.getMessage();
		}
		
		if(key.compareTo(sNode.getLocation())==0) {
			return null;
		}
		
		/*
		 * Also doesn't have a predecessor if it is a leaf
		 */
		if(node.isLeaf()) {
			return null;
		}
		
		/*
		 * First condition for having a predecessor. Node is not a leaf and left child is not a leaf
		 */
		if(!node.isLeaf() && !node.getLeft().isLeaf()) {
			try {
				return largest(node.getLeft());
			}
			catch(EmptyTreeException e) {
				e.getMessage();
			}
		}
		else {
			pNode=node.getParent();
			
			if(pNode.getLeft().isLeaf()) {
				return pNode.getData();
			}
			
			/*
			 * While loop continues until either the parent is null or they are equal to each other
			 */
			while(pNode!=null && node.getData().getLocation().compareTo(pNode.getLeft().getData().getLocation())==0) {
				node=pNode;
				pNode=pNode.getParent();
				
				if(pNode.getLeft().isLeaf()) {
					return pNode.getData();
				}
			}
			
			/*
			 * if the node is null then thats what it returns
			 */
			if(pNode==null) {
				return null;
			}
			
			else {
				return pNode.getData();
			}
		}
		
		return null;
	}
	
	/*
	 * Helper method which returns the node with the smallest location key
	 * @param r
	 * @return BinaryNode
	 */
	private BinaryNode smallestNode(BinaryNode r) throws EmptyTreeException{
		/*
		 * If the node is null then null is returned
		 */
		if(r == null) {
			throw new EmptyTreeException();
		}
		else {
			/*
			 * Also checks is the node passed is a leaf, returns null if it is
			 */
			if(r.isLeaf()) {
				return null;
			}
			else {
				/*
				 * Otherwise a traversal is done to the left until a leaf is reached
				 */
				BinaryNode node=r;
				/*
				 * while loop goes to the left until a leaf is reached and then the parent of that lead is returned
				 */
				while(!node.isLeaf()) {
					node=node.getLeft();
				}
				
				return node.getParent();
			}
		}
	}
	
	/*
	 * Method returns the pixel stored in the node with the smallest location
	 * @param r
	 * @return Pixel
	 */
	public Pixel smallest(BinaryNode r) throws EmptyTreeException{
		
		/*
		 * If the node is null then null is returned
		 */
		if(r == null) {
			throw new EmptyTreeException();
		}
		else {
			/*
			 * Also checks is the node passed is a leaf, returns null if it is
			 */
			if(r.isLeaf()) {
				return null;
			}
			/*
			 * Otherwise a traversal is done to the left until a leaf is reached
			 */
			else {
				/*
				 * while loop goes to the left until a leaf is reached and then the parent of that lead is returned
				 */
				BinaryNode node=r;
				while(!node.isLeaf()) {
					node=node.getLeft();
				}
				
				return node.getParent().getData();
			}
		}
	}
	
	/*
	 * Method returns the node which stores the largest location
	 * @param r
	 * @return Pixel
	 */
	public Pixel largest(BinaryNode r) throws EmptyTreeException{
		
		/*
		 * If the node is null then null is returned
		 */
		if(r == null) {
			throw new EmptyTreeException();
		}
		/*
		 * Otherwise a traversal is done to the right until a leaf is reached
		 */
		else {
			/*
			 * Also checks is the node passed is a leaf, returns null if it is
			 */
			if(r.isLeaf()) {
				return null;
			}
			else {
				
				/*
				 * while loop goes to the right until a leaf is reached and then the parent of that lead is returned
				 */
				BinaryNode node=r;
				while(!node.isLeaf()) {
					node=node.getRight();
				}
				
				return node.getParent().getData();
			}
		}
		
	}
	/*
	 * Method returns the root of the tree
	 * @return root
	 */
	public BinaryNode getRoot() {
		return root;
	}
}
