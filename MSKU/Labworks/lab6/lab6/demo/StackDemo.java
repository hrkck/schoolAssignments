package demo;

import stack.Stack;
import stack.StackArrayListImpl;
import stack.StackImpl;

public class StackDemo {

	public static void main(String[] args) {
		Stack stack = new StackArrayListImpl();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		
		while (!stack.empty()){
			System.out.println(stack.pop());
		}
	}

}
