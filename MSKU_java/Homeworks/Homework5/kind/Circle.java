package kind;
import shape.Shape;

// "less" super class
public class Circle extends Shape {
        // Declaring Variables
        private double radius;

        // #################################
        // Constructions:
        // #################################

        // Initializer Construction
        public Circle(){
                radius = 1.0;
        }

        // Given Value Constructor
        public Circle(double radius){
                this.radius = radius;
        }

        // ... more on constructors
        public Circle(double radius, String color, boolean filled){
                super(color, filled);
                this.radius = radius;
        }

        // #################################
        // Setter Methods:
        // #################################

        // Setter Method for Radius
        public void setRadius(double radius){
                this.radius = radius;
        }

        // #################################
        // Getter Methods:
        // #################################

        // Getter Method for Radius
        public double getRadius(){
                return radius;
        }

        // Getter Method for Area
        public double getArea(){
                return 3.14 * radius * radius;
        }

        // yada yada...
        public double getPerimeter(){
                return 3.14 * 2 * radius;
        }

        // toString Override from class Shape
        public String toString() {
                return "A Circle with radius = " + radius + ", which is a subclass of " + super.toString();
        }
}
