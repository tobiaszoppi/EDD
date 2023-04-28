package TDACola;

/**
 * Define los datos y operaciones aplicables sobre una cola con arreglo circular.
 * @author Juan Ignacio S�nchez.
 * @param <E> Tipo de dato a almacenar en la cola con arreglo circular.
 */
public class ColaConArregloCircular<E> implements Queue<E>{
	
	/**
	 * Componente del arreglo donde se ubica el frente de la cola y del pr�ximo elemento a ser eliminado.
	 */
	protected int frente;
	/**
	 * Componente del arreglo donde se va a realizar la siguiente inserci�n de la cola.
	 */
	protected int rabo;
	/**
	 * Arreglo que mantiene los elementos de la cola.
	 */
	protected E[] datos;
	
	/**
	 * Inicializa una cola con arreglo circular vac�a, con el tama�o pasado por par�metro.
	 * @param tam Tama�o del arreglo a crear.
	 */
	public ColaConArregloCircular(int tam) {
		frente=0;
		rabo=0;
		datos=(E []) new Object[Math.abs(tam)];//valor absoluto por si el par�metro es negativo
	}
	
	/**
	 * Inicializa una cola con arreglo circular vac�a de tama�o 20.
	 */
	public ColaConArregloCircular() {
		frente=0;
		rabo=0;
		datos=(E []) new Object[20];
	}
	
	@Override
	public int size() {
		return (datos.length-frente+rabo) % datos.length;
	}
	
	@Override
	public boolean isEmpty() {
		return frente==rabo;
	}
	
	@Override
	public E front() throws EmptyQueueException {
		if (frente==rabo) {
			throw new EmptyQueueException("Error, la cola est� vac�a.");
		}
		else {
			return datos[frente];
		}
	}	
	
	@Override
	public void enqueue(E element) {
		if (size()==datos.length-1) {
			alargarArreglo();
		}
		datos[rabo]=element;
		rabo=(rabo+1) % datos.length;
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		if (frente==rabo) {
			throw new EmptyQueueException("Error, la cola est� vacia.");
		}
		else {
			E aux=datos[frente];
			datos[frente]=null;
			frente=(frente+1) % datos.length;
			return aux;
		}
	}
	
	/**
	 * Redimensiona el arreglo.
	 */
	private void alargarArreglo() {
		E[] arregloAux=(E[]) new Object[2*size()];
		int frenteAux=frente;
		//acomodo el frente y el rabo para que los elementos de la cola queden en las primeras posiciones del arreglo
		rabo=size(); 
		frente=0;
		for(int i=0;i<size();i++){
			arregloAux[i]=datos[frenteAux];
			frenteAux=(frenteAux+1) % datos.length;//avanza circularmente
		} 
		datos=arregloAux;
	}
}