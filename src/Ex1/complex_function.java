package Ex1;

/** This interface represents a complex Ex1.function of type y=g(f1(x), f2(x)), where both f1, f2 are Ex1.functions (or complex Ex1.functions),
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
**/
public interface complex_function extends function{
	
	/** Add to this Ex1.complex_function the f1 Ex1.complex_function
	 * 
	 * @param f1 the Ex1.complex_function which will be added to this Ex1.complex_function.
	 */
    void plus(function f1) throws Exception;
	/** Multiply this Ex1.complex_function with the f1 Ex1.complex_function
	 * 
	 * @param f1 the Ex1.complex_function which will be multiply be this Ex1.complex_function.
	 */
    void mul(function f1);
	/** Divides this Ex1.complex_function with the f1 Ex1.complex_function
	 * 
	 * @param f1 the Ex1.complex_function which will be divid this Ex1.complex_function.
	 */
    void div(function f1);
	/** Computes the maximum over this Ex1.complex_function and the f1 Ex1.complex_function
	 * 
	 * @param f1 the Ex1.complex_function which will be compared with this Ex1.complex_function - to compute the maximum.
	 */
    void max(function f1) throws Exception;
	/** Computes the minimum over this Ex1.complex_function and the f1 Ex1.complex_function
	 * 
	 * @param f1 the Ex1.complex_function which will be compared with this Ex1.complex_function - to compute the minimum.
	 */
    void min(function f1) throws Exception;
	/**
	 * This method wrap the f1 Ex1.complex_function with this Ex1.function: this.f(f1(x))
	 * @param f1 complex Ex1.function
	 */
    void comp(function f1) throws Exception;
	/** returns the left side of the complex Ex1.function - this side should always exists (should NOT be null).
	 * @return a Ex1.function representing the left side of this complex funcation
	 */
    function left();
	/** returns the right side of the complex Ex1.function - this side might not exists (aka equals null).
	 * @return a Ex1.function representing the left side of this complex funcation
	 */
    function right();
	/**
	 * The Ex1.complex_function oparation: plus, mul, div, max, min, comp
	 * @return
	 */
    Operation getOp();
}
