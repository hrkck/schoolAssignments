package sampleQuestions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Question25 {
	public static void main(String[] args) throws IOException {
		Set<Student> students = new TreeSet<>(new StudentComparator());
		Scanner scanner = null;
		Formatter formatter = null;
		try {
			scanner = new Scanner(new BufferedReader(new FileReader("scores.txt")));
			formatter = new Formatter(new BufferedWriter(new FileWriter("out.txt")));
			String[] columns = scanner.nextLine().split("\t");
			scanner.useDelimiter("\t|\r\n");
			while (scanner.hasNext()) {
				Student student = new Student(scanner.next(), scanner.nextInt());
				System.out.println(student.getId());
				students.add(student);
			}
			formatter.format("%s\t%s%n", columns[0], columns[1]);
			for (Student s : students) {
				formatter.format("%s\t%d%n", s.getId(), s.getScore());
			}
		}finally{
			if (scanner != null){
				scanner.close();
			}
			if (formatter != null){
				formatter.close();
			}
		}
	}
}
