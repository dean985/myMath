package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import Ex1.function;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class PolynomTest
{


    @Test
    void f()
    {
         String[] arr = {"-x^3+x^2+x+4","x^2+2x-1", "x^5-x","x^5+1","-x^6+3x+100","x+5","-2-2","5"};
        ArrayList<Polynom> testList = new ArrayList<>();

        double [] ans = {-91,34,3120,3126,-15510,10,0,0};

        for (int i = 0; i <testList.size(); i++)
        {

            assertEquals(ans[i], testList.get(i).f(5),testList.get(i).toString());
        }


    }


    @Test
    void testAddPolynom()
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
    void testAddMonom()
    {
        String[] good = {"x^2+x","5x^3+2x^2+4"};
        Monom m1 = new Monom("x^2");
        String[] ans = {"2x^2+x","5x^3+3x^2+4"};
        int i=0;
            //System.out.println(p2.toString());
            for (String string1 : good)
            {
                Polynom p = new Polynom(ans[i]);
                System.out.print(m1.toString()+" +  ");
                Polynom p1 = new Polynom(string1);

                    p1.add(m1);
                    assertEquals(p,p1.toString());
                    //System.out.println(p1.toString());
                i++;

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

        String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
                {"x" , "3x^3"} , {"-18.0x^6" , "9.0x^5" ,"21.0x^4" ,"-3.0x^3", "9.0x^2", "-2.0x"}};
        Polynom p1 = new Polynom();
        Polynom p2 = new Polynom();
        Polynom p3 = new Polynom();
        for (int i = 0; i < polynoms[0].length; i++) {
            Monom temp = new Monom(polynoms[0][i]);
            p1.add(temp);
        }
        for (int i = 0; i < polynoms[1].length; i++) {
            Monom temp = new Monom(polynoms[1][i]);
            p2.add(temp);
        }

        for (int i = 0; i < polynoms[2].length; i++) {
            Monom temp = new Monom(polynoms[2][i]);
            p3.add(temp);
        }
        Polynom multiply = new Polynom();
        multiply = (Polynom) p1.copy();
        multiply.multiply(p2);
        assertTrue(multiply.equals(p3));

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
    void root() {
/*
        double ans;
         ////////////////////////////////
        ///////// Exaction Tests ///////
       ////////////////////////////////
        String[] bad_example ={"x^2+2x-1","5"};
        assertThrows(Exception.class, () -> {
            Ex1.Polynom p = new Ex1.Polynom("x^2 +2");

            p.root(10, 0, Ex1.Monom.EPSILON);

        } );

        ////////////////////////////////
        /////////  Normal Tests  ///////
        ////////////////////////////////

        String[] arr = {"-x^3+x^2+x+4", "x^5-x","-x^6+3x+100","x+5","1.0x^5+-1.0x^1+1.0"};
        int [] low ={0,0,0,-10,-10};
        int [] high = {10,10,10,0,10};
        double [] expected_ans = {10.0,1,10,-5,-1.17};
        int i = 0;
        for(String s: arr){
            Ex1.Polynom p = new Ex1.Polynom(s);

            ans = Double.parseDouble(new DecimalFormat("##.##").format( p.root(low[i], high[i], Ex1.Monom.EPSILON)));
              // assertEquals( ,p.root(low, high, Ex1.Monom.EPSILON));

            assertEquals(expected_ans[i],ans,i + ") " + arr + "  (" + low[i] +","+high[i]+") ");
                //System.out.println("("+ p.toString()+")"+ "       Root: "+ans);
            i++;
        }*/
        String[][] polynoms = {{"3x^2", "-6x^3", "9x", "-2"},
                {"x", "5x", "0", "-5"},
                {"4x^6", "-5x^5", "1"}};
        double[][] res = {{0, 0.2135}, {0, 0.83334}, {2.404, 0.9999}};
        for (int i = 0; i < polynoms.length; i++) {
            Polynom p1 = new Polynom();
            for (int j = 0; j < polynoms[i].length; j++) {
                Monom temp = new Monom(polynoms[i][j]);
                p1.add(temp);
            }

            assertTrue((p1.root(0, 1, 0.0001) - res[i][1]) <= 0.0001);


        }
    }


    @Test
    void copy(){
        Polynom_able p1 = new Polynom("x^2+x+3");
        Polynom_able p2 = (Polynom_able) p1.copy();

        ///check if the objects are not the same so the developer didn't copy the pointer to the object
        assertNotSame(p1,p2);
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
       /* String [] parameter_1 ={"x^2+x+2","x^2+x+2","x^2+x+2","2","0","2x^2-2","x+2","x^2+2","x^2-2x"};
        double [] x0 = {0,1,-2,0,0,0,0,0,0};
        double [] x1 = {1,5,0,3,3,3,3,3,3};
        double [] ans = {2.83,2.83,4.67,6,0,12,10.5,15,0};

        for (int i = 0; i <parameter_1.length; i++)
        {

            Ex1.Polynom p_1 = new Ex1.Polynom(parameter_1[i]);
            Double d = Double.parseDouble(new DecimalFormat("##.##").format(p_1.area(x0[i],x1[i],0.001)));

            //assertTrue( Math.abs(ans[i] - d) < .0001);
            assertEquals( ans[i],d,i + ") " +parameter_1[i]);
        }*/
        String[][] polynoms = {{"3x^2","-6x^3","9x","-2"},
                                {"x","5x","0","-5"},
                                 {"4x^6", "-5x^5", "1"}};
        double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}};
        for (int i = 0; i < polynoms.length; i++) {
            Polynom p1 = new Polynom();
            for (int j = 0; j < polynoms[i].length; j++) {
                Monom temp = new Monom(polynoms[i][j]);
                p1.add(temp);

            }
            assertTrue(p1.area(-1, 0, 0.0001) - res[i][0] <= 0.01);


        }
    }

    @Test
    void iteretor()
    {
        Polynom polynom_able = new Polynom();
        Iterator<Monom> iterator = polynom_able.iteretor();
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals(new Polynom("0"),iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, () -> {
            iterator.next();
        } );
        iterator.remove();
        assertTrue(polynom_able.polynom_list.size()==0,polynom_able.polynom_list.size()+" ");


        //fail("add code");

    }


    /*@Test
    void testMultiply()
    {
        String [] parameter_1 ={"-2-2", "5",    "3","x",    "-x",   "x",    "x^3",   "x^3+x",       "x^3+x^2+5"};
        String [] parameter_2 ={"4",    "5",    "x","x",    "x",    "x^2",  "-x",    "x^2+x",       "x^2+x+1"};

        String [] ans = {       "-16",  "25",  "3x","x^2", "-x^2",  "x^3", "-x^4","x^5+x^4+x^3+x^2","x^5+2x^4+2x^3+6x^2+5x+5"};

        for (int i = 0; i <parameter_1.length; i++)
        {
            Ex1.Polynom p_1 = new Ex1.Polynom(parameter_1[i]);
            Ex1.Polynom p_2 = new Ex1.Polynom(parameter_2[i]);
            Ex1.Polynom ans_p = new Ex1.Polynom(ans[i]);
            p_1.multiply(p_2);
            assertEquals(ans_p, p_1,parameter_1[i] + "+" + parameter_2[i]);
        }

    }*/

    @Test
    void testToString()
    {

        String [] parameter_1 ={"x^2+x+2",    "-2x^3 + 5", "x^0 -2"};
        String [] ans ={"1.0x^2+1.0x^1+2.0","-2.0x^3+5.0", "-1.0"};

        /////// Normal Testing //////////

        for (int i = 0; i < parameter_1.length; i++)
        {

            Polynom p_1 = new Polynom(parameter_1[i]);
            assertEquals(ans[i],p_1.toString(), i + ") " +parameter_1[i]);
        }


        assertEquals("0.0",new Polynom().toString(), "Ex1.Monom zero test");


         /////////////////////////////////
        //////// Exception Testing //////
       /////////////////////////////////

        /**
         * i put Ex1.Polynom("a") it will make the test good
         *
         * if i put Ex1.Polynom("x+1") which is leagle it will fail the testing
         */


        String[] bad_polynoms = {"(1)","5x+d^4","(x^2+x)+2", "0.5x^2","a"};

        assertThrows(Exception.class, () -> {
            for (int i = 0; i < bad_polynoms.length; i++) {
                Polynom p_1 = new Polynom(bad_polynoms[i]);
            }

        } );


    }

    @Test
    void initFromString()
    {
        Polynom p =new Polynom();
        function f = p.initFromString("x^2+3");
        Polynom p2 = new Polynom("x^2+3");
        assertEquals(p2,f);
    }
}
