package stack;

public class StackImpl implements Stack {

	StackItem top;
	
	@Override
	public void push(Object item) {
		StackItem newTop = new StackItem(item);
		newTop.setNext(top);
		top = newTop;
	}

	@Override
	public Object pop() {
		Object item = top.getItem();
		top = top.getNext();
		return item;
	}

	@Override
	public boolean empty() {
		return (top == null);
	}

}
