package sinavOncesi;

// NESTED LOOPS

public class Question4 {
        public static void main(String args[]) {
                for (int x = 1; x <= 5; x++) {
                        // Bir Debuggin yontemi: Onemli Ifadelerden once bir sey
                        // print et. Gormen Kolaylasir.
                        //
                        System.out.println();
                        for (int y = 1; y <= x; y++) {
                                System.out.print(x + ",");
                        }
                }
        }
}
