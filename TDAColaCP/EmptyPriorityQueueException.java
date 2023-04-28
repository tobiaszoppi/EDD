package TDAColaCP;

/**
 * Modela la excepci�n de una cola con prioridad vac�a.
 * @author Juan Ignacio S�nchez.
 */
public class EmptyPriorityQueueException extends Exception {
	
	/**
	 * Inicializa una excepci�n una cola con prioridad vac�a con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public EmptyPriorityQueueException (String msg) {
		super(msg);
	}
}

