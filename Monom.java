
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	
	private double _coefficient; 
	private int _power;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();

	private static Monom getNewZeroMonom() {
		return new Monom(ZERO);
	}
	public static Comparator<Monom> getComp() {
		return _Comp;
	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************
	public Monom(String s) {
		if(s.length()>0){
			if (s.contains("x")){
				//Coefficient
				String coeff = s.substring(0,s.indexOf("x"));
				double coeffD = Double.parseDouble(coeff);
				this.set_coefficient(coeffD);
				//Power
				if(s.contains("^")){
					int indexP = s.indexOf("^");
					Double p = Double.parseDouble(s.substring(indexP));
				}
				else{
					this.set_power(1);
				}

			}
			else{
				double sDouble = Double.parseDouble(s);
				this.set_coefficient(sDouble);
				this.set_power(0);
			}
			 
			
		}
		
	}
	public static void main(String[] args) {
		String s = "2x^2";
		Monom m = new Monom (s);
		System.out.println(m.get_coefficient());
		System.out.println(m.get_power());
	}
		
		//* check s legth above 1 char
		//* check if the s contain X else take the stirng and pare_duble
		//  and put pow <= 0
		//* run on string and get the coeff till you reach the x
	   //  get ^ index in the s and put the next chars in pow
		
	
	public void add(Monom m) {;}
	
	public void multipy(Monom d) {;}
	
	public String toString() {
		String ans = "";
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	
	
	
	
}
