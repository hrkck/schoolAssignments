import java.util.*;

public class Course {
        private String courseCode;
        private String courseName;
        List<Student> studentList = new ArrayList<>();


        public Course(String courseCode, String courseName){
                this.courseCode = courseCode;
                this.courseName = courseName;
        }
        public void addStudent(Student student){
                studentList.add(student);
        }
        public List<Student> getStudentList(){
                return studentList;
        }
        public Student getStudent(int ID){
                for (Student stu : studentList) {
                        if (stu.getID() == ID) {
                                return stu;
                        }
                }
                return null;
        }
        public void setGrade(Student stu, int grade){
                stu.StudentsGrade.setGrade(grade);
        }
        public String toString(){
                return "Course = " + courseCode + " : " + courseName;
        }


        /* SORTER MECHANISMS:
         *
         ***ID          SORTER
         ***NAME        SORTER
         ***LASTNAME SORTER
         ***GRADE	SORTER
         */

        // ID SORTER MECHANISM
        public static Comparator<Student> listStudentsOrderbyID = new Comparator<Student>(){
                @Override
                public int compare(Student student1, Student student2){
                        return student1.getID() - (student2.getID());
                }
        };

        // NAME SORTER MECHANISM
        public static Comparator<Student> listStudentsOrderbyName = new Comparator<Student>(){
                @Override
                public int compare(Student student1, Student student2){
                        return student1.getName().compareTo(student2.getName());
                }
        };

        // LASTNAME SORTER MECHANISM
        public static Comparator<Student> listStudentsOrderbyLastname = new Comparator<Student>(){
                @Override
                public int compare(Student student1, Student student2){
                        return student1.getLastname().compareTo(student2.getLastname());
                }
        };

        // GRADE SORTER MECHANISM
        public static Comparator<Student> listStudentsOrderbyGrade = new Comparator<Student>(){
                @Override
                public int compare(Student student1, Student student2){
                        return student2.getGrade() - (student1.getGrade());
                }
        };
}
