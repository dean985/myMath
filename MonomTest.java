package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    	isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    	isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****  <br>
0) 0    	isZero: true  	eq: true  <br>
1) -1.0    	isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
*/
public class MonomTest {
	public static void main(String[] args) {
		test1();
		// test2();
		//test3();
		//test4();
		//test5();
		//test6();
	}
	private static void test6(){
		String st = "x";
		Monom m = new Monom (st);
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2", "x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			try {
				Monom m = new Monom(monoms[i]);
				String s = m.toString();
				m = new Monom(s);
				double fx = m.f(i);
				System.out.println(i+") "+m +" \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
	
			} catch (Exception e) {
				System.out.println(monoms[i] +" crashed");
			}
			
				}
	}
	private static void test3()
	{
		//test for method add
		System.out.println("*****  Test3:  *****");
		String[] monoms1 = {"2", "-x","-3.2x^2","0"};
		String[] monoms2 = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms1.length;i++) 
		{
			Monom m1 = new Monom(monoms1[i]);
			for (int j = 0; j < monoms2.length; j++) 
			{
				Monom temp = new Monom(m1);
				Monom m2 = new Monom(monoms2[j]);
				System.out.print(i+") "+ temp );	
				
				try {

				temp.add(m2);
					System.out.print(" +"+m2+"= "+temp+"\n");

				} catch (Exception e) {
					//TODO: handle exception
					System.out.print(" +"+m2+" = unable to solve\n");
				}
				
		
			}
		}
	}
	private static void test4(){
		//test for multiply method
		System.out.println("*****  Test4:  *****");
		String[] monoms1 = {"2", "-x","-3.2x^2","0"};
		String[] monoms2 = {"2", "-x","-3.2x^2","0"};
		for(int i=0;i<monoms1.length;i++) 
		{
			Monom m1 = new Monom(monoms1[i]);
			for (int j = 0; j < monoms2.length; j++) 
			{
				Monom temp = new Monom(m1);
				Monom m2 = new Monom(monoms2[j]);
				System.out.print(i+") "+ temp );
				temp.multiply(m2);
				System.out.print(" *"+m2+"= "+temp+"\n");
			}
		}
	}
	private static void test5(){
		String[] arr = {"2.2x^-1", "-x^2","(2*3)*x^3","-3*x^3", "-3*x^(3+1)","4ax^6","2x^2" };
		for(int i= 0 ; i<arr.length; i++){
			System.out.print("\n"+ arr[i]+" ");
			try{	
				Monom temp = new Monom(arr[i]);
			}
			catch(Exception e){
				System.out.println("ERROR");
			}
		}
			
		}
	
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,0));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
}
