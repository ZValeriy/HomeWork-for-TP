import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Main {

    static class Point {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

    }

    public static String readJson(String filepath) throws FileNotFoundException, IOException{
            BufferedReader reader = new BufferedReader( new FileReader (filepath));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while( ( line = reader.readLine() ) != null ) {
                stringBuilder.append( line );
                stringBuilder.append( ls );
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);

            return stringBuilder.toString();
    }
    public static void main(String[] args) {
        String path1 = "a.json";
        String path2 = "b.json";

        try {
            String a_json = readJson(path1);

            Gson gson1 = new Gson();
            Point point1 = gson1.fromJson(a_json, Point.class);

            String b_json = readJson(path2);

            Gson gson2 = new Gson();
            Point point2 = gson2.fromJson(b_json, Point.class);

            Point new_point = new Point(point1.x +point2.x, point1.y + point2.y);


            try (Writer writer = new FileWriter("c.json")) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(new_point, writer);
            }

        } catch (IOException ex) {
            System.out.println(ex.getStackTrace());
        }
    }
}
