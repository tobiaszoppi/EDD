package TDAColaCP;

import java.util.Comparator;

public class ComparadorDescendente<Integer extends Comparable<Integer>> implements Comparator<Integer> {
	
	public int compare(Integer o1, Integer o2) {
		return -o1.compareTo(o2);//negativo al DefaultComparator. Retorna 1 si o2>o1 y -1 si o2<o1
	}
}
