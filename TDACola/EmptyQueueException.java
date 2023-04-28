package TDACola;

/**
 * Modela la excepción de una cola vacía.
 * @author Juan Ignacio Sánchez.
 */
public class EmptyQueueException extends Exception {
	
	/**
	 * Inicializa una excepción de una cola vacía con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
