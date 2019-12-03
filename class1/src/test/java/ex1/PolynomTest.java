package ex1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PolynomTest
{
    private static String[] arr = {"-x^3+x^2+x+4","x^2+2x-1", "x^5-x","x^5+1","-x^6+3x+100","x+5","-2-2","5"};
    private static ArrayList<Polynom> testList = new ArrayList<>();
    @BeforeEach
    void setUp()
    {
        for (int i = 0; i < arr.length; i++)
        {
            testList.add(new Polynom(arr[i]));
        }

    }

//    @AfterEach
//    void tearDown() {
//    }
    @Test
    void f()
    {
        double [] ans = {-91,34,3120,3126,-15510,10,0,0};

        for (int i = 0; i <testList.size(); i++)
        {

            assertEquals(ans[i], testList.get(i).f(5),testList.get(i).toString());
        }


    }


    @Test
    void testAdd()
    {
        String [] parameter_1 ={"-2-2","5","3","x","-x","x","x^3","x^3+x","x^3+x^2+5"};
        String [] parameter_2 ={"4","5","x","x","x","x^2","-x","x^2+x","x^3+x^2+x+1"};

        String [] ans = {"0","10","3+x","2x","0","x^2+x","x^3-x","x^3+x^2+2x","2x^3+2x^2+x+6"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Polynom p_1 = new Polynom(parameter_1[i]);
            Polynom p_2 = new Polynom(parameter_2[i]);
            Polynom ans_p = new Polynom(ans[i]);
            p_1.add(p_2);
            assertEquals(ans_p, p_1,parameter_1[i] + "+" + parameter_2[i]);
        }
    }

    @Test
    void substract()
    {
        String [] parameter_1 ={"-2","4","x","2x","2x+2","x^2+x+1","x^3+x+3","-x^3-x^2-x"};
        String [] parameter_2 ={"2","3","x","x","3x-2","x^2+3x+2","x^2-x","x^3+x^2-x"};

        String [] ans = {"-4","1","0","x","-x+4","-2x-1","x^3-x^2+2x+3","-2x^3-2x^2"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Polynom p_1 = new Polynom(parameter_1[i]);
            Polynom p_2 = new Polynom(parameter_2[i]);
            Polynom ans_p = new Polynom(ans[i]);
            p_1.substract(p_2);
            assertEquals(ans_p.toString(), p_1.toString(),(i+1)+") "+parameter_1[i] + " - " + parameter_2[i]);
        }
    }

    @Test
    void multiply()
    {
        String [] parameter_1 ={"2","-2","2x","3","x+2","x^2+x+1","x^2+x+1","x^2+x-1"};
        String [] parameter_2 ={"4","5","2","2x+1","x+2","x","x^2+1","x+2+x^2"};

        String [] ans = {"8","-10","4x","6x+3","x^2+4x+4","x^3+x^2+x","x^4+x^3+2x^2+x+1","x^4+2x^3+2x^2+x-2"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Polynom p_1 = new Polynom(parameter_1[i]);
            Polynom p_2 = new Polynom(parameter_2[i]);
            Polynom ans_p = new Polynom(ans[i]);
            p_1.multiply(p_2);
            assertEquals(ans_p, p_1,parameter_1[i] + "+" + parameter_2[i]);
        }
    }

    @Test
    void testEquals()
    {
        String [] parameter_1 ={"-2-2","5","3","x","-x","x","x^3","x^3+x","x^3+x^2+5"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Polynom p_1 = new Polynom(parameter_1[i]);
            Polynom p_2 = new Polynom(parameter_1[i]);
            assertEquals(true, p_1.equals(p_2),parameter_1[i] );
        }
    }

    @Test
    void isZero()
    {
        Polynom p_1 = new Polynom("0");

        assertEquals(true,p_1.isZero());

    }

    @Test
    void root()
    {
    }


    @Test
    void copy()
    {
        Polynom_able p1 = new Polynom("x^2+x+3");
        Polynom_able p2 = p1.copy();
        assertEquals(p1.toString(),p2.toString());
    }

    @Test
    void derivative()
    {
        String [] parameter_1 ={"2","x","x^2","2x^3","x^2+x"};

        String [] ans = {"0","1","2x","6x^2","2x+1"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Polynom_able p_1 = new Polynom(parameter_1[i]);
            Polynom ans_p = new Polynom(ans[i]);
            p_1 = p_1.derivative();
            assertEquals(ans_p.toString(), p_1.toString(),(i+1)+") "+parameter_1[i] );
        }
    }

    @Test
    void area()
    {
        String [] parameter_1 ={"x^2+x+2","x^2+x+2","x^2+x+2","2","0","2x^2-2","x+2","x^2+2","x^2-2x"};
        double [] x0 = {0,1,-2,0,0,0,0,0,0};
        double [] x1 = {1,0,0,3,3,3,3,3,3};
        double [] ans = {2.83,2.83,4.67,6,0,3,10.5,15,0};

        for (int i = 0; i <parameter_1.length; i++)
        {

            Polynom p_1 = new Polynom(parameter_1[i]);
            Double d = Double.parseDouble(new DecimalFormat("##.##").format(p_1.area(x0[i],x1[i],0.001)));

            //assertTrue( Math.abs(ans[i] - d) < .0001);
            assertEquals( ans[i],d,i + ") " +parameter_1[i]);
        }
    }

    @Test
    void iteretor()
    {

    }

    @Test
    void testMultiply()
    {

    }

    @Test
    void testToString()
    {

    }

    @Test
    void initFromString()
    {

    }
}
