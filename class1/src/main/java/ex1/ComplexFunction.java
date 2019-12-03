

package ex1;

/**
 * ComplexFunction
 */
public class ComplexFunction implements complex_function{

    public ComplexFunction(String string, Monom m1, Monom m2) {
	}

	public ComplexFunction(String string, Polynom p1, Polynom p2) {
	}

	public ComplexFunction(String string, Monom m1, Polynom p3) {
	}



	@Override
    public double f(double x) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public function initFromString(String s) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public function copy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void plus(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mul(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void div(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void max(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void min(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void comp(function f1) {
        // TODO Auto-generated method stub

    }

    @Override
    public function left() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public function right() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Operation getOp() {
        // TODO Auto-generated method stub
        return null;
    }

    
}