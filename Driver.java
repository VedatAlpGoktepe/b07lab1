public class Driver { 
	public static void main(String [] args) { 
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int [] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-2,-9};
		int [] e2 = {1,4};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		} else {
			System.out.println("1 is not a root of s");
		}
		
		double[] c3 = {1};
		int[] e3 = {1};
		Polynomial p3 = new Polynomial(c3, e3);
		double[] c4 = {1,1,1};
		int[] e4 = {1,2,3};
		Polynomial p4 =  new Polynomial(c4, e4);
		Polynomial m = p3.multiply(p4);
		System.out.println("m(2): " + m.evaluate(2));
		System.out.println("m(3): " + m.evaluate(3));
		m.saveTofIle("Hello");
		
		double[] c5 = {-1,-1,-1};
		int[] e5 = {2,3,4};
		Polynomial p5 = new Polynomial(c5, e5);
		Polynomial m2 = m.add(p5);
		m2.saveTofIle("Hello2");
		
		double[] c6 = {0, 0, 1};
		int[] e6 = {1,2,3};
		Polynomial p6 = new Polynomial(c6, e6);
		p6.saveTofIle("Hello3");
	}
}
