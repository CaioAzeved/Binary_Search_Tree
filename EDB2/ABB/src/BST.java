package src;

public class BST {
    public Node root;
    public int max_level;
    
    public void insert(Node node, int n) {
    	if(n > node.value) {
    		if(node.right == null) {
    			++node.qtd_RNodes;
    			node.right = new Node(n, node.level+1);
    			System.out.println("Inseri o " + node.right.value);
    		}
    		else {
    			++node.qtd_RNodes;
    			insert(node.right, n);
    		}
    	}
    	else if(n < node.value) {
    		if(node.left == null) {
    			++node.qtd_LNodes;
    			node.left = new Node(n, node.level+1);
    			System.out.println("Inseri o " + node.left.value);
    		}
    		else {
    			++node.qtd_LNodes;
    			insert(node.left, n);
    		}
    	}
    	else {
    		System.out.println("Valor já se encontra na árvore, não será possível inserir.");
    	}
    }
    
    public int calculateMax_Level(Node node, int n) {
    	int max = n;
    	if(node.left != null) {
    		if(calculateMax_Level(node.left, max) > max) {
    			max = calculateMax_Level(node.left, max);
    		}
    	}
    	if(node.right != null) {
    		if(calculateMax_Level(node.right, max) > max) {
    			max = calculateMax_Level(node.right, max);
    		}
    	}
    	return max;
    }
    
    public void ordemSimetrica(Node node) {
		if(node.left != null) {
			ordemSimetrica(node.left);
		}
		System.out.print(node.value + " ");
		if(node.right != null) {
			ordemSimetrica(node.right);
		}
	}
    
    public Node search(Node node, int key, int mode) {
    	if(node != null) {
    		if(node.value == key) {
    			mode = 1;
    		}
    		else {
    			if(key < node.value) {
    				if(node.left == null) {
    					mode = 2;
    				}
    				else {
    					node = node.left;
    				}
    			}
    			else {
    				if(node.right == null) {
    					mode = 2;
    				}
    				else {
    					node = node.right;
    				}
    			}
    			if(mode < 1) {
    				search(node, key, mode);
    			}
    		}
    	}
    	return node;
    }
    
    public int findNthElement(Node node, int n) {
    	int count = node.value;
		if((node.left != null) && (n != 0)) {
    		count = findNthElement(node.left, n);
    		
    	}
		--n;
		if(n == 0) {
			return count;
		}
		count = node.value;
    	if((node.right != null) && (n != 0)) {
    		count = findNthElement(node.right, n);
    	}
    	return count;
    }
    
    public int findPosition(Node node, int pos, int m, int y) {
    	
    }
    
    public boolean ItIsFull(Node node, boolean bool) {
    	if(node.left != null) {
    		bool = ItIsFull(node.left, bool);
		}
		if(node.right != null) {
			bool = ItIsFull(node.right, bool);
		}
		if((node.level != max_level) && (node.level-1 != max_level)) {
    		bool = false;
    	}
    	return bool;
    }
    
    public boolean ItIsFlooding(Node node, boolean bool) {
    	if(node.left != null) {
			bool = ItIsFlooding(node.left, bool);
		}
		if(node.qtd_LNodes != node.qtd_RNodes) {
			bool = false;
		}
		if(node.right != null) {
			bool = ItIsFlooding(node.right, bool);
		}
		return bool;
    }
    
    private void printFormat1(int m, Node node, int n) {
    	for(int i = 0; i < m; ++i) {
    		System.out.print(" ");
    	}
    	System.out.print(node.value);
    	for(int i = 0; i < n; ++i) {
    		System.out.print("-");
    	}
    	System.out.print("\n");
    	if(node.left != null) {
    		printFormat1(m+4, node.left, n-4);
    	}
    	if(node.right != null) {
    		printFormat1(m+4, node.right, n-4);
    	}
    }
    
	private void printFormat2(Node node) {
	    	System.out.print("(" + node.value);
	    	//System.out.print(" [" + node.qtd_LNodes + node.qtd_RNodes + "] ");
	    	if(node.left != null) {
	    		//System.out.print(" ");
	    		printFormat2(node.left);
	    	}
	    	if(node.right != null) {
	    		//System.out.print(" ");
	    		printFormat2(node.right);
	    	}
	    	System.out.print(")");
	}
	
	public void format(boolean bool) {
		if(bool) {
			printFormat1(0, root, 40);
		}
		else {
			printFormat2(root);
		}
	}

    
    
    
}