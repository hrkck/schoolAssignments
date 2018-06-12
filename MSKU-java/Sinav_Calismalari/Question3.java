package sinavOncesi;

import java.util.*;

public class Question3 {
        /*
         * doSomething artik
         * 1- Kuresel Degiskenleri Aliyor
         * 2- Artiriyor.
         * 3- Liste icin donderiyor.
         * 4- Rcok n ROll.
         *
         */

        private static ArrayList<Integer> doSomething(int x, int y, int z) {
                x++;
                y++;
                z++;
                ArrayList<Integer> incNum = new ArrayList<>();
                incNum.add(x);
                incNum.add(y);
                incNum.add(z);

                // System.out.println("x= " + x + ", y= " + y + ", z= " + z);
                return incNum;
        }

        public static void main(String args[]) {
                int x = 1, y = 2, z = 3;
                ArrayList<Integer> incNum = doSomething(x, y, z);
                x = incNum.get(0);
                // y = incNum.get(1);
                // z = incNum.get(2);
                System.out.println("x= " + x + ", y= " + y + ", z= " + z);
        }
}
// GLOBAL
// LOCAL
// BODY
// FIELD
