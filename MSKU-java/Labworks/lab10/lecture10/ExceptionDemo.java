package lecture10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExceptionDemo {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("1 in main");
		method1();
		System.out.println("2 in main");
	}

	private static void method1() {
		System.out.println("1 in m1");		
		method2();
		System.out.println("2 in m1");

	}

	private static void method2() {
		
		System.out.println("1 in m2");
		try{
			method3();
		}catch(NullPointerException ex){
			System.out.println("Catch in m2");
			throw new MyApplicationException("error occured in my app", ex);
		}finally{
			System.out.println("finally in m2");
			
			
		}
		System.out.println("2 in m2");
	}

	private static void method3() {
		System.out.println("1 in m3");
		String r =null;
		r.length();	
		System.out.println("2 in m3");
	}

}
