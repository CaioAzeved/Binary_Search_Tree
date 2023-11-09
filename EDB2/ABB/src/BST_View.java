package src;
import java.util.ArrayList;
import java.util.Scanner;

public class BST_View {
	
	public void Menu() {
		Scanner ler = new Scanner(System.in);
		int n;
		BST ABB = new BST();
		
		System.out.println("Informe os valores para a sua árvore:");
		
		for(int i = 0; i < 5; ++i) {
			n = ler.nextInt();
			if(i == 0) {
				ABB.root = new Node(n, 1);
			}
			else {
				ABB.insert(ABB.root, n);
			}
		}
		ABB.max_level = ABB.calculateMax_Level(ABB.root, 1);
		System.out.println("Informe a ordem de impressão de sua árvore:");
		n = ler.nextInt();
		if(n == 1) {
			ABB.format(true);
		}
		else {
			ABB.format(false);
		}
		ABB.calculateNumberOfNodes();
		//Integer i = new Integer(0);
		ArrayList<Integer> count = new ArrayList<Integer>();
		count.add(0);
		//System.out.println(ABB.findNthElement(ABB.root, 1, count));
		//lembrar de calcular o número de nodes
		ArrayList<Boolean> bool = new ArrayList<Boolean>();
		bool.add(false);
		//lembrar de olhar o bool
		//System.out.println(ABB.findPosition(ABB.root, 10, 0, bool));
		//lembrar de calcular o número de nodes
		//System.out.println(ABB.median());
		bool.set(0, false);
		int sum = 0;
		//System.out.printf("%.2f %n", ABB.average(ABB.root, 9, sum, bool));
		String str = "";
		//System.out.println(ABB.pre_order(ABB.root, count, str));
		ABB.remove(ABB.root, 8);
		System.out.println("Informe a ordem de impressão de sua árvore:");
		n = ler.nextInt();
		if(n == 1) {
			ABB.format(true);
		}
		else {
			ABB.format(false);
		}
		
	}
}