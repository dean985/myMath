package ex1;//https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
//package stdDraw;


import java.awt.*;

public class TestStdDraw {
	public static void main(String[] args) {
		StdDraw.setScale(-2, +2);
		StdDraw.enableDoubleBuffering();


		for (double t = 0.0; true; t += 0.02) {
			double x = Math.sin(t);
			double y = Math.cos(t);
			StdDraw.clear();
			StdDraw.setPenColor(Color.RED);
			StdDraw.filledCircle(0.1, 0.1, Math.abs(0.05+x));
			//StdDraw.filledCircle(-x, -y, 0.05);
			StdDraw.show();
			StdDraw.pause(10);
		}
		//StdDraw.point(0.5, 0.5);
		//StdDraw.setPenColor(StdDraw.MAGENTA);
		//StdDraw.line(0.2, 0.2, 0.8, 0.3);


	}

}
