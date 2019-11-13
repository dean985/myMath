package myMath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.function.Predicate;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Dean and Elon (c)
 *
 * 
 */
public class Polynom implements Polynom_able{
	

	////////////////////////////////////////////
    //////////////    fields     ///////////////
	////////////////////////////////////////////
	
	ArrayList<Monom> polynom_list = new ArrayList<Monom>();
	

	
    /////////////////////////////////////////////////////////////////
    ///////////////////     Constructor     /////////////////////////
	/////////////////////////////////////////////////////////////////
	
	/**
	 * Zero (empty polynom_list)
	 */
	public Polynom() {
		this.polynom_list = new ArrayList<Monom>();
		this.polynom_list.add(Monom.ZERO);
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {

		if(!s.contains("+-"))
			s = s.replaceAll("-","+-"); //excluding identical process of substraction
		s = s.replaceAll(" ", "");// delete spaces
		String[] poly = s.split("\\+");
		for (String str:poly){
			this.polynom_list.add(new Monom(str));
		} 
		Monom_Comperator comp = new Monom_Comperator();
		polynom_list.sort(comp);

		//adding simillar monom
		for (int i = 1; i < polynom_list.size(); i++)
		{
			
			if(polynom_list.get(i).get_power() == polynom_list.get(i-1).get_power())
			{
				polynom_list.get(i-1).add(polynom_list.get(i));
				polynom_list.set(i, new Monom(0, 0));
			}
		}
		polynom_list.sort(comp);

		for (int i = 0; i < polynom_list.size(); i++) {
			if(polynom_list.get(i).isZero() && i == polynom_list.size()-1)
			{
				break;
			}
			if(polynom_list.get(i).isZero() || polynom_list.get(i).get_coefficient()==0)
					polynom_list.remove(i);
			
		}
	}


	///////////////////////////////////////////////////////////////////////////
    ////////////////////////////       methods        /////////////////////////
    ///////////////////////////////////////////////////////////////////////////



	@Override
	public double f(double x) {
		double res = 0;			
		for(Monom m: this.polynom_list){
			res += m.f(x);
		}
		return res;

	}



	@Override
	public void add(Polynom_able p1) {
		
		Polynom p = new Polynom(p1.toString());
		this.polynom_list.addAll(p.polynom_list);
		Polynom res = new Polynom(this.toString());
		this.polynom_list = res.polynom_list;	
	}

	@Override
	public void add(Monom m1) {
		this.polynom_list.add(m1);
		Polynom res = new Polynom(this.toString());
		this.polynom_list= res.polynom_list;
	}

	@Override
	public void substract(Polynom_able p1) 
	{
		Polynom p = new Polynom(p1.toString());
		Monom m1 = new Monom ("-1");
		p.multiply(m1);
		this.add(p);
		Polynom res = new Polynom(this.toString());
		this.polynom_list = res.polynom_list;		// to eliminate Zeros (appears also at the last row of add (Poly) method)
	}

	@Override
	public void multiply(Polynom_able p1) {
		Polynom p = new Polynom(p1.toString());
		Polynom res = new Polynom();
		for (int i =0;i < this.polynom_list.size(); i++){
			
			for( int k =0; k<p.polynom_list.size();k++){
				Monom temp = new Monom(this.polynom_list.get(i));
				temp.multiply( p.polynom_list.get(k));
				res.polynom_list.add(temp);

			}
		}
		Polynom res2 = new Polynom(res.toString());		// we initialize to rearrange the result of multiplication
		
		this.polynom_list= res2.polynom_list;			// return value
		
		
	}

	@Override
	public boolean equals(Polynom_able p1) {
	// Initialization of both polynoms, sorted and without zeros
		Polynom k1 = new Polynom(p1.toString());
		Polynom k2 = new Polynom(this.toString());
		k2.substract(k1);
		Polynom_able substraction = k2.copy();
		if (substraction.isZero()){
			return true;
		} 
		return false;
	}
	/**
	 * Method to check whether a polynom's value is zero
	 */
	@Override
	public boolean isZero() {
		Polynom p1 = new Polynom(this.toString());		// reordering the list of monoms and removing zeros
	
		for (int i = p1.polynom_list.size()-1 ; i>=0;i-- ){		
			if (!p1.polynom_list.get(i).isZero()){				
				return false;
			}
		}
		return true;		
	}
		

	/**
	 * @param x0,x1 - An interval in the domain of Polynom P , eps - being a constant small number (defined)
	 * @return double x - The point where p(x)=0 
	 * Return the root of the polynom for a given interval
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		// assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
		//	(i) x0<=x2<=x1 & (ii) {f(x2)<eps
			if (x0 > x1){
				throw new RuntimeException("Invalid input for polynom "+this.toString()+" , Check that x0<x1 ");
			}
			if (this.f(x0)*this.f(x1)>0  && this.f(x0) != this.f(x1) ){ 		//latter argument is because of the private case- constant monom
				throw new RuntimeException("For polynom "+this.toString()+" , Check that f(x0)*f(x1)<=0 ");
			}
			
			// BS for a root
			double mid = (x1+x0)/2;
			while ((x1-x0) >=eps ){
				mid = (x1+x0)/2;
				if (this.f(mid) == 0){
					break;
				}
				else if (this.f(mid)* this.f(x0) < 0){
					x1 = mid;
				}
				else {
					x0 = mid;
				}
			}
			if (this.f(mid) <=eps ){
				return mid;
			}
			//In case there are no roots, return max value
			return Double.MAX_VALUE;
		}
	/**
	 * 
	 *  @@return the polynom_able
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able p = new Polynom();
		for (int i =0; i< this.polynom_list.size();i++){
			p.add(this.polynom_list.get(i));
		}
		return p;

	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p = new Polynom();
		for (int i =0; i<this.polynom_list.size(); i++){
			Monom temp = this.polynom_list.get(i).derivative();
			p.add(temp);
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		final int n = 10;
		double dx = (x1 -x0)/n;
		double sum = 0;

		while(x0<x1)
		{
			sum += f(x0)*eps;
			x0 += eps;
			//sum += this.f( )*dx;
		}

		return sum;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return this.polynom_list.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		for(Monom m2: this.polynom_list){
			m2.multiply(m1);
		}
		
	}

	@Override
	public String toString(){
		String str="";
		int length = this.polynom_list.size();		// size of arraylist of monoms
		for(int i = 0; i< length-1 ; i++){

			
				str += this.polynom_list.get(i).toString() + " + ";
						
		}
		str += this.polynom_list.get(length-1).toString();
		
		return str;
	}
	
}
