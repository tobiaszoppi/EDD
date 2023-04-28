/*package TDAArbolBinario;

import java.util.Iterator;

import TDALista.PositionList;
import TDALista.ListaDoblementeEnlazada;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
	protected BTPosition<E> raiz;
	protected int size;
	
	public ArbolBinarioEnlazado(){
		size = 0;
		raiz = null;
	}
	
	public ArbolBinarioEnlazado(E rot){
		size = 1;
		raiz = new BTNodo<E>(rot, null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Iterator<E> iterator() {
		Iterable<Position<E>> positions=positions();
		PositionList<E> l=new ListaDoblementeEnlazada<E>();
		for (Position<E> p:positions) {
			l.addLast(p.element());
		}
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l=new ListaDoblementeEnlazada<Position<E>>();
		if (size!=0) {
			pre(raiz, l);
		}
		return l;
	}

	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		BTNodo<E> p=checkPosition(v);
		E aux=p.element();
		p.setElement(e);
		return aux;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("Árbol vacío");
		}
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> p=checkPosition(v);
		if (p==raiz) {
			throw new BoundaryViolationException("p corresponde a la raiz del árbol");
		}
		return p.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		PositionList<Position<E>> hijos=new ListaDoblementeEnlazada<Position<E>>();
		try {
			if (hasLeft(v)) {
				hijos.addLast(left(v));
			}
			if (hasRight(v)) {
				hijos.addLast(right(v));
			}
		}
		catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		return hijos;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		return hasLeft(v) || hasRight(v);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		return !hasLeft(v) && !hasRight(v);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		BTNodo <E> nodo=checkPosition(v);
		return nodo.getParent()==null;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> n=checkPosition(v);
		if (n.getLeft()==null) {
			throw new BoundaryViolationException("La posición v no tiene hijo izquierdo");
		}
		return n.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTPosition<E> n=checkPosition(v);
		if (n.getRight()==null) {
			throw new BoundaryViolationException("La posición v no tiene hijo derecho");
		}
		return n.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		return n.getLeft()!=null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		return n.getRight()!=null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if (raiz!=null) {
			throw new InvalidOperationException("El árbol ya tiene raiz.");
		}
		BTPosition<E> padre=new BTNodo<E>(r,null);
		raiz=padre;
		size++;
		return padre;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		BTPosition<E> izq;
/*		if (isEmpty()) {
			throw new InvalidPositionException("El árbol está vacío.") ;
		}
		if (n.getLeft()!=null) {
			throw new InvalidOperationException("La posición v ya tiene un hijo izquierdo.");
		}
		izq=new BTNodo<E>(r,n);
		n.setLeft(izq);//le asigno a n el hijo izq
		size++;
		return izq;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		BTPosition<E> der;
	/*	if (size==0) {
			throw new InvalidPositionException("El árbol está vacío.") ;
		}
		if (n.getRight()!=null) {
			throw new InvalidOperationException("La posición v ya tiene un hijo derecho.");
		}
		der=new BTNodo<E>(r,n);
		n.setLeft(der);//le asigno a n el hijo izq
		size++;
		return der;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		BTPosition<E> n=checkPosition(v);
		BTPosition<E> hijo=null;
		BTPosition<E> padre=null;
		E rotulo=v.element();
		/*if (size==0) {
			throw new InvalidPositionException("El árbol está vacío.");
		}
		if (n.getLeft()!=null && n.getRight()!=null) {
			throw new InvalidPositionException("La posición no puede ser eliminada porque tiene 2 hijos.");
		}
		if (n.getLeft()!=null) {
			hijo=n.getLeft();
		}
		else {
			if (n.getRight()!=null) {
				hijo=n.getRight();
			}
		}
		if (n==raiz) {
			if (hijo!=null) {
				hijo.setParent(null);
			}
			raiz=hijo;
		}
		else {
			padre=n.getParent();
			if (n==padre.getLeft()) {
				padre.setLeft(hijo);
			}
			else {
				padre.setRight(hijo);
			}
		}
		size--;
		return rotulo;
		
	}

	@Override
	public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTPosition<E> raiz_local = checkPosition(p), hi_raiz_local, hd_raiz_local;
		Position<E> raiz_t1, raiz_t2;
		if (raiz_local.getLeft() != null || raiz_local.getRight() != null)
		throw new InvalidPositionException("La posicion no corresponde a un nodo hoja");
		try {
		//Clonación de T1 como subárbol izquierdo
		if (!t1.isEmpty()) {
		raiz_t1 = t1.root();
		hi_raiz_local = new BTNodo<E>(raiz_t1.element(), raiz_local);
		raiz_local.setLeft(hi_raiz_local);
		clonar(hi_raiz_local, raiz_t1, t1);
		}
		//Clonación de T2 como subárbol derecho
		if (!t2.isEmpty()) {
		raiz_t2 = t2.root();
		hd_raiz_local = new BTNodo<E>(raiz_t2.element(), raiz_local);
		raiz_local.setRight(hd_raiz_local);
		clonar(hd_raiz_local, raiz_t2, t2);
		}
		size += t1.size() + t2.size();
		}catch(EmptyTreeException e) { raiz_local.setLeft(null); raiz_local.setRight(null); }
		}
	
	protected void clonar(BTPosition<E> padre_local, Position<E> padre_t, BinaryTree<E> t) {
		BTPosition<E> hi_padre_local, hd_padre_local;
		Position<E> hi_padre_t, hd_padre_t;
		try {
		//Si existe hijo izquierdo en T de padre_t, se clona este y el subárbol a partir del hijo izquierdo de padre_t.
		if (t.hasLeft(padre_t)) {
		hi_padre_t = t.left(padre_t);
		hi_padre_local = new BTNodo<E>(hi_padre_t.element(), padre_local);
		padre_local.setLeft(hi_padre_local);
		clonar(hi_padre_local, hi_padre_t, t);
		}
		//Si existe hijo derecho en T de padre_t, se clona este y el subárbol a partir del hijo derecho de padre_t.
		if (t.hasRight(padre_t)) {
		hd_padre_t = t.right(padre_t);
		hd_padre_local = new BTNodo<E>(hd_padre_t.element(), padre_local);
		padre_local.setRight(hd_padre_local);
		clonar(hd_padre_local, hd_padre_t, t);
		}
		}catch(InvalidPositionException | BoundaryViolationException e) {
		padre_local.setLeft(null); padre_local.setRight(null);
		}
		}
	
	private void pre(BTPosition<E> v, PositionList<Position<E>> l) {
		try {
			l.addLast(v);
			if (hasLeft(v)) {
				pre(v.getLeft(), l);
			}
			if (hasRight(v)) {
				pre(v.getRight(), l);
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		

	}
	
	/**
	 * Devuelve un nodo de árbol creado a partir de la posición n.
	 * @param n Posición a partir de la cual se crea el nodo de árbol.
	 * @return Nodo de árbol creado a partir de la posición n.
	 * @throws InvalidPositionException si la posición es nula, si fue eliminada anteriormente o si no es de este árbol.
	 
	private BTNodo<E> checkPosition(Position<E> n) throws InvalidPositionException {
		try {
			if (n==null) {
				throw new InvalidPositionException("Error, posición nula.");
			}
			if (n.element()==null) {
				throw new InvalidPositionException("El elemento fue eliminado previamente.");
			}
			if (size==0) {
				throw new InvalidPositionException("El árbol está vacío.");
			}
			return (BTNodo<E>) n;
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("n no es un nodo de este árbol.");
		}
	}
	
}*/

package TDAArbolBinario;

import java.util.Iterator;
import TDALista.*;

public class ArbolBinarioEnlazado<E> implements BinaryTree<E> {
	
	protected BTNodo<E> raiz;
	protected int tamaño;
	
	public ArbolBinarioEnlazado(){
		raiz=null;
		tamaño=0;
	}
	
	@Override
	public int size() {
		return tamaño;
	}

	@Override
	public boolean isEmpty() {
		return (tamaño==0);
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> lista = new ListaDoblementeEnlazada<E>();
		for(Position<E> posicion : positions())
			lista.addLast(posicion.element());
		return lista.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> lista = new ListaDoblementeEnlazada<Position<E>>();
		if(!isEmpty())
			preOrden(lista,raiz);
		return lista;
	}
	
	private void preOrden(PositionList<Position<E>> L,BTPosition<E> n){
		L.addLast(n);
		if(n.getLeft()!=null)
			preOrden(L,n.getLeft());
		if(n.getRight()!=null)
			preOrden(L,n.getRight());
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkPosition(v);
		E aux = n.element();
		n.setElement(e);
		return aux;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if(tamaño == 0){
			throw new EmptyTreeException("Arbol vacio");
		}
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		if(isRoot(v))
			throw new BoundaryViolationException("v es la raiz y no tiene padre");
		BTNodo<E> n = checkPosition(v);
		return n.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		PositionList<Position<E>> L = new ListaDoblementeEnlazada<Position<E>>();
		try{	
			if(hasLeft(v))
				L.addLast(left(v));
			if(hasRight(v))
				L.addLast(right(v));
		}
		catch(BoundaryViolationException e){
			e.printStackTrace();
		}
		return L;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkPosition(v);
		return n.getRight()!=null||n.getLeft()!=null;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkPosition(v);
		return n.getRight()==null&&n.getLeft()==null;
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		if(isEmpty())
			throw new InvalidPositionException("Arbol vacio");
		BTNodo<E> n = checkPosition(v);
		return n==raiz;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n=checkPosition(v);
		if (n.getLeft()==null) 
			throw new BoundaryViolationException("no tiene hijo izquierdo");
		return n.getLeft();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		BTNodo<E> n=checkPosition(v);
		if (n.getRight()==null) 
			throw new BoundaryViolationException("no tiene hijo derecho");
		return n.getRight();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n=checkPosition(v);
		return (n.getLeft()!=null);
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNodo<E> n=checkPosition(v);
		return (n.getRight()!=null);
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if (raiz!=null) 
			throw new InvalidOperationException("el arbol ya tiene raiz");
		BTNodo<E> n=new BTNodo<E>(r);
		raiz=n;
		tamaño++;
		return raiz;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("posicion invalida");
		BTNodo<E> n=checkPosition(v);
		if (hasLeft(v)) 
			throw new InvalidOperationException("ya tiene hijo izquierdo");
		BTNodo<E> nuevo=new BTNodo<E>(r,n);
		n.setLeft(nuevo);
		tamaño++;
		return nuevo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("posicion invalida");
		BTNodo<E> n=checkPosition(v);
		if (hasRight(v)) 
			throw new InvalidOperationException("ya tiene hijo derecho");
		BTNodo<E> nuevo=new BTNodo<E>(r,n);
		n.setRight(nuevo);
		tamaño++;
		return nuevo;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("posicion invalida");
		BTNodo<E> n=checkPosition(v);
		if (hasLeft(v) && hasRight(v)) 
			throw new InvalidOperationException("no es posible realizar esta operacion, ya que tiene 2 hijos");
		BTPosition<E> padre= n.getParent();
		E retorno=n.element();
		if (n==raiz) {
			if (hasLeft(v)) 
				raiz=(BTNodo<E>) n.getLeft();
            else 
            	if(hasRight(v)) 
            		raiz=(BTNodo<E>) n.getRight();
				 else 
					raiz=null;
		}
		else {
			if (hasLeft(v)) {
				if (padre.getLeft()==n) 
					padre.setLeft(n.getLeft());
				else 
					padre.setRight(n.getLeft());
			}
			else 
				if (hasRight(v)){
					if (padre.getLeft()==n) 
						padre.setLeft(n.getRight());
					else 
						padre.setRight(n.getRight());
				}
				else{
					if (padre.getLeft()==n) 
						padre.setLeft(null);
					else 
						padre.setRight(null);
				}
			}	  
		tamaño--;
		return retorno;
	}

	@Override
	public void attach(Position<E> p, BinaryTree<E> t1, BinaryTree<E> t2) throws InvalidPositionException {
		BTPosition<E> raiz_local = (BTPosition<E>) p, hi_raiz_local, hd_raiz_local;
		Position<E> raiz_t1, raiz_t2;
		if (p==null || raiz_local.getLeft() != null || raiz_local.getRight() != null) {
			throw new InvalidPositionException("La posicion no corresponde a un nodo hoja");
		}
		try {
			//Clonación de T1 como subárbol izquierdo
			if (!t1.isEmpty()) {
				raiz_t1 = t1.root();
				hi_raiz_local = new BTNodo<E>(raiz_t1.element(), (BTNodo<E>) raiz_local);
				raiz_local.setLeft(hi_raiz_local);
				clonar(hi_raiz_local, raiz_t1, t1);
			}
			//Clonación de T2 como subárbol derecho
			if (!t2.isEmpty()) {
				raiz_t2 = t2.root();
				hd_raiz_local = new BTNodo<E>(raiz_t2.element(), (BTNodo<E>) raiz_local);
				raiz_local.setRight(hd_raiz_local);
				clonar(hd_raiz_local, raiz_t2, t2);
			}
			tamaño+= t1.size() + t2.size();
		}
		catch(EmptyTreeException e) { 
			raiz_local.setLeft(null); raiz_local.setRight(null); 
		}
	}
	
	protected void clonar(BTPosition<E> padre_local, Position<E> padre_t, BinaryTree<E> t) {
		BTPosition<E> hi_padre_local, hd_padre_local;
		Position<E> hi_padre_t, hd_padre_t;
		try {
			//Si existe hijo izquierdo en T de padre_t, se clona este y el subárbol a partir del hijo izquierdo de padre_t.
			if (t.hasLeft(padre_t)) {
				hi_padre_t = t.left(padre_t);
				hi_padre_local = new BTNodo<E>(hi_padre_t.element(), (BTNodo<E>) padre_local);
				padre_local.setLeft(hi_padre_local);
				clonar(hi_padre_local, hi_padre_t, t);
			}
			//Si existe hijo derecho en T de padre_t, se clona este y el subárbol a partir del hijo derecho de padre_t.
			if (t.hasRight(padre_t)) {
				hd_padre_t = t.right(padre_t);
				hd_padre_local = new BTNodo<E>(hd_padre_t.element(), (BTNodo<E>) padre_local);
				padre_local.setRight(hd_padre_local);
				clonar(hd_padre_local, hd_padre_t, t);
			}
		}
		catch(InvalidPositionException | BoundaryViolationException e) {
			padre_local.setLeft(null); padre_local.setRight(null);
		}
	}
	
	private BTNodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
		try{
			if(p==null)
				throw new InvalidPositionException("Posicion nul");
			else
				return (BTNodo<E>) p;
		}
		catch(ClassCastException e){
			throw new InvalidPositionException("Error de casting");
		}
	}
	
	///////////////////////////////////////////////////OPERACIONES////////////////////////////////////////////////////////////////
	
	public void preorden() {
		if (tamaño!=0 && raiz!=null) {
			preordenAux(raiz);
		}
	}
	
	private void preordenAux(Position<E> p) {
		try {
			System.out.print(p.element()+" - ");
			if (hasLeft(p)) {
				preordenAux(left(p));
			}
			if (hasRight(p)) {
				preordenAux(right(p));
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	public void espejar() {
		try {
			if (tamaño!=0) {
				ArbolBinarioEnlazado<E> arbol=new ArbolBinarioEnlazado<E>();//arbol en el ingreso el esepjado
				arbol.createRoot(raiz.element());
				BTNodo<E> nuevaRaiz=(BTNodo<E>) arbol.root();//raiz del nuevo arbol
				espejarAux(arbol,nuevaRaiz,raiz);
				raiz=nuevaRaiz;//paso la raiz del arbol espejado a la raiz del arbol original
			}
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
	}

	private void espejarAux(BinaryTree<E> t, BTNodo<E> pt, BTNodo<E> p) {
		try {
			if (p.getLeft()!=null) {
				BTNodo<E> ppp=(BTNodo<E>) t.addRight(pt, p.getLeft().element());
				espejarAux(t,(BTNodo<E>) ppp, (BTNodo<E>) p.getLeft());
			}
			if (p.getRight()!=null) {
				BTNodo<E> ppp=(BTNodo<E>) t.addLeft(pt, p.getRight().element());
				espejarAux(t,(BTNodo<E>) ppp, (BTNodo<E>) p.getRight());
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
	}
	
	
	public void padreDeHojas() {
		if (tamaño!=0) {
			padreDeHojasAux(raiz);
		}
	}
	
	private void padreDeHojasAux(BTPosition<E> p ) {
		boolean esPadreDeHoja=false;
		try {
			for(Position<E> hijo:children(p)) {
				if (!esPadreDeHoja && isExternal(hijo)) {//si el primer hijo ya es hoja, no vuelve a mostrar el nodo de nuevo
					System.out.println(p.element()+" en nivel: "+profundidad(p));
					esPadreDeHoja=true;
				}
			}
			for(Position<E> hijo:children(p)) {
				if (isInternal(hijo)) {
					padreDeHojasAux((BTPosition<E>) hijo);
				}
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	private int profundidad(BTPosition<E> p) {//Longitud del camino de la raiz de t a la posicion p
		int cont=0;
		if (tamaño!=0) {
			if (raiz==p) {
				cont=0;
			}
			else {
				cont=1+profundidad(p.getParent());
			}
		}
		return cont;
	}
}
