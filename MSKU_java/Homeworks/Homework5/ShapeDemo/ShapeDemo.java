package ShapeDemo;

import shape.Shape;
import kind.Circle;
import kind.Rectangle;
import uniqe.Square;

public class ShapeDemo {
        public static void main(String[] args) {
                // Working out Circle
                Shape circle = new Circle(5, "pink", true);
                System.out.println(circle.toString());
                System.out.println("Area of Circle is: " + circle.getArea());
                System.out.println("Perimeter of Circle is: " + circle.getPerimeter());

                System.out.println();

                // Working out Rectangle
                Shape rectangle = new Rectangle(3, 5, "blue", true);
                System.out.println(rectangle.toString());
                System.out.println("Area of rectangle is: " + rectangle.getArea());
                System.out.println("Perimeter of rectangle is: " + rectangle.getPerimeter());

                System.out.println();

                // Working out Square
                Shape square = new Square(5, "red", false);
                System.out.println(square.toString());
                System.out.println("Area of square of is: " + square.getArea());
                System.out.println("Perimeter of square is: " + square.getPerimeter());
        }
}
