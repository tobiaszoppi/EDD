package TDAABB;

public class Tester {

	public static void main(String[] args) {
		DefaultComparator<Integer> comp = new DefaultComparator<Integer>();
		ArbolBinarioBusqueda<Integer> a = new ArbolBinarioBusqueda<Integer>();
		a.expandir(a.getRaiz(),50);
		NodoABB<Integer> p1 = a.buscar(50);
		a.expandir(p1.getLeft(),25);
		a.expandir(p1.getRight(),75);
		NodoABB<Integer> p3 = a.buscar(75);
		NodoABB<Integer> p2 = a.buscar(25);
		a.expandir(p2.getLeft(),20);
		a.expandir(p2.getRight(),30);
		a.expandir(p3.getLeft(),60);
		a.expandir(p3.getRight(),100);
		NodoABB<Integer> p4 = a.buscar(100);
		a.expandir(p4.getLeft(),99);
		a.expandir(p4.getRight(),101);
		a.imprimir();
		System.out.println("elimino");
		a.eliminar(75);
		a.imprimir();
	}

}
