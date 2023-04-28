package TDALista;

/**
 * Modela la excepción de posición inválida de una lista.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidPositionException  extends Exception{
	
	/**
	 * Inicializa una excepción de posición inválida de una lista con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidPositionException (String msg) {
		super(msg);
	}
}
