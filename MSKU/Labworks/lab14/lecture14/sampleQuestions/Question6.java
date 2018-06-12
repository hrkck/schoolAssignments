package sampleQuestions;

public class Question6 {

	public static void main(String args[]) {
		int [][] multiArray = new int [][] {{5,8,11}, {2,11,5}, {9, 1, 4}};
		System.out.println(sum(multiArray));
	}
	
	public static int sum(int [][] multiArray){
		int sum = 0; 
		for (int i = 0; i< multiArray.length; i ++){
			for (int j =0; j< multiArray[i].length; j++){
				sum+= multiArray[i][j];
			}
		}
		return sum;
	}
}
