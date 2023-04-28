package AVL;

public class NodoAVL<E> {
	private E rotulo;
	private int altura; 
	private boolean eliminado;
	private NodoAVL<E> padre, izq, der;
	
	public NodoAVL(E rotulo) {
		altura=0;
		this.rotulo=rotulo;
		eliminado=false;
		padre=null;izq=null;der=null;
	}
	
	public void setRotulo(E x) {
		rotulo=x;
	}
	
	public void setAltura(int h) {
		altura=h;
	}
	
	public void setEliminado(boolean b) {
		eliminado=b;
	}
	
	public void setParent(NodoAVL<E> p) {
		padre=p;
	}
	
	public void setLeft(NodoAVL<E> hi) {
		izq=hi;
	}
	
	public void setRight(NodoAVL<E> hd) {
		der=hd;
	}
	
	public E getRotulo() {
		return rotulo;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public boolean getEliminado() {
		return eliminado;
	}
	
	public NodoAVL<E> getParent() {
		return padre;
	}
	
	public NodoAVL<E> getLeft() {
		return izq;
	}
	
	public NodoAVL<E> getRight() {
		return der;
	}
}
