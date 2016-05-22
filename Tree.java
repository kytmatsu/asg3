/*
 * Kyle Matsumoto
 * kytmatsu
 * CMPS012B
 * 11/21/14
 * The tree class which holds the functions necesarry for inputting and such in the tree.
 * */
import static java.lang.System.*; 

class Tree <key_t extends Comparable <key_t>, value_t>
{ 
	
	private class node 
	{ 
		key_t key; 
		value_t value; 
		node left; 
		node right; 

		node(key_t new_key, value_t new_value)
		{ 
			key = new_key; 
			value = new_value; 
			left = null; 
			right = null; 
		} 
	} 
	private node root; 
	
	//Helper function for debug_dump_rec 
	//Used for printing the depth of the tree 
	private void printDebug(int depth, key_t key, value_t val)
	{ 
		for (int x = 0; x < depth; ++x) 
			out.printf("  "); 
		out.printf("%d %s %s%n",depth, key, val); 
	} 

	//Performs and inorder traversal of tree 
	//Must use depth+1 and not (depth++ || ++depth) 
	private void debugDumpRec(node tree, int depth) 
	{ 
		if(tree.left != null) debugDumpRec(tree.left, depth+1); 

		printDebug(depth, tree.key, tree.value); 

		if(tree.right != null) debugDumpRec(tree.right, depth+1); 
	} 


	//Helper function for use in get 
	//Will perform a BS on the root node 
	//If found we will return a value_t else return null 
	private value_t getRec (node tree, key_t key)
	{ 

		if (tree == null) return null; 
		if (key.compareTo(tree.key) < 0) { 
			//get from left subtree 
			return getRec(tree.left, key);   
		}
		else if (key.compareTo(tree.key) > 0) 
		{ 
			//get from right subtree 
			return getRec(tree.right, key);  
		}
		else
		{ 
			//key found 
			return tree.value;                
		} 
	} 

	//Helper function of put 
	//Perform a binary search for a valid entry point 
	private node putRec(node tree, key_t key, value_t value)
	{ 
		if (tree == null) { 
			tree = new node(key,value); //additon place found 
		}else if(key.compareTo(tree.key) < 0){ 
			tree.left = putRec(tree.left, key, value); 
		}else if(key.compareTo(tree.key) > 0){ 
			tree.right = putRec(tree.right, key, value); 
		}else{ 
			tree.value = value; //replace old value iwth new 
		} 
		return tree; 
	} 

	private void visitRec ( X <key_t, value_t> visit_fn, node tree) 
	{ 
		if(tree == null) return; 
		visitRec(visit_fn, tree.left);  
		visit_fn.visit(tree.key, tree.value); 
		visitRec(visit_fn, tree.right); 
	} 


	//Perform a binary search on the tree. Return value if found, 
	//and null if not found. We use get_rec to check for a value in tree  
	public value_t get (key_t key)
	{ 
		value_t result; 
		result = getRec (root, key); 
		return result; 
	} 


	//If found, replace old value by new one, and return old value 
	//If not found, create a new node as leaf, and insert k-val, and 
	//return null 
	public value_t put (key_t key, value_t value)
	{ 
		value_t result; 
		result = getRec (root,key); 
		root = putRec(root,key, value); 
		return result; 
	} 


	public void debugDump ()
	{ 
		debugDumpRec (root, 0); 
	} 

	public void doVisit (X <key_t, value_t> visit_fn)
	{ 
		visitRec (visit_fn, root); 
	} 


} 
