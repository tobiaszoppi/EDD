package TDAArbolBinario;

/**
 * Modela la excepcion de violación de límites de un árbol binario.
 * @author Juan Ignacio Sánchez.
 */
public class BoundaryViolationException extends Exception {
	
	/**
	 * Inicializa una excepción de violación de límites de un árbol binario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepción.
	 */
	public BoundaryViolationException (String msg) {
		super(msg);
	}
}
