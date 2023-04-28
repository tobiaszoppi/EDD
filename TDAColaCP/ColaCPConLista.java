package TDAColaCP;
import java.util.Comparator;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;

public class ColaCPConLista<K,V> implements PriorityQueue<K,V>{
	protected TDALista.PositionList<Entry<K,V>> elementos;
	protected Comparator<K> comparador;
	protected int tamaño;
	
	public ColaCPConLista(Comparator<K> comp){
		elementos = new TDALista.ListaDoblementeEnlazada<Entry<K,V>>();
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
		Entry<K,V> entrada = new Entrada<K,V>(key,value);
		elementos.addLast(entrada);
		tamaño++;
		return entrada;
	}
	
	public Entry<K,V> min() throws EmptyPriorityQueueException{
		Entry<K,V> entrada = null;
		try {
			if(tamaño == 0){
				throw new EmptyPriorityQueueException("Cola vacia");	
			}
			entrada = elementos.first().element();
			for(TDALista.Position<Entry<K,V>> p : elementos.positions()){
				if(comparador.compare(p.element().getKey(),entrada.getKey()) < 0){
					entrada = p.element();
				}
			}
		}catch(EmptyListException e){
			e.getStackTrace();
		}
		return entrada;
	}
	
	public Entry<K,V> removeMin()throws EmptyPriorityQueueException{
		Entry<K,V> entrada = null;
		TDALista.Position<Entry<K,V>> posicion = null;
		try {
			if(tamaño == 0){
				throw new EmptyPriorityQueueException("Cola vacia");	
			}
			posicion = elementos.first();
			for(TDALista.Position<Entry<K,V>> p : elementos.positions()){
				if(comparador.compare(p.element().getKey(),posicion.element().getKey()) < 0){
					posicion = p;
				}
			}
			entrada = posicion.element();
			elementos.remove(posicion);
			tamaño--;
		}
		catch(EmptyListException | InvalidPositionException e){
			e.getStackTrace();
		}
		return entrada;	
	}
}
