package Ex1Testing;//import org.junit.Assertions;
import Ex1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Note: minor changes (thanks to Amichai!!)
 * The use of "get" was replaced by iterator!
 * 
 * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[r=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)

 * @author boaz_benmoshe
 *
 */
class Functions_GUITest {

	public static void main(String[] a) {
		functions data = FunctionsFactory();
		int w=1000, h=600, res=200;
	  	Range rx = new Range(-10,10);
	 	Range ry = new Range(-5,15);
		data.drawFunctions(w,h,rx,ry,res);
		String file = "function_file.txt";

		String file2 = "function_file2.txt";
		try {
			data.saveToFile(file);
			 Functions_GUI data2 = new Functions_GUI();
			data2.initFromFile(file);
			data.saveToFile(file2);
		}
		catch(Exception e) {e.printStackTrace();}

		String JSON_param_file = "GUI_params.txt";
		data.drawFunctions(JSON_param_file);
	}
	private functions _data=null;

	@BeforeEach
	void setUp() throws Exception {
		_data = FunctionsFactory();
	}

	@Test
	void testFunctions_GUI() {
		Functions_GUI functions_gui = new Functions_GUI();
		//Assert.assertTrue(functions_gui.ff.isEmpty());
		assertTrue(functions_gui.ff.isEmpty());
	}


	@Test
	void testSaveToFile() {

		ArrayList<function> testf = new ArrayList<>();

		///save the current array list
		try {
			_data.saveToFile("testSaveToFile.txt");

		} catch (IOException e) {
			fail(""+e.getMessage());
		}
		Object[] actualArr = new function[_data.size()];
		actualArr = _data.toArray();

		FileInputStream streamIn = null;

		/// read the file and put to array list
		try {
			streamIn = new FileInputStream("input/"+"testSaveToFile.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
			String line = reader.readLine();
			while(line != null)
			{
				System.out.println(line);
				function f = new ComplexFunction();
				testf.add(new ComplexFunction(f.initFromString(line)));
				line = reader.readLine();
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		///check if what i saved is similar to what saved in the file
		for (int i = 0; i < testf.size(); i++) {
			assertEquals(testf.get(i).toString(),actualArr[i].toString());

		}


	}

	@Test
	void testInitFromFile() {

		ArrayList<function> testf = new ArrayList<>();
		ArrayList<function> axpected = new ArrayList<>();
/////////////////////////call initFromfile///////////////////////
		try {
			_data.initFromFile("function_file.txt");

		} catch (IOException e) {
			fail("fail the reading");
		}
		Object[] actualArr = new function[_data.size()];
		actualArr = _data.toArray();
/////////////////////////////////////////////////////////////////////


///////////////////////read the file and put it in arrty//////////////////////////
		FileInputStream streamIn = null;

		/// read the file and put to array list
		try {
			streamIn = new FileInputStream("input/"+"function_file.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
			String line = reader.readLine();
			while(line != null)
			{
				System.out.println(line);
				function f = new ComplexFunction();
				testf.add(new ComplexFunction(f.initFromString(line)));
				line = reader.readLine();
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	///check if what i saved is similar to what saved in the file
		for (int i = 0; i < testf.size(); i++) {
			assertEquals(testf.get(i).toString(),actualArr[i].toString());

		}

	}



	public static functions FunctionsFactory() {
		functions ans = new Functions_GUI();
		String s1 = "3.1 +2.4x^2 -x^4";
		String s2 = "5 +2x -3.3x +0.1x^5";
		String[] s3 = {"x +3","x -2", "x -4"};
		Polynom p1 = new Polynom(s1);
		Polynom p2 = new Polynom(s2);
		Polynom p3 = new Polynom(s3[0]);
		ComplexFunction cf3 = new ComplexFunction(p3);
		for(int i=1;i<s3.length;i++) {
			cf3.mul(new Polynom(s3[i]));
		}
		
		ComplexFunction cf = new ComplexFunction(Operation.Plus, p1,p2);
		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
		cf4.plus(new Monom("2"));
		ans.add(cf.copy());
		ans.add(cf4.copy());
		cf.div(p1);
		ans.add(cf.copy());
		String s = cf.toString();
		function cf5 = cf4.initFromString(s1);
		function cf6 = cf4.initFromString(s2);
		ans.add(cf5.copy());
		ans.add(cf6.copy());
		Iterator<function> iter = ans.iterator();
		function f = (function) iter.next();
		ComplexFunction max = new ComplexFunction(f);
		ComplexFunction min = new ComplexFunction(f);
		while(iter.hasNext()) {
			f = (function) iter.next();
			max.max(f);
			min.min(f);
		}
		ans.add(max);
		ans.add(min);
		return ans;
	}
}
