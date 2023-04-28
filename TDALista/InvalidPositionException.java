package TDALista;

/**
 * Modela la excepci�n de posici�n inv�lida de una lista.
 * @author Juan Ignacio S�nchez.
 */
public class InvalidPositionException  extends Exception{
	
	/**
	 * Inicializa una excepci�n de posici�n inv�lida de una lista con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public InvalidPositionException (String msg) {
		super(msg);
	}
}
