package Huffman;
import TDAArbolBinario.*;
import TDAColaCP.*;
import TDACola.*;
public class Huffman {
	
	public BinaryTree<Character> huffman(String cad) {
		Character elemento;
		int i=0,f1=0,f2=0;
		BinaryTree<Character> t1,t2,retorna=null;
		PriorityQueue<Integer,BinaryTree<Character>> c=new ColaCPConLista<Integer,BinaryTree<Character>>(new DefaultComparator<Integer>());
		BTPosition<Character> raizArbol;
		try {
			while(i<cad.length()) {
				elemento=cad.charAt(i);
				BinaryTree<Character> t=new ArbolBinarioEnlazado<Character>();//arbol con elemento como raiz
				t.createRoot(elemento);
				//System.out.println("frecuencia de "+elemento+" es "+frecuencia(cad, elemento));
				if (!repite(cad,i,elemento)) {//inserto en la cola el elemento si en los caracteres anteriores de la cadena no estaba elemento
					c.insert(frecuencia(cad, elemento),t);//inserta el arbol con prioridad=frecuencia de elemento
				}
				i++;
			}
			while (c.size()>1) {
				f1=c.min().getKey();
				t1=c.removeMin().getValue();
				f2=c.min().getKey();
				t2=c.removeMin().getValue();
				BinaryTree<Character> arbol=new ArbolBinarioEnlazado<Character>();//arbol con t1 como hijo izq y t2 como hijo der
				raizArbol=(BTPosition<Character>) arbol.createRoot('*');
				System.out.println("menor: "+t1.root().element());
				System.out.println("mayor: "+t2.root().element());
				BTPosition<Character> hi=(BTPosition<Character>) arbol.addLeft(raizArbol, t1.root().element());
				insertarArbol(arbol,hi,t1,(BTPosition<Character>) t1.root());
				BTPosition<Character> hd=(BTPosition<Character>) arbol.addRight(raizArbol, t2.root().element());
				insertarArbol(arbol,hd,t2,(BTPosition<Character>) t2.root());
				c.insert(f1+f2, arbol);//inserto arbol con prioridad f1+f2
				System.out.println(c.size());
			}
			retorna=c.removeMin().getValue();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
		catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		
		return retorna;
	}
	
	public BinaryTree<Character> huffman2(String cad) {
		Character elemento;
		int i=0,f1=0,f2=0;
		BinaryTree<Character> t1,t2,retorna=null;
		PriorityQueue<Integer,BTPosition<Character>> c=new ColaCPConLista<Integer,BTPosition<Character>>(new ComparadorDescendente<Integer>());
		BTPosition<Character> raizArbol;
		try {
			while(i<cad.length()) {
				elemento=cad.charAt(i);
				BTPosition<Character> n=new BTNodo<Character>(elemento,null);
				if (!repite(cad,i,elemento)) {//inserto en la cola el elemento si en los caracteres anteriores de la cadena no estaba elemento
					c.insert(frecuencia(cad, elemento),n);//inserta el arbol con prioridad=frecuencia de elemento
				}
				i++;
			}
			retorna=new ArbolBinarioEnlazado<Character>();
			raizArbol=(BTPosition<Character>) retorna.createRoot('*');
			while(!c.isEmpty()) {
				BTPosition<Character> m=c.removeMin().getValue();
				if (!retorna.hasLeft(raizArbol)) {
					retorna.addLeft(raizArbol,m.element());
				}
				else {
					if (!retorna.hasRight(raizArbol)) {
						retorna.addRight(raizArbol,m.element());
					}
					else {
						insertarDerecha(retorna,raizArbol,m.element());
					}
				}
				
			}
		}
		catch(InvalidKeyException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
		catch(EmptyPriorityQueueException e) {
			e.printStackTrace();
		}
		return retorna;
	}
	
	
	private void insertarDerecha(BinaryTree<Character> t, BTPosition<Character> n, Character elemento) {
		try {
			if (!t.hasLeft(n) && !t.hasRight(n)) {//n es hoja
				Character aux=n.element();
				n.setElement('*');
				t.addLeft(n,aux);
				t.addRight(n, elemento);
			}
			else {
				insertarDerecha(t,n.getRight(),elemento);
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
	}
	
	private boolean repite(String cad, int i, Character c) {
		boolean repite=false;
		for (int j=0;j<i && !repite;j++) {
			if (cad.charAt(j)==c) {
				repite=true;
			}
		}
		return repite;
	}
	private void insertarArbol(BinaryTree<Character> t,BTPosition<Character> h,BinaryTree<Character> t1,BTPosition<Character> r1) {
		try {
			if (t1.hasLeft(r1)) {
				System.out.println("tiene izq");
				BTPosition<Character> hi=(BTPosition<Character>) t.addLeft(h, r1.getLeft().element());
				insertarArbol(t,hi,t1,r1.getLeft());
			}
			if (t1.hasRight(r1)) {
				System.out.println("tiene der");
				BTPosition<Character> hd=(BTPosition<Character>) t.addRight(h, r1.getRight().element());
				insertarArbol(t,hd,t1,r1.getRight());
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(InvalidOperationException e) {
			e.printStackTrace();
		}
	}
	private int frecuencia(String cad, Character x) {
		Character elemento;
		int i=0,retorna=0;
		while(i<cad.length()) {
			elemento=cad.charAt(i);
			if (x==elemento) {
				retorna++;
			}
			i++;
		}
		return retorna;
	}
	
	public <E> void porNiveles(BinaryTree<E> t) {//ANDA SOLO SI LAS POSICIONES NO TIENEN EL MISMO ROTULO
		E elemento;
		Position<E> aux=null;
		Queue<E> cola=new ColaConArregloCircular<E>();
		try {
			if (!t.isEmpty() && t.root()!=null) {
				cola.enqueue(t.root().element());
				cola.enqueue(null);//para que imprima el salto de linea
				while(!cola.isEmpty()) {
					elemento=cola.dequeue();
					if (elemento!=null) {
						System.out.print(elemento+" - ");
						for (Position<E> p:t.positions()) {//busca la posicion que tiene a elemento
							if (p.element()==elemento) {
								aux=p;
							}
						}
						if (t.hasLeft(aux)) {
							cola.enqueue(t.left(aux).element());
						}
						if (t.hasRight(aux)) {
							cola.enqueue(t.right(aux).element());
						}
					}
					else  {
						System.out.println();
						if (!cola.isEmpty()) {
							cola.enqueue(null);
						}
					}
				}
			}
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		catch(EmptyQueueException e) {
			e.printStackTrace();
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		catch(BoundaryViolationException e) {
			e.printStackTrace();
		}
		
	}
	
	public String descomprimir(BinaryTree<Character> t, String cad) {//asume que cad es correcta y solo contiene 0 y 1
		Contador i=new Contador();//clase necesaria para actualizar el contador al salir de descomprimirAux
		Character elemento;
		BTPosition<Character> raiz=null;
		String s="";
		try {
			if (!t.isEmpty()) {
				raiz=(BTPosition<Character>) t.root();
			}
			while(i.getContador()<cad.length()) {
				elemento=cad.charAt(i.getContador());
				if (elemento=='1') {
					s=s+raiz.getRight().element();
				}
				else {
					if (i.getContador()+1<cad.length()) {//si no es el ultimo caracter
						i.setContador(i.getContador()+1);
						s=s+descomprimirAux(t,raiz.getLeft(),cad,i);
					}
					else {//es el ultimo caracter
						s=s+raiz.getRight().element();
					}
				}
				i.setContador(i.getContador()+1);
			}
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return s;
			
	}
	
	private Character descomprimirAux(BinaryTree<Character> t, BTPosition<Character> n, String cad, Contador i) {
		Character elemento,retorna=null;
		elemento=cad.charAt(i.getContador());
		if (elemento=='1') {
			retorna=n.getRight().element();
		}
		else {
			if (i.getContador()+1<cad.length()) {//si no es el ultimo caracter
				i.setContador(i.getContador()+1);
				retorna=descomprimirAux(t,n.getLeft(),cad,i);
			}
			else {//es el ultimo caracter
				retorna=n.getLeft().element();
			}
		}
		return retorna;
	}
	
	public String descomprimir2(BinaryTree<Character> t, String cad) {//asume que cad es correcta y solo contiene 0 y 1
		Contador i=new Contador();//clase necesaria para actualizar el contador al salir de descomprimirAux
		Character elemento;
		BTPosition<Character> raiz=null;
		String s="";
		try {
			if (!t.isEmpty()) {
				raiz=(BTPosition<Character>) t.root();
			}
			while(i.getContador()<cad.length()) {
				elemento=cad.charAt(i.getContador());
				if (elemento=='0') {
					s=s+raiz.getLeft().element();
				}
				else {
					if (i.getContador()+1<cad.length()) {//si no es el ultimo caracter
						i.setContador(i.getContador()+1);
						s=s+descomprimirAux(t,raiz.getRight(),cad,i);
					}
					else {//es el ultimo caracter
						s=s+raiz.getRight().element();
					}
				}
				i.setContador(i.getContador()+1);
			}
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return s;
			
	}
	
	private Character descomprimirAux2(BinaryTree<Character> t, BTPosition<Character> n, String cad, Contador i) {
		Character elemento,retorna=null;
		elemento=cad.charAt(i.getContador());
		if (elemento=='0') {
			retorna=n.getLeft().element();
		}
		else {
			if (i.getContador()+1<cad.length()) {//si no es el ultimo caracter
				i.setContador(i.getContador()+1);
				retorna=descomprimirAux(t,n.getRight(),cad,i);
			}
			else {//es el ultimo caracter
				retorna=n.getRight().element();
			}
		}
		return retorna;
	}
	
	
	public String comprimir(BinaryTree<Character> t, String cad) {//asume que cad es correcta y solo contiene los caracteres de las hojas de t
		Contador i=new Contador();//clase necesaria para actualizar el contador al salir de descomprimirAux
		Character elemento;
		BTPosition<Character> raiz=null;
		String s="";
		try {
			if (!t.isEmpty()) {
				raiz=(BTPosition<Character>) t.root();
			}
			while(i.getContador()<cad.length()) {
				elemento=cad.charAt(i.getContador());
				s=s+buscarElemento(t,raiz,elemento,"");
				i.setContador(i.getContador()+1);
			}
		}
		catch(EmptyTreeException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	public String buscarElemento(BinaryTree<Character> t, BTPosition<Character> n, Character elem,String s) {
		try {
			System.out.println(elem+"="+s);
			if (!t.hasLeft(n) && !t.hasRight(n)) {//si es una hoja
				if (n.element()==elem) {
					return s;
				}
				else {
					s="";
				}
			}
			else {
				if (t.hasLeft(n)) {
					String aux=s;//guarda s, por si al buscar en la izq la cadena no fue encontrada
					s=buscarElemento(t,n.getLeft(),elem,s+"0");
					if (s=="") {
						if (t.hasRight(n)) {
							s=buscarElemento(t,n.getRight(),elem,aux+"1");
							if(s!="") {
								System.out.println(elem+"="+s);
							}
						}
					}
					else {
						System.out.println(elem+"="+s);
					}
				}
			}
		}
		catch(InvalidPositionException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	
}
