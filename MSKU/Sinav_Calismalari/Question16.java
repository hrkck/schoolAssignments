package sinavOncesi;

public class Question16 extends A {
        void f() {
                System.out.println("Question16.f");
        }

        void g() {
                f();
                super.f();
        }

        public static void main(String[] args) {
                Question16 o = new Question16();
                o.g();
        }
}

class A {
        void f() {
                System.out.println("A.f");
        }
}
