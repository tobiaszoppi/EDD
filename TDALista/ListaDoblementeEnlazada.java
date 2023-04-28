package TDALista;

import java.util.Iterator;

/**
 * Define los datos y operaciones aplicables sobre una lista doblemente enlazada.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en la lista doblemente enlazada.
 */

public class ListaDoblementeEnlazada<E> implements PositionList<E> {
	
	/**
	 * Tamaño de la lista.
	 */
	protected int tamaño;
	/**
	 * Celda de encabezamiento nula, que indica que la posición siguiente es la primer nodo de la lista.
	 */
	protected DNodo<E> cabeza;
	/**
	 * Celda de encabezamiento nula, que indica que la posición previa es el último nodo de la lista.
	 */
	protected DNodo<E> rabo;
	
	/**
	 * Inicializa una lista doblemente enlazada vacía.
	 */
	public ListaDoblementeEnlazada() {
		cabeza=new DNodo<E>(null,null,null);
		rabo= new DNodo<E>(null,null,null);
		cabeza.setAnterior(null);
		cabeza.setSiguiente(rabo);
		rabo.setAnterior(cabeza);
		rabo.setSiguiente(null);
		tamaño=0;
	}
	
	@Override
	public int size() {
		return tamaño;
	}
	
	@Override
	public boolean isEmpty() {
		return tamaño==0;
	}
	
	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Error, la lista está vacía.");
		}
		else {
			return cabeza.getSiguiente();
		}
	}
	
	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty()) {
			throw new EmptyListException("Error, la lista está vacía.");
		}
		else {
			return rabo.getAnterior();
		}
	}
	
	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n=checkPosition(p);
		if (n.getSiguiente()==rabo) {
			throw new BoundaryViolationException("Error, siguiente de último.");
		}
		else {
			return n.getSiguiente();
		}
	}
	
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> n=checkPosition(p);
		if (n.getAnterior()==cabeza) {
			throw new BoundaryViolationException("Error, anterior de primero.");
		}
		else {
			return n.getAnterior();
		}
	}
	
	@Override
	public void addFirst(E element) {
		DNodo<E> n=new DNodo<E>(element,null,null);
		DNodo<E> aux= cabeza.getSiguiente();
		cabeza.setSiguiente(n);
		n.setAnterior(cabeza);
		n.setSiguiente(aux);
		aux.setAnterior(n);
		tamaño++;
	}
	
	@Override
	public void addLast(E element) {
		/*if (isEmpty()) {
			addFirst(element);
		}
		else {*/
			DNodo<E> n=new DNodo<E>(element,null,null);
			DNodo<E> aux=rabo.getAnterior();
			rabo.setAnterior(n);
			n.setSiguiente(rabo);
			n.setAnterior(aux);
			aux.setSiguiente(n);
			tamaño++;
		//}
	}
	
	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos=checkPosition(p);
		DNodo<E> nuevo=new DNodo<E>(element,null,null);
		DNodo<E> aux=pos.getSiguiente();
		pos.setSiguiente(nuevo);
		nuevo.setAnterior(pos);
		nuevo.setSiguiente(aux);
		aux.setAnterior(nuevo);
		tamaño++;
	}
	
	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos=checkPosition(p);
		DNodo<E> nuevo=new DNodo<E>(element,null,null);
		DNodo<E> aux=pos.getAnterior();
		pos.setAnterior(nuevo);
		nuevo.setSiguiente(pos);
		nuevo.setAnterior(aux);
		aux.setSiguiente(nuevo);
		tamaño++;
	}
	
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		DNodo<E> pos=checkPosition(p);
		/*DNodo<E> aux=cabeza.getSiguiente();
		while (aux!=pos ) {
			aux=aux.getSiguiente();
		}*/
		E elem=pos.element();
		pos.getAnterior().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setAnterior(pos.getAnterior());
		pos.setAnterior(null);
		pos.setSiguiente(null);
		pos.setElemento(null);//setea el elemento a null para dejar en evidencia que esa posición ya fue eliminada
		tamaño--;
		return elem;
	}
	
	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos=checkPosition(p);
		E aux=pos.element();
		pos.setElemento(element);
		return aux;
		
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterador<E>(this);
	}
	
	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDoblementeEnlazada<Position<E>>();
		Position<E> p = null;//PREGUNTAR SI USAN EL NEXT DE ABAJO/////////////////7
		try{
			if(tamaño!=0) {
				p = cabeza.getSiguiente();
			}
			else {
				return l;
			}
			while(p != rabo.getAnterior()) {
				l.addLast(p);
				p = next(p);
			}
			l.addLast(p);
		}
		catch(BoundaryViolationException e) {
			System.out.println("Violación de límites");
		}
		catch(InvalidPositionException e) {
			System.out.println("Posición Inválida");
		}
		/*catch(EmptyListException e){
			System.out.println("Lista vacía");
		}*/
		return l;
	}
	
	/**
	 * Devuelve un nodo doblemente enlazado creado a partir de la posición del parámetro.
	 * @param n Posición a partir de la cual se crea el nodo doblemente enlazado.
	 * @return Nodo doblemente enlazado creado a partir de la posición del parámetro.
	 * @throws InvalidPositionException si la posición es nula, si fue eliminada anteriormente o si no es de esta lista.
	 */
	private DNodo<E> checkPosition(Position<E> n) throws InvalidPositionException {
		try {
			if (n==null) {
				throw new InvalidPositionException("Error, posición nula.");
			}
			if (n.element()==null) {
				throw new InvalidPositionException("El elemento fue eliminado previamente.");
			}
			return (DNodo<E>) n;
		}
		catch (ClassCastException e) {
			throw new InvalidPositionException("n no es un nodo de esta lista.");
		}
	}
}