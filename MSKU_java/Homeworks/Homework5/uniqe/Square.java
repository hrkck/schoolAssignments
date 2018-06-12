package uniqe;
import kind.Rectangle;

// not a super class
public class Square extends Rectangle {
        // #################################
        // Constructions:
        // #################################

        // Initializer Constructor
        public Square(){
                super();
        }

        // Given variable Constructor
        public Square(double side){
                super(side, side);
        }

        // ... more on Constructor
        public Square(double side, String color, boolean filled){
                super(side, side, color, filled);
        }

        // ################################
        // Getter Methods:
        // ################################

        // Overriding for Getter
        public double getSide() {
                return super.getWidth();
        }

        // Overriding for Getter
        public double getArea() {
                return super.getArea();
        }

        // Overriding for Getter
        public double getPerimeter() {
                return super.getPerimeter();
        }

        // ################################
        // Setter Methods:
        // ################################

        // Identical setSide for Square
        public void setSide(double side){
                // TODO
        }

        // Overriding for setLength
        public void setLength(double side) {
                super.setLength(side);
        }

        // Overriding for setWidth
        public void setWidth(double side) {
                super.setWidth(side);
        }

        // toString Override from class Rectangle
        public String toString() {
                return "A Square with side = " + getSide() + ", which is a subclass of " + super.toString();
        }
}
