package src;

public class Node {
	public int value;
	public Node left;
	public Node right;
	public int qtd_LNodes;
	public int qtd_RNodes;
	public int level;
	
	

	
	public Node (int value, int level) {
		this.value = value;
		this.left = null;
		this.right = null;
		this.qtd_LNodes = 0;
		this.qtd_RNodes = 0;
		this.level = level;
	}
}