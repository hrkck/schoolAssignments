package lab10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AverageGradeCalculator {
        public double processFile(String filename) throws FileNotFoundException {
                Scanner scanner = new Scanner(new FileReader(filename));
                int stuCount = 0;
                int totalGrade = 0;
                try {
                        while (scanner.hasNext()) {
                                String stuID = scanner.next();
                                try {
                                        int grade = scanner.nextInt();
                                        totalGrade = totalGrade + grade;
                                        stuCount++;
                                } catch (InputMismatchException ex)   {
                                        System.out.println("Error: " + stuID + " does not have a valid grade.");
                                        scanner.next();
                                }
                        }
                        return totalGrade / stuCount;
                }
                finally {
                        scanner.close();
                }
        }

        public static void main(String[] args){
                AverageGradeCalculator cal = new AverageGradeCalculator();
                String filename = "grades.txt";
                try {
                        double averageGrade = cal.processFile(filename);
                        System.out.println("Average grade is " + averageGrade);
                } catch (FileNotFoundException e)   {
                        System.out.println(filename + " does not exist.");
                } catch (Exception ex)   {
                        System.out.println(filename + " contains damaged lines");
                }
        }
}
