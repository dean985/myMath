package myMath;


public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		test4();//polynom add test
		//test5();

	}
	/**
	 * tester for the add(polynom_able) 
	 */
	public static void test4() {
		
		String[] googd = {"x^2+x","5x^3+2x^2+4"};
		
		Polynom p2 = new Polynom("x^2+x");

		//System.out.println(p2.toString());
		for (String string : googd) {

			//Polynom p = new Polynom("2x^2+4x+1");
			//System.out.println(p2.toString());
			for (String string1 : googd) 
			{
				System.out.print(p2.toString()+" +  ");
				Polynom p1 = new Polynom(string1);
				System.out.print(p1 +" = ");
				try {
					p2.add(p1);
				System.out.println(p2.toString());
				} catch (Exception e) {
					System.out.println("faild to calculate");
				}
				
			}
		
		}
			
	}
	/**
	 * tester for checking the normalecy of input
	 */
	public static void test1() {

		String[] good_polynoms = {"2", "x", "x^3","x^2+1","4x+x^3+1"};
		String[] bad_polynoms = {"(1)","x","x^2", "0.5x^2"};

		for (String string : good_polynoms) 	
		{
			System.out.print(string + " => ");
			try {
				Polynom p1 = new Polynom(string);
				System.out.println(p1.toString());
			} 
			catch (Exception e) 
			{
				System.out.println("wrong input");

			}

			

		
		}
		
	}
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 = Polynom.parse(s1);
		System.out.println("from string: "+pp1);
	}
	public static void test3(){
		Polynom p = new Polynom("2x^2+x");
		//System.out.println(p1.polynom_list.get(0).toString());
		//Polynom p = new Polynom();
	}	
	/**
	 * Test for toString in polynom 
	 * @param null
	 * 
	 */
	public static void test5(){
		//testing toString method for polynom
		Polynom m = new Polynom("3x^3+2x^2+x+1");
		System.out.println(m.toString());
		
	}
	/**
	 * Test for add(Polynom_able)
	 */
	public static void test6(){
		Polynom_able p1 = new Polynom("3x^2+2x");
		Polynom p2 = new Polynom("4x^2+4x");
		Polynom p3 = new Polynom("x^3");

		System.out.println(p1.add(p2).toString);
	}
}
