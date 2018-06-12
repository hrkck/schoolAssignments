package kind;

import shape.Shape;

// "less" super class
public class Rectangle extends Shape {
        // Declaring Variables
        private double width;
        private double length;

        // #################################
        // Constructions:
        // #################################

        // Initializer Construction
        public Rectangle(){
                width = 1.0;
                length = 1.0;
        }

        // Given Value Constructor
        public Rectangle(double width, double length){
                this.width = width;
                this.length = length;
        }

        // ... more on Construction
        public Rectangle(double width, double length, String color, boolean filled){
                super(color, filled);
                this.width = width;
                this.length = length;
        }

        // #################################
        // Setter Methods:
        // #################################

        // Setter for Width
        public void setWidth(double width){
                this.width = width;
        }

        // Setter for Length
        public void setLength(double length){
                this.length = length;
        }

        // #################################
        // Getter Methods:
        // #################################

        // Getter for Width
        public double getWidth(){
                return width;
        }

        // Getter fir Length
        public double getLength(){
                return length;
        }

        // Getter for Area
        public double getArea(){
                return width * length;
        }

        // ...
        public double getPerimeter(){
                return 2 * width + 2 * length;
        }

        // toString override from class Shape
        public String toString() {
                return "A Rectangle with width = " + width + " and length = " + length + ", which is a subclass of " + super.toString();
        }
}
