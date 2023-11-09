package src;

import java.util.ArrayList;

public class BST {
    public Node root;
    public int max_level;
    public int number_of_nodes;
    
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
    
    public void calculateNumberOfNodes() {
    	number_of_nodes =  1 + root.qtd_LNodes + root.qtd_RNodes;
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
    
    //root, unknown, true
    public boolean search(Node node, int key, boolean bool) {
    	if(node.value != key) {
    		if(key < node.value) {
    			if(node.left != null) {
    				bool = search(node.left, key, bool);
    			}
    			else {
    				return false;
    			}
    		}
    		else {
    			if(node.right != null) {
    				bool = search(node, key, bool);
    			}
    			else {
    				return false;
    			}
    		}
    	}
    	return bool;
    }
    
    private Node pred(Node node) {
    	Node node_fake = null;
    	if(node.right != null) {
    		node_fake = pred(node.right);
    	}
    	else {
    		node_fake = node;
    	}
    	if(node_fake != null) {
    		return node_fake;
    	}
    	return node;
    }
    
    private Node succ(Node node) {
    	if(node.left != null) {
    		succ(node.left);
    	}
    	return node;
    }
    
    //root, key
    
    public void remove(Node node, int key) {
    	if(key == node.value) {
    		if((node.left == null) && (node.right == null)) {
        		node = null;
        	}
    		else if(node.left == null) {
    			node = node.right; 
    		}
    		else if(node.right == null) {
    			node = node.left;
    		}
    		else if(node.qtd_LNodes <= node.qtd_RNodes) {
    			pred(node).left = node.left;
    			pred(node).right = node.right;
    			node = pred(node);
    		}
    		else {
    			succ(node).left = node.left;
    			succ(node).right = node.right;
    			node = succ(node);
    		}
    	}
    	else if(key < node.value) {
    		if(node.left != null) {
    			remove(node.left, key);
    		}
    		else {
    			System.out.println("Chave não encontrada");
    		}
    	}
    	else {
    		if(node.right != null) {
    			remove(node.right, key);
    		}
    		else {
    			System.out.println("Chave não encontrada");
    		}
    	}
    	if(node == null) {
    		System.out.println("Não há nenhum nó na árvore");
    	}
    	
    }
    
    public int findNthElement(Node node, Integer n, ArrayList<Integer> count) {
    	if(n > number_of_nodes) {
    		//System.out.println("Acharei");
    		return -1;
    	}
    	int key = -1;
		if(node.left != null) {
    		key = findNthElement(node.left, n, count);
    	}
		//System.out.println(count);
    	//System.out.println(node.value);
    	if(key != -1) {
    		//System.out.println("Achou");
    		return key;
    	}
		count.set(0, count.get(0) + 1);
		if(count.get(0) == n) {
			//System.out.println("Achei");
			return node.value;
		}
    	if(node.right != null) {
    		key = findNthElement(node.right, n, count);
    	}
    	
    	return key;
    }
    //root, key, 0, false
    public int findPosition(Node node, int key, int pos, ArrayList<Boolean> bool) {
    	if(node.left != null) {
			pos = findPosition(node.left, key, pos, bool);
    	}
    	if(bool.get(0)) {
    		return pos;
    	}
		++pos;
		if(key == node.value) {
			bool.set(0, true);
			return pos;
		}
    	if(node.right != null) {
    		pos = findPosition(node.right, key, pos, bool);
    	}
    	return pos;
    }
    
    public int median() {
    	ArrayList<Integer> count = new ArrayList<Integer>();
		count.add(0);
		int n;
		if(number_of_nodes == 1) {
			return root.value;
		}
    	if(number_of_nodes % 2 == 1) {
    		return findNthElement(root, number_of_nodes/2 + 1, count);
    	}
    	if(findNthElement(root, number_of_nodes/2, count) < findNthElement(root, number_of_nodes/2 + 1, count)) {
    		n = findNthElement(root, number_of_nodes/2, count);
    	}
    	else {
    		n = findNthElement(root, number_of_nodes/2 + 1, count);
    	}
    	return n;
    }
    //root, key, 0, false 
    public double average(Node node, int key, double sum, ArrayList<Boolean> bool) {
    	if((node.value == key) || (bool.get(0))) {
    		sum = sum + node.value;
    		bool.set(0,  true);
    		if(node.left != null) {
        		sum = average(node.left, key, sum, bool);
        	}
        	if(node.right != null) {
        		sum = average(node.right, key, sum, bool);
        	}
    	}
    	else if(key < node.value) {
    		if(node.left != null) {
    			sum = average(node.left, key, sum, bool);
    		}
    	}
    	else {
    		if(node.right != null) {
    			sum = average(node.right, key, sum, bool);
    		}
    	}
    	if(node.value == key) {
    		return sum/(node.qtd_LNodes + 1 + node.qtd_RNodes); 
    	}
    	return sum;
    }
    
    //root, true
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
    //root, true
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
    
    //root, count.get(0) = 0, ""
    public String pre_order(Node node, ArrayList<Integer> count, String order) {
    	count.set(0, count.get(0) + 1);
    	if(count.get(0) != number_of_nodes) {
    		order = order + Integer.toString(node.value) + " -> " ;
    	}
    	else {
    		order = order + Integer.toString(node.value);
    	}
    	
    	if(node.left != null) {
    		order = pre_order(node.left, count, order);
    	}
    	if(node.right != null) {
    		order = pre_order(node.right, count, order);
    	}
    	return order;
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