package Arbol23;

import java.util.Comparator;

public class Arbol23<E extends Comparable<E>> {

	private NodoA23<E> raiz;
	private Comparator<E> comp;
	
	public Arbol23(Comparator<E> comp) {
		raiz=new NodoA23<E>(null);
		this.comp=comp;
	}
	
	public Arbol23() {
		raiz=new NodoA23<E>(null);
		comp=new DefaultComparator<E>();
	}
	
	public boolean isEmpty() {
		return raiz.getRotulo1()==null;
	}
	
	public NodoA23<E> root() {
		return raiz;
	}
	public NodoA23<E> buscar(E clave) {
		return buscarAux(raiz,clave);
	}
	
	private NodoA23<E> buscarAux(NodoA23<E> n, E clave) {
		System.out.println(n.getRotulo1());
		NodoA23<E> retorna=null;
		int comparacion;
		if (n.getRotulo1()==clave) {
			retorna=n;
		}
		else {
			if (n.getRotulo2()==clave) {
				retorna=n;
			}
			else {//clave no esta en n
				if (n.getLeft()==null && n.getMedio()==null && n.getRight()==null) {//si n es una hoja
					retorna=null;
				}
				else {//n no es hoja
					if (n.getRotulo1()!=null && n.getRotulo2()==null) {//si n tiene un rotulo
						comparacion=comp.compare(clave, n.getRotulo1());
						System.out.println("comparacion 1: "+comparacion);
						if (comparacion<0) {//si clave < al rotulo de n
							retorna=buscarAux(n.getLeft(),clave);
						}
						else {
							if (comparacion==0) {
								retorna=n;
							}
							else {//clave > rotulo1 de n
								retorna=buscarAux(n.getRight(),clave);
							}
						}
					}
					else {
						if (n.getRotulo1()!=null && n.getRotulo2()!=null) {//n tiene 2 rotulos
							comparacion=comp.compare(clave, n.getRotulo1());
							if (comparacion<0) {//clave < al rotulo1 de n
								retorna=buscarAux(n.getLeft(),clave);//busco en el hijo izquierdo de n
							}
							else {//clave >= al rotulo1 de n
								if (comparacion==0) {
									retorna=n;
								}
								else {//clave > rotulo1 de n
									comparacion=comp.compare(clave, n.getRotulo2());
									if (comparacion<0) {//clave < al rotulo2 de n
										retorna=buscarAux(n.getMedio(),clave);//busco en el hijo medio de n
									}
									else {
										if (comparacion==0) {
											retorna=n;
										}
										else {//clave > al rotulo2 de n
											retorna=buscarAux(n.getRight(),clave);//busco en el hijo derecho de n
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return retorna;
	}
	
	public void insertar(E rot) {
		insertarAux(raiz,rot);
	}
	
	private void insertarAux(NodoA23<E> n, E rot) {
		int comparacion;
		E aux;
		NodoA23<E> hijoIzq, hijoMedio, hijoDer;
		/*mostrar();
		System.out.println();*/
		System.out.println(n);
		if (n.getLeft()==null && n.getRight()==null) {//si n es hoja
			System.out.println("inserto: "+rot+" en "+n.getRotulo1());
			if (n.getRotulo1()==null) {//n no tiene rotulos (si no tiene routulo1, tampoco tiene rotulo2)
				n.setRotulo1(rot);
			}
			else {//n tiene rotulo1
				if (n.getRotulo2()==null) {//n no tiene rotulo2
					comparacion=comp.compare(rot, n.getRotulo1());
					if (comparacion<0) {//rot < al rotulo1 de n
						aux=n.getRotulo1();//cambias el rotulo1 al rotulo2, y ponemos a rot como rotulo 1
						n.setRotulo1(rot);
						n.setRotulo2(aux);
					}
					else {
						if (comparacion>0) {//rot > al rotulo1 de n
							n.setRotulo2(rot);//solo ponemos a rot como rotulo2
						}//si comparacion=0, no hacemos nada
					}
				}
				else {//n tiene los 2 rotulos	
					if (n==raiz) {//si n es la raiz
						partirRaiz(n,rot);
					}
					else {//n no es la raiz y tiene 2 claves
						padreCon2Rotulos(n,rot);
					}
				}
			}
		}
		else {//n no es una hoja
			if (n.getRotulo1()!=null && n.getRotulo2()==null) {//si n tiene rotulo1
				comparacion=comp.compare(rot, n.getRotulo1());
				if (comparacion<0) {//rot < rotulo1
					insertarAux(n.getLeft(),rot);//insertamos en su hijo izquierdo
				}
				else {
					if (comparacion>0) {//rot > rotulo1
						insertarAux(n.getRight(),rot);//insertamos en su hijo derecho
					}
				}
			}
			if (n.getRotulo1()!=null && n.getRotulo2()!=null) {//si n tiene rotulo1 y rotulo2
				comparacion=comp.compare(rot, n.getRotulo1());
				if (comparacion<0) {//rot < rotulo1
					insertarAux(n.getLeft(),rot);//insertamos en el hijo izquierda
				}
				else {
					if (comparacion>0) {//rot > rotulo1
						comparacion=comp.compare(rot, n.getRotulo2());
						if (comparacion<0) {//rot < rotulo2
							insertarAux(n.getMedio(),rot);//insertamos en el hijo medio
						}
						else {
							if (comparacion>0) {//rot > rotulo2
								insertarAux(n.getRight(),rot);
							}
						}
					}
				}
			}
		}
	}
	
	private void padreCon2Rotulos(NodoA23<E> n, E rot) {
		int comparacion,comparacion2;
		if (n.getParent().getRotulo2()==null) {//si el padre de n no tiene rotulo2
			comparacion=comp.compare(rot, n.getRotulo1());
			if (comparacion<0) {//rot < rotulo1
				if (n.getParent().getRight()==n) {//si n es el hijo derecho de su padre
					//rotulo1 sube al padre, rot pasa a ser el hijo medio del padre de n, y el rotulo2 de n pasa a ser su rotulo1
					System.out.println(n.getRotulo1());
						n.getParent().setRotulo2(n.getRotulo1());
						n.getParent().setMedio(new NodoA23<E>(rot));
						n.getParent().getMedio().setParent(n.getParent());
						if (n.getLeft()!=null) {
							n.getParent().getMedio().setLeft(new NodoA23<E>(n.getLeft().getRotulo1()));//el hijo izq de n pasa a ser hijo izq del nuevo hijo medio del padre de n
							if (n.getLeft().getRotulo2()!=null) {//si el hijo medio de n tiene rotulo2, se lo agregamos
								n.getParent().getMedio().getLeft().setRotulo2(n.getLeft().getRotulo2());
							}
							n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
							n.getParent().getMedio().setRight(new NodoA23<E>(n.getLeft().getRotulo2()));
							n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						}
						/*n.getParent().getMedio().setRight(new NodoA23<E>(n.getMedio().getRotulo1()));//el hijo medio de n pasa a ser hijo der del nuevo hijo medio del padre de n
						if (n.getMedio().getRotulo2()!=null) {//si el hijo der de n tiene rotulo2, se lo agregamos
							n.getParent().getMedio().getRight().setRotulo2(n.getMedio().getRotulo2());
						}
						n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());*/
						if (n.getMedio()!=null) {
							n.setLeft(new NodoA23<E>(n.getMedio().getRotulo1()));
							if (n.getMedio().getRotulo2()!=null) {
								n.getLeft().setRotulo2(n.getMedio().getRotulo2());
							}
							n.getLeft().setParent(n);
						}
						if (n.getRight()!=null) {
							n.setRight(new NodoA23<E>(n.getRight().getRotulo1()));
							if (n.getRight().getRotulo2()!=null) {
								n.getRight().setRotulo2(n.getRight().getRotulo2());
							}
							n.getRight().setParent(n);
						}
						n.setMedio(null);
						n.setRotulo1(n.getRotulo2());
						n.setRotulo2(null);
					}
					else {//n es el hijo izq de su padre
						n.getParent().setRotulo2(n.getParent().getRotulo1());//rotulo del padre pasa a ser su rotulo2
						n.getParent().setRotulo1(n.getRotulo1());//rotulo1 de n pasa a ser el rotulo1 de su padre
						n.getParent().setMedio(new NodoA23<E>(n.getRotulo2()));//rotulo2 pasa a ser el hijo medio del padre de n
						n.getParent().getMedio().setParent(n.getParent());
						if (n.getMedio()!=null) {
							n.getParent().getMedio().setLeft(new NodoA23<E>(n.getMedio().getRotulo1()));//el hijo medio de n pasa a ser hijo izq del nuevo hijo medio del padre de n
							if (n.getMedio().getRotulo2()!=null) {//si el hijo medio de n tiene rotulo2, se lo agregamos
								n.getParent().getMedio().getLeft().setRotulo2(n.getMedio().getRotulo2());
							}
							n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
						}
						if (n.getRight()!=null) {
							n.getParent().getMedio().setRight(new NodoA23<E>(n.getRight().getRotulo1()));//el hijo der de n pasa a ser hijo der del nuevo hijo medio del padre de n
							if (n.getRight().getRotulo2()!=null) {//si el hijo der de n tiene rotulo2, se lo agregamos
								n.getParent().getMedio().getRight().setRotulo2(n.getRight().getRotulo2());
							}
							n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						}
						n.setRotulo1(rot);//rot pasa a ser rotulo1 de n, hijo izq de su padre
						n.setRotulo2(null);
					}
			}
			else {//rot >=rotulo1
				comparacion=comp.compare(rot, n.getRotulo2());
				if (comparacion<0) {//rot < rotulo2
					if (n.getParent().getRight()==n) {//si n es el hijo derecho de su padre
						//rot sube al padre, rotulo1 pasa a ser el hijo medio del padre de n y el rotulo2 de n pasa a ser su rotulo1
						n.getParent().setRotulo2(rot);
						n.getParent().setMedio(new NodoA23<E>(n.getRotulo1()));
						n.getParent().getMedio().setParent(n.getParent());
						n.getParent().getMedio().setLeft(new NodoA23<E>(n.getLeft().getRotulo1()));
						if (n.getLeft().getRotulo2()!=null) {
							n.getParent().getMedio().getLeft().setRotulo2(n.getLeft().getRotulo2());
						}
						n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
						n.getParent().getMedio().setRight(new NodoA23<E>(n.getMedio().getRotulo1()));
						n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						n.setLeft(new NodoA23<E>(n.getMedio().getRotulo2()));
						n.getLeft().setParent(n);
						n.setMedio(null);	
						n.setRotulo1(n.getRotulo2());
						n.setRotulo2(null);
					}
					else {//n es el hijo izq de su padre
						n.getParent().setRotulo2(n.getParent().getRotulo1());//rotulo1 del padre pasa a ser su rotulo2
						n.getParent().setRotulo1(rot);//rot pasa a ser el rotulo1 de su padre
						n.getParent().setMedio(new NodoA23<E>(n.getRotulo2()));//rotulo2 pasa a ser el hijo medio del padre de n
						n.getParent().getMedio().setParent(n.getParent());
						if (n.getRight()!=null) {
							n.getParent().getMedio().setRight(new NodoA23<E>(n.getRight().getRotulo1()));
							if (n.getRight().getRotulo2()!=null) {//si el hijo der de n tiene rotulo2, se lo agregamos
								n.getParent().getMedio().getRight().setRotulo2(n.getRight().getRotulo2());
							}
							n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						}
						/*System.out.println(n.getRotulo1());
						n.getParent().getMedio().setLeft(new NodoA23<E>(n.getMedio().getRotulo2()));//rotulo2 del hijo medio de n pasa a ser hijo izq del hijo medio del padre de n
						n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
						n.getParent().getMedio().setRight(new NodoA23<E>(n.getRight().getRotulo1()));///rotulo1 del hijo derr de n pasa a ser hijo izq del hijo der del padre de n
						if (n.getRight().getRotulo2()!=null) {//si el hijo der de n tiene rotulo2, se lo agregamos
							n.getParent().getMedio().getRight().setRotulo2(n.getRight().getRotulo2());
						}
						n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());*/
						//n.setRotulo1(n.getRotulo1());//rotulo1 pasa a ser rotulo1 de n, hijo izq de su padre
						n.setRotulo2(null);
					}
				}
				else {//rot >= rotulo 2
					System.out.println(n.getRotulo1());
					System.out.println(n.getParent().getRight().getRotulo1());
					if (n.getParent().getRight()==n) {//si n es el hijo derecho de su padre
						//rotulo2 sube al padre, rotulo1 pasa a ser el hijo medio del padre de n, y rot pasa a ser rotulo1 de n
						n.getParent().setRotulo2(n.getRotulo2());
						n.getParent().setMedio(new NodoA23<E>(n.getRotulo1()));
						n.getParent().getMedio().setParent(n.getParent());
						if (n.getLeft()!=null) {
							n.getParent().getMedio().setLeft(new NodoA23<E>(n.getLeft().getRotulo1()));
							if (n.getLeft().getRotulo2()!=null) {
								n.getParent().getMedio().getLeft().setRotulo2(n.getLeft().getRotulo2());
							}
							n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
						}
						if (n.getMedio()!=null) {
							n.getParent().getMedio().setRight(new NodoA23<E>(n.getMedio().getRotulo1()));
							if (n.getMedio().getRotulo2()!=null) {
								n.getParent().getMedio().getRight().setRotulo2(n.getMedio().getRotulo2());
							}
							n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						}
						/*if (n.getRight()!=null) {
							n.setLeft(new NodoA23<E>(n.getRight().getRotulo1()));
							n.getLeft().setParent(n);
						}*/
						n.setRotulo1(rot);
						n.setRotulo2(null);
					}
					else {//n es el hijo izq de su padre
						/*n.setRight(new NodoA23<E>(n.getMedio().getRotulo1()));
						if (n.getMedio().getRotulo2()!=null) {
							n.getRight().setRotulo2(n.getMedio().getRotulo2());
						}
						n.getRight().setParent(n);
						n.setMedio(null);*/
						n.getParent().setRotulo2(n.getParent().getRotulo1());//rotulo1 del padre pasa a ser su rotulo2
						n.getParent().setRotulo1(n.getRotulo2());//rotulo2 de n pasa a ser el rotulo1 de su padre
						n.getParent().setMedio(new NodoA23<E>(rot));//rot pasa a ser el hijo medio del padre de n
						n.getParent().getMedio().setParent(n.getParent());
						n.getParent().getMedio().setLeft(new NodoA23<E>(n.getRight().getRotulo1()));
						n.getParent().getMedio().getLeft().setParent(n.getParent().getMedio());
						n.getParent().getMedio().setRight(new NodoA23<E>(n.getRight().getRotulo2()));
						n.getParent().getMedio().getRight().setParent(n.getParent().getMedio());
						/*n.getParent().setLeft(new NodoA23<E>(n.getRotulo1()));//rotulo1 pasa a ser el hijo izq del padre de n
						n.getParent().getLeft().setParent(n.getParent());*/
						//n.setRotulo1(n.getRotulo1());//rotulo1 pasa a ser rotulo1 de n, hijo izq de su padre
						n.setRotulo2(null);
						n.setRight(new NodoA23<E>(n.getMedio().getRotulo1()));
						if (n.getMedio().getRotulo2()!=null) {
							n.getRight().setRotulo2(n.getMedio().getRotulo2());
						}
						n.getRight().setParent(n);
						n.setMedio(null);
					}
				}
			}
		}
		else {//padre de n tiene 2 rotulos
			if (n.getParent()==raiz) {
				if (n.getParent().getLeft()==n) {//si n es el hijo izq de su padre
					comparacion=comp.compare(rot, n.getRotulo1());
					if (comparacion<0) {//rot < rotulo1
						hijoIzqDeRaiz(rot,n.getRotulo1(),n.getRotulo2());
					}
					else {//rot >= rotulo1
						System.out.println(n.getRotulo1());
						comparacion=comp.compare(rot, n.getRotulo2());
						if (comparacion<0) {//rot < rotulo2
							hijoIzqDeRaiz(n.getRotulo1(),rot,n.getRotulo2());
						}
						else {//rot >=rotulo2
							hijoIzqDeRaiz(n.getRotulo1(),n.getRotulo2(),rot);
						}
					}
				}
				else {
					if (n.getParent().getMedio()==n) {
						comparacion=comp.compare(rot, n.getRotulo1());
						if (comparacion<0) {//rot < rotulo1
							hijoMedioDeRaiz(rot,n.getRotulo1(),n.getRotulo2());
						}
						else {//rot >= rotulo1
							comparacion=comp.compare(rot, n.getRotulo2());
							if (comparacion<0) {//rot < rotulo2
								hijoMedioDeRaiz(n.getRotulo1(),rot,n.getRotulo2());
							}
							else {//rot >=rotulo2
								hijoMedioDeRaiz(n.getRotulo1(),n.getRotulo2(),rot);
							}
						}
					}
					else {//n es hijo der de su padre
						comparacion=comp.compare(rot, n.getRotulo1());
						if (comparacion<0) {//rot < rotulo1
							hijoDerDeRaiz(rot,n.getRotulo1(),n.getRotulo2());
						}
						else {//rot >= rotulo1
							comparacion=comp.compare(rot, n.getRotulo2());
							if (comparacion<0) {//rot < rotulo2
								hijoDerDeRaiz(n.getRotulo1(),rot,n.getRotulo2());
							}
							else {//rot >=rotulo2
								hijoDerDeRaiz(n.getRotulo1(),n.getRotulo2(),rot);
							}
						}
					}
				}
			}
			else {//el padre de n tiene los 2 rotulos y no es la raiz
				comparacion=comp.compare(rot, n.getRotulo1());//el rotulo del medio sube al padre
				if (comparacion < 0) {//rot < rotulo1
					System.out.println(rot);
					padreCon2Rotulos(n.getParent(),n.getRotulo1());//rotulo1 sube al padre
					if (n.getParent().getLeft()==n) {//si n es el hijo izq de su padre
						n.getParent().setLeft(new NodoA23<E>(rot));//rot pasa a ser hijo izq del padre de n
						n.getParent().getLeft().setParent(n.getParent());
						n.getParent().setRight(new NodoA23<E>(n.getRotulo2()));//rotulo2 pasa a ser hijo der del padre de n
						n.getParent().getRight().setParent(n.getParent());
						n.getParent().setMedio(null);//borramos el hijo medio del padre de n
					}
					else {
						if (n.getParent().getMedio()==n) {//si n es el hijo medio de su padre
							n.getParent().setRight(new NodoA23<E>(rot));
							n.getParent().getRight().setParent(n.getParent());
							n.getParent().setMedio(null);//borramos el hijo medio del padre de n
							n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(n.getRotulo2()));
							n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());			
						}
						else {//n es hijo der de su padre HACEEEEEEEEEEEEEER
							/*n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(rot));//rot pasa a ser hijo izq del padre de n
							n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());*/
							n.setRotulo1(n.getRotulo2());
							n.setRotulo2(null);
							n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(rot));
							n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());
							
							
							
							/*if (n.getParent().getMedio()!=null) {
								n.getParent().setRight(new NodoA23<E>(n.getParent().getMedio().getRotulo1()));
								if (n.getParent().getMedio().getRotulo2()!=null) {
									n.getParent().getRight().setRotulo2(n.getParent().getMedio().getRotulo2());
								}
								n.getParent().getRight().setParent(n.getParent());
							}*/
							/*n.getParent().setRight(new NodoA23<E>(n..getRotulo2()));//rotulo2 pasa a ser hijo der del padre de n
							n.getParent().getRight().setParent(n.getParent());*/
							n.getParent().setMedio(null);//borramos el hijo medio del padre de 
						}
					}
				}
				else {//rot >= rotulo1
					comparacion=comp.compare(rot, n.getRotulo2());//el rotulo del medio sube al padre
					if (comparacion < 0) {//rot < rotulo2
						padreCon2Rotulos(n.getParent(),rot);//rot sube al padre
						if (n.getParent().getLeft()==n) {//si n es el hijo izq de su padre
							n.getParent().setLeft(new NodoA23<E>(n.getRotulo1()));//rotulo1 pasa a ser hijo izq del padre de n
							n.getParent().getLeft().setParent(n.getParent());
							n.getParent().setRight(new NodoA23<E>(n.getRotulo2()));//rotulo2 pasa a ser hijo der del padre de n
							n.getParent().getRight().setParent(n.getParent());
							n.getParent().setMedio(null);//borramos el hijo medio del padre de n
						}
						else {
							if (n.getParent().getMedio()==n) {//si n es el hijo medio de su padre
								n.getParent().setRight(new NodoA23<E>(n.getRotulo1()));//rotulo1 pasa a ser hijo derecho del padre de n
								n.getParent().getRight().setParent(n.getParent());
								n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(n.getRotulo2()));
								n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());	
								n.getParent().setMedio(null);//borramos el hijo medio del padre de n
							}
							else {//n es hijo der de su padre
								/*n.getParent().setLeft(new NodoA23<E>(n.getRotulo1()));
								n.getParent().getLeft().setParent(n.getParent());;
								n.setRotulo1(n.getRotulo2());
								n.setRotulo2(null);*///SIRVEEEEE
								
								
								/*n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(n.getRotulo1()));//rot pasa a ser hijo izq del padre de n
								n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());
								if (n.getParent().getMedio()!=null) {
									n.getParent().setRight(new NodoA23<E>(n.getParent().getMedio().getRotulo1()));
									if (n.getParent().getMedio().getRotulo2()!=null) {
										n.getParent().getRight().setRotulo2(n.getParent().getMedio().getRotulo2());
									}
									n.getParent().getRight().setParent(n.getParent());
								}*/
								n.getParent().setMedio(null);//borramos el hijo medio del padre de
							}
						}
					}
					else {//rot >= rotulo2
						padreCon2Rotulos(n.getParent(),n.getRotulo2());//rotulo2 sube al padre
						if (n.getParent().getLeft()==n) {//si n es el hijo izq de su padre
							n.getParent().setLeft(new NodoA23<E>(n.getRotulo1()));//rotulo1 pasa a ser hijo izq del padre de n
							n.getParent().getLeft().setParent(n.getParent());
							n.getParent().setRight(new NodoA23<E>(rot));//rot pasa a ser hijo der del padre de n
							n.getParent().getRight().setParent(n.getParent());
							n.getParent().setMedio(null);//borramos el hijo medio del padre de n
						}
						else {
							if (n.getParent().getMedio()==n) {//si n es el hijo izq de su padre
								n.getParent().setRight(new NodoA23<E>(n.getRotulo1()));//rotulo1 pasa a ser hijo derecho del padre de n
								n.getParent().getRight().setParent(n.getParent());
								n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(rot));
								n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());	
								n.getParent().setMedio(null);//borramos el hijo medio del padre de n
							}
							else {//n es hijo der de su padre
								/*n.getParent().getParent().getMedio().setLeft(new NodoA23<E>(n.getRotulo1()));//rot pasa a ser hijo izq del padre de n
								n.getParent().getParent().getMedio().getLeft().setParent(n.getParent().getParent().getMedio());*/
								/*n.getParent().getParent().getMedio().setRight(new NodoA23<E>(rot));
								n.getParent().getParent().getMedio().getRight().setParent(n.getParent().getParent().getMedio());*/
								
								
								/*if (n.getParent().getMedio()!=null) {
									n.getParent().setRight(new NodoA23<E>(n.getParent().getMedio().getRotulo1()));
									if (n.getParent().getMedio().getRotulo2()!=null) {
										n.getParent().getRight().setRotulo2(n.getParent().getMedio().getRotulo2());
									}
									n.getParent().getRight().setParent(n.getParent());
								}*/
								//N ES HIJO MEDIO DE SU PADRE
								/*System.out.println("medio: "+n.getParent().getMedio());
								n.getParent().setLeft(new NodoA23<E>(rot));
								n.getParent().getLeft().setParent(n.getParent());
								n.getParent().setMedio(null);//borramos el hijo medio del padre de*/
								n.getParent().setLeft(new NodoA23<E>(n.getRotulo1()));
								n.getParent().getLeft().setParent(n.getParent());
								n.getParent().setRight(new NodoA23<E>(rot));
								n.getParent().getRight().setParent(n.getParent());
								n.getParent().setMedio(null);
								/*n.getParent().setRight(new NodoA23<E>(n.getParent().getMedio().getRotulo1()));
								if (n.getParent().getMedio().getRotulo2()!=null) {
									n.getParent().getRight().setRotulo2(n.getParent().getMedio().getRotulo2());
								}
								n.getParent().getRight().setParent(n.getParent());
								n.getParent().setMedio(null);//borramos el hijo medio del padre de n
								n.getParent().getParent().getMedio().setRight(new NodoA23<E>(rot));
								n.getParent().getParent().getMedio().getRight().setParent((n.getParent().getParent().getMedio()));*/
							}
						}
					}
				}
			}
		}
	}
	
	private void hijoIzqDeRaiz(E r1, E r2, E r3) {//r1 < r2 < r3
		NodoA23<E> hijoMedio=raiz.getMedio();
		NodoA23<E> hijoDer=raiz.getRight();
		partirRaiz(raiz,r2);//r2 sube a la raiz
		raiz.getLeft().setLeft(new NodoA23<E>(r1));//r1 pasa a ser hijo izq del nuevo hijo izq de la raiz
		raiz.getLeft().getLeft().setParent(raiz.getLeft());
		raiz.getLeft().setRight(new NodoA23<E>(r3));//r3 pasa a ser hijo der del nuevo hijo izq de la raiz
		raiz.getLeft().getRight().setParent(raiz.getLeft());
		raiz.getRight().setLeft(new NodoA23<E>(hijoMedio.getRotulo1()));//el antiguo hijo medio de la raiz pasa a ser hijo izq del nuevo hijo der de la raiz
		if (hijoMedio.getRotulo2()!=null) {//si hijoMedio tiene rotulo2, se lo agregamos
			raiz.getRight().getLeft().setRotulo2(hijoMedio.getRotulo2());
		}
		raiz.getRight().getLeft().setParent(raiz.getRight());
		raiz.getRight().setRight(new NodoA23<E>(hijoDer.getRotulo1()));//el antiguo hijo der de la raiz pasa a ser hijo der del nuevo hijo der de la raiz
		if (hijoDer.getRotulo2()!=null) {//si hijoDer tiene rotulo2, se lo agregamos
			raiz.getRight().getRight().setRotulo2(hijoDer.getRotulo2());
		}
		raiz.getRight().getRight().setParent(raiz.getRight());
		raiz.setMedio(null);//borramos el hijo medio de la raiz
	}
	
	private void hijoMedioDeRaiz(E r1, E r2, E r3) {//r1 < r2 < r3
		NodoA23<E> hijoIzq=raiz.getLeft();
		NodoA23<E>  hijoDer=raiz.getRight();
		partirRaiz(raiz,r2);//r2 sube a la raiz
		raiz.getLeft().setLeft(new NodoA23<E>(hijoIzq.getRotulo1()));//el antiguo hijoIzq de la raiz pasa a ser hijo izq del nuevo hijo izq de la raiz
		if (hijoIzq.getRotulo2()!=null) {//si hijoIzq tiene rotulo2, se lo agregamos
			raiz.getLeft().getLeft().setRotulo2(hijoIzq.getRotulo2());
		}
		raiz.getLeft().getLeft().setParent(raiz.getLeft());
		raiz.getLeft().setRight(new NodoA23<E>(r1));//r1 pasa a ser hijo der del nuevo hijo izq de la raiz
		raiz.getLeft().getRight().setParent(raiz.getLeft());
		raiz.getRight().setLeft(new NodoA23<E>(r3));//r3 pasa a ser hijo izq del nuevo hijo der de la raiz
		raiz.getRight().getLeft().setParent(raiz.getRight());
		raiz.getRight().setRight(new NodoA23<E>(hijoDer.getRotulo1()));//el antiguo hijo der de la raiz pasa a ser hijo der del nuevo hijo der de la raiz
		if (hijoDer.getRotulo2()!=null) {//si hijoDer tiene rotulo2, se lo agregamos
			raiz.getRight().getRight().setRotulo2(hijoDer.getRotulo2());
		}
		raiz.getRight().getRight().setParent(raiz.getRight());
		raiz.setMedio(null);//borramos el hijo medio de la raiz
	}
	
	private void hijoDerDeRaiz(E r1, E r2, E r3) {
		NodoA23<E> hijoIzq=raiz.getLeft();
		NodoA23<E> hijoMedio=raiz.getMedio();
		partirRaiz(raiz,r2);//r2 sube a la raiz
		raiz.getLeft().setLeft(new NodoA23<E>(hijoIzq.getRotulo1()));//el antiguo hijoIzq de la raiz pasa a ser hijo izq del nuevo hijo izq de la raiz
		if (hijoIzq.getRotulo2()!=null) {//si hijoIzq tiene rotulo2, se lo agregamos
			raiz.getLeft().getLeft().setRotulo2(hijoIzq.getRotulo2());
		}
		raiz.getLeft().getLeft().setParent(raiz.getLeft());
		raiz.getLeft().setRight(new NodoA23<E>(hijoMedio.getRotulo1()));//el antiguo hijomedio de la raiz pasa a ser hijo der del nuevo hijo izq de la raiz
		if (hijoMedio.getRotulo2()!=null) {//si hijoMedio tiene rotulo2, se lo agregamos
			raiz.getLeft().getRight().setRotulo2(hijoMedio.getRotulo2());
		}
		raiz.getLeft().getRight().setParent(raiz.getLeft());
		raiz.getRight().setLeft(new NodoA23<E>(r1));//r1 pasa a ser hijoizq del nuevo hijo der de la raiz
		raiz.getRight().getLeft().setParent(raiz.getRight());
		raiz.getRight().setRight(new NodoA23<E>(r3));//r3 pasa a ser hijo der del nuevo hijo der de la raiz
		raiz.getRight().getRight().setParent(raiz.getRight());
		raiz.setMedio(null);//borramos el hijo medio de la raiz
	}
	private void partirRaiz(NodoA23<E> n, E rot) {
		int comparacion;
		comparacion=comp.compare(rot, n.getRotulo1());
		if (comparacion<0) {//rot < rotulo1
			//el valor del medio pasa a ser la raiz, el menor pasa como hi de la raiz y el mayor como hd de la raiz
			n.setLeft(new NodoA23<E>(rot));
			n.getLeft().setParent(n);
			n.setRight(new NodoA23<E>(n.getRotulo2()));
			n.getRight().setParent(n);
			n.setRotulo1(n.getRotulo1());
			n.setRotulo2(null);
		}
		else {
			if (comparacion>0) {//rot > rotulo1
				comparacion=comp.compare(rot, n.getRotulo2());
				if (comparacion<0) {//rot < rotulo2
					//el valor del medio pasa a ser la raiz, el menor pasa como hi de la raiz y el mayor como hd de la raiz
					n.setLeft(new NodoA23<E>(n.getRotulo1()));
					n.getLeft().setParent(n);
					n.setRight(new NodoA23<E>(n.getRotulo2()));
					n.getRight().setParent(n);
					n.setRotulo1(rot);//la raiz tiene a rot como rotulo1
					n.setRotulo2(null);
				}
				else {
					if (comparacion>0) {//rot > rotulo2
						//el valor del medio pasa a ser la raiz, el menor pasa como hi de la raiz y el mayor como hd de la raiz
						
						n.setLeft(new NodoA23<E>(n.getRotulo1()));
						n.getLeft().setParent(n);
						n.setRight(new NodoA23<E>(rot));
						n.getRight().setParent(n);
						n.setRotulo1(n.getRotulo2());//la raiz tiene a rot como rotulo1
						n.setRotulo2(null);
					}
				}
			}	
		}
	}
	
	public void mostrar() {
		if (raiz.getRotulo1()!=null) {
			System.out.print("("+raiz.getRotulo1());
			if (raiz.getRotulo2()!=null) {
				System.out.print("|"+raiz.getRotulo2());
			}
			System.out.print(")");
			mostrarAux(raiz);
		}
	}
	
	private void mostrarAux(NodoA23<E> n) {
		System.out.println();
		if (n.getLeft()!=null && n.getLeft().getRotulo1()!=null) {
			System.out.print("("+n.getLeft().getRotulo1());
			if (n.getLeft().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getMedio()!=null && n.getMedio().getRotulo1()!=null) {
			System.out.print(" ("+n.getMedio().getRotulo1());
			if (n.getMedio().getRotulo2()!=null) {
				System.out.print("|"+n.getMedio().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getRight()!=null && n.getRight().getRotulo1()!=null) {
			System.out.print(" ("+n.getRight().getRotulo1());
			if (n.getRight().getRotulo2()!=null) {
				System.out.print("|"+n.getRight().getRotulo2());
			}
			System.out.print(")");
		}
		////////////////
		System.out.println();
		if (n.getLeft()!=null && n.getLeft().getLeft()!=null && n.getLeft().getLeft().getRotulo1()!=null) {
			System.out.print("("+n.getLeft().getLeft().getRotulo1());
			if (n.getLeft().getLeft().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getLeft().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getLeft()!=null && n.getLeft().getMedio()!=null && n.getLeft().getMedio().getRotulo1()!=null) {
			System.out.print(" ("+n.getLeft().getMedio().getRotulo1());
			if (n.getLeft().getMedio().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getMedio().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getLeft()!=null && n.getLeft().getRight()!=null && n.getLeft().getRight().getRotulo1()!=null) {
			System.out.print(" ("+n.getLeft().getRight().getRotulo1());
			if (n.getLeft().getRight().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getRight().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getMedio()!=null && n.getMedio().getLeft()!=null && n.getMedio().getLeft().getRotulo1()!=null) {
			System.out.print(" ("+n.getMedio().getLeft().getRotulo1());
			if (n.getMedio().getLeft().getRotulo2()!=null) {
				System.out.print("|"+n.getMedio().getLeft().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getMedio()!=null && n.getMedio().getMedio()!=null && n.getMedio().getMedio().getRotulo1()!=null) {
			System.out.print(" ("+n.getMedio().getMedio().getRotulo1());
			if (n.getMedio().getMedio().getRotulo2()!=null) {
				System.out.print("|"+n.getMedio().getMedio().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getMedio()!=null && n.getMedio().getRight()!=null && n.getMedio().getRight().getRotulo1()!=null) {
			System.out.print(" ("+n.getMedio().getRight().getRotulo1());
			if (n.getMedio().getRight().getRotulo2()!=null) {
				System.out.print("|"+n.getMedio().getRight().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getRight()!=null && n.getRight().getLeft()!=null && n.getRight().getLeft().getRotulo1()!=null) {
			System.out.print(" ("+n.getRight().getLeft().getRotulo1());
			if (n.getRight().getLeft().getRotulo2()!=null) {
				System.out.print("|"+n.getRight().getLeft().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getRight()!=null && n.getRight().getMedio()!=null && n.getRight().getMedio().getRotulo1()!=null) {
			System.out.print(" ("+n.getRight().getMedio().getRotulo1());
			if (n.getRight().getMedio().getRotulo2()!=null) {
				System.out.print("|"+n.getRight().getMedio().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getRight()!=null && n.getRight().getRight()!=null && n.getRight().getRight().getRotulo1()!=null) {
			System.out.print(" ("+n.getRight().getRight().getRotulo1());
			if (n.getRight().getRight().getRotulo2()!=null) {
				System.out.print("|"+n.getRight().getRight().getRotulo2());
			}
			System.out.print(")");
		}
		////////////////
		System.out.println();
		if (n.getLeft().getLeft()!=null && n.getLeft().getLeft().getLeft()!=null && n.getLeft().getLeft().getLeft().getRotulo1()!=null) {
			System.out.print("("+n.getLeft().getLeft().getLeft().getRotulo1());
			if (n.getLeft().getLeft().getLeft().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getLeft().getLeft().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getLeft().getLeft()!=null && n.getLeft().getLeft().getMedio()!=null && n.getLeft().getLeft().getMedio().getRotulo1()!=null) {
			System.out.print(" ("+n.getLeft().getLeft().getMedio().getRotulo1());
			if (n.getLeft().getLeft().getMedio().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getLeft().getMedio().getRotulo2());
			}
			System.out.print(")");
		}
		if (n.getLeft().getLeft()!=null && n.getLeft().getLeft().getRight()!=null && n.getLeft().getLeft().getRight().getRotulo1()!=null) {
			System.out.print(" ("+n.getLeft().getLeft().getRight().getRotulo1());
			if (n.getLeft().getLeft().getRight().getRotulo2()!=null) {
				System.out.print("|"+n.getLeft().getLeft().getRight().getRotulo2());
			}
			System.out.print(")");
		}

		
	}
	
	
	
}
