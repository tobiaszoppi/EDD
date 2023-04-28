package TDAColaCP;
import java.util.Comparator;

public class ColaCPConHeap <K extends Comparable<K>,V> implements PriorityQueue<K,V>{
	protected Entrada<K,V> [] elems;
	protected Comparator<K> comp;
	protected int size;
	
	public ColaCPConHeap(int maxElems, Comparator<K> comp ) {
		// Ojo: ��Mirar bien c�mo se hace la creaci�n del arreglo!!
		// Creo un arreglo de maxElems entradas
		elems = (Entrada<K,V> []) new Entrada[maxElems];
		this.comp = comp; // Me guardo el comparador del cliente
		size = 0; // Digo que el �rbol est� vac�o porque no tiene entradas
	}
	
	public ColaCPConHeap (){
		comp = new DefaultComparator<K>();
		elems=(Entrada<K,V> []) new Entrada [10];
		size=0;
		
	}
	
	public int size() {
		return size; // Size es la cantidad de entradas del �rbol
	}
	
	public boolean isEmpty() {
		return size == 0; // El �rbol est� vac�o cuando no tiene entradas
	}
	
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Cola con prioridad vac�a.");
		}
		return elems[1];// Recuerde que la componente 0 del arreglo no se usa
	}
	
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if (key==null ) {
			throw new InvalidKeyException("Clave invalida");
		}
		if(size == elems.length-1){
			redimensionar();
		}
		Entrada<K,V> entrada = new Entrada<K,V>(key, value); // Creo una entrada nueva
		elems[++size] = entrada; // Incremento size y pongo la entrada nueva al final del arreglo
		// Burbujeo para arriba.
		int i = size; // seteo indice i de la posicion corriente en arreglo que es la �ltima
		boolean seguir = true; // Bandera para saber cu�ndo encontr� la ubicaci�n de entrada
		while ( i>1 && seguir ) {
			Entrada <K,V> elemActual = elems[i]; // obtengo entrada i-�sima
			Entrada <K,V> elemPadre = elems[i/2]; // obtengo el padre de la entrada i-�sima
			if( comp.compare(elemActual.getKey(), elemPadre.getKey()) < 0) {
				Entrada<K,V> aux = elems[i]; // Intercambio entradas si est�n desordenadas
				elems[i] = elems[i/2];
				elems[i/2] = aux;
				i /= 2; // Reinicializo i con el �ndice de su padre
			} 
			else // Si no pude intercambiar => la entrada ya estaba ordenada
				seguir = false; // Aviso que termin�
		} // fin while
		return entrada;
	}
	
	private void redimensionar(){
		Entrada<K,V> [] arregloviejo = elems;
		elems = (Entrada<K,V> []) new Entrada[elems.length*2];
		for(int i = 0; i < arregloviejo.length; i++){
			elems[i] = arregloviejo[i];
		}
	}
	
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if (size==0) {
			throw new EmptyPriorityQueueException("Cola con heap vac�a.");
		}
		Entry<K,V> entrada = min(); // Salvo valor a retornar.
		int m=1;
		int hi;
		int hd;
		if( size == 1 ) {
			elems[1] = null; size = 0; return entrada; 
		}
		else {// Paso la �ltima entrada a la ra�z y la borro del final del arreglo y decremento size:
			elems[1] = elems[size]; elems[size] = null; size--;
			// Burbujeo la nueva ra�z hacia abajo buscando su ubicaci�n correcta:
			int i = 1; // i es mi ubicaci�n corriente (Me ubico en la ra�z)
			boolean seguir = true; // Bandera para saber cu�ndo terminar
			while ( seguir ) {
				// Calculo la posici�n de los hijos izquierdo y derecho de i; y veo si existen realmente:
				hi = i*2; hd = i*2+1;
				boolean tieneHijoIzquierdo = hi <= size(); boolean tieneHijoDerecho = hd <= size();
				if( !tieneHijoIzquierdo ) 
					seguir = false; // Si no hay hijo izquierdo, llegu� a una hoja
				else {
					// En m voy a computar la posici�n del m�nimo de los hijos de i:
					if( tieneHijoDerecho ) {
		// Calculo cu�l es el menor de los hijos usando el comparador de prioridades:
						if( comp.compare( elems[hi].getKey(), elems[hd].getKey()) < 0 ) m = hi;
						else m = hd;
					} 
					else m = hi; // Si hay hijo izquierdo y no hay hijo derecho, el m�nimo es el izq.
				} 
				// Me fijo si hay que intercambiar el actual con el menor de sus hijos:
				if( comp.compare(elems[i].getKey(), elems[m].getKey()) > 0 ) {
					Entrada<K,V> aux = elems[i]; // Intercambio la entrada i con la m
					elems[i] = elems[m];
					elems[m] = aux;
					i = m; // Reinicializo i para en la siguiente iteraci�n actualizar a partir de posici�n m.
				} 
				else seguir = false; // Si la comparaci�n de entrada i con la m dio bien, termino.
			} // Fin while
		}
		return entrada;
		} // Fin m�todo removeMin
}
