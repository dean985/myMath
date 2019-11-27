package ex1;

public class PolynomTest {
	public static void main(String[] args)
	{
		//test1();
		//test2();
		//test3();
		//test4();//polynom add test
		//test5();
		//test6(); // test for method for polynom add(Monom m)
		//test7();
		//test8();
		//test9();//subb function
		//test10();
		//test11();
		//test12();

	}
	
/**
 * Test for method root
 */
 
 public static void test12(){
	String[] arr = {"-x^3+x^2+x+4","x^2+2x-1", "x^5-x","x^5-x+1","-x^6+3x+100","x+5","5"};
	int low = 10;
	int high = -10;
	
	for(String s: arr){
		Polynom p = new Polynom(s);
		try {
			if( p.root(low, high, Monom.EPSILON) == Double.MAX_VALUE ){
				System.out.println("("+ p.toString()+")  ------no roots in interval");
				System.out.println();
			}
			else{
				System.out.println("("+ p.toString()+")"+ "       Root: "+ p.root(low, high, Monom.EPSILON));
			}
		}
		catch (Exception e ){
			System.out.println("("+ p.toString()+")"+" "+ e.getMessage());
			continue;
		}
	}
	

}

	/**
	 * tester for the area
	 */
public static void test11(){
		String[] googd = {"x^2","x","5x^3+2x^2+4"};	
		double[] ans = {1000/3,50,39620/3};	
		
		//Polynom m1 = new Polynom("2");
	
		//System.out.println(p2.toString());
		int i = 0;
		for (String string : googd) {
			
			Polynom p = new Polynom(string);

					System.out.println(p.toString() +" => " + p.area(0, 10, Monom.EPSILON) + " the right ans: "+ ans[i]);
			i++;
		}
		
	}


/**
 * tester for the equle()
 */
public static void test10(){
	String[] googd = {"x^2+x","2","5x^3+2x^2+4"};
	String[] seco = {"x^2+x"," 2","5x^3+2x^2+4"};

	Polynom p1 = new Polynom("2-2+2-2+2");
	Polynom p3 = new Polynom("2");
	System.out.println(p1.equals(p3)+"\n\n");
	//System.out.println(p2.toString());
	
	for (int i = 0; i < seco.length; i++) {
			Polynom p = new Polynom(googd[i]);
			Polynom p2 = new Polynom(seco[i]);
			System.out.println(p.equals(p2)+" ");
	}
	
}




/**
 * tester for the Subbstruct()
 */
	public static void test9(){
		String[] googd = {"x^2+x","2","5x^3+2x^2+4"};
		//String[] seco = {"x^2+x","2","5x^3+2x^2+4"};
		//Polynom m1 = new Polynom("2");

		//System.out.println(p2.toString());
		for (String string : googd) {

			Polynom p = new Polynom(string);
			//System.out.println(p2.toString());
			for (String string1 : googd) 
			{
				Polynom p1 = new Polynom(string1);

				System.out.print(p1.toString()+" -  ");
				System.out.print("( "+p.toString() +" ) = ");
				try {
					p1.substract(p);
				System.out.println(p1.toString());
				} catch (Exception e) {
					System.out.println("faild to calculate error log: "+ e.getLocalizedMessage()+"  "+e.getMessage());
				}
				
			}
		
		}
	}

/**
 * general test - (dean)
 * several monoms and polynom ready to play with 
 */
	public static void test8(){
		Monom m1 = new Monom ("x^2");
		Monom m2 = new Monom("4");
		Monom m3 = new Monom("-1");
		Polynom p = new Polynom("x^2+x+1");
		Polynom p2 = new Polynom("x");
		Polynom p3 = new Polynom("4x^5+3x^2+2");
		
		//System.out.println(p.toString()+" - "+p.isZero());
		//System.out.println(p2.toString()+" - "+p2.isZero());
		//System.out.println(p3.toString()+" - "+ p3.isZero());
		//Polynom_able p55 = p3.derivative();
		//System.out.println(p55.toString());
		//System.out.println(p2.equals(p3));
		Polynom pp = new Polynom("x^2+-x^2+1");
		Polynom pp1 = new Polynom("x^2-x^2+1");
		System.out.println(pp.toString());
		System.out.println(pp1.toString());
		
	}
	public static void test7() {
		
		String[] googd = {"x^2+x","5x^3+2x^2+4"};
		
		

		//System.out.println(p2.toString());
		for (String string : googd) {

			//Polynom p = new Polynom("2x^2+4x+1");
			//System.out.println(p2.toString());
			for (String string1 : googd) 
			{
				Polynom p2 = new Polynom("x^2+x");
				System.out.print("("+p2.toString()+") *  (");
				Polynom p1 = new Polynom(string1);
				System.out.print(p1 +") = ");
				try {
					p2.multiply(p1);
				System.out.println(p2.toString());
				} catch (Exception e) {
					System.out.println("faild to calculate");
				}
				
			}
		
		}
			
	}
	
	/**
	 * test for add (monom m)
	 */
	public static void test6(){
		String[] googd = {"x^2+x","5x^3+2x^2+4"};
		
		Monom m1 = new Monom("x^2");

		//System.out.println(p2.toString());
		for (String string : googd) {

			//Polynom p = new Polynom("2x^2+4x+1");
			//System.out.println(p2.toString());
			for (String string1 : googd) 
			{
				System.out.print(m1.toString()+" +  ");
				Polynom p1 = new Polynom(string1);
				System.out.print(p1 +" = ");
				try {
					p1.add(m1);
				System.out.println(p1.toString());
				} catch (Exception e) {
					System.out.println("faild to calculate");
				}
				
			}
		
		}
	}
	/**
	 * tester for the add(polynom_able) 
	 */

	public static void test4() {
		
		String[] googd = {"2","5x^3+2x^2+4"};
		
		Polynom p2 = new Polynom("-2");

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
					p1.add(p2);
				System.out.println(p1.toString());
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

		String[] good_polynoms = {"2 - 2", "5-x", "x^3","x^2-x^2+1","4x+x^3+1","3x^2+1x+4x^2+4+2"};
		String[] bad_polynoms = {"(1)","x+d^4","(x^2+x)+2", "0.5x^2"};

		System.out.println("\t   test 1");
		System.out.println("\t***************\n");

		System.out.println("good input test");
		System.out.println("====================");
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

		System.out.println("\nWrong input test");
		System.out.println("====================");

		for (String string : bad_polynoms) 	
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
		Polynom_able pp1 = new Polynom(s1);
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
	
}