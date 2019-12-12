/** This interface represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions),
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
**/
public interface complex_function extends function{
	
	/** Add to this complex_function the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be added to this complex_function.
	 */
    void plus(function f1) throws Exception;
	/** Multiply this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be multiply be this complex_function.
	 */
    void mul(function f1);
	/** Divides this complex_function with the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be divid this complex_function.
	 */
    void div(function f1);
	/** Computes the maximum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
	 */
    void max(function f1) throws Exception;
	/** Computes the minimum over this complex_function and the f1 complex_function
	 * 
	 * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
	 */
    void min(function f1) throws Exception;
	/**
	 * This method wrap the f1 complex_function with this function: this.f(f1(x))
	 * @param f1 complex function
	 */
    void comp(function f1) throws Exception;
	/** returns the left side of the complex function - this side should always exists (should NOT be null).
	 * @return a function representing the left side of this complex funcation
	 */
    function left();
	/** returns the right side of the complex function - this side might not exists (aka equals null).
	 * @return a function representing the left side of this complex funcation
	 */
    function right();
	/**
	 * The complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
    Operation getOp();
}