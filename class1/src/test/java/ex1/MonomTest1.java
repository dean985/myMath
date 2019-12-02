package ex1;

import org.junit.jupiter.api.Test;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import static org.junit.jupiter.api.Assertions.*;

class MonomTest1 {

    @Test
    void get_coefficient() {
        String[] monoms = {"-2x", "0", "x","-x^3","-1x^5","x^6","-x^0", "0.5x^2", " 00023"};
        double[] coeff = {  -2,    0,   1,   -1,     -1,   1,     -1,    0.5 , 23};
        for(int i =0; i<monoms.length; i++){
            Monom m = new Monom(monoms[i]);
            assertEquals(coeff[i], m.get_coefficient());
        }
    }

    @Test
    void get_power() {
        String[] monoms = {"-2x", "0", "x","-x^3","-1x^5","x^6","-x^0", "0.5x^2", "00023x"};
        int[] power =     { 1,    0,   1 ,   3,     5,     6,      0,        2  ,      1 };
        for( int i =0; i< monoms.length; i++){
            Monom m = new Monom(monoms[i]);
                assertEquals(power[i], m.get_power(), "Monom - " + monoms[i] + " is wrong");
        }
    }

    @Test
    void getComp() {
    }

    @Test
    void derivative() {
        String[] monoms = {         "2x^4", "3x", "0", "1", "2x", "-1", "2"};
        String[] afterDerivative = {"8x^3", "3",  "0", "0", "2", "0", "0"};
        for (int i =0 ; i< monoms.length; i++){
            Monom m = new Monom(monoms[i]);
            Monom n = new Monom(afterDerivative[i]);

            m = m.derivative();
            assertEquals( n.toString(),m.toString(), "Error with: "+m.toString()+ " "+n.toString());
                //Test ver2
            //assertTrue(n.equals(m.derivative()), "Before Derivative: "+ monoms[i].toString()+
            //        " . After derivative : "+m.derivative().toString()+ "\n Expected value: "+ n.toString());
        }
    }

    @Test
    void f() {
        // testing f(x)
        double x = 5;
        String[] monoms = {"2x^4", "3x", "0", "1", "2x", "-1"};
        int[] res =     {   1250,   15,   0,   1,   10,   -1 };
        for (int i = 0; i<monoms.length; i++){
            Monom m = new Monom(monoms[i]);
            assertEquals(m.f(x), res[i], "Before method: "+m.toString()+" After Method: "+m.f(x)+
                    "\n Expected: "+ res[i]);
        }
    }

    @Test
    void isZero() {
        String[] monoms = { "0", "00x", "x^3", "2", "0x^3", "-4"};
        boolean[] res =   { true,  true ,false, false, true, false };
        for( int i = 0; i<monoms.length; i++){
            Monom m = new Monom (monoms[i]);
            assertEquals(m.isZero(), res[i], "Error with: "+ monoms[i]);
        }
    }

    @Test
    void testEquals() {
        String[] monoms1 = {"1", "2x", "5x^2", "0", "-1", "x"};
        String[] monoms2 = {"x^0", "2x^1", "5x^2", "0x", "-1x^0", "x^1"};
        for (int i = 0 ; i< monoms1.length; i++){
            Monom m = new Monom(monoms1[i]);
            Monom n = new Monom(monoms2[i]);
            assertTrue(m.equals(n), "Error with: "+ m.toString()+ " and "+n.toString());
        }
    }

    @Test
    void add() {
        String[] monoms1 = { "1",  "2x", "5x^2" , "0",    "-1", "x"};
        String[] monoms2 = { "2", "-3x", "-5x^2", "3x^3", "-6", "-x"};
        String[] ans =     { "3", "-x",  "0",     "3x^3", "-7", "0"};

        for( int i =0 ; i<monoms1.length; i++){
            Monom m1 = new Monom(monoms1[i]);
            Monom m2 = new Monom(monoms2[i]);
            m1.add(m2);

            Monom m = new Monom(m1.toString());         //Tizmzum of expression m1
            Monom res = new Monom(ans[i]);

            assertEquals( res.toString(), m.toString(), "Error with: "+m+ " and "+res);
        }
    }

    @Test
    void multiply() {
        String[] monoms1 = {"1",  "2x",    "5x^2" , "0",    "-1", "x"};
        String[] monoms2 = {"2",  "-3x"   ,"-x^4", "3x^3", "-6x", "-x"};
        String[] ans =     {"2",  "-6x^2", "-5x^6", "0"  , " 6x", "-x^2"};

        for (int i = 0 ; i<monoms1.length; i++){
            Monom m1 = new Monom(monoms1[i]);
            Monom m2 = new Monom(monoms2[i]);
            Monom m_ans = new Monom(ans[i]);        //expected

            m1.multiply(m2);
            Monom m = new Monom(m1.toString());     //actual
            assertEquals(m_ans.toString(), m.toString());
        }
    }

    @Test
    void testToString() {

    }

    @Test
    void initFromString() {
    }

    @Test
    void copy() {
    }
}