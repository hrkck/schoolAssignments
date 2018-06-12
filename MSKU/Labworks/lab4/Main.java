import Circle.Circle;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args){
                Circle circle1 = new Circle(5);
                Circle circle2 = new Circle(6);
                Circle circle3 = new Circle(7);

                ArrayList<Circle> list = new ArrayList<Circle>();
                list.add(circle1);
                list.add(circle2);
                list.add(circle3);
                if (list.get(1) == circle2) {
                        System.out.println("index 1 has circle 2");
                }
                for (int i = 0; i < list.size(); i++) {
                        Circle circle = list.get(i);
                        System.out.println(circle.calculateArea());
                }
        }
}
