package TDAColaCP;

/**
 * Define los datos y operaciones aplicables sobre una entrada.
 * @author Juan Ignacio Sánchez.
 * @param <K> Tipo de dato a almacenar en la clave de la entrada.
 * @param <V> Tipo de dato a almacenar en el valor de la entrada.
 */
public class Entrada<K,V> implements Entry<K,V> {
	
	/**
	 * Clave de la entrada.
	 */
	private K clave;
	
	/**
	 * Valor de la entrada.
	 */
	private V valor;
	
	/**
	 * Inicializa una entrada con clave y valor.
	 * @param clave Clave a establecer como la clave de la entrada.
	 * @param valor Valor a establecer como el valor de la entrada.
	 */
	public Entrada (K clave,V valor) {
		this.clave=clave;
		this.valor=valor;
	}
	
	@Override
	public K getKey() {
		return clave;
	}
	
	@Override
	public V getValue() {
		return valor;
	}
	
	/**
	 * Establece la clave del parámetro en ela clave de la entrada actual.
	 * @param clave CLave a establecer en la clave de la entrada actual.
	 */
	public void setKey (K clave) {
		this.clave=clave;
	}
	
	/**
	 * Establece el valor del parámetro en el valor de la entrada actual.
	 * @param valor Valor a establecer en el valor de la entrada actual.
	 */
	public void setValue (V valor) {
		this.valor=valor;
	}
	
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
	
	/*public boolean equals(Entrada<K,V> e) {
		return clave==e.getKey() && valor==e.getValue(); 		
	}*/
}