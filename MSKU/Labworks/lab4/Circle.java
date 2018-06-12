public class Circle {
        private double radius;
        private static final double pi = 3.14;
        public Circle(double r){
                this.radius = r;
        }
        public double calculateArea(){
                double area;
                area = pi * radius * radius;
                return area;
        }
}
