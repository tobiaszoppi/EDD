package TDAArbolBinario;

/**
 * Modela la excepci�n de un �rbol binario vac�o.
 * @author Juan Ignacio S�nchez.
 */
public class EmptyTreeException extends Exception {
	
	/**
	 * Inicializa una excepci�n de un �rbol binario vac�o con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public EmptyTreeException (String msg) {
		super(msg);
	}
}
