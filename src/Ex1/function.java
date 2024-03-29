package Ex1;

import java.io.Serializable;

/** This interface represents a simple Ex1.function of type y=f(x), where both y and x are real numbers.
**/
public interface function extends Serializable{
	double f(double x);
	/** 
	 * return a String representing this complex Ex1.function
	 */
	String toString();
	function initFromString(String s) ;
	function copy(); // clone
	boolean equals(Object obj);
}
