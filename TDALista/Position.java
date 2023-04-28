package TDALista;

/**
 * Define las operaciones aplicables sobre una posici�n de lista.
 * @author Juan Ignacio S�nchez.
 * @param <E> Tipo de dato de los elementos que almacena la posici�n de lista.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posici�n de lista. 
	 * @return Elemento de la posici�n de lista.
	 */
	public E element();
}
