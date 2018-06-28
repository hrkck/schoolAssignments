package demo;

import stack.Stack;
import stack.StackArrayListImpl;
import stack.StackImpl;

public class StackDemo {

	public static void main(String[] args) {
		Stack<Object> stack = new StackImpl<>();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		//stack.push(5);

		Stack<Integer> stackStr = new StackImpl<>();
		stackStr.push(7);
		stackStr.push(5);
		
		stack.addAll(stackStr);
		
		System.out.println(stack.toList());
		
//		while (!stack.empty()){
//			System.out.println(stack.pop());
//		}
	}

}
