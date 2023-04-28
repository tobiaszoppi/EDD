package TDALista;

/**
 * Define los datos y operaciones aplicables sobre un nodo doblemente enlazado.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos a almacenar en el nodo doblemente enlazado.
 */
public class DNodo<E> implements Position<E> {
	/**
	 * Elemento del nodo.
	 */
	protected E elemento;
	/**
	 * Nodo siguiente al nodo actual.
	 */
	protected DNodo<E> siguiente;
	/**
	 * Nodo anterior al nodo actual.
	 */
	protected DNodo<E> anterior;
	
	/**
	 * Inicializa un nodo doblemente enlazado con rótulo, refencia al nodo siguiente y refencia al nodo anterior.
	 * @param item Elemento a establecer como elemento del nodo doblemente enlazado.
	 * @param sig Nodo a establecer como siguiente del nodo doblemente enlazado.
	 * @param ant Nodo a establecer como anterior del nodo doblemente enlazado.
	 */
	public DNodo(E item, DNodo<E> sig, DNodo<E> ant){
		elemento = item;
		siguiente = sig;
		anterior=ant;
	}
	
	@Override
	public E element(){
		return elemento;
	}
	
	/**
	 * Establece el elemento del parámetro en el elemento del nodo doblemente enlazado.
	 * @param elemento Elemento a establecer en el elemento del nodo doblemente enlazado.
	 */
	public void setElemento(E elemento){
		this.elemento = elemento;
	}
	
	/**
	 * Establece el nodo del parámetro como el siguiente del nodo doblemente enlazado.
	 * @param siguiente Nodo a establecer como el siguiente del nodo doblemente enlazado.
	 */
	public void setSiguiente(DNodo<E> siguiente){
		this.siguiente = siguiente;
	}
	
	/**
	 * Establece el nodo del parámetro como el anterior del nodo doblemente enlazado.
	 * @param anterior Nodo a establecer como el anterior del nodo doblemente enlazado.
	 */
	public void setAnterior(DNodo<E> anterior){
		this.anterior = anterior;
	}


	/**
	 * Devuelve el nodo siguiente al nodo doblemente enlazado.
	 * @return Nodo siguiente al nodo doblemente enlazado.
	 */
	public DNodo<E> getSiguiente(){
		return siguiente;
	}
	
	/**
	 * Devuelve el nodo anterior al nodo doblemente enlazado.
	 * @return Nodo anterior al nodo doblemente enlazado.
	 */
	public DNodo<E> getAnterior(){
		return anterior;
	}
	
}