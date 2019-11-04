
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
	/**
	 * Return the value of the monom at a specific value of x
	 * @param x
	 * @return result (double)
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	
	public boolean isZero() {
		boolean iszero = this.get_coefficient() == 0;
		if(iszero)
			this.set_power(0);
		return iszero;
	}
	// ***************** add your code below **********************
	/**
	 * A method to proccess human readable version of monom (represented as a String)
	 * to an instance of the monom object.
	 * @param s
	 */
	public Monom(String s) {
		double coeffD;
		if(s.length()>0){
			if (s.contains("x")){
				//Coefficient
				String coeff = s.substring(0,s.indexOf("x"));
				if(s.charAt(0) == 'x' )
					coeffD = 1.0;
				if (s.charAt(0) == '-' && s.charAt(1) == 'x')
					coeffD = -1.0;
				else
					coeffD = Double.parseDouble(coeff);
					
				this.set_coefficient(coeffD);
				//Power
				if(s.contains("^")){
					int indexP = s.indexOf("^");
					//Double p = Double.parseDouble(s.substring(indexP+1));
					int p = Integer.parseInt(s.substring(indexP+1));
					this.set_power(p);
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
	/**
	 * A method to determine whether two monoms are equal
	 * The coefficient comparison is being applies using EPSILON. Two coefficients are considered equal
	 * when their difference is less or equal to EPSILON
	 * @param m
	 * @return true if monoms are equal , false otherwise.
	 */
	public boolean equals(Monom m){
		if(m.isZero() && this.isZero()){
			return true;
		}
		double difference = this.get_coefficient() - m.get_coefficient(); // this variable represents the substraction of both coefficients
																		  // if the difference is smaller than EPSILON (as defined above)
																		  // then the coeffiencts are equal
		if (difference <=EPSILON 
			&& this.get_power() == m.get_power()){
			return true;
		}
		return false;
		
	}
	public static void main(String[] args) {
		String s = "5x^3";
		Monom m = new Monom (s);
		//System.out.println(m.get_coefficient());
		//System.out.println(m.derivative());
		Monom m1 = new Monom(-3, -2);
		m1.multipy(m);
		System.out.println(m1.toString());
	}
		
		//* check s legth above 1 char
		//* check if the s contain X else take the stirng and pare_duble
		//  and put pow <= 0
		//* run on string and get the coeff till you reach the x
	   //  get ^ index in the s and put the next chars in pow
		
	/**
	 * add the recived monom to the current monom
	 * if the power is diffrent throw exaption
	 * @param m - the recived monom
	 */
	public void add(Monom m) 
	{
		
		if (m.get_power() == this.get_power())
		{
			this.set_coefficient(this.get_coefficient()+ m.get_coefficient());//sum the current coefficent
			
			
		}
		else if(this.isZero())
		{	
				this.set_coefficient(m.get_coefficient()); 
				this.set_power(m.get_power());
		}
		else if (m.isZero()){
			return;
		}
		else
			throw new RuntimeException("Monom Error - Unabled to sum the monom");

	}

	
	/**
	 * Multiply two monoms. coefficient and power are being multiplied seperatly.
	 * @param d
	 */
	public void multiply(Monom d) {
		// initial test if one of the is monom zero
		if(this.isZero() || d.isZero()){
			this.set_coefficient(0);
			this.set_power(0);
			return;
		}
		// coefficient multiplication
		double coeff = this.get_coefficient() * d.get_coefficient();
		this.set_coefficient(coeff);
		
		//Power 
			int pow = this.get_power() + d.get_power();
			this.set_power(pow);
	}
	/**
	 * Creates a human-readable string of a monom.
	 * @return String representation of monom
	 */
	public String toString() {
		String ans = "";
		ans = _coefficient+"";
		if(_power != 0)
			ans += "x"+"^"+_power;

		
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	
	
	
	
}
