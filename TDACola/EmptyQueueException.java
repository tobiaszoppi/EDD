package TDACola;

/**
 * Modela la excepci�n de una cola vac�a.
 * @author Juan Ignacio S�nchez.
 */
public class EmptyQueueException extends Exception {
	
	/**
	 * Inicializa una excepci�n de una cola vac�a con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public EmptyQueueException(String msg) {
		super(msg);
	}
}
