package stack;

import java.util.ArrayList;

public class StackArrayListImpl implements Stack {

	ArrayList<Object> stack = new ArrayList<Object>();
	
	@Override
	public void push(Object item) {
		stack.add(0, item);
	}

	@Override
	public Object pop() {		
		return stack.remove(0);
	}

	@Override
	public boolean empty() {
		return stack.isEmpty();
	}

}
