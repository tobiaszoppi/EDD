package TDAArbolBinario;

/**
 * Modela la excepci�n de posici�n inv�lida de un �rbol binario.
 * @author Juan Ignacio S�nchez.
 */
public class InvalidPositionException  extends Exception{
	
	/**
	 * Inicializa una excepci�n de posici�n inv�lida de un �rbol binario con msg como mensaje a mostrar.
	 * @param msg Cadena de caracteres que contiene el mensaje de la excepci�n.
	 */
	public InvalidPositionException (String msg) {
		super(msg);
	}
}