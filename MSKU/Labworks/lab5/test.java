package shapes3D;

import shapes3D.Cylinder;

public class test {
        public static void main(String[] args) {
                Cylinder cylinder = new Cylinder(1, 2);
                System.out.println(cylinder.area());
                System.out.println(cylinder.volume());
                System.out.println(cylinder);
        }
}
