package TDALista;

/**
 * Define las operaciones aplicables sobre una posición de lista.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos que almacena la posición de lista.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posición de lista. 
	 * @return Elemento de la posición de lista.
	 */
	public E element();
}
