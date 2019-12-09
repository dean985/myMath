package ex1;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Function;

public class Functions_GUI implements functions {

    public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
            Color.red, Color.GREEN, Color.PINK};
    static ArrayList<function> ff = new ArrayList<function>();

    public static ArrayList<function> init() {
        ArrayList<function> ans = new ArrayList<function>();
        Polynom s0 = new Polynom("x^2+x+2");
        Polynom s1 = new Polynom("4x+1");
        Polynom s2 = new Polynom("0.3x^3");
        ans.add(s0); ans.add(s1); ans.add(s2);
        return ans;
    }

    public static void main(String[] args) {

            ff.addAll(init());
            Functions_GUI functions_gui = new Functions_GUI();
            functions_gui.drawFunctions(800,600,new Range(-10,10),new Range(-1.2,1.5),200);
    }

    @Override
    public void initFromFile(String file) throws IOException {

    }

    @Override
    public void saveToFile(String file) throws IOException {

    }



    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution)
    {

        int n = resolution;
        StdDraw.setCanvasSize(width, height);
        int size = ff.size();
        double[] x = new double[n+1];
        double[][] yy = new double[size][n+1];
        double x_step = (rx.get_max()-rx.get_min())/n;
        double x0 = rx.get_min();

        for (int i=0; i<=n; i++) {
            x[i] = x0;
            for(int a=0;a<size;a++) {
                yy[a][i] = ff.get(a).f(x[i]);
            }
            x0+=x_step;
        }
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());

        //////// vertical lines
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.line(x[i], ry.get_min(), x[i], ry.get_max());
        }

        //////// horizontal  lines
        for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
            StdDraw.line(rx.get_min(), i,rx.get_max(), i);
        }

        //////// x axis
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);

        StdDraw.line(0, 0, height/2, 0);
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 10));
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-n/2));
        }

        //////// y axis
        StdDraw.line(x[n/2], ry.get_min(), x[n/2], ry.get_max());
        for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
            StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
        }

        // plot the approximation to the function
//        for(int a=0;a<size;a++) {
//            int c = a%Colors.length;
//            StdDraw.setPenColor(Colors[c]);
//            System.out.println( a +") "+ Colors[a] + "  f(x)= " + ff.get(a));
//            for (int i = 0; i < n; i++) {
//                StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
//            }
//        }

    }

    @Override
    public void drawFunctions(String json_file)
    { ; }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<functions> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(functions functions) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends functions> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }
}
