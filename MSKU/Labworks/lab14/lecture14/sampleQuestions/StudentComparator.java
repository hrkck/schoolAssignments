package sampleQuestions;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if ((o1.getScore() > o2.getScore())){
			return -1;
		}else if(o2.getScore() > o1.getScore()){
			return 1;
		}else{
			return o1.getId().compareTo(o2.getId());
		}
	}
}
