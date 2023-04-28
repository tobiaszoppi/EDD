/*package TDAArbolBinario;

public class BTNodo<E> implements BTPosition<E> {
	protected E elemento;
	protected BTPosition<E> padre, hi, hd;
	
	public BTNodo(E el, BTPosition<E> p){
		elemento = el;
		padre = p;
	}
	
	public BTPosition<E> getParent() { 
		return padre; 
	}
	
	public BTPosition<E> getLeft() { 
		return hi; 
	}
	
	public BTPosition<E> getRight() { 
		return hd;
	}
	
	public E element() { 
		return elemento; 
	}
	
	public void setParent(BTPosition<E> p) {
		padre = p; 
	}
	
	public void setLeft(BTPosition<E> l) { 
		hi = l; 
	}
	
	public void setRight(BTPosition<E> r) {
		hd = r; 
	}
	
	public void setElement(E e) { 
		elemento = e; 
	}

}
*/

package TDAArbolBinario;

public class BTNodo<E> implements BTPosition<E>{
	
	private E elemento;
	private BTNodo<E> hijoI;
	private BTNodo<E> hijoD; 
	private BTNodo<E> padre;
	
	public BTNodo(E e,BTNodo<E> p){
		elemento=e;
		padre=p;
	}
	
	public BTNodo(E e){
		this(e,null);
	}

	@Override
	public E element() {
		return elemento;
	}

	@Override
	public BTPosition<E> getParent() {
		return padre;
	}

	@Override
	public BTPosition<E> getLeft() {
		return hijoI;
	}

	@Override
	public BTPosition<E> getRight() {
		return hijoD;
	}

	@Override
	public void setElement(E e) {
		elemento=e;
	}

	@Override
	public void setParent(BTPosition<E> p) {
		padre=(BTNodo<E>) p;
		
	}

	@Override
	public void setLeft(BTPosition<E> l) {
		hijoI=(BTNodo<E>) l;
		
	}

	@Override
	public void setRight(BTPosition<E> r) {
		hijoD=(BTNodo<E>) r;
		
	}
}

