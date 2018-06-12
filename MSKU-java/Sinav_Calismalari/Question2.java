package sinavOncesi;

public class Question2 {
        static int triType(int a, int b, int c) {
                if (a > b) {
                        int t = a;
                        a = b;
                        b = t;
                }
                if (a > c) {
                        int t = a;
                        a = c;
                        c = t;
                }
                if (b > c) {
                        int t = b;
                        b = c;
                        c = t;
                }
                if (a + b <= c) {
                        return 0;
                } else {
                        if ((a == b) && (b == c)) {
                                return 2;
                        } else if (a * a + b * b == c * c) {
                                return 4;
                        } else if ((a == b) || (b == c)) {
                                return 3;
                        }
                        return 1;
                }
        }

        public static void main(String args[]) {
                System.out.println(triType(5, 4, 3));
                System.out.println(triType(5, 5, 3));
                System.out.println(triType(5, 2, 2));
                System.out.println(triType(5, 5, 5));
        }
}
