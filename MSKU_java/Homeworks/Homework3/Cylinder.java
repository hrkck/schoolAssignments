public class Cylinder {
        double radius;
        double height;
        Cylinder(double r, double h){
                radius = r;
                height = h;
        }
        public double calculateArea(){
                return 3.14 * radius * radius * 2 + 2 * 3.14 * radius * height;
        }
        public double calculateVolume(){
                return 3.14 * radius * radius * height;
        }
}
