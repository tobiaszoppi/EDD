package TDAArbolBinario;

/**
 * Define las operaciones aplicables sobre una posición de árbol binario.
 * @author Juan Ignacio Sánchez.
 * @param <E> Tipo de dato de los elementos que almacena la posición de árbol binario.
 */
public interface Position<E> {
	
	/**
	 * Devuelve el elemento de la posición de árbol binario. 
	 * @return Elemento de la posición de árbol binario.
	 */
	public E element();
}
