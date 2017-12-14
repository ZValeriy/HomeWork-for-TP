import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static class Point {


        private int x;
        private int y;

        public Point(Point p1, Point p2) {
            this.setX(p1.getX() + p2.getX());
            this.setY(p2.getY() + p2.getY());
        }

        public Point() {
            this.setX(0);
            this.setY(0);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public static String readJson(String filepath) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public static Point makePoint(String json) {
        Point point = new Point();
        ObjectMapper mapper = new ObjectMapper();
        try {
            point = mapper.readValue(json, Point.class);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return point;
    }

    public static void main(String[] args) {
        String path1 = "a.json";
        String path2 = "b.json";

        String js1;
        js1 = readJson(path1);

        String js2;
        js2 = readJson(path2);

        ObjectMapper mapper = new ObjectMapper();

        Point point1 = new Point();
        point1 = makePoint(js1);

        Point point2 = new Point();
        point2 = makePoint(js2);


        Point point3 = new Point(point1, point2);
        try (FileOutputStream fout = new FileOutputStream("c.json")){
            mapper.writeValue(fout, point3);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
