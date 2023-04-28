package TDAColaCP;

public class tester {

	public static void main(String[] args) {
	try {
		ColaCPConListaOrdenada<Integer,String> c=new ColaCPConListaOrdenada<Integer,String>();
		c.insert(4,"4");
		c.insert(1,"1");
		c.insert(5,"5");
		c.insert(3,"3");
		c.insert(6,"6");
		c.insert(2,"2");
		c.insert(6,"7");
		System.out.println(c.toString());
		for (int i=0;i<3;i++) {
		System.out.println(c.removeMin());
		System.out.println(c.toString());
		}
	}
	catch (InvalidKeyException e) {
		e.printStackTrace();
	}
	catch (EmptyPriorityQueueException e) {
		e.printStackTrace();
	}
	
	}


}
