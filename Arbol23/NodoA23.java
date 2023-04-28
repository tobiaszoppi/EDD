package Arbol23;

public class NodoA23<E> {
	private E rotulo1,rotulo2;
	private boolean eliminado;
	private NodoA23<E> padre, izq, med, der;
	
	public NodoA23(E rotulo1, E rotulo2) {
		this.rotulo1=rotulo1;
		this.rotulo2=rotulo2;
		eliminado=false;
		padre=null;izq=null;med=null;der=null;
	}
	public NodoA23(E rotulo1) {
		this.rotulo1=rotulo1;
		this.rotulo2=null;
		eliminado=false;
		padre=null;izq=null;med=null;der=null;
	}
	
	public void setRotulo1(E x) {
		rotulo1=x;
	}
	
	public void setRotulo2(E x) {
		rotulo2=x;
	}
	
	public void setEliminado(boolean b) {
		eliminado=b;
	}
	
	public void setParent(NodoA23<E> p) {
		padre=p;
	}
	
	public void setLeft(NodoA23<E> hi) {
		izq=hi;
	}
	
	public void setMedio(NodoA23<E> hm) {
		med=hm;
	}
	
	public void setRight(NodoA23<E> hd) {
		der=hd;
	}
	
	public E getRotulo1() {
		return rotulo1;
	}
	
	public E getRotulo2() {
		return rotulo2;
	}
	
	public boolean getEliminado() {
		return eliminado;
	}
	
	public NodoA23<E> getParent() {
		return padre;
	}
	
	public NodoA23<E> getLeft() {
		return izq;
	}
	
	public NodoA23<E> getMedio() {
		return med;
	}
	
	public NodoA23<E> getRight() {
		return der;
	}
}

