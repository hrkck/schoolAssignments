public class StudentGrade {
        private int grade;
        private Student student;
        private int studentsID;

        public void setGrade(int grade){
                this.grade = grade;
        }
        public void setStudent(Student student){
                this.student = student;
        }
        public void setStudentsID(int studentsID){
                this.studentsID = studentsID;
        }
        public int getGrade(){
                return grade;
        }
        public Student getStudent(){
                return student;
        }
        public int getStudentsID(){
                return studentsID;
        }

        public String toString(){
                return "My Student is " + student;
        }
}
