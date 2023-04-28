package TDALista;

import java.util.*;
import java.lang.*;
/////////////////CAMBIAR JAVADOC/////////////////////////////////////////







/**
 * Define los datos y operaciones aplicables sobre un elemento iterador. Permite abstraer el recorrido de una lista.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en el elemento iterador.
 */
public class ElementIterador<E> implements Iterator<E> {
	/**
	 * Lista a iterar.
	 */
	protected PositionList<E> list;
	/**
	 * Posición del elemento corriente.
	 */
	protected Position<E> cursor;
	
	/**
	 * Inicializa un elemento iterador con la lista del parámetro como list y, si la lista está vacía con cursor nulo, caso contrario con la primer posición de l como cursor.
	 * @param l Lista a establecer como list del elemento iterador.
	 */
	public ElementIterador (PositionList<E> l) {
		try {
			list=l;
			if (list.isEmpty()) {
				cursor=null;			
			}
			else {
				cursor=list.first();
			}
		}
		catch (EmptyListException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Consulta si el elemento iterador tiene siguiente.
	 * @return Verdadero si el elemento iterador tiene siguiente, falso en caso contrario.
	 */
	public boolean hasNext() {
		return cursor!=null;
	}

	/**
	 * Devuelve el elemento siguiente del elemento iterador y avanza el cursor. 
	 * @return Elemento siguiente del elemento iterador.
	 * @throws NoSuchElementException si la lista no tiene siguiente.
	 */
	public E next () throws NoSuchElementException {
		try {
			if (cursor==null) {
				throw new NoSuchElementException("Error, no hay siguiente");
			}
			E toReturn=cursor.element();
			cursor=(cursor==list.last()) ? null:list.next(cursor);//el curso avanza a la siguiente posición
			return toReturn;
		}
		catch (EmptyListException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
		catch (InvalidPositionException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
		catch (BoundaryViolationException e) {
			throw new  NoSuchElementException("Pila Vacia");
		}
	}
}
