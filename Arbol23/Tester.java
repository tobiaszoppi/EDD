package Arbol23;

public class Tester {

	public static void main(String[] args) {
		Arbol23<Integer> a=new Arbol23<Integer>();
		a.insertar(100);
		a.insertar(200);
		a.insertar(120);
		a.insertar(50);
		a.insertar(220);
		a.insertar(300);
		a.insertar(170);
		a.insertar(400);
		a.insertar(210);
		a.insertar(70);
		a.insertar(40);
		//a.insertar(30);
		a.insertar(90);
		a.insertar(165);
		a.insertar(500);
		a.insertar(205);
		a.insertar(225);
		a.insertar(405);
		a.insertar(207);
		a.mostrar();
		Operaciones o=new Operaciones();
		System.out.println();
		System.out.println("Preorden");
		o.preorden(a);
		System.out.println();
		System.out.println("Postorden");
		o.postorden(a);
		System.out.println();
		System.out.println("Inorder");
		o.inorder(a);
		System.out.println();
		System.out.println("Por niveles");
		o.porNiveles(a);
		System.out.println();
		System.out.println("Profundidad de 50 "+o.profundidad(a, 400));
		
	}

}
