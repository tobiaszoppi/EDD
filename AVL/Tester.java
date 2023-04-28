package AVL;
import TDAABB.ArbolBinarioBusqueda;
import TDAABB.DefaultComparator;
import TDAABB.NodoABB;
import TDAArbolBinario.*;
public class Tester {

	public static void main(String[] args) {
		//try {
		//BinaryTree<Integer> a=new ArbolBinarioEnlazado<Integer>();
		AVL<Integer> avl=new AVL<Integer>();
		/*Position<Integer> p1=a.createRoot(5);
		Position<Integer> p2=a.addLeft(p1,2);
		Position<Integer> p3=a.addRight(p1,8);
		Position<Integer> p4=a.addLeft(p2,1);
		Position<Integer> p5=a.addRight(p2,4);
		Position<Integer> p6=a.addLeft(p5,3);
		Position<Integer> p7=a.addRight(p3,7);
		Position<Integer> p8=a.addLeft(p7,6);
		Position<Integer> p9=a.addLeft(p6,1);
		for(Position<Integer> p:avl.listaNodosNoCumplenCondicion(a)) {
			System.out.print(p.element()+" ");
		}*/
		
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
		//a.expandir(p4.getLeft(),99);
		a.expandir(p4.getRight(),101);
		NodoABB<Integer> p5 = a.buscar(101);
		a.expandir(p5.getRight(),102);
		/*NodoABB<Integer> p6 = a.buscar(102);
		a.expandir(p6.getRight(),103);*/
		a.imprimir();
		System.out.println();
		for(NodoABB<Integer> p:avl.listaNodosNoCumplenCondicion(a)) {
			System.out.print(p.getRotulo()+" ");
		}
		
		//RotacionI
		/*System.out.println("RotacionI");
		AVL<Integer> avl1=new AVL<Integer>();
		avl1.insertar(10);
		avl1.insertar(20);
		avl1.insertar(20);
		/*avl1.insertar(1);
		avl1.insertar(2);
		avl1.insertar(40);
		avl1.insertar(35);
		avl1.insertar(50);
		avl1.insertar(55);
		avl1.insertar(66);*/
		//avl1.mostrar();
		/*avl1.insertar(5);
		avl1.insertar(2);
		avl1.insertar(8);
		avl1.insertar(1);
		avl1.insertar(4);
		avl1.insertar(7);
		avl1.insertar(3);
		avl1.insertar(6);
		avl1.mostrar();
		//RotacionII
		System.out.println();
		System.out.println("RotacionII");
		AVL<Float> avl2=new AVL<Float>();
		avl2.insertar((float) 5);
		avl2.insertar((float) 2);
		avl2.insertar((float) 8);
		avl2.insertar((float) 1);
		avl2.insertar((float) 4);
		avl2.insertar((float) 9);
		avl2.insertar((float) 3);
		avl2.insertar((float) 7);
		avl2.insertar((float) 6);
		avl2.insertar((float) 6.5);
		avl2.mostrar();
		//RotacionIII
		System.out.println();
		System.out.println("RotacionIII");
		AVL<Integer> avl3=new AVL<Integer>();
		avl3.insertar(5);
		avl3.insertar(2);
		avl3.insertar(8);
		avl3.insertar(1);
		avl3.insertar(4);
		avl3.insertar(9);
		avl3.insertar(3);
		avl3.insertar(10);
		avl3.mostrar();
		//RotacionIV
		System.out.println();
		System.out.println("RotacionIV");
		AVL<Integer> avl4=new AVL<Integer>();
		avl4.insertar(5);
		avl4.insertar(2);
		avl4.insertar(15);
		avl4.insertar(1);
		avl4.insertar(4);
		avl4.insertar(8);
		avl4.insertar(19);
		avl4.insertar(3);
		avl4.insertar(10);
		avl4.insertar(9);
		avl4.mostrar();*/
		/*}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}*/
	}

}
