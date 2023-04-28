package TDAArbolBinario;

/**
 * Modela la excepción de operación inválida de un árbol binario.
 * @author Juan Ignacio Sánchez.
 */
public class InvalidOperationException extends Exception {
	
	/**
	 * Inicializa una excepción de operación inválida de un árbol binario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public InvalidOperationException (String msg) {
		super(msg);
	}
}
