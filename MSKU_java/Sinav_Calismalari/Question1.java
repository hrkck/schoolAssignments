package sinavOncesi;


public class Question1 {
        public static void test(int x){
                if (x > 5) {
                        System.out.print("A");
                } else if (x == 5) {
                        System.out.print("B");
                } else {
                        System.out.print("C");
                }
        }
        public static void main(String[] args){
                test(4);
                test(5);
                test(6);
        }
}
