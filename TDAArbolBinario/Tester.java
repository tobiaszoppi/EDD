package TDAArbolBinario;

public class Tester {

	public static void main(String[] args) {
		try {
			ArbolBinarioEnlazado<Integer> arbol=new ArbolBinarioEnlazado<Integer>();
			BTNodo<Integer> r=(BTNodo<Integer>) arbol.createRoot(0);
			BTNodo<Integer> p1=new BTNodo<Integer>(1,r);
			BTNodo<Integer> p2=new BTNodo<Integer>(2,r);
			r.setLeft(p1); r.setRight(p2);
			BTNodo<Integer> p3=new BTNodo<Integer>(3,p1);
			BTNodo<Integer> p4=new BTNodo<Integer>(4,p1);
			p1.setLeft(p3); p1.setRight(p4);
			BTNodo<Integer> p5=new BTNodo<Integer>(5,p2);
			p2.setRight(p5);
			BTNodo<Integer> p6=new BTNodo<Integer>(6,p3);
			p3.setLeft(p6);
			BTNodo<Integer> p7=new BTNodo<Integer>(7,p5);
			BTNodo<Integer> p8=new BTNodo<Integer>(8,p5);
			p5.setLeft(p7); p5.setRight(p8);
			System.out.println("Arbol");
			arbol.preorden();
			System.out.println();
			System.out.println("Arbol espejado");
			arbol.espejar();
			arbol.preorden();
			System.out.println();
			System.out.println("Padres de hojas");
			arbol.padreDeHojas();
			
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}

	}

}
