
package ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Dean and Elon inc(c)
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	
	////////////////////////////////////////////
    //////////////    fields     ///////////////
    ////////////////////////////////////////////

	private double _coefficient; 
	private int _power;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();





    /////////////////////////////////////////////////////////////////
    ///////////////////     Constructor     /////////////////////////
    /////////////////////////////////////////////////////////////////

	/**
	 * constructor for monom
	 * @param a - coefficient
	 * @param b - power
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}

	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

		/**
	 * A method to proccess human readable version of monom (represented as a String)
	 * to an instance of the monom object.
	 * @param s
	 */
	public Monom(String s) {
		double coeffD =0;
		if(s.length()>0){
			if (s.contains("x")){

				//Coefficient
				String coeff = s.substring(0,s.indexOf("x"));
				if(s.charAt(0) == 'x' ) {
					coeffD = 1.0;
				}
					else if (s.charAt(0) == '-' && s.charAt(1) == 'x')
						coeffD = -1.0;
					else
						coeffD = Double.parseDouble(coeff);
					
				this.set_coefficient(coeffD);


				//Power
				if(s.contains("^")){
                    if (this.get_coefficient() == 0){
                        this.set_power(0);
                        getNewZeroMonom();
                    }
				    else{
				        int indexP = s.indexOf("^");
                        int p = Integer.parseInt(s.substring(indexP+1));
                        this.set_power(p);
                    }

				}
				else{
                    if (this.get_coefficient() == 0){
                        this.set_power(0);
                    }else{
                        this.set_power(1);
                    }
				}

			}
			else{
				double sDouble = Double.parseDouble(s);
				this.set_coefficient(sDouble);
				this.set_power(0);
			}

			 
			
		}
		
	}



    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////       methods        /////////////////////////
    ///////////////////////////////////////////////////////////////////////////


	/**
	 * the function return a monom with zero value
	 * @return Monom(Zero)
	 */
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}

	public double get_coefficient() {return this._coefficient;}

	public int get_power() {return this._power;}

	public static Comparator<Monom> getComp() {return _Comp;}


	private void set_coefficient(double a){this._coefficient = a;}

	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	

	/** 
	 * this method returns the derivative monom of this.
	 * @return derivative form of the monom
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
	

	/**
	 * 
	 * @return if the coeffcient == 0 then return true and set power to 0
	 */
	public boolean isZero() {
		boolean iszero = this.get_coefficient() == 0;
		if(iszero)
			this.set_power(0);
		return iszero;
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
	 * @param d - monom to multiply
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

	//@Override
	public function initFromString(String s) {
		Monom m = new Monom(s);
		return m;
	}

	//@Override
	public function copy() {
		Monom m = new Monom(this.get_coefficient(), this._power);
		return m;
	}

	//****************** Private Methods and Data *****************
	
	public Monom comp(Monom m){
		// Comp: comp(f1(x), f2(x)) == f1(f2(x))
		int newPow = this.get_power() * m.get_power();
		double newCoeff = this.get_coefficient() * m.get_coefficient();
		Monom res = new Monom(newCoeff, newPow);
		return res;
	}
	
	
	
	
}
