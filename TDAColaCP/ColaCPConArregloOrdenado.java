package TDAColaCP;
import java.util.Comparator;

public class ColaCPConArregloOrdenado <K extends Comparable<K>,V> implements PriorityQueue<K,V>{
	protected Entry<K,V> [] arreglo;
	protected Comparator<K> comparador;
	protected int size;

	public ColaCPConArregloOrdenado (){
		comparador = new DefaultComparator<K>();
		arreglo=new Entrada [10];
		size=0;
		
	}
	public ColaCPConArregloOrdenado (Comparator<K> comp){
		comparador = comp;
		arreglo=new Entrada [10];
		size=0;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size==0) {
			throw new EmptyPriorityQueueException("Cola con prioridad.");
		}
		return arreglo[0];//como el arreglo está ordenado, el elemento minimo está en la primer posición
	}
	@Override
	public Entry<K,V> insert(K key,V value)throws InvalidKeyException{
		Entry <K,V> elemActual;
		boolean lopuso = false;
		if(key == null){
			throw new InvalidKeyException("Clave Invalida");
		}
		if(size == arreglo.length){
			redimensionar();
		}
		Entrada <K,V> entrada = new Entrada<K,V>(key,value);
		for(int i = 0; i < size && !lopuso; i++){
			elemActual = arreglo[i]; 
			if(comparador.compare(key,elemActual.getKey()) <= 0){
				moverADerecha(i+1,arreglo[i]);
				arreglo[i] = entrada;
				lopuso = true;
			}
		}
		if(!lopuso){
			arreglo[size] = entrada;
		}
		size++;
		return entrada;
		
	}
	
	private void moverADerecha(int p, Entry<K,V> e) {
		Entry<K,V> aux;
		for (int i=p;i<size+1;i++) {
			aux=arreglo[i];
			arreglo[i]=e;
			e=aux;
		}
	}
	
	private void redimensionar() {
		Entry<K,V> [] aux=arreglo;
		arreglo=new Entrada[2*arreglo.length];
		for (int i=0;i<aux.length;i++) {
			arreglo[i]=aux[i];
		}
	}
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> min;
		if (size==0) {
			throw new EmptyPriorityQueueException("Cola con prioridad vacia");
		}
		min=arreglo[0];
		moverAIzquierda();
		size--;
		return min;
	}
	
	private void moverAIzquierda() {
		for (int i=0;i<size-1;i++) {
			arreglo[i]=arreglo[i+1];
		}
		arreglo[size-1]=null;
	}
	
	public String toString () {
		String s="";
		for (int i=0;i<size;i++) {
			s=s+arreglo[i].toString();
		}
		return s;
	}
}
