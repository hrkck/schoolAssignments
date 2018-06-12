package sinavOncesi;

public class Question15 {
        public static void main(String[] args) {
                ChildClass c = new ChildClass();
                c.showID();
                c.showParentsID();
        }
}

class ParentClass {
        int id = 1;

        void showID() {
                System.out.println(id);
        }
}

class ChildClass extends ParentClass {
        int id = 2;

        void showID() {
                System.out.println(id);
        }

        void showParentsID(){
                super.showID();
        }
}
