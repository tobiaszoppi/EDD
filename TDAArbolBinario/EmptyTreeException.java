package TDAArbolBinario;

/**
 * Modela la excepción de un árbol binario vacío.
 * @author Juan Ignacio Sánchez.
 */
public class EmptyTreeException extends Exception {
	
	/**
	 * Inicializa una excepción de un árbol binario vacío con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public EmptyTreeException (String msg) {
		super(msg);
	}
}
