package TDAColaCP;

/**
 * Modela la excepción de una cola con prioridad vacía.
 * @author Juan Ignacio Sánchez.
 */
public class EmptyPriorityQueueException extends Exception {
	
	/**
	 * Inicializa una excepción una cola con prioridad vacía con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public EmptyPriorityQueueException (String msg) {
		super(msg);
	}
}

