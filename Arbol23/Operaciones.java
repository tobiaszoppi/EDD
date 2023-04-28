package Arbol23;

import TDACola.*;
import TDALista.*;

public class Operaciones {
	public void preorden(Arbol23<Integer> t) {
		if (!t.isEmpty() && t.root()!=null) {
			preordenAux(t.root());
		}
	}
	
	private void preordenAux(NodoA23<Integer> p) {
		if (p.getRotulo1()!=null) {
			System.out.print("("+p.getRotulo1());
			if (p.getRotulo2()!=null) {
				System.out.print("|"+p.getRotulo2());
			}
			System.out.print(") - ");
			if (p.getLeft()!=null) {
				preordenAux(p.getLeft());
			}
			if (p.getMedio()!=null) {
				preordenAux(p.getMedio());
			}
			if (p.getRight()!=null) {
				preordenAux(p.getRight());
			}
		}
	}
	
	public <E> void postorden(Arbol23<Integer> t) {
		if (!t.isEmpty() && t.root()!=null) {
			postordenAux(t.root());
		}
	}
	
	private void postordenAux(NodoA23<Integer> p) {
		if (p.getRotulo1()!=null) {
			if (p.getLeft()!=null) {
				postordenAux(p.getLeft());
			}
			if (p.getMedio()!=null) {
				postordenAux(p.getMedio());
			}
			if (p.getRight()!=null) {
				postordenAux(p.getRight());
			}
			System.out.print("("+p.getRotulo1());
			if (p.getRotulo2()!=null) {
				System.out.print("|"+p.getRotulo2());
			}
			System.out.print(") - ");
		}
	}
	
	public void inorder(Arbol23<Integer> t) {
		if (!t.isEmpty()) {
			inorderAux(t.root());
		}
	}
	
	private <E> void inorderAux(NodoA23<Integer> v) {
		if (v.getRotulo1()!=null) {
			if(v.getLeft()==null && v.getMedio()==null && v.getRight()==null) {//si un nodo hoja
				System.out.print("("+v.getRotulo1());
				if (v.getRotulo2()!=null) {
					System.out.print("|"+v.getRotulo2());
				}
				System.out.print(") - ");
			}
			else {
				if (v.getLeft()!=null) {//primer hijo de v
					inorderAux(v.getLeft());
				}
				System.out.print("("+v.getRotulo1());
				if (v.getRotulo2()!=null) {
					System.out.print("|"+v.getRotulo2());
				}
				System.out.print(") - ");
				if (v.getMedio()!=null) {//primer hijo de v
					inorderAux(v.getMedio());
				}
				if (v.getRight()!=null) {//primer hijo de v
					inorderAux(v.getRight());
				}
			}
		}
	}
	
	public void porNiveles(Arbol23<Integer> t) {//ANDA SOLO SI LAS POSICIONES NO TIENEN EL MISMO ROTULO
		NodoA23<Integer> elemento;
		Queue<NodoA23<Integer>> cola=new ColaConArregloCircular<NodoA23<Integer>>();
		try {
			if (!t.isEmpty() && t.root()!=null) {
				cola.enqueue(t.root());
				cola.enqueue(null);//para que imprima el salto de linea
				while(!cola.isEmpty()) {
					elemento=cola.dequeue();
					if (elemento!=null) {
						if (elemento.getRotulo1()!=null) {
							System.out.print("("+elemento.getRotulo1());
							if (elemento.getRotulo2()!=null) {
								System.out.print("|"+elemento.getRotulo2());
							}
							System.out.print(") ");
						}
						if (elemento.getLeft()!=null) {
							cola.enqueue(elemento.getLeft());
						}
						if (elemento.getMedio()!=null) {
							cola.enqueue(elemento.getMedio());
						}
						if (elemento.getRight()!=null) {
							cola.enqueue(elemento.getRight());
						}
					}
					else  {
						System.out.println();
						if (!cola.isEmpty()) {
							cola.enqueue(null);
						}
					}
				}
			}
		}
		catch(EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	public <E> int profundidad(Arbol23<Integer> t, Integer p) {//Longitud del camino de la raiz de t a la posicion p
		NodoA23<Integer> n=t.buscar(p);
		return profundidadAux(t,n);
	}
	
	private int profundidadAux(Arbol23<Integer> t,NodoA23<Integer> n) {
		int cont=0;
		if (t!=null && !t.isEmpty() && n!=null) {
			if (t.root()==n) {
				cont=0;
			}
			else {
				cont=1+profundidadAux(t,n.getParent());
			}
		}
		return cont;
	}
	
}
