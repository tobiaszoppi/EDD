package TDAABB;
import java.util.Comparator;

public class ArbolBinarioBusqueda<E extends Comparable<E>> {
	protected NodoABB <E> raiz;
	protected Comparator <E> comparador;

	public ArbolBinarioBusqueda() {
		raiz=new NodoABB<E>(null , null);
		comparador=new DefaultComparator <E>();
	}
	
	public ArbolBinarioBusqueda(Comparator <E> comp ) {
		raiz=new NodoABB<E>(null , null);
		comparador=comp;
	}
	
	public NodoABB<E> getRaiz() {
		return raiz;
	}
	
	public NodoABB <E> buscar(E e) {
		return buscarAux(e,raiz);
	}
	
	private NodoABB<E> buscarAux(E e, NodoABB<E> n) {
		int comp;
		NodoABB<E> retorna;
		if (n.getRotulo()==null) {//llegue a un dummy
			retorna=n;
		}
		else {
			comp = comparador.compare(e, n.getRotulo() );
			if( comp == 0 ) {
				retorna=n; // Lo encontré porque e = p.getRotulo()
			}
			else {
				if( comp < 0 ) { /* Busco a izq porque e < p.getRotulo() */
					return buscarAux(e, n.getLeft() );
				}
				else { /* Busco a derecha porque e > p.getRotulo() */
					return buscarAux(e, n.getRight() );
				}
			}
		}
		return retorna;
	}
	/**
	 * Expande un nodo DUMMY, reconvirtiéndolo en un nodo con rótulo y dos hijos DUMMY
	 */
	public void expandir(NodoABB<E> p, E e) {
		p.setRotulo(e);//le cambio el rotulo al nodo dummy
		p.setLeft( new NodoABB<E>( null, p) ); // Creo dummy a izquierda
		p.setRight( new NodoABB<E>( null, p ) ); // Creo dummy a derecha
	}
	
	public void eliminar(E e) {
		// Busco x en el árbol para obtener el nodo p que lo contiene
		NodoABB<E> p = buscar( e );
		// Si el rótulo de p es null, p es un dummy, entonces
		// x no está en el árbol
		if( p.getRotulo() != null ) {
			if (p.getLeft().getRotulo()==null && p.getRight().getRotulo()==null) {//p es una hoja
				//System.out.println(p.getRotulo()+" es hoja");
				p.setRotulo(null);
			}
			else {//p no es una hoja
				if (p!=raiz && p.getLeft().getRotulo()!=null && p.getRight().getRotulo()==null) {//p solo tiene hijo izquierdo
					//System.out.println(p.getRotulo()+" solo tiene hijo izquierdo");
					if (p.getParent().getLeft()==p) {//p es el hijo izquierdo de su padre
						p.getParent().setLeft(p.getLeft());//establezco al hijo de p como hijo izquierdo del padre de p
					}
					else {//p es el hijo derecho de su padre
						if (p!=raiz) {
							p.getParent().setRight(p.getLeft());//establezco al hijo de p como hijo derecho del padre de p
						}
						else {//si es la raiz
							E aux=raiz.getLeft().getRotulo();
							eliminar(aux);//borra el hijo
							raiz.setRotulo(aux);//la raiz queda con el rotulo del hijo				
						}
					}
					p.getLeft().setParent(p.getParent());//establezco al padre de p como padre del hijo de p
					p.setRotulo(null);//nulo en el rotulo de p
				}	
			}
		}
		else {			
			if (p!=raiz && p.getRight().getRotulo()!=null && p.getLeft().getRotulo()==null) {//p solo tiene hijo derecho
				if (p.getParent().getLeft()==p) {//p es el hijo izquierdo de su padre
						p.getParent().setLeft(p.getRight());//establezco al hijo de p como hijo izquierdo del padre de p
				}
				else {//p es el hijo derecho de su padre
					if (p!=raiz) {
						p.getParent().setRight(p.getRight());//establezco al hijo de p como hijo derecho del padre de p
					}
					else { //si es la raiz
						E aux=raiz.getRight().getRotulo();
						eliminar(aux);//borra el hijo
								raiz.setRotulo(aux);//la raiz queda con el rotulo del hijo
					}
				}
				p.getRight().setParent(p.getParent());//establezco al padre de p como padre del hijo de p
				p.setRotulo(null);//nulo en el rotulo de p
			}					
			else {
				E aux=eliminarAux(p);
				p.setRotulo(aux);
			}
		}
	}			
		
	
	
	private E eliminarAux(NodoABB<E> p) {//p tiene 2 hijos
		E rotulo=null;
		NodoABB<E> pos = p.getLeft();
		while(pos.getRight().getRotulo() != null){
			pos = pos.getRight();
		}
		rotulo = pos.getRotulo();
		eliminar(pos.getRotulo());
		return rotulo;
	}
	
	public void imprimir() {
		if (this.getRaiz().getRotulo()!=null) {
			System.out.println(this.getRaiz().getRotulo());
		}
		if (this.getRaiz().getLeft().getRotulo()!=null) {
			mostrar(this.getRaiz().getLeft());
		}
		if (this.getRaiz().getRight().getRotulo()!=null) {
			mostrar(this.getRaiz().getRight());
		}
	}
	
	private void mostrar(NodoABB<E> n) {
		if (this.getRaiz().getRotulo()!=null) {
			System.out.println(n.getRotulo()+" hijo de "+n.getParent().getRotulo());
			if (n.getLeft().getRotulo()!=null) {
				mostrar(n.getLeft());
			}
			if (n.getRight().getRotulo()!=null) {
				mostrar(n.getRight());
			}
		}
		
	}
}