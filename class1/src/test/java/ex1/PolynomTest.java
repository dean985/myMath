package ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void multiply() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void isZero() {
    }

    @Test
    void root() {
    }

    @Test
    void copy() {
    }

    @Test
    void derivative() {
    }

    @Test
    void area() {
    }

    @Test
    void iteretor() {
    }

    @Test
    void testMultiply() {
    }

    @Test
    void testToString() {
    }

    @Test
    void initFromString() {
    }
}
