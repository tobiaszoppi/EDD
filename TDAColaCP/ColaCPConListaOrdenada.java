package TDAColaCP;
import java.util.Comparator;
import TDALista.PositionList;
import TDALista.EmptyListException;
import TDALista.ListaDoblementeEnlazada;

public class ColaCPConListaOrdenada <K extends Comparable<K>,V> implements PriorityQueue<K,V>{
	protected PositionList<Entry<K,V>> lista;
	protected Comparator<K> comparador;

	public ColaCPConListaOrdenada (){
		comparador = new DefaultComparator<K>();
		lista=new ListaDoblementeEnlazada();
		
	}
	public ColaCPConListaOrdenada (Comparator<K> comp){
		comparador = comp;
	}
	@Override
	public int size() {
		return lista.size();
	}
	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}
	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		Entry<K,V> entrada=null;
		try {
			if (lista.isEmpty()) {
				throw new EmptyPriorityQueueException("Cola con prioridad vacía");
			}
			entrada=lista.first().element();
		}
		catch (EmptyListException e) {
			e.printStackTrace();
		}
		return entrada;//como el arreglo está ordenado, el elemento minimo está en la primer posición
	}
	@Override
	public Entry<K,V> insert(K key,V value)throws InvalidKeyException{
		Entry <K,V> elemActual,entrada=null;
		boolean lopuso = false;
		try {
			if(key == null){
				throw new InvalidKeyException("Clave Invalida");
			}
			entrada = new Entrada<K,V>(key,value);
			for(TDALista.Position<Entry<K,V>> e:lista.positions()) {
				elemActual = e.element(); 
				if(comparador.compare(key,elemActual.getKey()) <= 0) {
					lista.addBefore(e, entrada);//inserta la entrada antes de la posicion con clave mayor a la del parametro
					lopuso = true;
					break;//para que corte
				}
			}
			if(!lopuso){
				lista.addLast(entrada);
			}
		}
		catch (TDALista.InvalidPositionException ex) {
			ex.printStackTrace();
		}
		return entrada;
		
	}
	
	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K,V> min=null;
		try {
			if (lista.isEmpty()) {
				throw new EmptyPriorityQueueException("Cola con prioridad vacia");
			}
			min=lista.remove(lista.first());
		}
		catch (TDALista.EmptyListException e) {
			e.printStackTrace();
		}
		catch (TDALista.InvalidPositionException e) {
			e.printStackTrace();
		}
		return min;
	}
	
	public String toString() {
		String s="";
		for(TDALista.Position<Entry<K,V>> e:lista.positions()) {
			s=s+"("+e.element().getKey()+","+e.element().getValue()+")";
		}
		return s;
	}

}
