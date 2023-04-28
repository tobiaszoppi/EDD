package TDALista;

/**
 * Modela la excepción de una lista vacía.
 * @author Juan Ignacio Sánchez.
 */
public class EmptyListException extends Exception {
	
	/**
	 * Inicializa una excepción de una lista vacía con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public EmptyListException (String msg) {
		super(msg);
	}
}
