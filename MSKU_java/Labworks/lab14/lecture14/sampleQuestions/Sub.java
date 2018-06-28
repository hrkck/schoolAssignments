package sampleQuestions;

class Base {
	static void f() {
		System.out.println("Base.f");
	}
	void g() {
		System.out.println("Base.g");
	}
}
public class Sub extends Base {
	static void f() {
		System.out.println("Sub.f");
	}
	void g() {
		System.out.println("Sub.g");
	}
	public static void main(String[] args) {
		Base ref = new Sub();
		ref.f();
		ref.g();
	}
}