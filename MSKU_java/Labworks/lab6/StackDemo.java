package demo;

import stack.Stack;
import stack.StackArrayListImpl;

public class StackDemo {
        public static void main(String[] args) {
                Stack<String> stack = new StackArrayListImpl<>();
                stack.push("A");
                stack.push("B");
                stack.push("C");
                stack.push("D");
                while (!stack.empty()) {
                        System.out.println(stack.pop());
                }
        }
}
