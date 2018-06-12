public class Student {
        private String name;
        private String lastname;
        private int idNumber;
        StudentGrade StudentsGrade = new StudentGrade();


        public Student(String name, String lastname, int idNumber){
                this.name = name;
                this.lastname = lastname;
                this.idNumber = idNumber;
                StudentsGrade.setStudent(this);
        }


        // GETTER Methods
        public String getName(){
                return name;
        }
        public String getLastname(){
                return lastname;
        }
        public int getID(){
                return idNumber;
        }
        public int getGrade(){
                return StudentsGrade.getGrade();
        }


        // SETTER Methods
        public void setName(String name){
                this.name = name;
        }
        public void setLastname(String lastname){
                this.lastname = lastname;
        }
        public void setID(int idNumber){
                this.idNumber = idNumber;
                StudentsGrade.setStudentsID(idNumber);
        }
        public void setGrade(int grade){
                StudentsGrade.setGrade(grade);
        }


        public String toString(){
                return "[id = " + idNumber + ", name = " + name + ",  lastname = " + lastname + "]";
        }
}
