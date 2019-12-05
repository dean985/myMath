

package ex1;

import static ex1.Operation.Plus;
import static ex1.Operation.Times;

/**
 * ComplexFunction
 */
public class ComplexFunction implements complex_function{
    Operation op ;
    public ComplexFunction(String string, Monom m1, Monom m2) {
        // Understanding which operation is in input
        String str = string.substring(0, 1).toUpperCase() + string.substring(1);      // Capitalize first letter of operation
        Operation op = Operation.Plus;
        op = op.valueOf(str);
        Monom res = new Monom (0,0);                                                 //result

        switch (op){
            case Plus:
                res.add(m1);
                res.add(m2);
            case Times:
                res.add(m1);
                res.multiply(m2);
            case Divide:
                if (m1.get_power() - m2.get_power() >=0){
                    double res_coeff = m1.get_coefficient()/m2.get_coefficient();
                    int res_power = m1.get_power() - m2.get_power();
                    res = new Monom(res_coeff, res_power);
                }
                else{
                    System.out.println("Not divisable and still be a monom.");
                }
            case Max:
                if (m1.get_power() < m2.get_power()){
                    res.add(m2);
                }
                else if (m1.get_power() == m2.get_power()){
                    if (m1.get_coefficient() < m2.get_coefficient()){
                        res.add(m2);
                    }
                }
                else {
                    res.add(m1);
                }
            case Min:
                if (m1.get_power() < m2.get_power()){
                    res.add(m1);
                }
                else if (m1.get_power() == m2.get_power()){
                    if (m1.get_coefficient() < m2.get_coefficient()){
                        res.add(m1);
                    }
                }
                else {
                    res.add(m2);
                }
            case Comp:
                //comp(f1(x), f2(x)) == f1(f2(x))
                res = m1.comp(m2);
            case None:
                //None representing no Operation
                System.out.println("No operation given");

            case Error:
                System.out.println("unsupported operation");

        }



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

    //@Override
    public function initFromString(String s) {
        // TODO Auto-generated method stub
        return null;
    }

    //@Override
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