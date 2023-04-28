package AVL;
import java.util.Comparator;
import TDALista.*;
import TDAArbolBinario.*;
import TDAArbolBinario.BoundaryViolationException;
import TDAArbolBinario.InvalidPositionException;
import TDAArbolBinario.Position;
import TDAABB.*;

public class AVL<E extends Comparable<E>> {
	
	private NodoAVL<E> raiz;
	private Comparator<E> comp;
	
	public AVL(Comparator<E> comp) {
		raiz=new NodoAVL<E>(null);
		this.comp=comp;
	}
	public AVL() {
		raiz=new NodoAVL<E>(null);
		comp=new DefaultComparator<E>();
	}
	
	public NodoAVL<E> getRaiz() {
		return raiz;
	}
	
	public NodoAVL<E> buscar(E clave) {
		return buscarAux(raiz,clave);
	}
	
	private NodoAVL<E> buscarAux(NodoAVL<E> n, E clave) {
		NodoAVL<E> retorna;
		int comparacion;
		if (n.getRotulo()==null) {//si n es un dummy
			retorna=n;//retorno el dummy
		}
		else {//n no es un dummy
			comparacion=comp.compare(clave,n.getRotulo());
			if (n.getEliminado()==false && comparacion==0) {//n tiene la clave y no fue eliminado
				retorna=n;
			}
			else {//n no tiene la clave
				if (comparacion<0) {//clave < rotulo de n
					retorna=buscarAux(n.getLeft(),clave);//busca en el hijo izquierdo de n
				}
				else {//clave > rotulo de n
					retorna=buscarAux(n.getRight(),clave);//busca en el hijo der de n
				}
			}
		}
		return retorna;
	} 
	
	public void remove(E x) {
		NodoAVL<E> nodoEncontrado=buscar(x);
		if (nodoEncontrado.getRotulo()!=null) {//si se encontro un nodo con rotulo x
			nodoEncontrado.setEliminado(true);//setea el atributo eliminado en true	
		}
	}
	
	
	public void insertar(E x) {
		insertarAux(raiz,x);
	}
	
	private void insertarAux(NodoAVL<E> p, E item) { // p es el nodo actual, la primera vez es la raíz 
		if(p.getRotulo()==null) {// Llegué a un dummy
			p.setRotulo(item); 
			p.setAltura(1); // Le seteo la altura al dummy como 1 y le hago 2 hijos dummys
			p.setLeft(new NodoAVL<E>(null));
			p.getLeft().setParent(p); 
			p.setRight(new NodoAVL<E>(null));
			p.getRight().setParent(p);
			p.getLeft().setAltura(0);
			p.getRight().setAltura(0);
		} 
		else {//si p no es un dummy
			int comparacion=comp.compare(item, p.getRotulo());
			/*if(comparacion==0) { // ítem ya está en el árbol => no cambia nada 
				p.setEliminado(false);//se deja el atributo eliminado como false, por si el nodo estaba eliminado
			}
			else { */
				if(comparacion<0) {//si item < rotulo de p, va al hijo izq
					insertarAux(p.getLeft(),item); // Inserto recursivamente en el HI y a la vuelta rebalanceo
					System.out.println(p.getLeft().getAltura()+" "+p.getRight().getAltura());
					if(Math.abs(p.getLeft().getAltura() - p.getRight().getAltura())>1) {//si la diferencia entre las alturas de los hijos >1
						// Rebalancear mediante rotaciones: testeo por rotaciones (i) o (ii)
						// Si estoy aca => item < x, debo testear si (item < y) o (item > y)
					 	// si item < y => rotacion (i); si item > y => rotacion (ii)
						//System.out.println(p.getLeft().getAltura()+"-"+p.getRight().getAltura()+"="+Math.abs(p.getLeft().getAltura() - p.getRight().getAltura()));
						E y = p.getLeft().getRotulo();
						int comp_item_y = comp.compare(item,y);
						if( comp_item_y < 0 ) {//si item < rotulo deol hijo izq de p
							rotacionI(p); // rotacion izq-izq (i)
						}
						else {//si item >= rotulo deol hijo izq de p
							rotacionII(p); //rotacion izq-der (ii)
						}
					}
				} 
				else {// Caso simétrico pero insertando hacia la derecha y luego testeo por rotaciones (iii) y (iv) */
					insertarAux(p.getRight(),item); // Inserto recursivamente en el HD y a la vuelta rebalanceo
					if(Math.abs(p.getLeft().getAltura() - p.getRight().getAltura())>1) {//si la diferencia entre las alturas de los hijos >1
						
						E y = p.getRight().getRotulo();
						int comp_item_y = comp.compare(item,y);
						if( comp_item_y < 0 ) {//si item < rotulo del hijo i der de p
							rotacionIV(p); // rotacion der-izq (iv)
						}
						else {//si item >= rotulo del hijo der de p
							rotacionIII(p); //rotacion der-der (iii)
						}
					}
				}
				//System.out.println("altura de p antes: "+p.getAltura());
				p.setAltura(max(p.getLeft().getAltura(),p.getRight().getAltura())+1); // Setear altura del nodo p con el max de la de sus hijos + 1
				//System.out.println("altura de p despues: "+p.getAltura());
			//}
		}
	}
	
	private void rotacionI(NodoAVL<E> x) {//Rotacin izq-izq
		System.out.println("RotacionI");
		NodoAVL<E> y=x.getLeft();
		NodoAVL<E> z=y.getLeft();
		NodoAVL<E> padreX=x.getParent();
		NodoAVL<E> derY=y.getRight();
		if (x.getRotulo()==raiz.getRotulo()) {//caso especial: x es raiz
			raiz=y;
		}
		else {
			if (padreX.getLeft().getRotulo()==x.getRotulo()) {//si x es el hijo izq de su padre
				padreX.setLeft(y);
			}
			else {//si x es el hijo der de su padre
				padreX.setRight(y);
			}	
		}
		//operaciones comunes a los 3 casos
		System.out.println("altura de x: "+x.getAltura()+" altura de y: "+y.getAltura()+" altura de z: "+z.getAltura());
		System.out.println("z: "+z.getRotulo());
		System.out.println(derY);
		y.setParent(padreX);
		y.setRight(x);
		x.setParent(y);
		x.setLeft(derY);
		derY.setParent(x);
		x.setAltura(x.getAltura()-2);//x pasa a tener altura 1, ya que queda como nodo hoja	
		/*derY.setAltura(0);
		x.getRight().setAltura(0);*/
	}
	
	private void rotacionII(NodoAVL<E> x) {//Rotacion izq-der
		System.out.println("RotacionII");
		NodoAVL<E> y=x.getLeft();
		NodoAVL<E> z=y.getRight();
		NodoAVL<E> padreX=x.getParent();
		NodoAVL<E> derZ=z.getRight();
		NodoAVL<E> izqZ=z.getLeft();
		if (x.getRotulo()==raiz.getRotulo()) {//caso especial: x es raiz
			raiz=z;
		}
		else {
			if (padreX.getLeft().getRotulo()==x.getRotulo()) {//si x es el hijo izq de su padre
				padreX.setLeft(z);
			}
			else {//si x es el hijo der de su padre
				padreX.setRight(z);
			}
		}
		//operaciones comunes a los 3 casos
		z.setParent(padreX);
		z.setLeft(y);
		y.setParent(z);
		z.setRight(x);
		x.setParent(z);
		System.out.println("altura de x: "+x.getAltura()+" altura de y: "+y.getAltura()+" altura de z: "+z.getAltura());
		System.out.println("z: "+z.getRotulo());
		System.out.println(izqZ);
		y.setRight(izqZ);
		izqZ.setParent(y);
		x.setLeft(derZ);
		derZ.setParent(x);
		/*derZ.setAltura(0);
		x.getRight().setAltura(0);*/
		x.setAltura(x.getAltura()-2);//x pasa a tener altura 1, ya que queda como nodo hoja	
		y.setAltura(y.getAltura()-1);//y pasa a tener altura 1, ya que queda como nodo hoja	
		z.setAltura(z.getAltura()+1);//z pasa a tener altura 2, ya que es padre de x e y
		
	}
	
	private void rotacionIII(NodoAVL<E> x) {//Rotacion der-der
		System.out.println("RotacionIII");
		NodoAVL<E> y=x.getRight();
		NodoAVL<E> z=y.getRight();
		NodoAVL<E> padreX=x.getParent();
		NodoAVL<E> izqY=y.getLeft();
		if (x.getRotulo()==raiz.getRotulo()) {//caso especial: x es raiz
			raiz=y;
		}
		else {
			if (padreX.getLeft().getRotulo()==x.getRotulo()) {//si x es el hijo izq de su padre
				padreX.setLeft(y);
			}
			else {//si x es el hijo der de su padre
				padreX.setRight(y);
			}
		}
		System.out.println("altura de x: "+x.getAltura()+" altura de y: "+y.getAltura()+" altura de z: "+z.getAltura());
		System.out.println("z: "+z.getRotulo());
		System.out.println(izqY);
		//operaciones comunes a los 3 casos
		y.setParent(padreX);
		y.setLeft(x);
		x.setParent(y);
		x.setRight(izqY);
		izqY.setParent(x);
		/*x.getLeft().setAltura(0);
		izqY.setAltura(0);*/
		x.setAltura(z.getAltura()-2);//x pasa a tener altura 1, ya que queda como nodo hoja	
	}
	
	private void rotacionIV(NodoAVL<E> x) {//Rotacion der-izq
		System.out.println("RotacionIV");
		NodoAVL<E> y=x.getRight();
		NodoAVL<E> z=y.getLeft();
		NodoAVL<E> padreX=x.getParent();
		NodoAVL<E> derZ=z.getRight();
		NodoAVL<E> izqZ=z.getLeft();
		if (x.getRotulo()==raiz.getRotulo()) {//caso especial: x es raiz
			raiz=z;
		}
		else {
			if (padreX.getLeft().getRotulo()==x.getRotulo()) {//si x es el hijo izq de su padre
				padreX.setLeft(z);
			
			}
			else {//si x es el hijo der de su padre
				padreX.setRight(z);
			}
			
		}
		//operaciones comunes a los 3 casos
		z.setParent(padreX);
		z.setLeft(x);
		x.setParent(z);
		z.setRight(y);
		y.setParent(z);
		System.out.println("altura de x: "+x.getAltura()+" altura de y: "+y.getAltura()+" altura de z: "+z.getAltura());
		System.out.println("z: "+z.getRotulo());
		System.out.println(izqZ);
		x.setRight(izqZ);
		izqZ.setParent(x);
		y.setLeft(derZ);
		derZ.setParent(y);
		x.setAltura(x.getAltura()-2);//x pasa a tener altura 1, ya que queda como nodo hoja	
		y.setAltura(y.getAltura()-1);//y pasa a tener altura 1, ya que queda como nodo hoja	
		z.setAltura(z.getAltura()+1);//z pasa a tener altura 2, ya que es padre de x e y
		
	}
	
	private int max(int i, int j ) {// Este método sirve para calcular el máximo entre i y j
		return i>j ? i : j;
	}
	
	public void mostrar() {
		if (raiz!=null) {
			System.out.println(raiz.getRotulo());
			if (raiz.getLeft()!=null) {
				System.out.print(raiz.getLeft().getRotulo()+" ");
			}
			if (raiz.getRight()!=null) {
				System.out.print(raiz.getRight().getRotulo()+" ");
			}
			System.out.println();
			if (raiz.getLeft().getLeft()!=null) {
				System.out.print(raiz.getLeft().getLeft().getRotulo()+" ");
			}
			if (raiz.getLeft().getRight()!=null) {
				System.out.print(raiz.getLeft().getRight().getRotulo()+" ");
			}
			if (raiz.getRight().getLeft()!=null) {
				System.out.print(raiz.getRight().getLeft().getRotulo()+" ");
			}
			if (raiz.getRight().getRight()!=null) {
				System.out.print(raiz.getRight().getRight().getRotulo()+" ");
			}
			System.out.println();
			if (raiz.getLeft().getLeft().getLeft()!=null) {
				System.out.print(raiz.getLeft().getLeft().getLeft().getRotulo()+" ");
			}
			if (raiz.getLeft().getLeft().getRight()!=null) {
				System.out.print(raiz.getLeft().getLeft().getRight().getRotulo()+" ");
			}
			if (raiz.getLeft().getRight().getLeft()!=null) {
				System.out.print(raiz.getLeft().getRight().getLeft().getRotulo()+" ");
			}
			if (raiz.getLeft().getRight().getRight()!=null) {
				System.out.print(raiz.getLeft().getRight().getRight().getRotulo()+" ");
			}
			if (raiz.getRight().getLeft().getLeft()!=null) {
				System.out.print(raiz.getRight().getLeft().getLeft().getRotulo()+" ");
			}
			if (raiz.getRight().getLeft().getRight()!=null) {
				System.out.print(raiz.getRight().getLeft().getRight().getRotulo()+" ");
			}
			if (raiz.getRight().getRight().getLeft()!=null) {
				System.out.print(raiz.getRight().getRight().getLeft().getRotulo()+" ");
			}
			if (raiz.getRight().getRight().getRight()!=null) {
				System.out.print(raiz.getRight().getRight().getRight().getRotulo()+" ");
			}
		}
	}
	
	
	
	/////////////////////////////////////////////////////EJERCICIO DE FINAL//////////////////////////////////////////////////////////
	public PositionList<TDAArbolBinario.Position<E>> listaNodosNoCumplenCondicion(BinaryTree<E> t) {
		PositionList<TDAArbolBinario.Position<E>> lista=new ListaDoblementeEnlazada<TDAArbolBinario.Position<E>>();
		try {
			if (!t.isEmpty()) {
				listaAux(t,t.root(),lista);
			}
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public PositionList<NodoABB<E>> listaNodosNoCumplenCondicion(ArbolBinarioBusqueda<E> t) {
		PositionList<NodoABB<E>> lista=new ListaDoblementeEnlazada<NodoABB<E>>();
		if (t.getRaiz()!=null) {
			listaAux(t,t.getRaiz(),lista);
		}
		return lista;
	}
	
	private void listaAux(BinaryTree<E> t, TDAArbolBinario.Position<E> n, PositionList<TDAArbolBinario.Position<E>> l) {
		int hi=0,hd=0;
		try {
			System.out.println("n: "+n.element());
			//si no tiene hi o hd la altura de estos es 0
			if (t.hasLeft(n)) {
				hi=altura(t,t.left(n));
				System.out.println("hi: "+t.left(n).element()+" altura: "+hi);
				listaAux(t,t.left(n),l);
			}
			if (t.hasRight(n)) {
				hd=altura(t,t.right(n));
				System.out.println("hd: "+t.right(n).element()+" altura: "+hd);
				listaAux(t,t.right(n),l);
			}
			if (Math.abs(hi - hd)>1) {
				System.out.println(n.element()+"no cumple");
				insertarOrdenado(l,n);
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(TDAArbolBinario.BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	private void listaAux(ArbolBinarioBusqueda<E> t, NodoABB<E> n, PositionList<NodoABB<E>> l) {
		int hi=0,hd=0;
		//si no tiene hi o hd la altura de estos es 0
		if (n.getLeft()!=null && n.getLeft().getRotulo()!=null) {
			hi=altura(t,n.getLeft());
			listaAux(t,n.getLeft(),l);
		}
		if (n.getRight()!=null && n.getRight().getRotulo()!=null) {
			hd=altura(t,n.getRight());
		}
		if (Math.abs(hi - hd)>1) {
			l.addLast(n);//para que inserte ordenado
		}
		if (n.getRight()!=null) {
			listaAux(t,n.getRight(),l);
		}
	}
	
	private void insertarOrdenado(PositionList< TDAArbolBinario.Position<E>> l,  TDAArbolBinario.Position<E> n) {
		DefaultComparator<E> comp=new DefaultComparator();
		int comparacion;
		boolean inserto=false;
		try {
			if (l.isEmpty()) {
				l.addLast(n);
			}
			else {
				for(TDALista.Position<Position<E>> p:l.positions()) {
					comparacion=comp.compare(n.element(), p.element().element());
					if (comparacion<0) {//n < p
						l.addBefore(p,n);
						inserto=true;
						break;
					}
				}
				if (!inserto) {//si no inserto, es pq n es mayor que todos los elementos de l, entonces insertamos a n al final de l
					l.addLast(n);
				}
			}
		}
		catch(TDALista.InvalidPositionException e) {
			e.printStackTrace();
		}

	}
	private int altura(BinaryTree<E> t,  TDAArbolBinario.Position<E> p) {//Longitud del camino más largo a una hoja en el subarbol con raiz p
		int h=-1;
		try {
			if (t!=null && !t.isEmpty() && existePosicion(t,p)) {
				if (t.isExternal(p)) {
					return 1;
				}
				else {
					h=0;
					for (Position<E> v:t.children(p)) {
						h=Math.max(h,altura(t,v));
					}
		
				}
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		return 1+h;
	}
	
	private int altura(ArbolBinarioBusqueda<E> t,  NodoABB<E> p) {//Longitud del camino más largo a una hoja en el subarbol con raiz p
		int h=-1;
		if (t!=null && t.getRaiz()!=null && p!=null) {
			if (p.getLeft()==null && p.getRight()==null) {
				return 0;
			}
			else {
				h=0;
				h=Math.max(h,altura(t,p.getLeft()));
				h=Math.max(h,altura(t,p.getRight()));
			}
		}
		return 1+h;
	}
	
	private boolean existePosicion(BinaryTree<E> t, TDAArbolBinario.Position<E> p1) {
		boolean existe=false;
		for(Position<E> p : t.positions()) {
			if (p==p1) {
				existe=true;
				break;//para que corte el for each y sea más eficiente
			}
		}
		return existe;
	}
}
