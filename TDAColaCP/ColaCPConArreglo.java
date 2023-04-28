package TDAColaCP;
import java.util.Comparator;

public class ColaCPConArreglo<K,V> implements PriorityQueue<K,V>{
	protected Entrada<K,V> [] elementos;
	protected Comparator<K> comparador;
	protected int tamaño;
	
	public ColaCPConArreglo(int maxelementos, Comparator<K> comp ){
		elementos = (Entrada<K,V> []) new Entrada[maxelementos];
		comparador = comp;
		tamaño = 0;
	}
	
	public int size(){
		return tamaño;
	}
	
	public boolean isEmpty(){
		return tamaño == 0;
	}
	
	public Entry<K,V> insert(K key,V value)throws InvalidKeyException{
		if(key == null){
			throw new InvalidKeyException("Clave Invalida");
		}
		if(tamaño == elementos.length){
			redimensionar();
		}
		Entrada <K,V> entrada = new Entrada<K,V>(key,value); 
		elementos[tamaño] = entrada;
		tamaño++;
		return entrada;
	}
	
	private void redimensionar(){
		Entrada<K,V> [] arregloviejo = elementos;
		elementos = (Entrada<K,V> []) new Entrada[elementos.length*2];
		for(int i = 0; i < arregloviejo.length; i++){
			elementos[i] = arregloviejo[i];
		}
	}
	
	public Entry<K,V> min()throws EmptyPriorityQueueException{
		if(tamaño == 0){
			throw new EmptyPriorityQueueException("Cola vacia");
		}
		Entrada<K,V> minimo = elementos[0];
		for(int i = 1; i < tamaño; i++){
			if(comparador.compare(elementos[i].getKey(),minimo.getKey()) < 0){
				minimo = elementos[i];
			}
		}
		return minimo;
	}
	
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		if(tamaño == 0){
			throw new EmptyPriorityQueueException("Cola vacia");
		}
		Entrada<K,V> minimo = elementos[0];
		int posicion = 0;
		for(int i = 1; i < tamaño; i++){
			if(comparador.compare(elementos[i].getKey(),minimo.getKey()) < 0){
				minimo = elementos[i];
				posicion = i;
			}
		}
		retroceder(posicion);
		tamaño--;
		return minimo;
	}
	
	private void retroceder(int pos){
		for(int i = pos; i < tamaño; i++){
			elementos[i] = elementos[i+1];
		}
	}
}
