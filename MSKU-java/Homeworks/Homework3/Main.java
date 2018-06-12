public class Main {
        public static void main(String args[]){
                Prism rectPrism = new Prism(3, 4, 5);
                Prism cube = new Prism(5, 5, 5);
                Prism rectangle = new Prism(0, 2, 3);
                Sphere sphere = new Sphere(3);
                Cylinder cylinder = new Cylinder(1, 10);

                System.out.println("Area of rectPrism = " + rectPrism.calculateArea());
                System.out.println("Volume of rectPrism = " + rectPrism.calculateVolume());
                System.out.println("");

                System.out.println("Area of cube = " + cube.calculateArea());
                System.out.println("Volume of cube = " + cube.calculateVolume());
                System.out.println("");

                System.out.println("Area of rectangle = " + rectangle.calculateArea());
                System.out.println("Volume of rectangle = " + rectangle.calculateVolume() + "  << This is why my life makes sense.");
                System.out.println("");

                System.out.println("Area of sphere = " + sphere.calculateArea());
                System.out.println("Volume of sphere = " + sphere.calculateVolume());
                System.out.println("");

                System.out.println("Area of cylinder = " + cylinder.calculateArea());
                System.out.println("Volume of cylinder = " + cylinder.calculateVolume());
        }
}
