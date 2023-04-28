package TDAColaCP;

/**
 * Modela la excepci�n de clave inv�lida de una cola con prioridad.
 * @author Juan Ignacio S�nchez.
 */
public class InvalidKeyException  extends Exception {
	
	/**
	 * Inicializa una excepci�n de clave inv�lida de una cola con prioridad con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public InvalidKeyException (String msg) {
		super(msg);
	}
}
