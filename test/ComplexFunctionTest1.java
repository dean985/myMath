
//import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;


class ComplexFunctionTest1 {
    @Test
    void constructor(){
        // #1
        Monom m1 = new Monom(2,2);
        Monom m2 = new Monom(3,3);
        Polynom p1 = new Polynom("2x^2 +x");


        ComplexFunction cf = new ComplexFunction("plus", m1,m2);
        String str = "plus(2.0x^2,3.0x^3)";
        assertEquals(str,cf.toString() );

        ComplexFunction cf2 = new ComplexFunction("abc", p1, m2);
        System.out.println(cf2.toString());

    }
    @Test
    void f() {
        String[] polys = {"2x^2-1", "-2x-x^2", "-x", "0", "4"};
        int n = polys.length;
        Polynom[] ps = new Polynom[n];
        for (int i = 0; i<ps.length; i++){
            ps[i] = new Polynom(polys[i]);
        }

        ComplexFunction cf1 = new ComplexFunction("plus", ps[0], ps[1]);
        ComplexFunction cf2 = new ComplexFunction("times", ps[1], ps[2]);
        ComplexFunction cf3 = new ComplexFunction("max", ps[3], ps[4]);
        ComplexFunction cf4 = new ComplexFunction("min", ps[4], ps[3]);
        ComplexFunction cf5 = new ComplexFunction("comp", ps[1], ps[2]);
        ComplexFunction cf6 = new ComplexFunction("None", ps[2], ps[3]);
        ComplexFunction cf7 = new ComplexFunction("E", ps[1],ps[0]);
        ComplexFunction cf8 = new ComplexFunction("divide", ps[1],ps[0]);


        double x = 2; // value to give to function
        System.out.println("Value: "+x);
        System.out.println(cf1.toString() +" - "+cf1.f(x));
        System.out.println(cf2.toString() +" - "+cf2.f(x));
        System.out.println(cf3.toString() +" - "+cf3.f(x));
        System.out.println(cf4.toString() +" - "+cf4.f(x));
        System.out.println(cf5.toString() +" - "+cf5.f(x));
        System.out.println(cf6.toString() +" - "+cf6.f(x));
        System.out.println(cf7.toString() +" - "+cf7.f(x));


    }
    @Test
    void tostring(){
        String s1 = "plus(-1.0x^4+2.4x^2+3.1,0.1x^5+-1.0x^1+5.0)";
        Polynom p1 = new Polynom("-1.0x^4+2.4x^2+3.1");
        Polynom p2 = new Polynom("0.1x^5-1.0x+5.0");
        ComplexFunction cf = new ComplexFunction("plus", p1, p2);
        assertEquals(s1, cf.toString());
    }
    @Test
    void initFromString() {
        String s1 = "plus(-1.0x^4+2.4x^2+3.1,0.1x^5+-1.0x^1+5.0)";
        String s2 = "plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)";
        function cf = new ComplexFunction(null) ;
        //function f = new Polynom("-1.0x^4+2.4x^2+3.1+0.1x^5-1.x+5.0");

        cf = cf.initFromString(s1);
        assertEquals(s1, cf.toString());


    }

    @Test
    void copy() {
        function f1 = new Polynom("3x^3");
        function f2 = new Polynom("x");
        ComplexFunction cf1 = new ComplexFunction("div", f1, f2);
        ComplexFunction cf2 = (ComplexFunction) cf1.copy();

        //check for copy
        assertEquals(cf1.f(3), cf2.f(3));
        //check for deep copy
        assertEquals(cf1.toString(), cf2.toString());
    }

    @Test
    void plus() {
        function f1 = new Polynom("x^2-5x");
        function f2 = new Polynom("x^3");

        ComplexFunction cf = new ComplexFunction(f1);
        cf.plus(f2);
        String res = "plus(1.0x^2+-5.0x^1,1.0x^3)";
        assertTrue(cf.f(2) == 2);

    }

    @Test
    void mul() {
        function p1 = new Polynom("x+2x^2");
        function p2 = new Polynom("-x");
        function m1 = new Monom("0");

        ComplexFunction cf1 = new ComplexFunction( p1);
        ComplexFunction cf2 = new ComplexFunction( p2);
        ComplexFunction cf3 = new ComplexFunction(m1);

        cf1.mul(cf2);
        assertEquals(-20, cf1.f(2));
        cf1.mul(cf3);
        assertEquals(0, cf3.f(2));


    }

    @Test
    void div() {
        Polynom p1 = new Polynom("2x+2");
        Polynom p2 = new Polynom("x^2");
        ComplexFunction cf1 = new ComplexFunction(p1);
        ComplexFunction cf2 = new ComplexFunction(p2);

        cf1.div(cf2);
        assertEquals(1.5, cf1.f(2));


    }

    @Test
    void max() {
        Polynom p1 = new Polynom("x^3+2x");
        Polynom p2 = new Polynom("x+x^2");

        ComplexFunction cf = new ComplexFunction("max", p1,p2);

        assertTrue(cf.f(2) == 12);


    }

    @Test
    void min() {
        Polynom p1 = new Polynom("2x^2+4x");
        Polynom p2 = new Polynom("2x");
        ComplexFunction cf1 = new ComplexFunction(p1);
        ComplexFunction cf2 = new ComplexFunction(p2);
        cf1.min(cf2);
        assertEquals(4, cf1.f(2));
    }

    @Test
    void comp() {
        Polynom p1 = new Polynom("3x^3");
        Polynom p2 = new Polynom("x");

        ComplexFunction cf = new ComplexFunction("comp", p1,p2);
        assertEquals(24, cf.f(2));
    }

    @Test
     void equalsTest(){
        Polynom p1 = new Polynom("x^2+x");
        Polynom p2 = new Polynom("x+1");
        Monom m = new Monom ("x");
        ComplexFunction cf1 = new ComplexFunction("mul",p2,m); //equals to p1
        ComplexFunction cf2 = new ComplexFunction(p1);
        ComplexFunction cf3 = new ComplexFunction(m);

        assertEquals(true, cf1.equals(cf2));
        assertEquals(false, cf3.equals(cf2));

    }
}