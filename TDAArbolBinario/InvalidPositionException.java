package TDAArbolBinario;

/**
 * Modela la excepción de posición inválida de un árbol binario.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidPositionException  extends Exception{
	
	/**
	 * Inicializa una excepción de posición inválida de un árbol binario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidPositionException (String msg) {
		super(msg);
	}
}