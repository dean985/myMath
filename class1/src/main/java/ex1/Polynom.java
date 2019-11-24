package ex1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.function.Predicate;

import ex1.Monom;

/**
 * This class represents a Polynom with add, multiply functionality, it also
 * should support the following: 1. Riemann's Integral:
 * https://en.wikipedia.org/wiki/Riemann_integral 2. Finding a numerical value
 * between two values (currently support root only f(x)=0). 3. Derivative
 * 
 * @author Dean and Elon (c)
 *
 * 
 */
public class Polynom implements Polynom_able {

	////////////////////////////////////////////
	////////////// fields ///////////////
	////////////////////////////////////////////

	ArrayList<Monom> polynom_list = new ArrayList<Monom>();

	/////////////////////////////////////////////////////////////////
	/////////////////// Constructor /////////////////////////
	/////////////////////////////////////////////////////////////////

	/**
	 * Zero (empty polynom_list)
	 */
	public Polynom() {
		this.polynom_list = new ArrayList<Monom>();
		this.polynom_list.add(Monom.ZERO);
	}

	/**
	 * init a Polynom from a String such as: {"x", "3+1.4X^3-34x","3","2x^4"};
	 * 
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {

		s = s.replaceAll(" ", "");// delete spaces
		if (!s.contains("+-"))
			s = s.replaceAll("-", "+-"); // excluding identical process of substraction

		String[] poly = s.split("\\+");
		for (String str : poly) {
			this.polynom_list.add(new Monom(str));
		}
		Monom_Comperator comp = new Monom_Comperator();
		polynom_list.sort(comp);

		// adding simillar monom
		for (int i = 1; i < polynom_list.size(); i++) {

			if (polynom_list.get(i).get_power() == polynom_list.get(i - 1).get_power()) {
				polynom_list.get(i - 1).add(polynom_list.get(i));
				polynom_list.set(i, new Monom(0, 0));
			}
		}
		polynom_list.sort(comp);

		// delete the zeroz monom

		if(polynom_list.size()>1)
		for (int i = 0; i < polynom_list.size(); i++) {
			if (polynom_list.get(i).isZero() && i == polynom_list.size()) {
				break;
			}
			// if (i == polynom_list.size())
			// if(!polynom_list.get(polynom_list.size() - 1).isZero() &&
			// polynom_list.get(polynom_list.size()).isZero())
			// polynom_list.remove(i);
			if (polynom_list.get(i).isZero() || polynom_list.get(i).get_coefficient() == 0)
				polynom_list.remove(i);

		}
	}

	///////////////////////////////////////////////////////////////////////////
	//////////////////////////// methods /////////////////////////
	///////////////////////////////////////////////////////////////////////////

	@Override
	public double f(double x) {
		double res = 0;
		for (Monom m : this.polynom_list) {
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
		this.polynom_list = res.polynom_list;
	}

	@Override
	public void substract(Polynom_able p1) {
		Polynom p = new Polynom(p1.toString());
		Monom m1 = new Monom("-1");
		p.multiply(m1);
		this.add(p);
		Polynom res = new Polynom(this.toString());
		this.polynom_list = res.polynom_list;	
	}

	@Override
	public void multiply(Polynom_able p1) {
		Polynom p = new Polynom(p1.toString());
		Polynom res = new Polynom();
		for (int i = 0; i < this.polynom_list.size(); i++) {

			for (int k = 0; k < p.polynom_list.size(); k++) {
				Monom temp = new Monom(this.polynom_list.get(i));
				temp.multiply(p.polynom_list.get(k));
				res.polynom_list.add(temp);

			}
		}
		Polynom res2 = new Polynom(res.toString()); // we initialize to rearrange the result of multiplication

		this.polynom_list = res2.polynom_list; // return value
	}

	@Override
	public boolean equals(Polynom_able p1) {
		// Initialization of both polynoms, sorted and without zeros
		Polynom k1 = new Polynom(p1.toString());
		Polynom k2 = new Polynom(this.toString());
		k2.substract(k1);
		Polynom_able substraction = k2.copy();
		if (substraction.isZero()) {
			return true;
		}
		return false;
	}

	/**
	 * Method to check whether a polynom's value is zero
	 */
	@Override
	public boolean isZero() {
		Polynom p1 = new Polynom(this.toString()); // reordering the list of monoms and removing zeros

		for (int i = p1.polynom_list.size() - 1; i >= 0; i--) {
			if (!p1.polynom_list.get(i).isZero()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param x0,x1 - An interval in the domain of Polynom P , eps - being a
	 *              constant small number (defined)
	 * @return double x - The point where p(x)=0 Return the root of the polynom for
	 *         a given interval
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		// assuming (f(x0)*f(x1)<=0, returns f(x2) such that:
		// (i) x0<=x2<=x1 & (ii) {f(x2)<eps
		if (x0 > x1) {
			throw new RuntimeException("Invalid input for polynom " + this.toString() + " , Check that x0<x1 ");
		}
		if (this.f(x0) * this.f(x1) > 0 && this.f(x0) != this.f(x1)) { // latter argument is because of the private
																		// case- constant monom
			throw new RuntimeException("For polynom " + this.toString() + " , Check that f(x0)*f(x1)<=0 ");
		}

		// BS for a root
		double mid = (x1 + x0) / 2;
		while ((x1 - x0) >= eps) {
			mid = (x1 + x0) / 2;
			if (this.f(mid) == 0) {
				break;
			} else if (this.f(mid) * this.f(x0) < 0) {
				x1 = mid;
			} else {
				x0 = mid;
			}
		}
		if (this.f(mid) <= eps) {
			return mid;
		}
		// In case there are no roots, return max value
		return Double.MAX_VALUE;
	}

	/**
	 * make a deep copy for the polynom
	 * 
	 * @@return the polynom_able
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able p = new Polynom();
		for (int i = 0; i < this.polynom_list.size(); i++) {
			p.add(this.polynom_list.get(i));
		}
		return p;

	}

	@Override
	public Polynom_able derivative() {
		Polynom_able p = new Polynom();
		for (int i = 0; i < this.polynom_list.size(); i++) {
			Monom temp = this.polynom_list.get(i).derivative();
			p.add(temp);
		}
		return p;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		 if (x0 > x1) { // swap in that case
			 double x_t = x1;
			 x1 = x0;
			 x0 = x_t;
		 }
		 final int n = 100; // n partitions
		 double h = (x1 - x0) / n; // partitioning delta x to n partitions
		 double _x = x0;
		 double mid = _x + h / 2;
		 double sum = this.f(_x); // intializing sum as the value of the function in the middle of the interval
		 int i = 0;
		 while (i < n - 1) { // loop to 'run' on each partition, rieman sums
			 i++;
			 _x = x0 + i * h; 
			 sum += 4 * this.f(mid) + 2 * this.f(_x);
			 mid = _x + h / 2;
		 }
		 sum += 4 * this.f(mid) + this.f(x1);
		 return sum * h / 6;
		 
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return this.polynom_list.iterator();
	}

	@Override
	public void multiply(Monom m1) {
		for (Monom m2 : this.polynom_list) {
			m2.multiply(m1);
		}

	}

	@Override
	public String toString() {
		String str = "";
		int length = this.polynom_list.size(); // size of arraylist of monoms
		for (int i = 0; i < length - 1; i++) {

			str += this.polynom_list.get(i).toString() + " + ";

		}
		str += this.polynom_list.get(length - 1).toString();

		return str;
	}

}
