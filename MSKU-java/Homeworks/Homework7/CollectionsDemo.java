import java.util.*;

public class CollectionsDemo {
        public static void main(String[] args) {
                Course ceng1004 = new Course("CENG 1004", "Introduction to OOP");

                Student student = new Student("Ali", "Dere", 1001);
                ceng1004.addStudent(student);

                student = new Student("Aysu", "Aral", 1002);
                ceng1004.addStudent(student);

                student = new Student("Ali", "Bora", 1004);
                ceng1004.addStudent(student);

                student = new Student("Demir", "Bora", 1003);
                ceng1004.addStudent(student);

                student = new Student("Ceren", "Aslan", 1005);
                ceng1004.addStudent(student);


                // TEST 1 : Print Course
                System.out.println("-----------TEST1----------");
                System.out.println(ceng1004);
                // TEST 1


                // TEST 2 : Print students in ascending order by ID Number
                System.out.println();
                System.out.println("----------TEST2-------------");
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyID);
                for (Student stu : ceng1004.getStudentList()) {
                        System.out.println(stu);
                }
                // TEST 2


                // TEST 3 : Print students in ascending order by name
                System.out.println();
                System.out.println("-----------TEST3--------------");
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyName);
                for (Student stu : ceng1004.getStudentList()) {
                        System.out.println(stu);
                }
                // TEST 3


                // TEST 4 : Print students in ascending order by Lastname
                System.out.println();
                System.out.println("------------TEST4--------------");
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyLastname);
                for (Student stu : ceng1004.getStudentList()) {
                        System.out.println(stu);
                }
                // TEST 4


                // assign grades to students
                student = ceng1004.getStudent(1001);
                ceng1004.setGrade(student, 75);

                student = ceng1004.getStudent(1002);
                ceng1004.setGrade(student, 80);

                student = ceng1004.getStudent(1003);
                ceng1004.setGrade(student, 85);

                student = ceng1004.getStudent(1004);
                ceng1004.setGrade(student, 80);

                student = ceng1004.getStudent(1005);
                ceng1004.setGrade(student, 85);


                // TEST 5 : Print students and grades in ascending order by ID Number
                System.out.println();
                System.out.println("----------------TEST5----------");
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyID);
                Map<Student, Integer> map = new LinkedHashMap<Student, Integer>();
                // Adding keys and values to the Map
                for (Student stu : ceng1004.getStudentList()) {
                        map.put(stu, stu.getGrade());
                }
                // Printing the Map
                for (Student stu : map.keySet()) {
                        System.out.println("Student\t" + stu + "\thas grade\t" + map.get(stu));
                }
                // TEST 5


                // TEST 6 : Print students and grades in ascending order by name
                System.out.println();
                System.out.println("----------------TEST6--------------");
                map.clear(); // Clearing the previous Map to reuse it
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyName);
                // Adding keys and values to the Map
                for (Student stu : ceng1004.getStudentList()) {
                        map.put(stu, stu.getGrade());
                }
                // Printing the Map
                for (Student stu : map.keySet()) {
                        System.out.println("Student\t" + stu + "\thas grade\t" + map.get(stu));
                }
                // TEST 6


                // TEST 7 : Print students and grades in descending order by grade
                System.out.println();
                System.out.println("--------------TEST7-----------");
                map.clear(); // Clearing the previous Map to reuse it
                Collections.sort(ceng1004.getStudentList(), Course.listStudentsOrderbyGrade);
                // Adding keys and values to the Map
                for (Student stu : ceng1004.getStudentList()) {
                        map.put(stu, stu.getGrade());
                }
                // Printing the Map
                for (Student stu : map.keySet()) {
                        System.out.println("Student\t" + stu + "\thas grade\t" + map.get(stu));
                }
                // TEST 7



                System.out.println("\nWhat an insane homework...");
        }
}
