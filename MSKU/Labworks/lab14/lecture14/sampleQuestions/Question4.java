package sampleQuestions;

public class Question4 {
	private static void doSomething(int[] intArray) {
		intArray = new int[] { 3, 5, 35, 45, 90, 150 };
	}
	public static void main(String args[]) {
		int[] items = new int[] { 5, 90, 35, 45, 150, 3 };
		doSomething(items);
		for (int i = 0; i < items.length; i++) {
			System.out.print(items[i]);
			if (i != items.length - 1)
				System.out.print(",");
		}
	}
}
