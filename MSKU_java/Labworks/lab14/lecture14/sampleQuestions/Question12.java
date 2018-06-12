package sampleQuestions;

public class Question12 {
	static int a = 0;
	int b = 0;
	public void incrementCounter() {
		a++;
		b++;
	}
	public static void main(String args[]) {
		Question12 obj1 = new Question12();
		Question12 obj2 = new Question12();
		obj1.incrementCounter();
		obj2.incrementCounter();
		obj2.incrementCounter();
		System.out.println("Obj1.a: " + obj1.a);
		System.out.println("Obj1.b: " + obj1.b);
		System.out.println("Obj2.a: " + obj2.a);
		System.out.println("Obj2.b: " + obj2.b);
	}
}