package shapes3D;

import shapes2D.Circle;

public class Cylinder extends Circle {
        private double height;

        public Cylinder() {
                super();
                height = 1;
        }

        public Cylinder(double height){
                super();
                this.height = height;
        }


        public Cylinder(double radius, double height){
                super(radius);
                this.height = height;
        }

        public double area(){
                return 2 * super.area() + super.perimeter() * height;
        }

        public double volume(){
                return super.area() * height;
        }

        public String toString(){
                return super.toString() + ", height = " + height;
        }

        public boolean equals(Object obj){
                if (obj instanceof Cylinder) {
                        Cylinder cylinder = (Cylinder) obj;
                        if ((height == cylinder.height) && super.equals(obj)) {
                                return true;
                        }
                }
                return false;
        }
}
