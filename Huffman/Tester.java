package Huffman;
import TDAArbolBinario.*;

public class Tester {
	public static void main(String[] args) {
		try {
		Huffman h=new Huffman();
		BinaryTree<Character> t=h.huffman2("abcd");
		System.out.println(h.comprimir(t, "abcd"));
		//System.out.println(h.descomprimir(t, "11111101010111111001001000"));
		/*BTPosition<Character> raiz=(BTPosition<Character>) t.root();
		System.out.println("      "+raiz.element());
		System.out.println("    "+raiz.getLeft().element()+"   "+raiz.getRight().element());
		BTPosition<Character> hi=(BTPosition<Character>) raiz.getLeft();
		if (t.hasLeft(hi)) {
			System.out.println("  "+hi.getLeft().element()+"   "+hi.getRight().element());
			hi=(BTPosition<Character>) hi.getLeft();
			if (t.hasLeft(hi)) {
				System.out.println(hi.getLeft().element()+"   "+hi.getRight().element());
			}
		}*/
		//////
		BTPosition<Character> raiz=(BTPosition<Character>) t.root();
		System.out.println("    "+raiz.element());
		System.out.println(" "+raiz.getLeft().element()+"     "+raiz.getRight().element());
		BTPosition<Character> hd=(BTPosition<Character>) raiz.getRight();
		if (t.hasRight(hd)) {
			System.out.println("    "+hd.getLeft().element()+"     "+hd.getRight().element());
			hd=(BTPosition<Character>) hd.getRight();
			if (t.hasRight(hd)) {
				System.out.println("        "+hd.getLeft().element()+"    "+hd.getRight().element());
			}
		}
		
		//h.porNiveles(t);
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		
	}
}
