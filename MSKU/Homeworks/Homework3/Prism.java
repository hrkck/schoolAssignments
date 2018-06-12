public class Prism {
        double length;
        double width;
        double height;
        Prism(double l, double w, double h){
                length = l;
                width = w;
                height = h;
        }
        public double calculateArea(){
                return 2 * length * width + 2 * height * width + 2 * length * height;
        }
        public double calculateVolume(){
                return length * width * height;
        }
}
