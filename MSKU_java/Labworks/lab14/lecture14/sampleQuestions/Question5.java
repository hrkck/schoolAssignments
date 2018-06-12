package sampleQuestions;

public class Question5 {
	private static void doSomething(int x, int y, int z) {
		x++;
		y++;
		z++;
		System.out.println("x= " + x + ", y= " +y + ", z= " +z );		
	}
	public static void main(String args[]) {
		int x=1, y=2, z=3;
		doSomething(x,y,z);
		System.out.println("x= " + x + ", y= " +y + ", z= " +z );
	}
}
