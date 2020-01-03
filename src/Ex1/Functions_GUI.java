package Ex1;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//import javax.json.Json;
//import javax.json.JsonArray;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
//import javax.json.JsonValue;
//import javax.json.stream.JsonParser;


import org.json.JSONObject;

public class Functions_GUI implements functions {

    public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE,
            Color.red, Color.GREEN, Color.PINK};
    public ArrayList<function> ff = new ArrayList<function>();

      public Functions_GUI()
      {
          ff.clear();
      }

    /**
     *
     * @param file - the file name
     * @throws IOException
     */

    @Override
    public void initFromFile(String file) throws IOException
    {

        try {
           FileInputStream streamIn = new FileInputStream("input/"+file);
           BufferedReader reader = new BufferedReader(new InputStreamReader(streamIn));
           String line = reader.readLine();

            while(line != null)
            {
                System.out.println(line);
                function f = new ComplexFunction();
               ff.add(new ComplexFunction(f.initFromString(line)));
                line = reader.readLine();
            }

            reader.close();
            streamIn.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param file - the file name
     * @throws IOException - throw when occured problem in the file save
     */
    @Override
    public void saveToFile(String file) throws IOException
    {

        File File = new File("input/"+file);
        BufferedWriter writer = null;
        try {

            // if file doesn't exists, then create it
            if (!File.exists()) {
                File.createNewFile();
            }

            Iterator<function> iterator = ff.iterator();

            writer = new BufferedWriter(new FileWriter(File));

            while (iterator.hasNext()){

                writer.write(iterator.next().toString()+"\n");

            }


            writer.flush();
            writer.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param width - the width of the window - in pixels
     * @param height - the height of the window - in pixels
     * @param rx - the range of the horizontal axis
     * @param ry - the range of the vertical axis
     * @param resolution - the number of samples with in rx: the X_step = rx/resulution
     */

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
        for (int i = (int) rx.get_min(); i <= rx.get_max(); i=i+1) {
            StdDraw.line(i,ry.get_min(), i, ry.get_max());
        }


        //////// horizontal  lines
        for (double i = ry.get_min(); i <= ry.get_max(); i=i+0.5) {
            StdDraw.line(rx.get_min(), (i),rx.get_max(), (i));
        }

        //////// x axis
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);

        StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
        StdDraw.setFont(new Font("--diff3-cmd CMD", Font.BOLD, 10));


        for (int i = (int)rx.get_min()+1; i < rx.get_max() ; i++) {
                StdDraw.line(i, -0.02, i, 0.02);
                StdDraw.text(i, -0.07, i+"");
        }

        //////// y axis
        StdDraw.line(0, ry.get_min(), 0, ry.get_max());



        for (int i = (int)ry.get_min(); i < ry.get_max(); i++) {
            if(i != 0){
            StdDraw.line(-0.2, i, 0.2, i);
            StdDraw.text(0.5, i, i+"");}
        }



        // plot the approximation to the Ex1.function
        for(int a=0;a<size;a++) {
            int c = a%Colors.length;
            StdDraw.setPenColor(Colors[c]);
            System.out.println( a +") "+ Colors[a] + "  f(x)= " + ff.get(a));
            for (int i = 0; i < n; i++) {
                StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
            }
        }
    }

    /**
     *
     * @param json_file - the file with all the parameters for the GUI window.
     */
    @Override
    public void drawFunctions(String json_file)
    {
        JSONObject json  = new JSONObject(loadJSONFromAsset("input/"+json_file));

         int W=800, H=600, RES=100;
        W = json.getInt("Width");
        H = json.getInt("Height");
        RES = json.getInt("Resolution");
        Range RX = new Range(json.getJSONArray("Range_X").getDouble(0),json.getJSONArray("Range_X").getDouble(1));
        Range RY = new Range(json.getJSONArray("Range_Y").getDouble(0),json.getJSONArray("Range_Y").getDouble(1));

        drawFunctions(W,H, RX, RY, RES);


    }

    /**
     *
     * @param path - the path to the file
     * @return - the string  parsed json file
     */
    public static String loadJSONFromAsset( String path)
    {
        String json = null;
        try {

            InputStream is = null;
            try {
                is = new FileInputStream(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {

            return null;
        }
        return json;
    }



    @Override
    public int size() {
        return ff.size();
    }

    @Override
    public boolean isEmpty() {
        return ff.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return ff.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        //Ex1.functions iterator = (Ex1.functions) this.ff.iterator();
        return ff.iterator();
    }

    @Override
    public Object[] toArray() {

        return this.ff.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return this.ff.toArray(ts);
    }


    public boolean add(function functions) {
        return ff.add(functions);
    }

    @Override
    public boolean remove(Object o) {
        return ff.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.ff.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return this.ff.addAll(collection);
    }


    @Override
    public boolean removeAll(Collection<?> collection) {
        return this.ff.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return this.ff.retainAll(collection);
    }

    @Override
    public void clear() {

        this.ff.clear();

    }

}
