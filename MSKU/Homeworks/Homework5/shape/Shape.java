package shape;

// the super super awesome class
public abstract class Shape {
        // Declaring variables
        private String color;
        private boolean filled;
        private String red;

        // #################################
        // Constructions:
        // #################################

        // Initializer ( sounds like Terminator ) Construction
        public Shape(){
                this.color = red;
                this.filled = true;
        }

        // Given value Constructor
        public Shape(String c, boolean f){
                color = c;
                filled = f;
        }

        // #################################
        // Getter Methods:
        // #################################

        // Getter Method for Color
        public String getColor(){
                return color;
        }

        // Getter Method for Boolean values ( named as "is..." don't know why )
        public boolean isFilled(){
                return filled;
        }

        // Setter Method for Color
        public void setColor(String color){
                this.color = color;
        }

        // Setter Method for Boolean values
        public void setFilled(boolean filled){
                this.filled = filled;
        }

        // An Abstract heck
        public abstract double getArea();

        // yet another abstract heck
        public abstract double getPerimeter();

        // toString
        public String toString(){
                return "a shape with color of " + color + " and " + (filled ? "filled" : "not filled");
        }
}
