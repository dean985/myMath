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
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	
	ArrayList<Monom> polynom_list = new ArrayList<Monom>();
	
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
		s = s.replaceAll("-","+-"); //excluding identical process of substraction
		s = s.replaceAll(" ", "");// delete spaces
		String[] poly = s.split("\\+");
		for (String str:poly){
			this.polynom_list.add(new Monom(str));
		} 
		Monom_Comperator comp = new Monom_Comperator();
		polynom_list.sort(comp);

		//adding similler monom
		for (int i = 1; i < polynom_list.size(); i++)
		{
			
			if(polynom_list.get(i).get_power() == polynom_list.get(i-1).get_power())
			{
				polynom_list.get(i-1).add(polynom_list.get(i));
				polynom_list.set(i, new Monom(0, 0));
			}
		}
		polynom_list.sort(comp);

		int length = polynom_list.size()-1;
		for (int i = 0; i < polynom_list.size() ; i++) {
			if(polynom_list.get(i).isZero())
					polynom_list.remove(i);
			
		}
		//polynom_list.sort(comp);



	}

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
		this.polynom_list= res.polynom_list;	
	}

	@Override
	public void add(Monom m1) {
		this.polynom_list.add(m1);
		Polynom res = new Polynom(this.toString());
		this.polynom_list= res.polynom_list;
	}

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean equals(Polynom_able p1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		
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
