package TDALista;

/**
 * Modela la excepci�n de una lista vac�a.
 * @author Juan Ignacio S�nchez.
 */
public class EmptyListException extends Exception {
	
	/**
	 * Inicializa una excepci�n de una lista vac�a con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public EmptyListException (String msg) {
		super(msg);
	}
}
